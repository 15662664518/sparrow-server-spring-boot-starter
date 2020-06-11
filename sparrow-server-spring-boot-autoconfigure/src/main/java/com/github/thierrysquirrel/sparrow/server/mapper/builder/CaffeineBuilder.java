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
package com.github.thierrysquirrel.sparrow.server.mapper.builder;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.thierrysquirrel.sparrow.server.mapper.entity.SparrowTopicEntity;
import com.github.thierrysquirrel.sparrow.server.mapper.constant.CaffeineConstant;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: CaffeineBuilder 
 * Description: 
 * date: 2020/6/9 2:52
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class CaffeineBuilder {
    private CaffeineBuilder() {
    }

    public static Cache<String, SparrowTopicEntity> builderSparrowTopicEntityCache() {
        return Caffeine.newBuilder ()
                .initialCapacity (CaffeineConstant.SPARROW_TOPIC_ENTITY_INITIAL_CAPACITY)
                .maximumSize (CaffeineConstant.SPARROW_TOPIC_ENTITY_MAXIMUM_SIZE)
                .expireAfterWrite (CaffeineConstant.SPARROW_TOPIC_ENTITY_EXPIRE_AFTER_WRITE, TimeUnit.MILLISECONDS)
                .build ();
    }
}
