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
package com.github.thierrysquirrel.sparrow.server.mapper.template;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.thierrysquirrel.sparrow.server.mapper.entity.SparrowTopicEntity;

/**
 * ClassName: SparrowTopicEntityCacheTemplate 
 * Description: 
 * date: 2020/6/9 2:55
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SparrowTopicEntityCacheTemplate {
    private final Cache<String, SparrowTopicEntity> sparrowTopicEntityCache;

    public SparrowTopicEntityCacheTemplate(Cache<String, SparrowTopicEntity> sparrowTopicEntityCache) {
        this.sparrowTopicEntityCache = sparrowTopicEntityCache;
    }

    public void put(String topic, SparrowTopicEntity sparrowTopicEntity) {
        sparrowTopicEntityCache.put (topic, sparrowTopicEntity);
    }

    public SparrowTopicEntity get(String topic) {
        return sparrowTopicEntityCache.getIfPresent (topic);
    }

    public void invalidate(String topic) {
        sparrowTopicEntityCache.invalidate (topic);
    }
}
