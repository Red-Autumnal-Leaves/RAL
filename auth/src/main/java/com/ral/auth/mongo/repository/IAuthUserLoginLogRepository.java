package com.ral.auth.mongo.repository;


import com.ral.auth.mongo.entity.AuthUserLoginLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author victor
 * @desc
 * @date 2018/1/24 20:44
 */
@Repository
public interface IAuthUserLoginLogRepository  extends MongoRepository<AuthUserLoginLog, String> {
}
