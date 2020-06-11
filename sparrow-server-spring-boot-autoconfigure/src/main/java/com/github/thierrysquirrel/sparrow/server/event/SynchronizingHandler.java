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
import com.github.thierrysquirrel.sparrow.server.event.core.factory.execution.SynchronizingHandlerFactoryExecution;
import com.github.thierrysquirrel.sparrow.server.common.netty.constant.Command;
import com.github.thierrysquirrel.sparrow.server.common.netty.constant.Modular;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import com.github.thierrysquirrel.sparrow.server.mapper.template.SparrowTopicEntityCacheTemplate;
import io.netty.channel.ChannelHandlerContext;

import javax.annotation.Resource;

/**
 * ClassName: SynchronizingHandler
 * Description:
 * date: 2020/6/9 4:05
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@SparrowServerHandler
public class SynchronizingHandler {

    @Resource
    private SparrowTopicEntityCacheTemplate sparrowTopicEntityCacheTemplate;

    @SparrowServerEvent(modular = Modular.SYNCHRONIZING, command = Command.SYNCHRONOUS_CLUSTER_TOPIC_CACHE)
    public void synchronousClusterTopicCache(ChannelHandlerContext ctx, SparrowRequestContext msg, String topic) {
        SynchronizingHandlerFactoryExecution.synchronizingHandlerFactory (ctx, sparrowTopicEntityCacheTemplate, topic);
    }
}
