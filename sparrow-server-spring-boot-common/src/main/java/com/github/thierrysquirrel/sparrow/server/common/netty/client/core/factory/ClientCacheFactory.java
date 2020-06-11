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

import com.github.thierrysquirrel.sparrow.server.common.netty.client.init.ClientInit;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * ClassName: ClientCacheFactory
 * Description:
 * date: 2020/6/8 18:23
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ClientCacheFactory {
    private static final ThreadLocal<Map<String, ClientInit>> CLIENT_CACHE = new InheritableThreadLocal<> ();

    private ClientCacheFactory() {
    }

    public static ClientInit getClientInit(String url) {
        Map<String, ClientInit> clientInitMap = CLIENT_CACHE.get ();
        if (null == clientInitMap) {
            clientInitMap = Maps.newConcurrentMap ();
            CLIENT_CACHE.set (clientInitMap);
        }
        return clientInitMap.computeIfAbsent (url, ClientInit::new);
    }

    public static void remove() {
        CLIENT_CACHE.remove ();
    }
}
