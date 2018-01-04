package com.ral.service.lock;

public interface ILock {

	 /***
     * 尝试锁定，锁定成功返回true
     * 锁定失败返回false
     * 锁定失败表示当前key正在执行中
     * @param key 需要锁定的key
     * @param exprie 过期时间 秒
     * @return
     */
    boolean lock(String key, int exprie);

    /***
     * 释放锁
     * @param key
     */
    void unlock(String key);
    
}
