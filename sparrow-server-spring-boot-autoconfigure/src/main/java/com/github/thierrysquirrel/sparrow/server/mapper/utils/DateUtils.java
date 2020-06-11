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

import com.github.thierrysquirrel.sparrow.server.mapper.constant.MapperConstant;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * ClassName: DateUtils
 * Description:
 * date: 2020/6/9 5:13
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class DateUtils {
    private DateUtils() {
    }

    private static Date getPastTime(int day) {
        LocalDate localDate = LocalDate.now ();
        LocalDate removeDate = localDate.minusDays (day);
        ZonedDateTime zonedDateTime = removeDate.atStartOfDay (ZoneId.systemDefault ());
        return Date.from (zonedDateTime.toInstant ());
    }

    public static Date getExpirationDays() {
        return getPastTime (MapperConstant.EXPIRATION_TIME_DAYS);
    }
}
