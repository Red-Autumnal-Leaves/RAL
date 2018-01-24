package com.ral.mongo.ral.repository.logs;

import com.ral.mongo.ral.entity.logs.RalOperationLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author victor
 * @desc
 * @date 2018/1/23 22:08
 */
@Repository
public interface IRalOperationLogRepository extends MongoRepository<RalOperationLog, String> {

}
