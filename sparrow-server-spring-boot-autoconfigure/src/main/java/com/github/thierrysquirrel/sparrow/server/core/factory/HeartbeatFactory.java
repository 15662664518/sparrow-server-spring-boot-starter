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

import com.google.common.collect.Maps;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ClassName: HeartbeatFactory
 * Description:
 * date: 2020/6/10 2:10
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Slf4j
public class HeartbeatFactory {
    private static final Map<String, String> ADDRESS_TOPIC_MAP = Maps.newConcurrentMap ();
    private static final Map<String, List<String>> TOPIC_ADDRESS_LIST_MAP = Maps.newConcurrentMap ();
    private static final Map<String, List<ChannelHandlerContext>> TOPIC_CONSUMER_CHANNEL_LIST_MAP = Maps.newConcurrentMap ();

    private HeartbeatFactory() {
    }

    public static void consumerPing(String topic, ChannelHandlerContext ctx) {
        String consumerAddress = ctx.channel ().remoteAddress ().toString ();
        ADDRESS_TOPIC_MAP.putIfAbsent (consumerAddress, topic);
        List<String> addressList = TOPIC_ADDRESS_LIST_MAP.computeIfAbsent (topic, key -> new ArrayList<> ());
        if (!addressList.contains (consumerAddress)) {
            addressList.add (consumerAddress);
        }
        List<ChannelHandlerContext> consumerChannelList = TOPIC_CONSUMER_CHANNEL_LIST_MAP.computeIfAbsent (topic, key -> new ArrayList<> ());
        if (!consumerChannelList.contains (ctx)) {
            consumerChannelList.add (ctx);
        }
    }

    public static void remove(ChannelHandlerContext ctx) {
        String consumerAddress = ctx.channel ().remoteAddress ().toString ();
        String topic = ADDRESS_TOPIC_MAP.get (consumerAddress);

        if (ObjectUtils.isEmpty (topic)) {
            return;
        }
        ADDRESS_TOPIC_MAP.remove (consumerAddress);
        TOPIC_ADDRESS_LIST_MAP.get (topic).remove (consumerAddress);
        TOPIC_CONSUMER_CHANNEL_LIST_MAP.get (topic).remove (ctx);

    }


    public static List<String> getTopicAddress(String topic) {
        return TOPIC_ADDRESS_LIST_MAP.get (topic);
    }

    public static List<ChannelHandlerContext> getTopicConsumerChannel(String topic) {
        return TOPIC_CONSUMER_CHANNEL_LIST_MAP.get (topic);
    }
}
