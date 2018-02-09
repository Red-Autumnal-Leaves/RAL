package com.ral.service.item.impl;

import com.ral.dao.item.ItemDetailMapper;
import com.ral.model.entity.item.ItemDetail;
import com.ral.model.entity.item.ItemDetailExample;
import com.ral.service.item.ItemDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by victor on 2018/2/9.
 */
@Service
public class ItemDetailServiceImpl implements ItemDetailService {

    @Autowired
    private ItemDetailMapper itemDetailMapper;

    @Override
    public ItemDetail getByItemCode(String itemCode) {
        ItemDetailExample example = new ItemDetailExample();
        example.createCriteria().andItemCodeEqualTo(itemCode);
        List<ItemDetail> details = itemDetailMapper.selectByExample(example);
        return details == null || details.isEmpty() ? null : details.get(0);
    }
}
