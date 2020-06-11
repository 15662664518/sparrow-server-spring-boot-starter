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
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.constant.ResponseDomainConstant;

/**
 * ClassName: ResponseDomainBuilder 
 * Description: 
 * date: 2020/6/9 3:38
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ResponseDomainBuilder {
    private ResponseDomainBuilder() {
    }

    private static ResponseDomain createResponseDomain() {
        return new ResponseDomain ();
    }

    public static ResponseDomain builderSuccess() {
        ResponseDomain responseDomain = createResponseDomain ();
        responseDomain.setCode (ResponseDomainConstant.SUCCESS_CODE);
        responseDomain.setMessage (ResponseDomainConstant.SUCCESS_MESSAGE);
        return responseDomain;
    }

    public static ResponseDomain builderSuccess(Object data) {
        ResponseDomain responseDomain = builderSuccess ();
        responseDomain.setData (data);
        return responseDomain;
    }

    public static ResponseDomain builderFailed(String message) {
        ResponseDomain responseDomain = createResponseDomain ();
        responseDomain.setCode (ResponseDomainConstant.FAILED_CODE);
        responseDomain.setMessage (message);
        return responseDomain;
    }
}
