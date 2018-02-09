package com.ral.sms.web.controller.item;

import com.ral.model.query.item.ItemQuery;
import com.ral.model.res.Result;
import com.ral.sms.business.item.ItemBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 2018/2/8.
 */
@RestController
@RequestMapping("/item/*")
public class ItemController {

    @Autowired
    private ItemBusiness itemBusiness;

    @RequestMapping(value =  "/query",method = RequestMethod.GET)
    public Result query(HttpServletRequest request, ItemQuery query){
        return itemBusiness.query(request,query);
    }

    @RequestMapping(value =  "/detail/{itemCode}",method = RequestMethod.GET)
    public Result detail(HttpServletRequest request,@PathVariable("itemCode")String itemCode){
        return itemBusiness.detail(request,itemCode);
    }



}
