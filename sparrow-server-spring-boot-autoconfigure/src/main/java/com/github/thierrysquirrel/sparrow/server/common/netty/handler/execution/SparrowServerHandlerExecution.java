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
package com.github.thierrysquirrel.sparrow.server.common.netty.handler.execution;

import com.github.thierrysquirrel.sparrow.server.core.factory.constant.ThreadPoolFactoryConstant;
import com.github.thierrysquirrel.sparrow.server.common.netty.core.factory.execution.ThreadPoolFactoryExecution;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import com.github.thierrysquirrel.sparrow.server.common.netty.thread.execution.SparrowServerBusinessThreadExecution;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: SparrowServerHandlerExecution
 * Description:
 * date: 2020/6/8 19:23
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SparrowServerHandlerExecution {
    private SparrowServerHandlerExecution() {
    }

    public static void sparrowServerBusiness(ChannelHandlerContext ctx, SparrowRequestContext msg) {
        ThreadPoolExecutor sparrowServerBusinessThreadPool = ThreadPoolFactoryConstant.SPARROW_SERVER_BUSINESS_THREAD_POOL;
        SparrowServerBusinessThreadExecution sparrowServerBusinessThreadExecution = new SparrowServerBusinessThreadExecution (ctx, msg);
        ThreadPoolFactoryExecution.statsThread (sparrowServerBusinessThreadPool, sparrowServerBusinessThreadExecution);
    }
}
