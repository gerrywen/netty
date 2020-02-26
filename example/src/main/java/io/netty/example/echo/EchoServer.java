/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.example.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

/**
 * Echoes back any received data from a client.
 *
 * Netty4.x 源码实战系列（一）： 深入理解ServerBootstrap 与 Bootstrap （1）
 * https://www.cnblogs.com/itdriver/p/8149913.html
 */
public final class EchoServer {

    static final boolean SSL = System.getProperty("ssl") != null;
    static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));

    public static void main(String[] args) throws Exception {
        // Configure SSL.
        final SslContext sslCtx;
        if (SSL) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }

        // Configure the server.
        // (1)初始化用于Acceptor的主"线程池"以及用于I/O工作的从"线程池"；
        // bossGroup主线程  workerGroup子线程
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);// 使用指定数量的线程
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // 使用系统默认的线程数量
        final EchoServerHandler serverHandler = new EchoServerHandler(); // 处理接收到数据时的事件
        try {
            ServerBootstrap b = new ServerBootstrap();// (2)初始化ServerBootstrap实例， 此实例是netty服务端应用开发的入口
            b.group(bossGroup, workerGroup)        // (3)通过ServerBootstrap的group方法，设置（1）中初始化的主从"线程池"
             .channel(NioServerSocketChannel.class)// (4)指定通道channel的类型，由于是服务端，故而是NioServerSocketChannel
             .option(ChannelOption.SO_BACKLOG, 100)// (7)配置ServerSocketChannel的选项
             .childOption(ChannelOption.SO_KEEPALIVE, true) // (8)配置子通道也就是SocketChannel的选项
             .handler(new LoggingHandler(LogLevel.INFO))// (5) 设置ServerSocketChannel的处理器
             .childHandler(new ChannelInitializer<SocketChannel>() {//(6)设置子通道也就是SocketChannel的处理器， 其内部是实际业务开发的"主战场"
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     ChannelPipeline p = ch.pipeline();
                     if (sslCtx != null) {
                         p.addLast(sslCtx.newHandler(ch.alloc()));
                     }
                     //p.addLast(new LoggingHandler(LogLevel.INFO));
                     p.addLast(serverHandler);
                 }
             });

            // Start the server.
            ChannelFuture f = b.bind(PORT).sync();//(9)绑定并侦听某个端口

            // Wait until the server socket is closed.
            // 等待服务端口关闭
            f.channel().closeFuture().sync();
        } finally {
            // Shut down all event loops to terminate all threads.
            // 关闭所有事件循环以终止所有线程。
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
