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

import com.github.thierrysquirrel.sparrow.server.event.core.factory.AdministrationHandlerFactory;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import com.github.thierrysquirrel.sparrow.server.service.AdministrationService;
import io.netty.channel.ChannelHandlerContext;

/**
 * ClassName: AdministrationHandlerFactoryExecution
 * Description:
 * date: 2020/6/9 3:49
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class AdministrationHandlerFactoryExecution {
    private AdministrationHandlerFactoryExecution() {
    }

    public static void createTopic(ChannelHandlerContext ctx, AdministrationService administrationService, String topic, byte isCluster) {
        SparrowRequestContext sparrowRequestContext = AdministrationHandlerFactory.createTopic (administrationService, topic, isCluster);
        ctx.writeAndFlush (sparrowRequestContext);
    }

    public static void deleteTopic(ChannelHandlerContext ctx, AdministrationService administrationService, String clusterServerUrl, String serverUrl, String topic) {
        SparrowRequestContext sparrowRequestContext = AdministrationHandlerFactory.deleteTopic (administrationService, clusterServerUrl, serverUrl, topic);
        ctx.writeAndFlush (sparrowRequestContext);
    }

    public static void getAllTopic(ChannelHandlerContext ctx, AdministrationService administrationService) {
        SparrowRequestContext sparrowRequestContext = AdministrationHandlerFactory.getAllTopic (administrationService);
        ctx.writeAndFlush (sparrowRequestContext);
    }

    public static void getTopic(ChannelHandlerContext ctx, AdministrationService administrationService, String topic) {
        SparrowRequestContext sparrowRequestContext = AdministrationHandlerFactory.getTopic (administrationService, topic);
        ctx.writeAndFlush (sparrowRequestContext);
    }
}
