package com.ral.service.sku;
import com.ral.model.dto.sku.SkuDto;
import com.ral.model.entity.sku.Sku;
import com.ral.model.entity.sku.SkuExample;
import com.ral.model.query.sku.SkuQuery;

import java.util.List;

/**
 * Created by victor on 2018/2/9.
 */
public interface ISkuService {

    List<Sku> selectByExample(SkuExample example);

    int countByExample(SkuExample example);

    List<SkuDto> query(SkuQuery query);

    int queryCount(SkuQuery query);

    List<SkuDto> selectDtoByItemCodes(List<String> itemCodes);

    List<SkuDto> selectDtoByItemCode(String itemCode);

    List<Sku> selectByItemCodes(List<String> itemCodes);
}

