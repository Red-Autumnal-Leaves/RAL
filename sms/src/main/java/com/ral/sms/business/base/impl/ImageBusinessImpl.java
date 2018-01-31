package com.ral.sms.business.base.impl;

import com.ral.exception.ServiceException;
import com.ral.model.dto.base.ImageDto;
import com.ral.model.entity.base.Image;
import com.ral.model.enums.HttpStatusEnum;
import com.ral.model.enums.base.ImageTypeEnum;
import com.ral.model.res.Result;
import com.ral.service.base.IImageService;
import com.ral.sms.business.base.IImageBusiness;
import com.victor.sdk.domain.VictorClient;
import com.victor.sdk.request.vos.UploadMultipartFileRequest;
import com.victor.sdk.response.vos.ServerFileInfo;
import com.victor.sdk.response.vos.UploadMultipartFileResponse;
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
    private VictorClient victorClient;

    @Override
    public Result upload(HttpServletRequest request, MultipartFile[] files,Integer type) {
        Result result  = this.checkUploadParams(files, type);
        if(!result.isSuccess()){
            return result;
        }
        ImageTypeEnum ite = ImageTypeEnum.indexOf(type);
        try {
            List<Image> images = imageService.save(upload(files, ite));
            return Result.initSuccessResult(convertToDto(images), null);
        } catch (Exception e) {
            logger.error("Upload file error :", e);
            e.printStackTrace();
            return Result.initErrorResult("Upload file error!");
        }
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

    //上传
    private List<Image> upload(MultipartFile[] files,ImageTypeEnum type) throws Exception{
        UploadMultipartFileRequest request = new UploadMultipartFileRequest(victorClient);
        request.setTimestamp(new Date().getTime() / 1000 + "");
        for(MultipartFile file : files ){
            if(file == null || file.getSize() <=0){
                continue;
            }
            request.getFiles().add(file);
        }
        UploadMultipartFileResponse response = victorClient.request(request, UploadMultipartFileResponse.class);
        List<Image> images = new ArrayList<>();
        if(response.isSuccess()){
            for(ServerFileInfo info : response.getResponse()){
                Image image = new Image();
                image.setType(type.getIndex());
                image.setFileId(info.getId() + "");
                image.setName(info.getFileName());
                image.setSize(info.getSize());
                image.setUrl(info.getUrl());
                images.add(image);
            }
        }else{
            logger.error("Upload file to server error :" + response.getMsg());
            throw new ServiceException("Upload file to server error :" + response.getMsg());
        }
        return images;
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

}
