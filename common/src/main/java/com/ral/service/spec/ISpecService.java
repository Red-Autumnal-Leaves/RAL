package com.ral.service.spec;

import com.ral.model.dto.spec.SpecDto;
import com.ral.model.entity.spec.Spec;
import com.ral.model.entity.spec.SpecExample;
import com.ral.model.query.spec.SpecQuery;

import java.util.List;

/**
 * Created by victor on 2018/2/8.
 */
public interface ISpecService {

    List<Spec> selectByExample(SpecExample example);

    int countByExample(SpecExample example);

    Spec selectById(Long specId);

    List<Spec> selectByCategoryId(Long categoryId);

    int insert(Spec spec);

    boolean insert(SpecDto spec);

    int update(Spec spec);

    boolean update(SpecDto spec);

    int delete(Long specId);

    List<SpecDto> query(SpecQuery query);

    SpecDto selectDtoById(Long specId);
}
