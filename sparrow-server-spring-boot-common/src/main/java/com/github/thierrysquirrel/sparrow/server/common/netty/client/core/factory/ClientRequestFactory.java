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
package com.github.thierrysquirrel.sparrow.server.common.netty.client.core.factory;

import com.github.thierrysquirrel.sparrow.server.common.netty.client.constant.ClientConstant;
import com.github.thierrysquirrel.sparrow.server.common.netty.client.init.ClientInit;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowRequestContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * ClassName: ClientRequestFactory 
 * Description: 
 * date: 2020/6/10 15:27
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ClientRequestFactory {
    private ClientRequestFactory() {
    }
    public static SparrowRequestContext request(ClientInit clientInit,SparrowRequestContext sparrowRequestContext) throws InterruptedException, TimeoutException, ExecutionException {
        clientInit.init ();
        clientInit.getChannel ().writeAndFlush (sparrowRequestContext);
        return clientInit.getCompletableFuture ().get (ClientConstant.TIMEOUT, TimeUnit.MILLISECONDS);
    }
}
