package com.ral.service.catalog;

import com.ral.model.dto.catalog.CatalogDto;
import com.ral.model.entity.catalog.Catalog;
import com.ral.model.entity.catalog.CatalogExample;

import java.util.List;
/**
 * Created by victor on 2018/1/26.
 */
public interface ICatalogService {

    List<Catalog> selectByExample(CatalogExample example);

    List<CatalogDto> getAllTree();

    List<CatalogDto> getAllDto();

    CatalogDto detail(Long catalogId);

    int delete(Long catalogId);

    int update(Catalog catalog);


}
