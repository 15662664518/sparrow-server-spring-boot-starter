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
package com.github.thierrysquirrel.sparrow.server.common.netty.client.listener;

import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowMessage;

/**
 * ClassName: ProducerListener
 * Description:
 * date: 2020/6/10 17:00
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@FunctionalInterface
public interface ProducerListener {
    /**
     * response
     *
     * @param isSuccess      isSuccess
     * @param sparrowMessage sparrowMessage
     */
    void response(boolean isSuccess, SparrowMessage sparrowMessage);
}