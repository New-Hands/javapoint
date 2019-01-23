package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.http.HttpResponse;

import java.net.SocketAddress;
import java.nio.charset.Charset;


/**
 * <p>模拟Http请求</p>
 *
 * @author 李尚庭
 * @date 2019-1-22
 */
public class ProxyHandler extends ChannelInboundHandlerAdapter {

    /**
     * 该channel为每个ProxyHandler实例私有 线程安全
     */
    private final Channel inboundChannel;

    /**
     * 使用请求连接 构造代理请求处理器
     * @param inboundChannel
     */
    public ProxyHandler(Channel inboundChannel) {
        this.inboundChannel = inboundChannel;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //调用read()方法
        System.out.println("代理请求连接建立！");
        //
    }

    /**
     * 代理handler read
     * @param ctx
     * @param msg 这个msg是来代理请求结果
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        //处理Http握手请求
        //调用请求端连接 回复代理请求的数据
        System.out.println("回复客户！");
        if (msg instanceof HttpResponse) {
            System.out.println("http");
        }else {
            ByteBuf byteBuf = (ByteBuf) msg;

            System.out.println(((ByteBuf) msg).getCharSequence(0,1000, Charset.defaultCharset()));
            SocketAddress socketAddress = inboundChannel.remoteAddress();

            System.out.println(socketAddress.toString());
        }
        inboundChannel.writeAndFlush(msg).addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                ctx.channel().read();
            } else {
                future.channel().close();
            }
        });
    }

    /**
     * 关闭请求连接
     * @param ctx
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        HttpServerHandler.closeOnFlush(inboundChannel);
    }

    /**
     * 关闭请求连接
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        HttpServerHandler.closeOnFlush(ctx.channel());
    }
}
