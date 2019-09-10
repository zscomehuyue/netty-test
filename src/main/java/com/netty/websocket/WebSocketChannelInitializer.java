package com.netty.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author: zscome
 * DateTime: 2019-09-09 20:25
 */
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {
    //需要支持 websocket，我们在 initChannel 是做一点改动
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //因为 websocket 是基于 http 的，所以要加入 http 相应的编解码器
        pipeline.addLast(new HttpServerCodec());
        //以块的方式进行写的处理器
        pipeline.addLast(new ChunkedWriteHandler());
        // 进行 http 聚合的处理器，将 HttpMessage 和 HttpContent 聚合到 FullHttpRequest 或者
        // FullHttpResponse
        //HttpObjectAggregator 在基于 netty 的 http 编程使用的非常多，粘包拆包。
        pipeline.addLast(new HttpObjectAggregator(8192));
        // 针对 websocket 的类,完成 websocket 构建的所有繁重工作，负责握手，以及心跳（close，ping，
        // pong）的处理， websocket 通过 frame 帧来传递数据。
        // BinaryWebSocketFrame，CloseWebSocketFrame，ContinuationWebSocketFrame，
        // PingWebSocketFrame，PongWebSocketFrame，TextWebSocketFrame。
        // /ws 是 context_path，websocket 协议标准，ws://server:port/context_path

// FIXME pipeline.addLast(new WebSocketServerProcotolHandler("/ws"));
        pipeline.addLast(new TextWebSocketFrameHandler());
    }
}
