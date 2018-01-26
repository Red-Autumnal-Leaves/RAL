package com.ral.constants.redis;

/**
 * Created by victor on 2018/1/4.
 */
public class RedisKeyConstants {

    /**
     * 存放Redis session 默认前缀,避免冲突
     */
    public final static String DEFAULT_SESSION_KEY = "session";


    /**
     * 存放Redis Token 默认前缀,避免冲突
     */
    public final static String DEFAULT_TOKEN_KEY = "token";


    /**
     * Redis id 生成策略 默认Map key
     */
    public final static String ID_GENERATER_KEY = "ID_GENERATER_KEY_";


    /**
     * catalog 目录hash 缓存key
     */
    public final static String CATALOG_MAP = "CATALOG_MAP";

    /**
     * catalog  树缓存
     */
    public final static String CATALOG_TREE_KEY = "CATALOG_TREE_KEY";
}
