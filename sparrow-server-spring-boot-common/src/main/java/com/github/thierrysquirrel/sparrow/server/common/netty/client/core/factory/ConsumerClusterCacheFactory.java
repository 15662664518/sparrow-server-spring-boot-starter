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
package com.github.thierrysquirrel.sparrow.server.common.netty.client.core.factory;

import com.github.thierrysquirrel.sparrow.server.common.netty.client.init.ConsumerInit;
import com.github.thierrysquirrel.sparrow.server.common.netty.client.init.ConsumerRequest;
import com.github.thierrysquirrel.sparrow.server.common.netty.client.listener.ConsumerListener;
import com.github.thierrysquirrel.sparrow.server.common.netty.constant.SeparatorConstant;
import com.github.thierrysquirrel.sparrow.server.common.netty.core.factory.LoadBalancingFactory;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName: ConsumerClusterCacheFactory
 * Description:
 * date: 2020/6/11 7:20
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ConsumerClusterCacheFactory {
    private static final Map<String, List<ConsumerRequest>> CONSUMER_CLUSTER_MAP = Maps.newConcurrentMap ();
    private static final List<ConsumerRequest> CONSUMER_REQUEST_LIST = new ArrayList<> ();
    private static final Map<String, AtomicInteger> CONSUMER_CLUSTER_INDEX_MAP = Maps.newConcurrentMap ();

    private ConsumerClusterCacheFactory() {
    }

    public static void putConsumerInit(String topic, ConsumerListener consumerListener, String clusterUrl) {
        CONSUMER_CLUSTER_INDEX_MAP.computeIfAbsent (topic, key -> new AtomicInteger ());
        List<ConsumerRequest> consumerRequestList = CONSUMER_CLUSTER_MAP.computeIfAbsent (topic, key -> new ArrayList<> ());
        String[] split = clusterUrl.split (SeparatorConstant.URL_SEPARATOR);
        for (String url : split) {
            ConsumerInit consumerInit = new ConsumerInit (url, consumerListener);
            ConsumerRequest consumerRequest = new ConsumerRequest (consumerInit, topic);
            consumerRequestList.add (consumerRequest);
            CONSUMER_REQUEST_LIST.add (consumerRequest);
        }
    }

    public static ConsumerInit getConsumerInit(String topic, String clusterUrl) {
        List<ConsumerRequest> consumerInits = CONSUMER_CLUSTER_MAP.get (topic);
        String[] split = clusterUrl.split (SeparatorConstant.URL_SEPARATOR);
        int maxIndex = split.length;
        AtomicInteger atomicInteger = CONSUMER_CLUSTER_INDEX_MAP.get (topic);
        Integer index = LoadBalancingFactory.getIndex (atomicInteger, maxIndex);
        return consumerInits.get (index).getConsumerInit ();
    }

    public static List<ConsumerRequest> getAllConsumerRequest() {
        return CONSUMER_REQUEST_LIST;
    }

    public static Map<String, List<ConsumerRequest>> getConsumerClusterMap() {
        return CONSUMER_CLUSTER_MAP;
    }
}
