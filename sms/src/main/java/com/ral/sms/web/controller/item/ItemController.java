package com.ral.sms.web.controller.item;

import com.ral.model.query.item.ItemQuery;
import com.ral.model.res.Result;
import com.ral.sms.business.item.ItemBusiness;
import com.ral.sms.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 2018/2/8.
 */
@RestController
@RequestMapping("/item/*")
public class ItemController extends BaseController{

    @Autowired
    private ItemBusiness itemBusiness;

    /**
     * 分页查询
     * @param request
     * @param query
     * @return
     */
    @RequestMapping(value =  "/query",method = RequestMethod.GET)
    public Result query(HttpServletRequest request, ItemQuery query){
        return itemBusiness.query(request,query);
    }

    /**
     * 查询详情
     * @param request
     * @param itemCode
     * @return
     */
    @RequestMapping(value =  "/detail/{itemCode}",method = RequestMethod.GET)
    public Result detail(HttpServletRequest request,@PathVariable("itemCode")String itemCode){
        return itemBusiness.detail(request,itemCode);
    }


    /**
     * 创建保存
     * @param request
     * @param body
     * @return
     */
    @RequestMapping(value =  "/save",method = RequestMethod.POST)
    public Result save(HttpServletRequest request,@RequestBody  String body){
        return itemBusiness.save(request,body,getUser());
    }

    /**
     * 更新商品
     * @param request
     * @param itemCode
     * @param body
     * @return
     */
    @RequestMapping(value =  "/update/{itemCode}",method = RequestMethod.PUT)
    public Result update(HttpServletRequest request ,@PathVariable("itemCode") String itemCode,  @RequestBody String body){
        return itemBusiness.update(request,itemCode,body,getUser());
    }



    /**
     * 查询商品的规格
     * @param request
     * @param itemCode
     * @return
     */
    @RequestMapping(value =  "/spec/{itemCode}",method = RequestMethod.GET)
    public Result specs(HttpServletRequest request,@PathVariable("itemCode")String itemCode){
        return itemBusiness.specs(request,itemCode);
    }

    /**
     * 查询SKU
     * @param request
     * @param itemCode
     * @return
     */
    @RequestMapping("/sku/{itemCode}")
    public Result sku(HttpServletRequest request,@PathVariable("itemCode")String itemCode){
        return itemBusiness.skus(request,itemCode);
    }



    /**
     * 移除商品规格关联
     * @param request
     * @param itemCode
     * @return
     */
    @RequestMapping(value = "/remove/{itemCode}/spec/{specId}",method = RequestMethod.DELETE)
    public Result removeSpec(HttpServletRequest request,@PathVariable("itemCode")String itemCode,@PathVariable("specId")Long specId){
        return itemBusiness.removeSpec(request,itemCode,specId);
    }


    /**
     * 移除商品规格关联
     * @param request
     * @param itemCode
     * @param specId
     * @return
     */
    @RequestMapping(value = "/add/{itemCode}/spec/{specId}",method = RequestMethod.DELETE)
    public Result addSpec(HttpServletRequest request,@PathVariable("itemCode")String itemCode,@PathVariable("specId")Long specId){
        return itemBusiness.addSpec(request,itemCode,specId);
    }


}
