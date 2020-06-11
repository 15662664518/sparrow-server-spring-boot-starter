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
package com.github.thierrysquirrel.sparrow.server.core.factory;

import com.github.thierrysquirrel.sparrow.server.common.netty.core.factory.LoadBalancingFactory;
import com.google.common.collect.Maps;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName: ConsumerLoadBalancing
 * Description:
 * date: 2020/6/10 5:47
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ConsumerLoadBalancing {
    private static final Map<String, AtomicInteger> LOAD_BALANCING_MAP = Maps.newConcurrentMap ();

    private ConsumerLoadBalancing() {
    }

    public static ChannelHandlerContext getChannelHandlerContext(String topic) {
        Integer index = getIndex (topic);
        if (ObjectUtils.isEmpty (index)) {
            return null;
        }
        return HeartbeatFactory.getTopicConsumerChannel (topic).get (index);
    }

    private static Integer getIndex(String topic) {
        List<String> remoteAddress = HeartbeatFactory.getTopicAddress (topic);
        if (ObjectUtils.isEmpty (remoteAddress)) {
            return null;
        }
        AtomicInteger atomicInteger = LOAD_BALANCING_MAP.computeIfAbsent (topic, key -> new AtomicInteger ());
        return LoadBalancingFactory.getIndex (atomicInteger, remoteAddress.size ());
    }
}
