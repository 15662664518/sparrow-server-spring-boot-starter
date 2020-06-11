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
import com.github.thierrysquirrel.sparrow.server.autoconfigure.SparrowServerProperties;
import com.github.thierrysquirrel.sparrow.server.event.core.factory.execution.AdministrationHandlerFactoryExecution;
import com.github.thierrysquirrel.sparrow.server.common.netty.constant.Command;
import com.github.thierrysquirrel.sparrow.server.common.netty.constant.Modular;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import com.github.thierrysquirrel.sparrow.server.service.AdministrationService;
import io.netty.channel.ChannelHandlerContext;

import javax.annotation.Resource;

/**
 * ClassName: AdministrationHandler
 * Description:
 * date: 2020/6/8 19:30
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@SparrowServerHandler
public class AdministrationHandler {
    @Resource
    private AdministrationService administrationService;
    @Resource
    private SparrowServerProperties sparrowServerProperties;

    @SparrowServerEvent(modular = Modular.ADMINISTRATION, command = Command.CREATE_TOPIC)
    public void createTopic(ChannelHandlerContext ctx, SparrowRequestContext msg, String topic, byte isCluster) {
        AdministrationHandlerFactoryExecution.createTopic (ctx, administrationService, topic, isCluster);
    }

    @SparrowServerEvent(modular = Modular.ADMINISTRATION, command = Command.DELETE_TOPIC)
    public void deleteTopic(ChannelHandlerContext ctx, SparrowRequestContext msg, String topic) {
        AdministrationHandlerFactoryExecution.deleteTopic (ctx,
                administrationService,
                sparrowServerProperties.getClusterServerUrl (),
                sparrowServerProperties.getUrl (),
                topic);
    }

    @SparrowServerEvent(modular = Modular.ADMINISTRATION, command = Command.GET_ALL_TOPIC)
    public void getAllTopic(ChannelHandlerContext ctx, SparrowRequestContext msg) {
        AdministrationHandlerFactoryExecution.getAllTopic (ctx, administrationService);
    }

    @SparrowServerEvent(modular = Modular.ADMINISTRATION, command = Command.GET_TOPIC)
    public void getTopic(ChannelHandlerContext ctx, SparrowRequestContext msg, String topic) {
        AdministrationHandlerFactoryExecution.getTopic (ctx, administrationService, topic);
    }
}
