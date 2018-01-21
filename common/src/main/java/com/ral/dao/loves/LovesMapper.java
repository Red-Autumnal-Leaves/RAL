package com.ral.dao.loves;

import java.util.List;

import com.ral.model.entity.loves.Loves;
import com.ral.model.entity.loves.LovesExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LovesMapper {

    int countByExample(LovesExample example);

    int deleteByExample(LovesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Loves record);

    int insertSelective(Loves record);

    List<Loves> selectByExample(LovesExample example);

    Loves selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Loves record, @Param("example") LovesExample example);

    int updateByExample(@Param("record") Loves record, @Param("example") LovesExample example);

    int updateByPrimaryKeySelective(Loves record);

    int updateByPrimaryKey(Loves record);
}