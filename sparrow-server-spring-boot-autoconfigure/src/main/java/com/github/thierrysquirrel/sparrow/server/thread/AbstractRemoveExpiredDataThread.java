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
package com.github.thierrysquirrel.sparrow.server.thread;

import com.github.thierrysquirrel.sparrow.server.service.AdministrationService;
import lombok.Data;

/**
 * ClassName: AbstractRemoveExpiredDataThread
 * Description:
 * date: 2020/6/9 5:25
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public abstract class AbstractRemoveExpiredDataThread implements Runnable {

    private AdministrationService administrationService;

    public AbstractRemoveExpiredDataThread(AdministrationService administrationService) {
        this.administrationService = administrationService;
    }

    /**
     * removeExpiredData
     *
     * @param administrationService administrationService
     */
    protected abstract void removeExpiredData(AdministrationService administrationService);

    @Override
    public void run() {
        removeExpiredData (this.administrationService);
    }
}
