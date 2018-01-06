package com.ral.service.id;

import com.ral.model.enums.id.IdTypeEnums;

import java.util.List;

/**
 * @author victor
 * @desc ID生成策略
 * @date 2018/1/6 18:44
 */
public interface IdGeneraterService {

    String generate();

    List<String> generate(int count);

    String generate(IdTypeEnums type);

    List<String> generate(IdTypeEnums type, int count);
}
