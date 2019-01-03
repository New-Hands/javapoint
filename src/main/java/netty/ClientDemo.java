package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class ClientDemo {

    /**
     * 读取请求 必要的 group channel handler option 输入和输出
     */
    private void receive() {
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.
                group(group).
                channel(NioSocketChannel.class).
                handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ClientHandler());
                    }
                });

        ChannelFuture connect = bootstrap.connect(new InetSocketAddress(6011));
        //Future 模式 异步提货单

    }

    public static void main(String[] args) {

    }
}
