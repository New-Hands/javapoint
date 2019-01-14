package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.Date;

public class ClientHandler extends SimpleChannelInboundHandler<String> {
    //数据结构 byteBuf

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
//            long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
//            System.out.println(new Date(currentTimeMillis));
            System.out.println("sffsd");
            //CharSequence charSequence = m.readCharSequence(100, Charset.forName("UTF-8"));
            System.out.println(msg);
            ctx.write("hello");
            ctx.flush();
            //客户端的close 有一方断开连接即可 客户端和服务端需要有一方主动断开连接才会断开连接
            ctx.close();
        } finally {

        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.close();
    }
}
