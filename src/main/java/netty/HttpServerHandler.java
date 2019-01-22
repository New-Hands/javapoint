package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;
import io.netty.util.concurrent.GlobalEventExecutor;
import pattern.proxy.client;

import java.nio.charset.Charset;
import java.util.concurrent.CountDownLatch;


import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

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

    private static final byte[] CONTENT = {'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd'};
    private HttpRequest request;
    private GlobalEventExecutor executor = GlobalEventExecutor.INSTANCE;
    private CountDownLatch latch = new CountDownLatch(1);

    private StringBuilder buffer = new StringBuilder();
    private StringBuilder respone;

    private static final AsciiString CONTENT_TYPE = AsciiString.cached("Content-Type");
    private static final AsciiString CONTENT_LENGTH = AsciiString.cached("Content-Length");
    private static final AsciiString CONNECTION = AsciiString.cached("Connection");
    private static final AsciiString KEEP_ALIVE = AsciiString.cached("keep-alive");

    public HttpServerHandler() {
        super();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        StringBuilder http = new StringBuilder();
        FullHttpRequest request = (DefaultFullHttpRequest) msg;
        this.request = request;
        if (msg instanceof HttpContent) {
            HttpContent httpContent = (HttpContent) msg;
            ByteBuf content = httpContent.content();
            if (content.isReadable()) {
                buffer.append(content.toString(Charset.defaultCharset()));
            }
        }

        //后台客户端模拟请求
        proxyRequest(ctx,"",80);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
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
     *
     * @param respone
     * @param current
     * @param ctx
     */
    private void writeResponse(StringBuilder respone, HttpObject current, ChannelHandlerContext ctx) {
        if (respone != null) {
            boolean keepAlive = HttpUtil.isKeepAlive(request);

            FullHttpResponse response = new DefaultFullHttpResponse(
                    HTTP_1_1, current == null ? OK : current.decoderResult().isSuccess() ? OK : BAD_REQUEST,
                    Unpooled.copiedBuffer(respone.toString(), Charset.defaultCharset()));

            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=GBK");

            if (keepAlive) {
                response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
                response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            }

            ctx.write(response);
        }
    }

    /**
     *
     * @param ctx
     * @param remoteHost
     * @param remotePort
     */
    private void proxyRequest(ChannelHandlerContext ctx,String remoteHost,int remotePort) {
        Channel inboundChannel = ctx.channel();

        Bootstrap b = new Bootstrap();
        b.group(inboundChannel.eventLoop())
                .channel(ctx.channel().getClass())
                .handler(new ProxyHandler(inboundChannel))
                .option(ChannelOption.AUTO_READ, false);
        ChannelFuture f = b.connect(remoteHost, remotePort);
        outboundChannel = f.channel();
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                if (future.isSuccess()) {
                    // connection complete start to read first data
                    inboundChannel.read();
                } else {
                    // Close the connection if the connection attempt has failed.
                    inboundChannel.close();
                }
            }
        });
    }

    //请求地址
    private String matchUrl(String localUrl) {

        return null;
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
