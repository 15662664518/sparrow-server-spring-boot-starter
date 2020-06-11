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
package com.github.thierrysquirrel.sparrow.server.event.core.factory;

import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.builder.SparrowRequestContextBuilder;
import com.github.thierrysquirrel.sparrow.server.core.factory.HeartbeatFactory;
import com.github.thierrysquirrel.sparrow.server.mapper.entity.SparrowTopicEntity;
import com.github.thierrysquirrel.sparrow.server.service.AdministrationService;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.util.ObjectUtils;

/**
 * ClassName: HeartbeatHandlerFactory
 * Description:
 * date: 2020/6/10 2:40
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class HeartbeatHandlerFactory {
    private HeartbeatHandlerFactory() {
    }

    public static SparrowRequestContext ping(ChannelHandlerContext ctx, AdministrationService administrationService, String topic) {
        SparrowTopicEntity sparrowTopicEntity = administrationService.getTopic (topic);
        if (ObjectUtils.isEmpty (sparrowTopicEntity)) {
            return SparrowRequestContextBuilder.builderFailed ("Ping Topic Does Not Exist");
        }
        HeartbeatFactory.consumerPing (topic, ctx);
        return SparrowRequestContextBuilder.builderPang ();
    }
}
