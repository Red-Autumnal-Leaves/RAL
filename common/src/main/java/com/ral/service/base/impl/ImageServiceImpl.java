package com.ral.service.base.impl;

import com.ral.dao.base.ImageMapper;
import com.ral.model.entity.base.Image;
import com.ral.model.entity.base.ImageExample;
import com.ral.service.base.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by victor on 2018/1/31.
 */
@Service
public class ImageServiceImpl implements IImageService {

    @Autowired
    private ImageMapper imageMapper;

    /**
     * 存储多条记录
     *
     * @param images
     * @return
     */
    @Override
    public List<Image> save(List<Image> images) {
        for(Image image : images){
            imageMapper.insertSelective(image);
        }
        return images;
    }

    /**
     * 新增
     *
     * @param image
     * @return
     */
    @Override
    public Image save(Image image) {
        if(imageMapper.insertSelective(image) > 0){
            return image;
        }
        return null;
    }

    /**
     * 修改 ，只修改非空字段
     *
     * @param image
     * @return
     */
    @Override
    public boolean update(Image image) {
        return imageMapper.updateByPrimaryKeySelective(image) > 0;
    }


    /**
     * 根据Id查询
     *
     * @param imageId
     * @return
     */
    @Override
    public Image queryById(Long imageId) {
        return imageMapper.selectByPrimaryKey(imageId);
    }

    /**
     * 批量查询
     *
     * @param ids
     * @return
     */
    @Override
    public List<Image> getByIds(List<Long> ids) {
        ImageExample example = new ImageExample();
        example.createCriteria().andIdIn(ids);
        List<Image> images = imageMapper.selectByExample(example);
        return images == null ? new ArrayList<>() : images;
    }
}
