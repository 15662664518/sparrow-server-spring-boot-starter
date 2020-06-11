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
package com.github.thierrysquirrel.sparrow.server.mapper.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: DomainUtils 
 * Description: 
 * date: 2020/6/8 19:32
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class DomainUtils {
    private DomainUtils() {
    }

    public static <T> T domainConvert(Object domain, Class<T> convertClass) {
        try {
            T newInstance = convertClass.getDeclaredConstructor ().newInstance ();
            BeanUtils.copyProperties (domain, newInstance);
            return newInstance;
        } catch (Exception e) {
            return null;
        }
    }

    public static <E, T> List<T> domainConvertList(List<E> domainList, Class<T> convertClass) {
        List<T> list = new ArrayList<> ();
        domainList.forEach (domainConvert -> list.add (domainConvert (domainConvert, convertClass)));
        return list;
    }
}
