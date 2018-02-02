package com.ral.service.express;

import com.ral.model.dto.express.ExpressDto;
import com.ral.model.entity.express.Express;
import com.ral.model.entity.express.ExpressExample;

import java.util.List;

/**
 * Created by victor on 2018/2/1.
 */
public interface IExpressService {

    List<Express> selectByExample(ExpressExample example);

    int countByExample(ExpressExample example);

    Express selectById(Long expressId);

    int save(Express express);

    int update(Express express);

    List<ExpressDto> convertToDto(List<Express> expresses);

    ExpressDto convertToDto(Express express);

    int countByExpressName(String expressName);

}

