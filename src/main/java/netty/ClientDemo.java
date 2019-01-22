package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.apache.commons.lang3.StringUtils;

import java.net.InetSocketAddress;

/**
 * <p>客户端 Http</p>
 *
 * @author 李尚庭
 * @date 2019-1-22
 */
public class ClientDemo {


    /**
     * 读取请求 必要的 group channel handler option 输入和输出
     * @param host
     * @param port
     */
    public Channel receive(String host, int port) {
        if (StringUtils.isBlank(host)) {
            throw new IllegalArgumentException("host 不能为空！");
        }
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.
                group(group).
                option(ChannelOption.SO_KEEPALIVE, false).
                channel(NioSocketChannel.class).
                handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new ClientHandler());
                    }
                });
        //Future 模式 异步提货单
        ChannelFuture f = bootstrap.connect(new InetSocketAddress(host,port));
        //这个f是connect给的，如果在添加监听器之前，调用监听器的流程已将被执行
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                if (future.isSuccess()) {

                } else {
                    // Close the connection if the connection attempt has failed.
                }
            }
        });
        return f.channel();
    }

    public static void main(String[] args) {
        ClientDemo clientDemo = new ClientDemo();
        System.out.println("client start");
        clientDemo.receive("",80 );
        System.out.println("client end");
    }
}
