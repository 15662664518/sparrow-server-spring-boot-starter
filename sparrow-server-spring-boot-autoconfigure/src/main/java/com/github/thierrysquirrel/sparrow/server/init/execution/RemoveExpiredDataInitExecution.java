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

import com.github.thierrysquirrel.sparrow.server.core.factory.ThreadPoolFactory;
import com.github.thierrysquirrel.sparrow.server.common.netty.core.factory.execution.ThreadPoolFactoryExecution;
import com.github.thierrysquirrel.sparrow.server.init.constant.InitConstant;
import com.github.thierrysquirrel.sparrow.server.service.AdministrationService;
import com.github.thierrysquirrel.sparrow.server.thread.execution.RemoveExpiredDataThreadExecution;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * ClassName: RemoveExpiredDataInitExecution
 * Description:
 * date: 2020/6/9 5:30
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class RemoveExpiredDataInitExecution {
    private RemoveExpiredDataInitExecution() {
    }

    public static void removeExpiredData(AdministrationService administrationService) {
        ScheduledThreadPoolExecutor removeExpiredDataThreadPool = ThreadPoolFactory.createRemoveExpiredDataThreadPool ();
        RemoveExpiredDataThreadExecution removeExpiredDataThreadExecution = new RemoveExpiredDataThreadExecution (administrationService);
        ThreadPoolFactoryExecution.statsTimingThread (removeExpiredDataThreadPool, removeExpiredDataThreadExecution, InitConstant.REMOVE_EXPIRED_DATA_INTERVAL);
    }
}
