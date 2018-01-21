package com.ral.dao.item;

import java.util.List;

import com.ral.model.entity.item.ItemDetail;
import com.ral.model.entity.item.ItemDetailExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDetailMapper {

    int countByExample(ItemDetailExample example);

    int deleteByExample(ItemDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ItemDetail record);

    int insertSelective(ItemDetail record);

    List<ItemDetail> selectByExampleWithBLOBs(ItemDetailExample example);

    List<ItemDetail> selectByExample(ItemDetailExample example);

    ItemDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ItemDetail record, @Param("example") ItemDetailExample example);

    int updateByExampleWithBLOBs(@Param("record") ItemDetail record, @Param("example") ItemDetailExample example);

    int updateByExample(@Param("record") ItemDetail record, @Param("example") ItemDetailExample example);

    int updateByPrimaryKeySelective(ItemDetail record);

    int updateByPrimaryKeyWithBLOBs(ItemDetail record);

    int updateByPrimaryKey(ItemDetail record);
}