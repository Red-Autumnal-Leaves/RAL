package com.ral.service.item.impl;

import com.ral.dao.item.ItemSpecMapper;
import com.ral.model.entity.item.ItemSpec;
import com.ral.model.entity.item.ItemSpecExample;
import com.ral.service.item.ItemSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 2018/2/11.
 */
@Service
public class ItemSpecServiceImpl implements ItemSpecService {

    @Autowired
    private ItemSpecMapper itemSpecMapper;

    @Override
    public List<ItemSpec> selectByExample(ItemSpecExample example) {
        example =  example == null ? new ItemSpecExample() : example;
        List<ItemSpec> specs = itemSpecMapper.selectByExample(example);
        return specs == null ? new ArrayList<>() : specs;
    }

    @Override
    public int countByExample(ItemSpecExample example) {
        example =  example == null ? new ItemSpecExample() : example;
        return itemSpecMapper.countByExample(example);
    }

    @Override
    public ItemSpec selectByItemCodeAndSpecId(String itemCode, Long specId) {
        ItemSpecExample example = new ItemSpecExample();
        example.createCriteria().andItemCodeEqualTo(itemCode).andSpecIdEqualTo(specId);
        List<ItemSpec> specs = itemSpecMapper.selectByExample(example);
        return specs == null || specs.isEmpty() ? null : specs.get(0);
    }

    @Override
    public List<ItemSpec> selectByItemCode(String itemCode) {
        ItemSpecExample example = new ItemSpecExample();
        example.createCriteria().andItemCodeEqualTo(itemCode);
        return selectByExample(example);
    }

    @Override
    public int insertSelective(ItemSpec itemSpec) {
        return itemSpecMapper.insertSelective(itemSpec);
    }

    @Override
    public int batchInsert(List<ItemSpec> specs) {
        return 0;
    }

    @Override
    public void remove(String itemCode) {
        ItemSpecExample example = new ItemSpecExample();
        example.createCriteria().andItemCodeEqualTo(itemCode);
        itemSpecMapper.deleteByExample(example);
    }

    @Override
    public void remove(Long specId) {
        ItemSpecExample example = new ItemSpecExample();
        example.createCriteria().andSpecIdEqualTo(specId);
        itemSpecMapper.deleteByExample(example);
    }

    @Override
    public void remove(String itemCode, Long specId) {
        ItemSpecExample example = new ItemSpecExample();
        example.createCriteria().andSpecIdEqualTo(specId).andItemCodeEqualTo(itemCode);
        itemSpecMapper.deleteByExample(example);
    }

    @Override
    public void remove(List<Long> ids) {
        ItemSpecExample example = new ItemSpecExample();
        example.createCriteria().andIdIn(ids);
        itemSpecMapper.deleteByExample(example);
    }

    /**
     * 查询 item 规格被sku关联数量
     *
     * @param itemCode
     * @param specId
     * @return
     */
    @Override
    public int selectActiveByItemCodeAndSpecId(String itemCode, Long specId) {
        return 0;
    }
}
