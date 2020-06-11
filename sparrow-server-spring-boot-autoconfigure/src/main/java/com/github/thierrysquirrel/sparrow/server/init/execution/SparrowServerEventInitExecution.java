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
package com.github.thierrysquirrel.sparrow.server.init.execution;

import com.github.thierrysquirrel.sparrow.server.annotation.SparrowServerEvent;
import com.github.thierrysquirrel.sparrow.server.common.netty.constant.Command;
import com.github.thierrysquirrel.sparrow.server.common.netty.constant.Modular;
import com.github.thierrysquirrel.sparrow.server.common.netty.core.factory.RouteEventFactory;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.RouteEvent;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.builder.RouteEventBuilder;

import java.lang.reflect.Method;

/**
 * ClassName: SparrowServerEventInitExecution
 * Description:
 * date: 2020/6/8 19:12
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SparrowServerEventInitExecution {
    private SparrowServerEventInitExecution() {
    }

    public static void setRouteEvent(Object bean, Method method, SparrowServerEvent sparrowServerEvent) {
        Modular modular = sparrowServerEvent.modular ();
        Command command = sparrowServerEvent.command ();
        RouteEvent routeEvent = RouteEventBuilder.builderRotateEvent (bean, method);
        RouteEventFactory.setRouteEvent (modular, command, routeEvent);
    }
}
