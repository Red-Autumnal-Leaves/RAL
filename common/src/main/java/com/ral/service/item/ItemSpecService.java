package com.ral.service.item;

import com.ral.model.entity.item.ItemSpec;
import com.ral.model.entity.item.ItemSpecExample;

import java.util.List;

/**
 * Created by victor on 2018/2/11.
 */
public interface ItemSpecService {

    List<ItemSpec> selectByExample(ItemSpecExample example);

    int countByExample(ItemSpecExample example);

    ItemSpec selectByItemCodeAndSpecId(String itemCode,Long specId);

    List<ItemSpec> selectByItemCode(String itemCode);

    int insertSelective(ItemSpec itemSpec);

    int batchInsert(List<ItemSpec> specs);

    void remove(String itemCode);

    void remove(Long specId);

    void remove(String itemCode,Long specId);

    void remove(List<Long> ids);

    /**
     * 查询 item 规格被sku关联数量
     * @param itemCode
     * @param specId
     * @return
     */
    int selectActiveByItemCodeAndSpecId(String itemCode, Long specId);
}
