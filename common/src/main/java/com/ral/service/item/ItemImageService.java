package com.ral.service.item;

import com.ral.model.dto.item.ItemImageDto;
import com.ral.model.entity.item.ItemImage;
import com.ral.model.entity.item.ItemImageExample;

import java.util.List;

/**
 * Created by victor on 2018/2/9.
 */
public interface ItemImageService {

    List<ItemImage> selectByExample(ItemImageExample example);

    int countByExample(ItemImageExample example);

    int batchInsert(List<ItemImage> images);

    int delete(List<Long> ids);

    List<ItemImageDto> selectDtosByItemCodes(List<String> itemCodes);

    List<ItemImageDto> selectDtoByItemCode(String itemCode);

}
