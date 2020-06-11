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
package com.github.thierrysquirrel.sparrow.server.common.netty.handler;

import com.github.thierrysquirrel.sparrow.server.core.factory.HeartbeatFactory;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;
import com.github.thierrysquirrel.sparrow.server.common.netty.handler.execution.SparrowServerHandlerExecution;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * ClassName: SparrowServerHandler
 * Description:
 * date: 2020/6/8 18:36
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SparrowServerHandler extends SimpleChannelInboundHandler<SparrowRequestContext> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SparrowRequestContext msg) throws Exception {
        SparrowServerHandlerExecution.sparrowServerBusiness (ctx, msg);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent) evt).state ();
            if (IdleState.READER_IDLE == state) {
                ctx.close ();
            }
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        HeartbeatFactory.remove (ctx);
    }
}
