package com.ral.dao.catalog;

import com.ral.model.dto.catalog.CatalogDto;
import com.ral.model.entity.catalog.Catalog;
import com.ral.model.entity.catalog.CatalogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogMapper {

    int countByExample(CatalogExample example);

    int deleteByExample(CatalogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Catalog record);

    int insertSelective(Catalog record);

    List<Catalog> selectByExample(CatalogExample example);

    Catalog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Catalog record, @Param("example") CatalogExample example);

    int updateByExample(@Param("record") Catalog record, @Param("example") CatalogExample example);

    int updateByPrimaryKeySelective(Catalog record);

    int updateByPrimaryKey(Catalog record);


}