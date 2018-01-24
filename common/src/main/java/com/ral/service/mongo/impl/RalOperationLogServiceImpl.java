package com.ral.service.mongo.impl;

import com.ral.model.enums.logs.RalOperationResultEnum;
import com.ral.model.enums.logs.RalOperationTypeEnum;
import com.ral.model.enums.queue.MessageQueueEnum;
import com.ral.mongo.ral.entity.logs.RalOperationLog;
import com.ral.mongo.ral.repository.logs.IRalOperationLogRepository;
import com.ral.service.mongo.IRalOperationLogService;
import com.ral.service.queue.IMessageQueueService;
import com.ral.util.codec.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by victor on 2018/1/24.
 */
@Service
public class RalOperationLogServiceImpl implements IRalOperationLogService {

    @Autowired
    private IRalOperationLogRepository ralOperationLogRepository;

    @Autowired
    private IMessageQueueService messageQueueService;

    @Override
    public void logger(RalOperationTypeEnum type, String relationId, String content, RalOperationResultEnum result) {
        RalOperationLog log = new RalOperationLog();
        log.setSource(type.getSource().getCode());
        log.setType(type.getIndex());
        log.setContent(content);
        log.setRelationId(relationId);
        log.setName(type.getName());
        log.setResult(result.getIndex());
        log.setCreateTime(new Date());
        messageQueueService.send(MessageQueueEnum.RAL_OPERATION_LOG_QUEUE, JSONUtils.toJson(log));
    }

    @Override
    public List<RalOperationLog> findAll() {
        return ralOperationLogRepository.findAll();
    }
}
