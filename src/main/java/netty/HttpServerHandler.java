package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

import java.nio.charset.Charset;


/**
 * <p>处理http消息</p>
 *
 * @author 李尚庭
 * @date 2019-1-4
 */
public class HttpServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 模拟请求的连接
     */
    private Channel outboundChannel;


    public HttpServerHandler() {

    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //连接建立逻辑 后台客户端模拟请求 host可以作为请求连接
    }

    /**
     * 在连接建立之后
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //获取客户请求
        System.out.println("接收客户请求");
        //判断请求的类型 简单的get请求 和 带表单的http请求 其他格式的请求
        HttpRequest httpRequest = (HttpRequest) msg;
        System.out.println(httpRequest.uri());
       if (HttpMethod.CONNECT.name().equalsIgnoreCase(httpRequest.method().name())) {
            //HTTPS建立代理握手
            HttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
            ctx.writeAndFlush(response);
            return;
        }

        System.out.println(httpRequest.protocolVersion());

        //通过header获取
        String [] split = httpRequest.headers().get("host").split(":");
        int i = 2;
        int remotePort = 80;
        if (split.length == i) {
             remotePort = Integer.valueOf(split[1]);
        }
        //连接地址
        proxyRequest(ctx,"juejin.im", remotePort, msg);

    }

    /**
     * <p>根据原始数据Bytebuf获取</p>
     *
     * @param msg
     * @return
     */
    private String[] getTarget(ByteBuf msg) {
        ByteBuf byteBuf = msg;
        int index = 0;
        char enter = '\r';
        char line = '\n';
        while (true) {
            char aChar = (char) byteBuf.getByte(index);
            char aChar1 = (char) byteBuf.getByte((index + 1));
            if (aChar == enter && aChar1 == line) {
                index += 2;
                break;
            }
            index++;
        }

        int end = index;
        while (byteBuf.getByte(end) != enter || byteBuf.getByte((end + 1)) != line) {
            end++;
        }

        //6未代表前面 host： 字符串
        CharSequence charSequence = byteBuf.getCharSequence(index+6, end - (index+6), Charset.defaultCharset());
        System.out.println(charSequence);
        return charSequence.toString().split(":");
    }

    /**
     * <p>可以默认在读取完毕之后进行关闭 再次封装 当使用短链接的时候 可以重写改方法关闭连接</p>
     *
     * @param ctx 上下文
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * <p>创建请求客户端</p>
     *
     * @param ctx
     * @param remoteHost
     * @param remotePort
     * @param msg
     */
    private void proxyRequest(ChannelHandlerContext ctx, String remoteHost, int remotePort, Object msg) {
        //原请求连接
        final Channel inboundChannel = ctx.channel();
        //修改实例的配置
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                //每个代理请求连接的handler
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();

                        //
                        pipeline.addLast(new ProxyHandler(inboundChannel));
                        pipeline.addLast(new HttpRequestEncoder());
                    }
                })
                .option(ChannelOption.AUTO_READ, false);
        ChannelFuture f = b.connect(remoteHost, remotePort);
        outboundChannel = f.channel();
        //监听回调的优先级 高于handler中事件的优先级
        f.addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                // 连接成功后
                if (outboundChannel.isActive()) {
                    System.out.println("发送代理请求");

                    outboundChannel.writeAndFlush(msg).addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture future) {
                            if (future.isSuccess()) {
                                System.out.println(outboundChannel.remoteAddress());
                                future.channel().read();
                            } else {
                                System.out.println("处理失败");
                                future.channel().close();
                            }
                        }
                    });
                }
            } else {
                // Close the connection if the connection attempt has failed.
                inboundChannel.close();
            }
        });
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        if (outboundChannel != null) {
            closeOnFlush(outboundChannel);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        closeOnFlush(ctx.channel());
    }

    /**
     * Closes the specified channel after all queued write requests are flushed.
     */
    static void closeOnFlush(Channel ch) {
        if (ch.isActive()) {
            ch.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        }
    }

}
