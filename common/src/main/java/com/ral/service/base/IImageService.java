package com.ral.service.base;

import com.ral.model.entity.base.Image;
import java.util.List;

/**
 * Created by victor on 2018/1/31.
 */
public interface IImageService {

    /**
     * 存储多条记录
     * @param images
     * @return
     */
    List<Image> save(List<Image> images);

    /**
     * 新增
     * @param image
     * @return
     */
    Image save(Image image);

    /**
     * 修改 ，只修改非空字段
     * @param image
     * @return
     */
    boolean update(Image image);


    /**
     * 根据Id查询
     * @param imageId
     * @return
     */
    Image queryById(Long imageId);


    /**
     * 批量查询
     * @param ids
     * @return
     */
    List<Image> getByIds(List<Long> ids);

    /**
     * 根据fileId 查询
     * @param fileIds
     * @return
     */
    List<Image> getByFileIds(List<String> fileIds);

}
