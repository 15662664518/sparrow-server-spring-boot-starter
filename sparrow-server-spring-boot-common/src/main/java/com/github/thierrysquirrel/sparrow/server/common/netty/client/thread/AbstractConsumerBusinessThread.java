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

import com.github.thierrysquirrel.sparrow.server.common.netty.client.listener.ConsumerListener;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;

/**
 * ClassName: AbstractConsumerBusinessThread
 * Description:
 * date: 2020/6/11 6:52
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public abstract class AbstractConsumerBusinessThread implements Runnable {
    private ConsumerListener consumerListener;
    private ChannelHandlerContext ctx;
    private SparrowRequestContext msg;

    public AbstractConsumerBusinessThread(ConsumerListener consumerListener, ChannelHandlerContext ctx, SparrowRequestContext msg) {
        this.consumerListener = consumerListener;
        this.ctx = ctx;
        this.msg = msg;
    }

    /**
     * consumerBusiness
     *
     * @param consumerListener consumerListener
     * @param ctx              ctx
     * @param msg              msg
     */
    protected abstract void consumerBusiness(ConsumerListener consumerListener, ChannelHandlerContext ctx, SparrowRequestContext msg);

    @Override
    public void run() {
        consumerBusiness (this.consumerListener, this.ctx, this.msg);
    }
}
