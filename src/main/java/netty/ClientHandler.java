package netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * <p>客户端 处理器</p>
 *
 * @author 李尚庭
 * @date 2019-1-22
 */
public class ClientHandler extends SimpleChannelInboundHandler<String> {

    /**
     * <p>该方法需要在有数据时才会被触发</p>
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        try {
            System.out.println(msg);
            ctx.write("hello");
            ctx.flush();
            ctx.close();
        } finally {

        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.close();
    }
}
