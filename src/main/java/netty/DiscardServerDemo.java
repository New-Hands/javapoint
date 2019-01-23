package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;


/**
 * <p>分发服务器</p>
 *
 * @author 李尚庭
 * @date 2018-12-29
 */
public class DiscardServerDemo {

    /**
     * 监听端口
     */
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
                            //转发器不用处理数据
                            ChannelPipeline pipeline = ch.pipeline();
                            // 几种不同http解码方式
                            pipeline.addLast(new HttpRequestDecoder());
                            //http聚合
                            pipeline.addLast(new HttpObjectAggregator(65536));
                            pipeline.addLast(new HttpServerHandler());

                            //pipeline.addLast(new HttpResponseEncoder());
                        }
                    }).
                    //自动调用channelRead方法
                    childOption(ChannelOption.AUTO_READ, true);
            ChannelFuture future = serverBootstrap.bind(port).sync();
            System.out.println("http server start");
            //在此阻塞到结束
            future.channel().closeFuture().sync();
        } catch (Exception e) {
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
