package com.ral.service.mongo;

import com.ral.model.enums.logs.RalOperationResultEnum;
import com.ral.model.enums.logs.RalOperationTypeEnum;
import com.ral.mongo.ral.entity.logs.RalOperationLog;
import java.util.List;

/**
 * Created by victor on 2018/1/24.
 */
public interface IRalOperationLogService {

    void logger(RalOperationTypeEnum type, String relationId, String content, RalOperationResultEnum result);

    List<RalOperationLog> findAll();
}
