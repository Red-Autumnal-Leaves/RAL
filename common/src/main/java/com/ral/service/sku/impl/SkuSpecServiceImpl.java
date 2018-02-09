package com.ral.service.sku.impl;

import com.ral.dao.sku.SkuSpecMapper;
import com.ral.dao.spec.SpecMapper;
import com.ral.dao.spec.SpecValueMapper;
import com.ral.model.dto.sku.SkuSpecDto;
import com.ral.model.entity.sku.SkuSpec;
import com.ral.model.entity.sku.SkuSpecExample;
import com.ral.model.entity.spec.Spec;
import com.ral.model.entity.spec.SpecExample;
import com.ral.model.entity.spec.SpecValue;
import com.ral.model.entity.spec.SpecValueExample;
import com.ral.service.sku.ISkuSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

/**
 * Created by victor on 2018/2/9.
 */
@Service
public class SkuSpecServiceImpl  implements ISkuSpecService{

    @Autowired
    private SpecMapper specMapper;

    @Autowired
    private SpecValueMapper specValueMapper;

    @Autowired
    private SkuSpecMapper skuSpecMapper;


    @Override
    public  Map<String,List<SkuSpecDto>> selectDtosBySkuCodes(List<String> skuCodes) {
        Map<String,List<SkuSpecDto>> map = new HashMap<>();
        List<SkuSpecDto> dtos = new ArrayList<>();
        if(skuCodes != null && !skuCodes.isEmpty()){
            List<SkuSpec> skuSpecs = selectBySkuCodes(skuCodes);
            Map<String,List<SkuSpec>> skuSpecMap = skuSpecs.stream().collect(Collectors.groupingBy(ss -> ss.getSkuCode()));
            List<Long> valueIds =  skuSpecs.stream().map(v -> v.getSpecValueId()).distinct().collect(Collectors.toList());
            List<SpecValue> values = selectSpecValesByIds(valueIds);
            Map<Long,SpecValue> valueMap = values.stream().collect(Collectors.toMap(SpecValue::getId, value -> value));
            List<Long> specIds  = values.stream().map(v -> v.getSpecId()).distinct().collect(Collectors.toList());
            List<Spec> specs  = selectSpecByIds(specIds);
            Map<Long,Spec> specMap = specs.stream().collect(Collectors.toMap(Spec::getId, value -> value));
            if(skuSpecMap != null && !skuSpecMap.keySet().isEmpty()){
                skuSpecMap.keySet().forEach(skuCode ->{
                    List<SkuSpec> ss = skuSpecMap.get(skuCode);
                    if(ss != null && !ss.isEmpty()){
                        ss.forEach(s->{
                            SkuSpecDto dto = new SkuSpecDto();
                            dto.setId(s.getId());
                            dto.setSkuCode(skuCode);
                            dto.setSpecValueId(s.getSpecValueId());
                            if(valueMap.containsKey(s.getSpecValueId())){
                                SpecValue value = valueMap.get(s.getSpecValueId());
                                dto.setValue(value);
                                dto.setSpecId(value.getSpecId());
                                if(specMap.containsKey(value.getSpecId())){
                                    dto.setSpec(specMap.get(value.getSpecId()));
                                }
                            }
                            dtos.add(dto);
                        });
                    }
                });
                map = dtos.stream().collect(Collectors.groupingBy(ssd -> ssd.getSkuCode()));
            }
        }
        return map;
    }

    @Override
    public List<SkuSpec> selectBySkuCodes(List<String> skuCodes) {
        List<SkuSpec> skuSpecs = new ArrayList<>();
        if(skuCodes != null && !skuCodes.isEmpty()){
            SkuSpecExample example = new SkuSpecExample();
            example.createCriteria().andSkuCodeIn(skuCodes);
            skuSpecs = skuSpecMapper.selectByExample(example);
        }
        return skuSpecs == null ? new ArrayList<>() : skuSpecs;
    }

    private List<SpecValue> selectSpecValesByIds(List<Long> valueIds){
        List<SpecValue> values = new ArrayList<>();
        if(valueIds != null && !valueIds.isEmpty()){
            SpecValueExample example = new SpecValueExample();
            example.createCriteria().andIdIn(valueIds);
            values = specValueMapper.selectByExample(example);
        }
        return values == null ? new ArrayList<>() : values;
    }

    private List<Spec> selectSpecByIds(List<Long> specIds){
        List<Spec> values = new ArrayList<>();
        if(specIds != null && !specIds.isEmpty()){
            SpecExample example = new SpecExample();
            example.createCriteria().andIdIn(specIds);
            values = specMapper.selectByExample(example);
        }
        return values == null ? new ArrayList<>() : values;
    }

}
