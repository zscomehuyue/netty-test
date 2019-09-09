package com.netty.hello;

/**
 * @author: zscome
 * DateTime: 2019-09-09 17:49
 */
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        //管道，管道里面可以有很多 handler，一层层过滤的柑橘
        ChannelPipeline pipeline = socketChannel.pipeline();
        //HttpServerCodec 是 HttpRequestDecoder 和 HttpReponseEncoder 的组合，编码和解码的 h      handler
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        pipeline.addLast("handler", new ServerHandler());
    }
}
