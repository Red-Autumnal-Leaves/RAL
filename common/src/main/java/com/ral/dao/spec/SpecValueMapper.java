package com.ral.dao.spec;

import java.util.List;

import com.ral.model.entity.spec.SpecValue;
import com.ral.model.entity.spec.SpecValueExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecValueMapper {

    int countByExample(SpecValueExample example);

    int deleteByExample(SpecValueExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SpecValue record);

    int insertSelective(SpecValue record);

    List<SpecValue> selectByExample(SpecValueExample example);

    SpecValue selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SpecValue record, @Param("example") SpecValueExample example);

    int updateByExample(@Param("record") SpecValue record, @Param("example") SpecValueExample example);

    int updateByPrimaryKeySelective(SpecValue record);

    int updateByPrimaryKey(SpecValue record);
}