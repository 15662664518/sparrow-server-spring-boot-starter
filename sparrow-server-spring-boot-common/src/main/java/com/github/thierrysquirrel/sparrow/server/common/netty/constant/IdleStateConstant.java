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
package com.github.thierrysquirrel.sparrow.server.common.netty.constant;

/**
 * ClassName: IdleStateConstant
 * Description:
 * date: 2020/6/8 18:09
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public final class IdleStateConstant {
    public static final int OTHER_TIMEOUT = 0;
    public static final int SPARROW_SERVER_READER_TIMEOUT = 1000 * 16;
    public static final int WRITE_TIMEOUT = 1000;
    public static final int PRODUCER_WRITE_TIMEOUT = 1000 * 16;
    public static final int CONSUMER_WRITE_TIMEOUT = 1000 * 16;

    private IdleStateConstant() {
    }
}
