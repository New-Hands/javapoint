package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.string.StringEncoder;

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
                    //会通过反射建立channel对象
                            channel(NioServerSocketChannel.class).
                    childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {

                            //回过头来 你会突然发现
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new StringEncoder());


                            pipeline.addLast(new HttpRequestDecoder());
                            pipeline.addLast(new HttpServerHandler());

                        }
                    }).
                    //设置返回信息？？
                            option(ChannelOption.SO_BACKLOG, 128).
                    childOption(ChannelOption.AUTO_READ, true).
                    childOption(ChannelOption.AUTO_CLOSE, true).
                    childOption(ChannelOption.SO_KEEPALIVE, false);

            ChannelFuture future = serverBootstrap.bind(port).sync();
            System.out.println("http server start");
            //在此阻塞到结束
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new DiscardServerDemo(6011).run();
    }
}
