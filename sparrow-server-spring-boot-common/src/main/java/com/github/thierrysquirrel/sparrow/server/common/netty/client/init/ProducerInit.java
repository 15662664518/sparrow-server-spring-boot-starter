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
import com.github.thierrysquirrel.sparrow.server.common.netty.client.handler.ProducerInitChannelHandler;
import com.github.thierrysquirrel.sparrow.server.common.netty.client.listener.ProducerListener;
import com.github.thierrysquirrel.sparrow.server.common.netty.constant.IdleStateConstant;
import com.github.thierrysquirrel.sparrow.server.common.netty.core.factory.SocketAddressFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Data;

/**
 * ClassName: ProducerInit
 * Description:
 * date: 2020/6/10 17:37
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public class ProducerInit {
    private Channel channel;
    private String url;
    private ProducerListener producerListener;

    public ProducerInit(String url, ProducerListener producerListener) {
        this.url = url;
        this.producerListener = producerListener;
    }

    public void init() throws InterruptedException {
        if (channel != null && channel.isActive ()) {
            return;
        }
        Bootstrap bootstrap = new Bootstrap ();
        bootstrap.group (ClientConstant.PRODUCER_EVENT_LOOP_GROUP)
                .channel (NioSocketChannel.class)
                .handler (new ProducerInitChannelHandler (IdleStateConstant.OTHER_TIMEOUT,
                        IdleStateConstant.PRODUCER_WRITE_TIMEOUT,
                        IdleStateConstant.OTHER_TIMEOUT,
                        producerListener));

        ChannelFuture channelFuture = bootstrap.connect (SocketAddressFactory.getSocketAddress (url)).sync ();
        this.channel = channelFuture.channel ();
    }
}
