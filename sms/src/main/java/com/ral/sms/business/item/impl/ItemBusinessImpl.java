package com.ral.sms.business.item.impl;

import com.google.common.collect.Lists;
import com.ral.exception.ParamsException;
import com.ral.model.auth.res.Manager;
import com.ral.model.dto.item.ItemDto;
import com.ral.model.dto.sku.SkuDto;
import com.ral.model.dto.sku.SkuSpecDto;
import com.ral.model.entity.item.Item;
import com.ral.model.entity.item.ItemDetail;
import com.ral.model.entity.item.ItemSpec;
import com.ral.model.entity.sku.Sku;
import com.ral.model.enums.HttpStatusEnum;
import com.ral.model.enums.id.IdTypeEnums;
import com.ral.model.query.Query;
import com.ral.model.query.item.ItemQuery;
import com.ral.model.res.Result;
import com.ral.service.id.IdGeneraterService;
import com.ral.service.item.ItemDetailService;
import com.ral.service.item.ItemImageService;
import com.ral.service.item.ItemService;
import com.ral.service.item.ItemSpecService;
import com.ral.service.sku.ISkuService;
import com.ral.service.sku.ISkuSpecService;
import com.ral.service.spec.ISpecService;
import com.ral.sms.business.item.ItemBusiness;
import com.ral.util.codec.JSONUtils;
import com.ral.util.codec.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private IdGeneraterService idGeneraterService;



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
    public Result addSpec(HttpServletRequest request, String itemCode, Long specId){
        Item item = itemService.selectByItemCode(itemCode);
        if(item == null){
            return Result.initErrorResult(HttpStatusEnum.BAD_REQUEST,"The 'itemCode' " + itemCode + " is not exits");
        }
        ItemSpec itemSpec = itemSpecService.selectByItemCodeAndSpecId(itemCode,specId);
        if(itemSpec != null){
            return Result.initErrorResult(HttpStatusEnum.BAD_REQUEST,"The spec is already used");
        }
        List<Sku> skus = skuService.selectByItemCodes(Lists.newArrayList());
        if(skus != null && skus.isEmpty()){
            return Result.initErrorResult(HttpStatusEnum.BAD_REQUEST,"It has been applied to SKU");
        }
        itemSpec = new ItemSpec();
        itemSpec.setSpecId(specId);
        itemSpec.setItemCode(itemCode);
        itemSpecService.batchInsert(Lists.newArrayList(itemSpec));
        return Result.initSuccessResult(null,null);
    }

    @Override
    public Result save(HttpServletRequest request, String body, Manager manager) {
        checkRequestBody(request,body);
        Item item = JSONUtils.toBean(body,Item.class);
        String itemCode = idGeneraterService.generate(IdTypeEnums.ITEM);
        item.setItemCode(itemCode);
        item.setIsDisable(false);
        item.setCreateUser(manager.getUserName());
        if(itemService.insertSelective(item) > 0){
            return detail(request,itemCode);
        }
        return Result.initErrorResult("System error!");
    }

    @Override
    public Result update(HttpServletRequest request, String itemCode, String body, Manager manager) {
        checkRequestBody(request,body);
        Item entity = itemService.selectByItemCode(itemCode);
        if(entity == null){
            return Result.initErrorResult(HttpStatusEnum.BAD_REQUEST,"The 'itemCode' " + itemCode + " is not exits");
        }
        Item item = JSONUtils.toBean(body,Item.class);
        {
            item.setItemCode(itemCode);
            item.setCategoryId(entity.getCategoryId());
            item.setIsDisable(false);
            item.setLastUpdateUser(manager.getUserName());
            item.setId(entity.getId());
            item.setSellerNote(StringUtils.isNullOrEmpty(item.getSellerNote()) ? "" : item.getSellerNote());
            item.setCreateTime(entity.getCreateTime());
            item.setCreateUser(entity.getCreateUser());
            item.setCreateUser(item.getCreateUser());
            item.setLastUpdateTime(new Date());
        }
        if(itemService.update(item) > 0){
            return detail(request,itemCode);
        }
        return Result.initErrorResult("System error!");
    }


    //校验参数
    private void checkRequestBody(HttpServletRequest request,String body){
        if(StringUtils.isNullOrEmpty(body)){
            throw  new ParamsException("The request body cannot be empty");
        }
        if(!StringUtils.isJson(body)){
            throw new ParamsException("Incorrectly formatting of the request body.");
        }
        Item item = JSONUtils.toBean(body,Item.class);
        if(StringUtils.isNullOrEmpty(item.getName())){
            throw new ParamsException("The 'name' cannot be empty");
        }
        if(StringUtils.isNullOrEmpty(item.getShortTitle())){
            throw new ParamsException("The 'shortTitle' cannot be empty");
        }
        if(item.getCatalogId() == null || item.getCatalogId() == 0){
            throw new ParamsException("The 'catalogId' cannot be empty");
        }
        if(item.getCategoryId() == null || item.getCategoryId() == 0){
            throw new ParamsException("The 'categoryId' cannot be empty");
        }
        if(item.getPrice() == null || item.getPrice().doubleValue() <= 0){
            throw new ParamsException("The 'price' cannot be empty");
        }
        if(item.getIsVirtual() == null){
            throw new ParamsException("The 'is_virtual' cannot be empty");
        }
    }


}
