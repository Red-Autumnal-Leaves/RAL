package com.ral.service.item;

import com.ral.model.entity.item.ItemDetail;

/**
 * Created by victor on 2018/2/9.
 */
public interface ItemDetailService {

    ItemDetail getByItemCode(String itemCode);
}
