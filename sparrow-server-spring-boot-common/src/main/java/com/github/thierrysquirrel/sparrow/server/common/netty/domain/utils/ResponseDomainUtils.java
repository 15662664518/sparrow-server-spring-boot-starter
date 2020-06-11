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
package com.github.thierrysquirrel.sparrow.server.common.netty.domain.utils;

import com.github.thierrysquirrel.sparrow.server.common.netty.domain.constant.ResponseDomainConstant;

/**
 * ClassName: ResponseDomainUtils
 * Description:
 * date: 2020/6/10 17:10
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class ResponseDomainUtils {
    private ResponseDomainUtils() {
    }

    public static boolean isSuccessConversion(byte conversion) {
        byte successCode = ResponseDomainConstant.SUCCESS_CODE;
        if (successCode == conversion) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}