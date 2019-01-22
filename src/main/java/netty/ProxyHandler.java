package netty;

import io.netty.channel.*;

/**
 * <p>模拟Http请求</p>
 *
 * @author 李尚庭
 * @date 2019-1-22
 */
public class ProxyHandler extends ChannelInboundHandlerAdapter {

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
        ctx.read();
    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, Object msg) {
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
