package com.ral.sms.business.item.impl;

import com.ral.model.auth.res.Manager;
import com.ral.model.dto.brand.BrandDto;
import com.ral.model.dto.item.ItemDto;
import com.ral.model.dto.item.ItemImageDto;
import com.ral.model.dto.sku.SkuDto;
import com.ral.model.dto.sku.SkuSpecDto;
import com.ral.model.entity.item.ItemDetail;
import com.ral.model.entity.item.ItemImage;
import com.ral.model.query.Query;
import com.ral.model.query.item.ItemQuery;
import com.ral.model.res.Result;
import com.ral.service.item.ItemDetailService;
import com.ral.service.item.ItemImageService;
import com.ral.service.item.ItemService;
import com.ral.service.sku.ISkuService;
import com.ral.service.sku.ISkuSpecService;
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
        ItemDetail detail = itemDetailService.getByItemCode(itemCode);
        item.setDetail(detail != null ? detail.getContent() : null);//detail
        List<SkuDto> skus = skuService.selectDtoByItemCode(itemCode);
        if(skus != null && !skus.isEmpty()){
            List<String> skuCodes = skus.stream().map(v -> v.getSkuCode()).distinct().collect(Collectors.toList());
            Map<String,List<SkuSpecDto>> skuSpecMap = skuSpecService.selectDtosBySkuCodes(skuCodes);
            skus.forEach(sku -> sku.setSpecs(skuSpecMap.containsKey(sku.getSkuCode()) ? skuSpecMap.get(sku.getSkuCode()) : new ArrayList<>()));
            item.setSkus(skus);//skus
        }
        item.setImages(itemImageService.selectDtoByItemCode(itemCode));//images
        return Result.initSuccessResult(item,null);
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
