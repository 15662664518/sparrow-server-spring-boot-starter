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
package com.github.thierrysquirrel.sparrow.server.common.netty.client.thread;

import com.github.thierrysquirrel.sparrow.server.common.netty.client.listener.ProducerListener;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowMessage;
import lombok.Data;

/**
 * ClassName: AbstractProducerBusinessThread
 * Description:
 * date: 2020/6/10 17:30
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public abstract class AbstractProducerBusinessThread implements Runnable {
    private ProducerListener producerListener;
    private boolean isSuccess;
    private SparrowMessage sparrowMessage;

    public AbstractProducerBusinessThread(ProducerListener producerListener, boolean isSuccess, SparrowMessage sparrowMessage) {
        this.producerListener = producerListener;
        this.isSuccess = isSuccess;
        this.sparrowMessage = sparrowMessage;
    }

    /**
     * producerBusiness
     *
     * @param producerListener producerListener
     * @param isSuccess        isSuccess
     * @param sparrowMessage   sparrowMessage
     */
    protected abstract void producerBusiness(ProducerListener producerListener, boolean isSuccess, SparrowMessage sparrowMessage);

    @Override
    public void run() {
        producerBusiness (this.producerListener, this.isSuccess, this.sparrowMessage);
    }
}
