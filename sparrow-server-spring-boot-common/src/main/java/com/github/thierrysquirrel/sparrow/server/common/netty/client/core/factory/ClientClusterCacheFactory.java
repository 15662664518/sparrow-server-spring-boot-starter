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
import com.github.thierrysquirrel.sparrow.server.common.netty.constant.SeparatorConstant;
import com.github.thierrysquirrel.sparrow.server.common.netty.core.factory.LoadBalancingFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName: ClientClusterCacheFactory
 * Description:
 * date: 2020/6/10 15:09
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ClientClusterCacheFactory {
    private static final ThreadLocal<List<ClientInit>> CLIENT_CLUSTER_CACHE = new InheritableThreadLocal<> ();
    private static final AtomicInteger CLIENT_CLUSTER_INDEX = new AtomicInteger ();

    private ClientClusterCacheFactory() {
    }

    public static ClientInit getClientInit(String clusterUrl) {
        List<ClientInit> clientInitList = CLIENT_CLUSTER_CACHE.get ();
        if (null == clientInitList) {
            clientInitList = putClientInit (clusterUrl);
            CLIENT_CLUSTER_CACHE.set (clientInitList);
        }
        Integer index = LoadBalancingFactory.getIndex (CLIENT_CLUSTER_INDEX, clientInitList.size ());
        return clientInitList.get (index);
    }

    public static void remove() {
        CLIENT_CLUSTER_CACHE.remove ();
    }

    private static List<ClientInit> putClientInit(String clusterUrl) {
        List<ClientInit> clientInits = new ArrayList<> ();
        String[] split = clusterUrl.split (SeparatorConstant.URL_SEPARATOR);
        for (String url : split) {
            ClientInit clientInit = new ClientInit (url);
            clientInits.add (clientInit);
        }
        return clientInits;
    }
}
