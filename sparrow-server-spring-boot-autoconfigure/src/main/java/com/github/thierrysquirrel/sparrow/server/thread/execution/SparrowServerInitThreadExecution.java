/**
 * Copyright 2020 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.thierrysquirrel.sparrow.server.thread.execution;

import com.github.thierrysquirrel.sparrow.server.common.netty.constant.IdleStateConstant;
import com.github.thierrysquirrel.sparrow.server.common.netty.core.factory.SocketAddressFactory;
import com.github.thierrysquirrel.sparrow.server.common.netty.handler.SparrowInitChannelHandler;
import com.github.thierrysquirrel.sparrow.server.thread.AbstractSparrowServerInitThread;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName: SparrowServerInitThreadExecution
 * Description:
 * date: 2020/6/8 18:39
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Slf4j
public class SparrowServerInitThreadExecution extends AbstractSparrowServerInitThread {

    public SparrowServerInitThreadExecution(String sparrowServerUrl) {
        super (sparrowServerUrl);
    }

    @Override
    protected void sparrowServerInit(String sparrowServerUrl) {

        EventLoopGroup bossGroup = new NioEventLoopGroup ();
        EventLoopGroup workerGroup = new NioEventLoopGroup ();

        ServerBootstrap b = new ServerBootstrap ();

        b.group (bossGroup, workerGroup)
                .channel (NioServerSocketChannel.class)
                .childHandler (new SparrowInitChannelHandler (IdleStateConstant.SPARROW_SERVER_READER_TIMEOUT,
                        IdleStateConstant.OTHER_TIMEOUT,
                        IdleStateConstant.OTHER_TIMEOUT));
        try {
            ChannelFuture f = b.bind (SocketAddressFactory.getSocketAddress (sparrowServerUrl)).sync ();
            f.channel ().closeFuture ().sync ();
        } catch (InterruptedException e) {
            log.error ("Sparrow Server Start Failed", e);
            Thread.currentThread ().interrupt ();
        } finally {
            workerGroup.shutdownGracefully ();
            bossGroup.shutdownGracefully ();
        }
    }
}
