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

import com.github.thierrysquirrel.sparrow.server.core.factory.constant.ThreadPoolFactoryConstant;
import com.github.thierrysquirrel.sparrow.server.common.netty.core.factory.execution.ThreadPoolFactoryExecution;
import com.github.thierrysquirrel.sparrow.server.event.thread.execution.PushMessageThreadExecution;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: PushMessageFactory
 * Description:
 * date: 2020/6/10 14:51
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class PushMessageFactory {
    private PushMessageFactory() {
    }

    public static void pushMessage(ChannelHandlerContext channelHandlerContext, SparrowRequestContext sparrowRequestContext) {
        PushMessageThreadExecution pushMessageThreadExecution = new PushMessageThreadExecution (channelHandlerContext, sparrowRequestContext);
        ThreadPoolExecutor pushMessageThreadPool = ThreadPoolFactoryConstant.PUSH_MESSAGE_THREAD_POOL;
        ThreadPoolFactoryExecution.statsThread (pushMessageThreadPool, pushMessageThreadExecution);
    }
}
