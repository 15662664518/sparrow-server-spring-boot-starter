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
package com.github.thierrysquirrel.sparrow.server.service;

import com.github.thierrysquirrel.sparrow.server.common.netty.domain.PageSparrowMessage;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowMessage;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.SparrowTopic;
import com.github.thierrysquirrel.sparrow.server.common.netty.domain.builder.PageSparrowMessageBuilder;
import com.github.thierrysquirrel.sparrow.server.error.SparrowServerException;
import com.github.thierrysquirrel.sparrow.server.mapper.SparrowMessageMapper;
import com.github.thierrysquirrel.sparrow.server.mapper.SparrowTopicMapper;
import com.github.thierrysquirrel.sparrow.server.mapper.builder.SparrowMessageEntityBuilder;
import com.github.thierrysquirrel.sparrow.server.mapper.constant.MapperConstant;
import com.github.thierrysquirrel.sparrow.server.mapper.entity.SparrowMessageEntity;
import com.github.thierrysquirrel.sparrow.server.mapper.entity.SparrowTopicEntity;
import com.github.thierrysquirrel.sparrow.server.mapper.template.SparrowTopicEntityCacheTemplate;
import com.github.thierrysquirrel.sparrow.server.mapper.utils.DateUtils;
import com.github.thierrysquirrel.sparrow.server.mapper.utils.DomainUtils;
import com.github.thierrysquirrel.sparrow.server.mapper.utils.SparrowTopicEntityUtils;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * ClassName: AdministrationService
 * Description:
 * date: 2020/6/8 19:34
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public class AdministrationService {
    @Resource
    private SparrowTopicMapper sparrowTopicMapper;
    @Resource
    private SparrowMessageMapper sparrowMessageMapper;
    @Resource
    private SparrowTopicEntityCacheTemplate sparrowTopicEntityCacheTemplate;

    public boolean createTopic(String topic, byte isCluster) {
        SparrowTopicEntity byTopic = getTopic (topic);
        if (!ObjectUtils.isEmpty (byTopic)) {
            return Boolean.FALSE;
        }
        sparrowTopicMapper.createSparrowTopic (topic, isCluster);
        return Boolean.TRUE;
    }

    @Transactional(rollbackFor = SparrowServerException.class)
    public void deleteTopic(String topic) {
        sparrowTopicMapper.deleteAllByTopic (topic);
        sparrowMessageMapper.deleteAllByTopic (topic);
        sparrowTopicEntityCacheTemplate.invalidate (topic);
    }

    public SparrowTopicEntity getTopic(String topic) {
        SparrowTopicEntity sparrowTopicEntity = sparrowTopicEntityCacheTemplate.get (topic);
        if (ObjectUtils.isEmpty (sparrowTopicEntity)) {
            SparrowTopicEntity byTopicAndIsDeleted = sparrowTopicMapper.findByTopicAndIsDeleted (topic, MapperConstant.NOT_DELETED);
            if (ObjectUtils.isEmpty (byTopicAndIsDeleted)) {
                return null;
            }
            SparrowTopicEntityUtils.getTopic (byTopicAndIsDeleted);
            sparrowTopicEntityCacheTemplate.put (topic, byTopicAndIsDeleted);
            return byTopicAndIsDeleted;
        }
        return sparrowTopicEntity;
    }

    public List<SparrowTopic> getAllTopic() {
        List<SparrowTopicEntity> allByIsDeleted = sparrowTopicMapper.findAllByIsDeleted (MapperConstant.NOT_DELETED);
        return DomainUtils.domainConvertList (allByIsDeleted, SparrowTopic.class);
    }

    @Transactional(rollbackFor = SparrowServerException.class)
    public void removeExpiredData() {
        Date expirationDays = DateUtils.getExpirationDays ();
        byte deleted = MapperConstant.DELETED;
        sparrowMessageMapper.deleteAllByIsDeletedAndGmtModifiedBefore (deleted, expirationDays);
    }

    @Transactional(rollbackFor = SparrowServerException.class)
    public void batchConfirmConsumption(List<Long> idList) {
        sparrowMessageMapper.updateIsDeletedByIdList (MapperConstant.DELETED, idList);
    }

    @Transactional(rollbackFor = SparrowServerException.class)
    public void confirmConsumption(Long id) {
        sparrowMessageMapper.updateIsDeletedById (MapperConstant.DELETED, id);
    }

    public SparrowMessage postMessage(String topic, byte[] message) {
        SparrowTopicEntity byTopic = getTopic (topic);
        if (ObjectUtils.isEmpty (byTopic)) {
            return null;
        }
        Byte isCluster = byTopic.getIsCluster ();
        SparrowMessageEntity sparrowMessageEntity = SparrowMessageEntityBuilder.builderPostMessage (topic, isCluster, message);
        sparrowMessageMapper.saveSparrowMessageEntity (sparrowMessageEntity);
        return DomainUtils.domainConvert (sparrowMessageEntity, SparrowMessage.class);
    }

    public PageSparrowMessage pullMessage(String topic, int pageIndex, int pageSize) {
        SparrowTopicEntity byTopic = getTopic (topic);
        if (ObjectUtils.isEmpty (byTopic)) {
            return null;
        }
        List<SparrowMessageEntity> sparrowMessageEntityList = sparrowMessageMapper.findAllByTopicAndIsDeleted (topic, MapperConstant.NOT_DELETED, pageIndex * pageSize, pageSize);
        int count = sparrowMessageMapper.findCountByTopicAndIsDeleted (topic, MapperConstant.NOT_DELETED);
        int pageTotal = (count + pageSize - MapperConstant.COUNT_OFFSET) / pageSize;

        List<SparrowMessage> sparrowMessagesList = DomainUtils.domainConvertList (sparrowMessageEntityList, SparrowMessage.class);
        return PageSparrowMessageBuilder.builderPullMessageResponse (pageIndex, pageTotal, sparrowMessagesList);
    }


}
