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
package com.github.thierrysquirrel.sparrow.server.event;

import com.github.thierrysquirrel.sparrow.server.annotation.SparrowServerEvent;
import com.github.thierrysquirrel.sparrow.server.annotation.SparrowServerHandler;
import com.github.thierrysquirrel.sparrow.server.event.core.factory.execution.MessageHandlerFactoryExecution;
import com.github.thierrysquirrel.sparrow.server.common.netty.constant.Command;
import com.github.thierrysquirrel.sparrow.server.common.netty.constant.Modular;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import com.github.thierrysquirrel.sparrow.server.service.AdministrationService;
import io.netty.channel.ChannelHandlerContext;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: MessageHandler
 * Description:
 * date: 2020/6/10 2:50
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@SparrowServerHandler
public class MessageHandler {

    @Resource
    private AdministrationService administrationService;

    @SparrowServerEvent(modular = Modular.MESSAGE, command = Command.BATCH_CONFIRM_CONSUMPTION)
    public void batchConfirmConsumption(ChannelHandlerContext ctx, SparrowRequestContext msg, List<Long> idList) {
        MessageHandlerFactoryExecution.batchConfirmConsumption (ctx, administrationService, idList);
    }

    @SparrowServerEvent(modular = Modular.MESSAGE, command = Command.CONFIRM_CONSUMPTION)
    public void confirmConsumption(ChannelHandlerContext ctx, SparrowRequestContext msg, Long id) {
        MessageHandlerFactoryExecution.confirmConsumption (ctx, administrationService, id);
    }

    @SparrowServerEvent(modular = Modular.MESSAGE, command = Command.POST_MESSAGE)
    public void postMessage(ChannelHandlerContext ctx, SparrowRequestContext msg, String topic, byte[] message) {
        MessageHandlerFactoryExecution.postMessage (ctx, administrationService, topic, message);
    }

    @SparrowServerEvent(modular = Modular.MESSAGE, command = Command.PULL_MESSAGE)
    public void pullMessage(ChannelHandlerContext ctx, SparrowRequestContext msg, String topic, int pageIndex, int pageSize) {
        MessageHandlerFactoryExecution.pullMessage (ctx, administrationService, topic, pageIndex, pageSize);
    }


}
