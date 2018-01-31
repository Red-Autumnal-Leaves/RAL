package com.ral.sms.web.controller.base;

import com.ral.model.res.Result;
import com.ral.sms.business.base.IImageBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 2018/1/31.
 *
 */
@RestController
@RequestMapping("/image/*")
public class ImageController {

    @Autowired
    private IImageBusiness imageBusiness;

    /**
     * 上传图片
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Result upload(HttpServletRequest request, MultipartFile[] files, @RequestParam("type") Integer type){
        return imageBusiness.upload(request,files,type);
    }


}
