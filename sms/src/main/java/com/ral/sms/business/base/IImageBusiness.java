package com.ral.sms.business.base;

import com.ral.model.res.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 2018/1/31.
 */
public interface IImageBusiness {


    /**
     * 上传
     * @param request
     * @param files
     * @return
     */
    Result upload(HttpServletRequest request, MultipartFile[] files, Integer type);
}
