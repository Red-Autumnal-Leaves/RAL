package com.ral.service.brand;

import com.ral.model.dto.brand.BrandDto;
import com.ral.model.entity.brand.Brand;
import com.ral.model.entity.brand.BrandExample;
import com.ral.model.query.brand.BrandQuery;

import java.util.List;

/**
 * Created by victor on 2018/2/8.
 */
public interface IBrandService {

    List<Brand> selectByExample(BrandExample example);

    int countByExample(BrandExample example);

    int insert(Brand brand);

    int insertSelective(Brand brand);

    int update(Brand brand);

    int updateSelective(Brand brand);

    BrandDto selectDtoById(Long brandId);

    Brand selectById(Long brandId);

    List<BrandDto> query(BrandQuery query);

    int queryCount(BrandQuery query);


}
