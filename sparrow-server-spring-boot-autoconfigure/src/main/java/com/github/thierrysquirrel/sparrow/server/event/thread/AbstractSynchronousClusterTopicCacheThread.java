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
package com.github.thierrysquirrel.sparrow.server.event.thread;

import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import lombok.Data;

/**
 * ClassName: AbstractSynchronousClusterTopicCacheThread
 * Description:
 * date: 2020/6/9 4:09
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public abstract class AbstractSynchronousClusterTopicCacheThread implements Runnable {
    private String url;
    private SparrowRequestContext sparrowRequestContext;

    public AbstractSynchronousClusterTopicCacheThread(String url, SparrowRequestContext sparrowRequestContext) {
        this.url = url;
        this.sparrowRequestContext = sparrowRequestContext;
    }

    /**
     * synchronousClusterTopicCache
     *
     * @param url                   url
     * @param sparrowRequestContext sparrowRequestContext
     */
    protected abstract void synchronousClusterTopicCache(String url, SparrowRequestContext sparrowRequestContext);

    @Override
    public void run() {
        synchronousClusterTopicCache (this.url, this.sparrowRequestContext);
    }
}
