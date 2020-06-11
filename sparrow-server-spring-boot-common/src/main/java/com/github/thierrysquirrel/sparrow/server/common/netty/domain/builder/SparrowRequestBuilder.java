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
package com.github.thierrysquirrel.sparrow.server.common.netty.domain.builder;

import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequest;

import java.util.List;

/**
 * ClassName: SparrowRequestBuilder
 * Description:
 * date: 2020/6/9 3:30
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SparrowRequestBuilder {
    private SparrowRequestBuilder() {
    }

    private static SparrowRequest createSparrowRequest() {
        return new SparrowRequest ();
    }

    private static SparrowRequest sparrowRequestPutTopic(String topic) {
        SparrowRequest sparrowRequest = createSparrowRequest ();
        sparrowRequest.setParameters (new Object[]{topic});
        return sparrowRequest;
    }

    public static SparrowRequest builderCreateTopic(String topic, byte isCluster) {
        SparrowRequest sparrowRequest = createSparrowRequest ();
        sparrowRequest.setParameters (new Object[]{topic, isCluster});
        return sparrowRequest;
    }

    public static SparrowRequest builderDeleteTopic(String topic) {
        return sparrowRequestPutTopic (topic);
    }

    public static SparrowRequest builderSynchronousClusterTopicCache(String topic) {
        return sparrowRequestPutTopic (topic);
    }

    public static SparrowRequest builderGetAllTopic() {
        return createSparrowRequest ();
    }

    public static SparrowRequest builderGetTopic(String topic) {
        return sparrowRequestPutTopic (topic);
    }

    public static SparrowRequest builderPing(String topic) {
        return sparrowRequestPutTopic (topic);
    }

    public static SparrowRequest builderBatchConfirmConsumption(List<Long> idList) {
        SparrowRequest sparrowRequest = createSparrowRequest ();
        sparrowRequest.setParameters (new Object[]{idList});
        return sparrowRequest;
    }

    public static SparrowRequest builderConfirmConsumption(Long id) {
        SparrowRequest sparrowRequest = createSparrowRequest ();
        sparrowRequest.setParameters (new Object[]{id});
        return sparrowRequest;
    }

    public static SparrowRequest builderPostMessage(String topic, byte[] message) {
        SparrowRequest sparrowRequest = createSparrowRequest ();
        sparrowRequest.setParameters (new Object[]{topic, message});
        return sparrowRequest;
    }

    public static SparrowRequest builderPullMessage(String topic, int pageIndex, int pageSize) {
        SparrowRequest sparrowRequest = createSparrowRequest ();
        sparrowRequest.setParameters (new Object[]{topic, pageIndex, pageSize});
        return sparrowRequest;
    }
}
