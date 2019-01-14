package netty;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * <p>netty服务案例</p>
 *
 * @author 李尚庭
 * @date 2018-12-29
 */
public class ChannelHandler extends ChannelInboundHandlerAdapter {

    /**
     * 复写方法 怎样才会触发这个请求
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            System.out.println("server receive");
            ctx.write("hello");
            System.out.println(" echo ready");
            ctx.flush();
            //监听分发的请求
            //模拟http返回
        } finally {
            //处理实现了对应接口的对象
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        System.out.println("exception !");
        ctx.close();
    }

    /**
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //连接进入
        System.out.println("come in !");
        ctx.flush();
        //错误实例 必须以Buffer作为容器?  好像不是 似乎是两端的数据类型需要对应 数据的传输需要实现特定的decoder encoder
        //ChannelFuture hello = ctx.write("hello");
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeCharSequence("hello", Charset.forName("UTF-8"));
        //ByteBuf buffer = ctx.alloc().buffer();
        //buffer.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
        ChannelFuture hello = ctx.write("It is " + new Date() + " now.\r\n");
        ctx.flush();
        //简写 回调逻辑
        hello.addListener((future)-> {
                //关键字 表达式
                assert hello == future;
                System.out.println("complete");
                //需要关闭之后才会断开连接 而且好像不是因为keepalive 控制的
                //ctx.close();
        });
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }
}
