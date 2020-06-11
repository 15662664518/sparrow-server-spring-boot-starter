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

import com.github.thierrysquirrel.sparrow.server.common.netty.domain.ResponseDomain;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowResponse;

/**
 * ClassName: SparrowResponseBuilder 
 * Description: 
 * date: 2020/6/9 3:36
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SparrowResponseBuilder {
    private SparrowResponseBuilder() {
    }

    private static SparrowResponse createSparrowResponse() {
        return new SparrowResponse ();
    }

    public static SparrowResponse builderSuccess() {
        SparrowResponse sparrowResponse = createSparrowResponse ();
        ResponseDomain responseDomain = ResponseDomainBuilder.builderSuccess ();
        sparrowResponse.setData (responseDomain);
        return sparrowResponse;
    }

    public static SparrowResponse builderSuccess(Object data) {
        SparrowResponse sparrowResponse = createSparrowResponse ();
        ResponseDomain responseDomain = ResponseDomainBuilder.builderSuccess (data);
        sparrowResponse.setData (responseDomain);
        return sparrowResponse;
    }

    public static SparrowResponse builderFailed(String message) {
        SparrowResponse sparrowResponse = createSparrowResponse ();
        ResponseDomain responseDomain = ResponseDomainBuilder.builderFailed (message);
        sparrowResponse.setData (responseDomain);
        return sparrowResponse;
    }
}
