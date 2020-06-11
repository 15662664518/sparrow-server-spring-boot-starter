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
package com.github.thierrysquirrel.sparrow.server.error;

/**
 * ClassName: SparrowServerException
 * Description:
 * date: 2020/6/10 3:58
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class SparrowServerException extends Exception {

    public SparrowServerException(String message) {
        super (message);
    }

    public SparrowServerException(String message, Throwable cause) {
        super (message, cause);
    }

    public SparrowServerException(Throwable cause) {
        super (cause);
    }

}
