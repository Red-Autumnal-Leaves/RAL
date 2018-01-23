package com.ral.mongo.repository;

import com.ral.mongo.entity.logs.RalOperationLog;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author victor
 * @desc
 * @date 2018/1/23 22:08
 */
public interface RalOperationLogRepository extends MongoRepository<RalOperationLog, Long> {


}
