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
package com.github.thierrysquirrel.sparrow.server.common.netty.client.handler;

import com.github.thierrysquirrel.sparrow.server.common.netty.client.listener.ConsumerListener;
import com.github.thierrysquirrel.sparrow.server.common.netty.handler.AbstractInitChannelHandler;
import io.netty.channel.socket.SocketChannel;
import lombok.Data;

/**
 * ClassName: ConsumerInitChannelHandler
 * Description:
 * date: 2020/6/11 6:08
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public class ConsumerInitChannelHandler extends AbstractInitChannelHandler {
    private long readerIdleTime;
    private long writerIdleTime;
    private long allIdleTime;
    private ConsumerListener consumerListener;

    public ConsumerInitChannelHandler(long readerIdleTime, long writerIdleTime, long allIdleTime, ConsumerListener consumerListener) {
        this.readerIdleTime = readerIdleTime;
        this.writerIdleTime = writerIdleTime;
        this.allIdleTime = allIdleTime;
        this.consumerListener = consumerListener;
    }

    @Override
    protected void initChannel(SocketChannel ch) {
        super.init (ch,
                this.readerIdleTime,
                this.writerIdleTime,
                this.allIdleTime);
        ch.pipeline ().addLast (new ConsumeHandler (this.consumerListener));
    }
}
