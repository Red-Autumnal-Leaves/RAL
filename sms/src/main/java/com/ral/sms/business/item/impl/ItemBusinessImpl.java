package com.ral.sms.business.item.impl;

import com.ral.model.auth.res.Manager;
import com.ral.model.dto.item.ItemDto;
import com.ral.model.dto.sku.SkuDto;
import com.ral.model.dto.sku.SkuSpecDto;
import com.ral.model.entity.item.Item;
import com.ral.model.entity.item.ItemDetail;
import com.ral.model.entity.item.ItemSpec;
import com.ral.model.enums.HttpStatusEnum;
import com.ral.model.query.Query;
import com.ral.model.query.item.ItemQuery;
import com.ral.model.res.Result;
import com.ral.service.item.ItemDetailService;
import com.ral.service.item.ItemImageService;
import com.ral.service.item.ItemService;
import com.ral.service.item.ItemSpecService;
import com.ral.service.sku.ISkuService;
import com.ral.service.sku.ISkuSpecService;
import com.ral.service.spec.ISpecService;
import com.ral.sms.business.item.ItemBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by victor on 2018/2/9.
 */
@Service
public class ItemBusinessImpl implements ItemBusiness {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemImageService itemImageService;

    @Autowired
    private ItemDetailService itemDetailService;

    @Autowired
    private ISkuService skuService;

    @Autowired
    private ISkuSpecService skuSpecService;

    @Autowired
    private ItemSpecService itemSpecService;

    @Autowired
    private ISpecService specService;


    @Override
    public Result query(HttpServletRequest request, ItemQuery query) {
        query.setPageNow(query.getPageNow() == null || query.getPageNow()<= 0 ? 1 :query.getPageNow());
        query.setPageSize(query.getPageSize() == null || query.getPageSize()<= 0 ? Query.DEFAULT_PAGE_SIZE :query.getPageSize());
        List<ItemDto> dtos = itemService.query(query);
        query.setTotal(itemService.queryCount(query));
        return Result.initSuccessResult(dtos, query);
    }

    @Override
    public Result detail(HttpServletRequest request, String itemCode) {
        ItemDto item = itemService.selectDtoByItemCode(itemCode);
        if(item != null){
            //detail
            ItemDetail detail = itemDetailService.getByItemCode(itemCode);
            item.setDetail(detail != null ? detail.getContent() : null);
            //skus
            List<SkuDto> skus = skuService.selectDtoByItemCode(itemCode);
            if(skus != null && !skus.isEmpty()){
                List<String> skuCodes = skus.stream().map(v -> v.getSkuCode()).distinct().collect(Collectors.toList());
                Map<String,List<SkuSpecDto>> skuSpecMap = skuSpecService.selectDtosBySkuCodes(skuCodes);
                skus.forEach(sku -> sku.setSpecs(skuSpecMap.containsKey(sku.getSkuCode()) ? skuSpecMap.get(sku.getSkuCode()) : new ArrayList<>()));
                item.setSkus(skus);
            }
            //specs
            List<ItemSpec> itemSpecs = itemSpecService.selectByItemCode(item.getItemCode());
            List<Long> specIds = itemSpecs.stream().map(v -> v.getSpecId()).distinct().collect(Collectors.toList());
            item.setSpecs(specService.selectDtoByIds(specIds));
            //images
            item.setImages(itemImageService.selectDtoByItemCode(itemCode));
        }
        return Result.initSuccessResult(item,null);
    }


    @Override
    public Result specs(HttpServletRequest request, String itemCode) {
        List<ItemSpec> itemSpecs = itemSpecService.selectByItemCode(itemCode);
        List<Long> specIds = itemSpecs.stream().map(v -> v.getSpecId()).distinct().collect(Collectors.toList());
        return Result.initSuccessResult(specService.selectDtoByIds(specIds),null);
    }

    @Override
    public Result skus(HttpServletRequest request, String itemCode){
        List<SkuDto> skus = skuService.selectDtoByItemCode(itemCode);
        if(skus != null && !skus.isEmpty()){
            List<String> skuCodes = skus.stream().map(v -> v.getSkuCode()).distinct().collect(Collectors.toList());
            Map<String,List<SkuSpecDto>> skuSpecMap = skuSpecService.selectDtosBySkuCodes(skuCodes);
            skus.forEach(sku -> sku.setSpecs(skuSpecMap.containsKey(sku.getSkuCode()) ? skuSpecMap.get(sku.getSkuCode()) : new ArrayList<>()));
        }
        return Result.initSuccessResult(skus,null);
    }





    @Override
    public Result removeSpec(HttpServletRequest request, String itemCode, Long specId) {
        Item item = itemService.selectByItemCode(itemCode);
        if(item == null){
            return Result.initErrorResult(HttpStatusEnum.BAD_REQUEST,"The 'itemCode' " + itemCode + " is not exits");
        }
        int count = itemSpecService.selectActiveByItemCodeAndSpecId(itemCode,specId);
        if(count > 0){
            return Result.initErrorResult(HttpStatusEnum.BAD_REQUEST,"The spec is already used");
        }
        itemSpecService.remove(itemCode,specId);
        return Result.initSuccessResult(null,null);
    }

    @Override
    public Result save(HttpServletRequest request, String body, Manager manager) {
        return null;
    }

    @Override
    public Result update(HttpServletRequest request, String itemCode, String body, Manager manager) {
        return null;
    }



}
