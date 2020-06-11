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
package com.github.thierrysquirrel.sparrow.server.common.netty.thread;

import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;

/**
 * ClassName: AbstractSparrowServerBusinessThread
 * Description:
 * date: 2020/6/8 19:21
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public abstract class AbstractSparrowServerBusinessThread implements Runnable {
    private ChannelHandlerContext ctx;
    private SparrowRequestContext msg;

    public AbstractSparrowServerBusinessThread(ChannelHandlerContext ctx, SparrowRequestContext msg) {
        this.ctx = ctx;
        this.msg = msg;
    }

    /**
     * sparrowServerBusiness
     *
     * @param ctx ctx
     * @param msg msg
     */
    protected abstract void sparrowServerBusiness(ChannelHandlerContext ctx, SparrowRequestContext msg);

    @Override
    public void run() {
        sparrowServerBusiness (this.ctx,
                this.msg);
    }
}
