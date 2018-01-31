package com.ral.sms.business.base.impl;

import com.aliyun.oss.OSSException;
import com.ral.model.domain.OssUploadResponse;
import com.ral.model.dto.base.ImageDto;
import com.ral.model.entity.base.Image;
import com.ral.model.enums.HttpStatusEnum;
import com.ral.model.enums.base.ImageTypeEnum;
import com.ral.model.res.Result;
import com.ral.service.base.IImageService;
import com.ral.service.file.IFileStoreService;
import com.ral.sms.business.base.IImageBusiness;
import com.ral.util.codec.JSONUtils;
import com.ral.util.codec.UUIDUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by victor on 2018/1/31.
 */
@Service
public class ImageBusinessImpl implements IImageBusiness {

    private static Logger logger = Logger.getLogger(ImageBusinessImpl.class);

    @Autowired
    private IImageService imageService;

    @Autowired
    private IFileStoreService fileStoreService;

    /**
     * 上传
     *
     * @param request
     * @param files
     * @param type
     * @return
     */
    @Override
    public Result upload(HttpServletRequest request, MultipartFile[] files, Integer type) {
        Result result  = this.checkUploadParams(files, type);
        if(!result.isSuccess()){
            return result;
        }
        ImageTypeEnum ite = ImageTypeEnum.indexOf(type);
        try {
            List<Image> images = this.upload(files, ite);
            return Result.initSuccessResult(convertToDto(images), null);
        } catch (Exception e) {
            logger.error("Upload file error :", e);
            e.printStackTrace();
            return Result.initErrorResult("Upload file error!");
        }
    }



    //上传
    private List<Image> upload(MultipartFile[] files,ImageTypeEnum type) throws Exception{
        List<Image> images = new ArrayList<>();
        for(MultipartFile file : files){
            Image image = new Image();
            image.setName(file.getOriginalFilename());
            image.setSize(file.getSize() + "");
            OssUploadResponse response = fileStoreService.upload(file.getBytes(),UUIDUtils.uuid());
            if(response.isSuccess()){
                image.setUrl(response.getUrl());
                images.add(image);
            }else{
                logger.error("upload file error: "  + JSONUtils.toJson(image));
                throw new OSSException("upload file error!");
            }
        }
        if(!images.isEmpty()){
            return imageService.save(images);
        }
        return null;
    }


    private ImageDto convertToDto(Image image){
        if(image == null){
            return null;
        }
        ImageDto dto = new ImageDto();
        BeanUtils.copyProperties(image,dto);
        return dto;
    }

    private List<ImageDto> convertToDto(List<Image> images){
        List<ImageDto> dtos = new ArrayList<>();
        if(images != null && !images.isEmpty()){
            images.forEach(image -> {
                ImageDto dto = convertToDto(image);
                if(dto != null){
                    dtos.add(dto);
                }
            });
        }
        return dtos;
    }


    //校验
    private Result checkUploadParams(MultipartFile[] files,Integer type){
        if(type == null || type == 0){
            return Result.initErrorResult(HttpStatusEnum.BAD_REQUEST, "Params error : type value is invalid!");
        }
        ImageTypeEnum ite = ImageTypeEnum.indexOf(type);
        if(ite == null){
            return Result.initErrorResult(HttpStatusEnum.BAD_REQUEST, "Params error : type value is invalid!");
        }
        if(files.length  == 0 ){
            return Result.initErrorResult(HttpStatusEnum.BAD_REQUEST, "Params error : files is empty!");
        }
        return Result.initSuccessResult(null, null);
    }

}
