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
package com.github.thierrysquirrel.sparrow.server.common.netty.client.init;

import com.github.thierrysquirrel.sparrow.server.common.netty.client.constant.ClientConstant;
import com.github.thierrysquirrel.sparrow.server.common.netty.client.core.factory.RandomFactory;
import com.github.thierrysquirrel.sparrow.server.common.netty.client.handler.ClientInitChannelHandler;
import com.github.thierrysquirrel.sparrow.server.common.netty.constant.IdleStateConstant;
import com.github.thierrysquirrel.sparrow.server.common.netty.core.factory.SocketAddressFactory;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Data;

import java.util.concurrent.CompletableFuture;

/**
 * ClassName: ClientInit
 * Description:
 * date: 2020/6/8 18:04
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public class ClientInit {
    private Channel channel;
    private String url;
    private CompletableFuture<SparrowRequestContext> completableFuture = new CompletableFuture<> ();

    public ClientInit(String url) {
        this.url = url;
    }

    public void init() throws InterruptedException {
        if (channel != null && channel.isActive ()) {
            sheep ();
            completableFuture = new CompletableFuture<> ();
            return;
        }
        Bootstrap bootstrap = new Bootstrap ();
        bootstrap.group (ClientConstant.CLIENT_EVENT_LOOP_GROUP)
                .channel (NioSocketChannel.class)
                .handler (new ClientInitChannelHandler (IdleStateConstant.OTHER_TIMEOUT,
                        IdleStateConstant.WRITE_TIMEOUT,
                        IdleStateConstant.OTHER_TIMEOUT,
                        this));

        ChannelFuture channelFuture = bootstrap.connect (SocketAddressFactory.getSocketAddress (url)).sync ();
        this.channel = channelFuture.channel ();

    }

    public synchronized void sheep() throws InterruptedException {
        while (completableFuture != null && !completableFuture.isDone ()) {
            int random = RandomFactory.getRandom ();
            this.wait (random);
        }
    }

    public void call(SparrowRequestContext sparrowRequestContext) {
        completableFuture.complete (sparrowRequestContext);
    }
}
