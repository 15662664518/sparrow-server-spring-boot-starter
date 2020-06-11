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
package com.github.thierrysquirrel.sparrow.server.mapper;

import com.github.thierrysquirrel.sparrow.server.mapper.entity.SparrowTopicEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * ClassName: SparrowTopicMapper
 * Description:
 * date: 2020/6/11 4:28
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Mapper
public interface SparrowTopicMapper {
    /**
     * initSparrowTopicEntity
     *
     * @return int
     */
    @Update("CREATE TABLE IF NOT EXISTS `sparrow_topic_entity`  (" +
            "  `id` int(4) NOT NULL AUTO_INCREMENT," +
            "  `topic` varchar(32) NOT NULL," +
            "  `is_cluster` tinyint(1) NOT NULL DEFAULT 1," +
            "  `is_deleted` tinyint(1) NOT NULL DEFAULT 0," +
            "  `gmt_create` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0)," +
            "  `gmt_modified` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0)," +
            "  PRIMARY KEY (`id`) USING BTREE," +
            "  INDEX `idx_topic`(`topic`) USING BTREE" +
            ")")
    int initSparrowTopicEntity();

    /**
     * findByTopicAndIsDeleted
     *
     * @param topic     topic
     * @param isDeleted isDeleted
     * @return SparrowTopicEntity
     */
    @Select("SELECT topic,is_cluster FROM sparrow_topic_entity WHERE topic = #{topic} AND is_deleted = #{isDeleted}")
    SparrowTopicEntity findByTopicAndIsDeleted(@Param("topic") String topic, @Param("isDeleted") byte isDeleted);


    /**
     * createSparrowTopic
     *
     * @param topic     topic
     * @param isCluster isCluster
     * @return int
     */
    @Insert("INSERT INTO sparrow_topic_entity (topic,is_cluster) VALUES (#{topic},#{isCluster})")
    int createSparrowTopic(@Param("topic") String topic, @Param("isCluster") byte isCluster);

    /**
     * deleteAllByTopic
     *
     * @param topic topic
     * @return int
     */
    @Delete("DELETE FROM sparrow_topic_entity WHERE topic = #{topic}")
    int deleteAllByTopic(@Param("topic") String topic);

    /**
     * findAllByIsDeleted
     *
     * @param isDeleted isDeleted
     * @return List
     */
    @Select("SELECT topic,is_cluster FROM sparrow_topic_entity WHERE is_deleted = #{isDeleted}")
    List<SparrowTopicEntity> findAllByIsDeleted(@Param("isDeleted") byte isDeleted);
}
