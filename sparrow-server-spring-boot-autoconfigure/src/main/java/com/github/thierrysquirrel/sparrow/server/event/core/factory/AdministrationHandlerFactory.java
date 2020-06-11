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

import com.github.thierrysquirrel.sparrow.server.common.netty.constant.SeparatorConstant;
import com.github.thierrysquirrel.sparrow.server.common.netty.core.factory.execution.ThreadPoolFactoryExecution;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowTopic;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.builder.SparrowRequestContextBuilder;
import com.github.thierrysquirrel.sparrow.server.core.factory.constant.ThreadPoolFactoryConstant;
import com.github.thierrysquirrel.sparrow.server.event.thread.execution.SynchronousClusterTopicCacheThreadExecution;
import com.github.thierrysquirrel.sparrow.server.mapper.entity.SparrowTopicEntity;
import com.github.thierrysquirrel.sparrow.server.mapper.utils.DomainUtils;
import com.github.thierrysquirrel.sparrow.server.service.AdministrationService;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: AdministrationHandlerFactory
 * Description:
 * date: 2020/6/9 3:23
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class AdministrationHandlerFactory {
    private AdministrationHandlerFactory() {
    }

    private static void synchronousClusterTopicCache(String clusterServerUrl, String serverUrl, String topic) {
        if (ObjectUtils.isEmpty (clusterServerUrl)) {
            return;
        }
        String[] split = clusterServerUrl.split (SeparatorConstant.URL_SEPARATOR);
        SparrowRequestContext sparrowRequestContext = SparrowRequestContextBuilder.builderSynchronousClusterTopicCache (topic);
        ThreadPoolExecutor synchronousClusterTopicCacheThreadPool = ThreadPoolFactoryConstant.SYNCHRONOUS_CLUSTER_TOPIC_CACHE_THREAD_POOL;
        for (String url : split) {
            if (url.equals (serverUrl)) {
                continue;
            }
            SynchronousClusterTopicCacheThreadExecution synchronousClusterThreadPool = new SynchronousClusterTopicCacheThreadExecution (url, sparrowRequestContext);
            ThreadPoolFactoryExecution.statsThread (synchronousClusterTopicCacheThreadPool, synchronousClusterThreadPool);
        }
    }

    public static SparrowRequestContext createTopic(AdministrationService administrationService, String topic, byte isCluster) {
        boolean isCreateTopic = administrationService.createTopic (topic, isCluster);
        if (isCreateTopic) {
            return SparrowRequestContextBuilder.builderFailed ("CreateTopic Topic Does Not Exist");
        }
        return SparrowRequestContextBuilder.builderSuccess ();
    }

    public static SparrowRequestContext deleteTopic(AdministrationService administrationService, String clusterServerUrl, String serverUrl, String topic) {
        administrationService.deleteTopic (topic);
        synchronousClusterTopicCache (clusterServerUrl, serverUrl, topic);
        return SparrowRequestContextBuilder.builderSuccess ();
    }

    public static SparrowRequestContext getAllTopic(AdministrationService administrationService) {
        List<SparrowTopic> allTopic = administrationService.getAllTopic ();
        return SparrowRequestContextBuilder.builderSuccess (allTopic);
    }

    public static SparrowRequestContext getTopic(AdministrationService administrationService, String topic) {
        SparrowTopicEntity sparrowTopicEntity = administrationService.getTopic (topic);
        if (ObjectUtils.isEmpty (sparrowTopicEntity)) {
            return SparrowRequestContextBuilder.builderFailed ("GetTopic Topic Does Not Exist");
        }
        SparrowTopic sparrowTopic = DomainUtils.domainConvert (sparrowTopicEntity, SparrowTopic.class);
        return SparrowRequestContextBuilder.builderSuccess (sparrowTopic);

    }


}
