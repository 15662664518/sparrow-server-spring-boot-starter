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
package com.github.thierrysquirrel.sparrow.server.event.core.factory.execution;

import com.github.thierrysquirrel.sparrow.server.event.core.factory.MessageHandlerFactory;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import com.github.thierrysquirrel.sparrow.server.service.AdministrationService;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

/**
 * ClassName: MessageHandlerFactoryExecution
 * Description:
 * date: 2020/6/10 3:02
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class MessageHandlerFactoryExecution {

    private MessageHandlerFactoryExecution() {
    }

    public static void batchConfirmConsumption(ChannelHandlerContext ctx, AdministrationService administrationService, List<Long> idList) {
        SparrowRequestContext sparrowRequestContext = MessageHandlerFactory.batchConfirmConsumption (administrationService, idList);
        ctx.writeAndFlush (sparrowRequestContext);
    }

    public static void confirmConsumption(ChannelHandlerContext ctx, AdministrationService administrationService, Long id) {
        SparrowRequestContext sparrowRequestContext = MessageHandlerFactory.confirmConsumption (administrationService, id);
        ctx.writeAndFlush (sparrowRequestContext);
    }

    public static void postMessage(ChannelHandlerContext ctx, AdministrationService administrationService, String topic, byte[] message) {
        SparrowRequestContext sparrowRequestContext = MessageHandlerFactory.postMessage (administrationService, topic, message);
        ctx.writeAndFlush (sparrowRequestContext);
    }

    public static void pullMessage(ChannelHandlerContext ctx,AdministrationService administrationService, String topic, int pageIndex, int pageSize) {
        SparrowRequestContext sparrowRequestContext = MessageHandlerFactory.pullMessage (administrationService, topic, pageIndex, pageSize);
        ctx.writeAndFlush (sparrowRequestContext);
    }
}
