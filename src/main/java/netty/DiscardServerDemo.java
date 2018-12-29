package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * <p>分发服务器</p>
 *
 * @author 李尚庭
 * @date 2018-12-29
 */
public class DiscardServerDemo {

    private int port;
    public DiscardServerDemo(int port) {
        this.port = port;
    }

    public void run() {
        //eventloopgroup 和 eventloop
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        //server启动器
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        try {
            //通道和缓冲区概念
            serverBootstrap.group(bossGroup, workGroup).
                    channel(NioServerSocketChannel.class).
                    childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ChannelHandler());
                        }
                    }).
                    //设置返回信息？？
                    option(ChannelOption.SO_BACKLOG, 128).
                    childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = serverBootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DiscardServerDemo(6011).run();

    }


}
