package com.ral.dao.item;

import java.util.List;

import com.ral.model.entity.item.EvaluateImage;
import com.ral.model.entity.item.EvaluateImageExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluateImageMapper {

    int countByExample(EvaluateImageExample example);

    int deleteByExample(EvaluateImageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EvaluateImage record);

    int insertSelective(EvaluateImage record);

    List<EvaluateImage> selectByExample(EvaluateImageExample example);

    EvaluateImage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EvaluateImage record, @Param("example") EvaluateImageExample example);

    int updateByExample(@Param("record") EvaluateImage record, @Param("example") EvaluateImageExample example);

    int updateByPrimaryKeySelective(EvaluateImage record);

    int updateByPrimaryKey(EvaluateImage record);
}