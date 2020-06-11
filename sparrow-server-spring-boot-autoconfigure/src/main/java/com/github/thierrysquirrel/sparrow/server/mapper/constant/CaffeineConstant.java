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
package com.github.thierrysquirrel.sparrow.server.mapper.constant;

/**
 * ClassName: CaffeineConstant 
 * Description: 
 * date: 2020/6/9 2:52
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public final class CaffeineConstant {
    public static final int SPARROW_TOPIC_ENTITY_INITIAL_CAPACITY=64;
    public static final int SPARROW_TOPIC_ENTITY_MAXIMUM_SIZE=128;
    public static final int SPARROW_TOPIC_ENTITY_EXPIRE_AFTER_WRITE=1000*32;

    private CaffeineConstant() {
    }
}
