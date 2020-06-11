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
package com.github.thierrysquirrel.sparrow.server.common.netty.core.constant;

/**
 * ClassName: ThreadPoolConstant
 * Description:
 * date: 2020/6/10 17:20
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public final class ThreadPoolConstant {
    public static final int INITIAL_DELAY = 0;

    public static final String PRODUCER_BUSINESS = "ProducerBusiness";
    public static final int PRODUCER_BUSINESS_CORE_POOL_SIZE = Runtime.getRuntime ().availableProcessors () * 2;
    public static final int PRODUCER_BUSINESS_MAXIMUM_POOL_SIZE = Runtime.getRuntime ().availableProcessors () * 2;
    public static final int PRODUCER_BUSINESS_KEEP_ALIVE_TIME = 0;

    public static final String CONSUMER_BUSINESS = "ConsumerBusiness";
    public static final int CONSUMER_BUSINESS_CORE_POOL_SIZE = Runtime.getRuntime ().availableProcessors () * 2;
    public static final int CONSUMER_BUSINESS_MAXIMUM_POOL_SIZE = Runtime.getRuntime ().availableProcessors () * 2;
    public static final int CONSUMER_BUSINESS_KEEP_ALIVE_TIME = 0;

    private ThreadPoolConstant() {
    }
}
