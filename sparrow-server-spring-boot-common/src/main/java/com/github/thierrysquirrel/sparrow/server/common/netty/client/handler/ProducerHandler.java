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

import com.github.thierrysquirrel.sparrow.server.common.netty.client.core.factory.execution.ProducerHandlerFactoryExecution;
import com.github.thierrysquirrel.sparrow.server.common.netty.client.listener.ProducerListener;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import com.github.thierrysquirrel.sparrow.server.common.netty.handler.AbstractClientHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;

/**
 * ClassName: ProducerHandler
 * Description:
 * date: 2020/6/10 16:58
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public class ProducerHandler extends AbstractClientHandler {

    private ProducerListener producerListener;

    public ProducerHandler(ProducerListener producerListener) {
        this.producerListener = producerListener;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SparrowRequestContext msg) {
        ProducerHandlerFactoryExecution.response (msg, producerListener);
    }
}