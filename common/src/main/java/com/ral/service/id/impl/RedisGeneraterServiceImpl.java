package com.ral.service.id.impl;

import com.ral.constants.redis.RedisKeyConstants;
import com.ral.model.enums.id.IdTypeEnums;
import com.ral.service.id.IdGeneraterService;
import com.ral.service.redis.IRedisService;
import com.ral.util.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author victor
 * @desc
 * @date 2018/1/6 18:53
 */
@Service("redisGeneraterService")
public class RedisGeneraterServiceImpl implements IdGeneraterService {

    private static final int KEY_EXPIRE = 60 * 60 * 24 * 2;//2 days

    @Autowired
    private IRedisService redisService;

    @Override
    public String generate() {
        return generate(IdTypeEnums.DEFAULT);
    }

    @Override
    public List<String> generate(int count) {
        return generate(IdTypeEnums.DEFAULT,count);
    }

    @Override
    public String generate(IdTypeEnums type) {
        List<String> ids = generate(type,1);
        return ids == null || ids.isEmpty() ? null : ids.get(0);
    }

    @Override
    public List<String> generate(IdTypeEnums type, int count) {
        count = count <= 0 ? 1 : count;
        type = type == null ? IdTypeEnums.DEFAULT : type;
        String key = key();
        String timestamp = System.currentTimeMillis() + "";
        String field = field(timestamp,type);
        List<String> ids = new ArrayList<>();
        long identity = identity(key,field,count);
        for(int index = 0 ; index < count ; index ++ ){
            ids.add(type.getPrefix() + timestamp + (identity - index));
        }
        return ids;
    }


    private long identity(String key,String field, int count){
        long identity = redisService.incrHash(key,field,count);
        if(identity == count){
            redisService.expire(key,KEY_EXPIRE);//第一次才设置过期时间，减少操作次数
        }
        return identity;
    }


    private String key(){
        String date = DateUtils.ConvertToStr(new Date(),"yyyyMMdd");
        return RedisKeyConstants.ID_GENERATER_KEY + date;
    }

    private String field(String timestamp, IdTypeEnums type){
        return "ID_" + timestamp + "" + type.getIndex();
    }


}
