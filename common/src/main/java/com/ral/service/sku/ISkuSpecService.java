package com.ral.service.sku;

import com.ral.model.dto.sku.SkuSpecDto;
import com.ral.model.entity.sku.SkuSpec;

import java.util.List;
import java.util.Map;

/**
 * Created by victor on 2018/2/9.
 */
public interface ISkuSpecService {

    List<SkuSpec> selectBySkuCodes(List<String> skuCodes);

    Map<String,List<SkuSpecDto>> selectDtosBySkuCodes(List<String> skuCodes);

}
