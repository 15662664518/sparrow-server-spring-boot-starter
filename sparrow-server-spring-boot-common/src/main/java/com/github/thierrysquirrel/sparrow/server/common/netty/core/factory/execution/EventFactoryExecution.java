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
package com.github.thierrysquirrel.sparrow.server.common.netty.core.factory.execution;

import com.github.thierrysquirrel.sparrow.server.common.netty.core.factory.EventFactory;
import com.github.thierrysquirrel.sparrow.server.common.netty.core.factory.RouteEventFactory;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.RouteEvent;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import io.netty.channel.ChannelHandlerContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ClassName: EventFactoryExecution
 * Description:
 * date: 2020/6/8 19:19
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class EventFactoryExecution {
    private EventFactoryExecution() {
    }

    public static void execution(ChannelHandlerContext ctx, SparrowRequestContext msg) throws InvocationTargetException, IllegalAccessException {
        Object[] eventParameter = EventFactory.getEventParameter (ctx, msg);
        RouteEvent routeEvent = RouteEventFactory.getRouteEvent (msg);
        Method method = routeEvent.getMethod ();
        Object object = routeEvent.getBean ();
        method.invoke (object, eventParameter);
    }
}
