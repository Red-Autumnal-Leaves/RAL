package com.ral.service.spec.impl;

import com.google.common.collect.Lists;
import com.ral.dao.spec.SpecMapper;
import com.ral.dao.spec.SpecValueMapper;
import com.ral.model.dto.spec.SpecDto;
import com.ral.model.entity.spec.Spec;
import com.ral.model.entity.spec.SpecExample;
import com.ral.model.entity.spec.SpecValue;
import com.ral.model.entity.spec.SpecValueExample;
import com.ral.model.query.spec.SpecQuery;
import com.ral.service.spec.ISpecService;
import com.ral.util.codec.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by victor on 2018/2/8.
 */
@Service
public class SpecServiceImpl implements ISpecService{

    @Autowired
    private SpecMapper specMapper;

    @Autowired
    private SpecValueMapper specValueMapper;

    @Override
    public List<Spec> selectByExample(SpecExample example) {
        example = example == null ? new SpecExample() : example;
        List<Spec> specs = specMapper.selectByExample(example);
        return specs == null ? new ArrayList<>() : specs;
    }

    @Override
    public int countByExample(SpecExample example) {
        example = example == null ? new SpecExample() : example;
        return specMapper.countByExample(example);
    }

    @Override
    public Spec selectById(Long specId) {
        return specMapper.selectByPrimaryKey(specId);
    }

    @Override
    public List<Spec> selectByCategoryId(Long categoryId) {
        SpecExample example = new SpecExample();
        example.createCriteria().andCategoryIdEqualTo(categoryId);
        List<Spec> specs = selectByExample(example);
        return specs == null ? new ArrayList<>() : specs;
    }

    @Override
    public List<SpecDto> selectDtoByCategoryId(Long categoryId) {
        List<Spec> specs = selectByCategoryId(categoryId);
        return convertToDto(specs);
    }

    @Override
    public List<SpecDto> selectDtoByIds(List<Long> specIds) {
        SpecExample example = new SpecExample();
        example.createCriteria().andIdIn(specIds);
        List<Spec> specs = selectByExample(example);
        return convertToDto(specs);
    }

    @Override
    public int insert(Spec spec) {
        return specMapper.insertSelective(spec);
    }

    @Transactional
    @Override
    public boolean insert(SpecDto dto) {
        Spec spec = new Spec();
        BeanUtils.copyProperties(dto,spec);
        specMapper.insert(spec);
        if(dto.getValues() != null && !dto.getValues().isEmpty()){
            List<SpecValue> values = new ArrayList<>();
            for(SpecValue value : dto.getValues()){
                if(!StringUtils.isNullOrEmpty(value.getValue())){
                    value.setSpecId(spec.getId());
                    values.add(value);
                }
            }
            if(!values.isEmpty()){
                specValueMapper.batchInsert(values);
            }
        }
        return true;
    }

    @Override
    public int update(Spec spec) {
        return specMapper.updateByPrimaryKey(spec);
    }

    @Transactional
    @Override
    public boolean update(SpecDto dto) {
        Spec spec = selectById(dto.getId());
        if(spec != null){
            BeanUtils.copyProperties(dto,spec);
            update(spec);
            if(dto.getValues() != null && !dto.getValues().isEmpty()){
                List<SpecValue> creates = new ArrayList<>();
                List<SpecValue> updates = new ArrayList<>();
                List<SpecValue> values = getValuesBySpecId(spec.getId());
                List<Long> removeIds = values.stream().map(v -> v.getId()).distinct().collect(Collectors.toList());
                dto.getValues().forEach(specValue -> {
                    specValue.setSpecId(spec.getId());
                    if(specValue.getId() == null || spec.getId() == 0){
                        creates.add(specValue);
                    }else{
                        if(removeIds.contains(specValue.getId())){
                            removeIds.remove(specValue.getId());
                        }
                        updates.add(specValue);
                    }
                });
                if(!creates.isEmpty()){
                    specValueMapper.batchInsert(creates);
                }
                if(!updates.isEmpty()){
                    specValueMapper.batchUpdate(updates);
                }
                if(removeIds != null && !removeIds.isEmpty()){
                    removeSpecValue(removeIds);
                }
            }
        }
        return true;
    }

    @Override
    public int delete(Long specId) {
        int res = specMapper.deleteByPrimaryKey(specId);
        SpecValueExample example = new SpecValueExample();
        example.createCriteria().andSpecIdEqualTo(specId);
        res += specValueMapper.deleteByExample(example);
        return res;
    }

    @Override
    public List<SpecDto> query(SpecQuery query) {
        List<Spec> specs = specMapper.query(query);
        return convertToDto(specs);
    }

    @Override
    public int queryCount(SpecQuery query) {
        return specMapper.queryCount(query);
    }

    @Override
    public SpecDto selectDtoById(Long specId) {
        return convertToDto(selectById(specId));
    }

    @Override
    public int removeValue(Long specId, Long valueId) {
        return specValueMapper.deleteByPrimaryKey(valueId);
    }

    private Map<Long,List<SpecValue>> getValuesGroupBySpecs(List<Long> specIds){
        if(specIds == null || specIds.isEmpty()){
            return new HashMap<>();
        }
        SpecValueExample example = new SpecValueExample();
        example.createCriteria().andSpecIdIn(specIds);
        List<SpecValue> values = specValueMapper.selectByExample(example);
        if(values == null || values.isEmpty()){
            return new HashMap<>();
        }
        Map<Long,List<SpecValue>> specValueMap = values.stream().collect(Collectors.groupingBy(p -> p.getSpecId()));
        return specValueMap == null ? new HashMap<>() : specValueMap;
    }

    private List<SpecValue> getValuesBySpecId(Long specId){
        Map<Long,List<SpecValue>> map = getValuesGroupBySpecs(Lists.newArrayList(specId));
        if(map == null || map.get(specId) == null || map.get(specId).isEmpty()){
            return new ArrayList<>();
        }
        return map.get(specId);
    }

    private List<SpecDto> convertToDto(List<Spec> specs){
        List<SpecDto> dtos = new ArrayList<>();
        if(!specs.isEmpty()){
            List<Long> specIds = specs.stream().map(v -> v.getId()).distinct().collect(Collectors.toList());
            Map<Long,List<SpecValue>> valueMap = getValuesGroupBySpecs(specIds);
            specs.forEach(spec -> {
                SpecDto dto = new SpecDto();
                BeanUtils.copyProperties(spec,dto);
                dto.setValues(valueMap.get(spec.getId()));
                dtos.add(dto);
            });
        }
        return dtos;
    }

    private SpecDto convertToDto(Spec spec){
        if(spec == null) {return null;}
        List<SpecDto> dtos = convertToDto(Lists.newArrayList(spec));
        if(!dtos.isEmpty()){
            return dtos.get(0);
        }
        return null;
    }

    private void removeSpecValue(List<Long> valueIds){
        SpecValueExample example = new SpecValueExample();
        example.createCriteria().andIdIn(valueIds);
        specValueMapper.deleteByExample(example);
    }

}
