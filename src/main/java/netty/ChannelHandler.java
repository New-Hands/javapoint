package netty;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * <p>netty服务案例</p>
 *
 * @author 李尚庭
 * @date 2018-12-29
 */
public class ChannelHandler extends ChannelInboundHandlerAdapter {

    /**
     * 复写方法
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            ctx.write("hello");
            ctx.flush();
            //监听分发的请求
            //模拟http返回
            ctx.write("200 ok ");
            ctx.write(" ");
        } finally {
            //处理实现了对应接口的对象
        }
    }
}
