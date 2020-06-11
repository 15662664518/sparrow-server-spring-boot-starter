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


import com.github.thierrysquirrel.sparrow.server.mapper.entity.SparrowMessageEntity;

/**
 * ClassName: SparrowMessageEntityBuilder
 * Description:
 * date: 2020/6/10 5:00
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SparrowMessageEntityBuilder {
    private SparrowMessageEntityBuilder() {
    }

    private static SparrowMessageEntity createSparrowMessageEntity() {
        return new SparrowMessageEntity ();
    }

    public static SparrowMessageEntity builderPostMessage(String topic, byte isCluster, byte[] message) {
        SparrowMessageEntity sparrowMessageEntity = createSparrowMessageEntity ();
        sparrowMessageEntity.setTopic (topic);
        sparrowMessageEntity.setIsCluster (isCluster);
        sparrowMessageEntity.setMessage (message);
        return sparrowMessageEntity;
    }


}
