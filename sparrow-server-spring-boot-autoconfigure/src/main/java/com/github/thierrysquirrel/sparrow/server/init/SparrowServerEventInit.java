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
package com.github.thierrysquirrel.sparrow.server.init;

import com.github.thierrysquirrel.sparrow.server.annotation.SparrowServerEvent;
import com.github.thierrysquirrel.sparrow.server.annotation.SparrowServerHandler;
import com.github.thierrysquirrel.sparrow.server.init.execution.SparrowServerEventInitExecution;
import com.github.thierrysquirrel.sparrow.server.init.utils.AnnotatedMethodsUtils;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;

import javax.annotation.PostConstruct;

/**
 * ClassName: SparrowServerEventInit
 * Description:
 * date: 2020/6/8 19:10
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public class SparrowServerEventInit implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        applicationContext.getBeansWithAnnotation (SparrowServerHandler.class).forEach ((beanName, bean) ->
                AnnotatedMethodsUtils.getMethodAndAnnotation (bean, SparrowServerEvent.class).
                        forEach ((method, sparrowServerEvent) -> SparrowServerEventInitExecution.setRouteEvent (bean, method, sparrowServerEvent))
        );
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
