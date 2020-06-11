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
package com.github.thierrysquirrel.sparrow.server.common.netty.core.factory;

import com.github.thierrysquirrel.sparrow.server.common.netty.constant.Command;
import com.github.thierrysquirrel.sparrow.server.common.netty.constant.Modular;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.RouteEvent;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName: RouteEventFactory
 * Description:
 * date: 2020/6/8 18:58
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class RouteEventFactory {
    private static final Map<Modular, Map<Command, RouteEvent>> ROTE_EVENT_MAP = new ConcurrentHashMap<> ();

    private RouteEventFactory() {
    }

    public static RouteEvent getRouteEvent(SparrowRequestContext sparrowRequestContext) {
        return ROTE_EVENT_MAP.get (sparrowRequestContext.getModular ())
                .get (sparrowRequestContext.getCommand ());
    }

    public static void setRouteEvent(Modular modular, Command command, RouteEvent routeEvent) {
        ROTE_EVENT_MAP.computeIfAbsent (modular, key -> Maps.newConcurrentMap ())
                .put (command, routeEvent);
    }
}
