package com.ral.service.lock.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import com.ral.model.domain.LockMeta;
import com.ral.service.lock.IJvmLock;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;


/**
 * JVM锁暂时不考虑过期时间
 * 需要处理完成后调用unLock方法释放锁
 *
 */
@Service
public class JvmLockImpl implements IJvmLock {

	private static Logger logger = Logger.getLogger(JvmLockImpl.class);

    private static ConcurrentHashMap<String,LockMeta> map= new ConcurrentHashMap<String, LockMeta>();

    private static ReentrantLock lock=new ReentrantLock();

    @Override
    public boolean lock(String key,int exprie){
        if (map.containsKey(key)) {
        	LockMeta meta = map.get(key);
        	if(meta.isLose()){//已经超时 释放
        		unlock(key);
        	}else{
        		logger.info("key:" + key + "获取JVM锁失败");
        		 return false;
        	}
        }
        lock.lock();
        try {
            if (map.containsKey(key)) {
            	logger.info("key:"+key+"获取JVM锁失败");
                return false;
            }
            LockMeta meta = new LockMeta();
            meta.setLockTime(System.currentTimeMillis());
            meta.setExpireTime(exprie);
            map.putIfAbsent(key, meta);
        } catch (Exception ex) {
        	logger.error(ex.getMessage());
            return false;
        } finally {
            lock.unlock();
        }
        logger.info("key:"+key+"获取JVM成功");
        return true;
    }

    @Override
    public void unlock(String key){
        try {
            map.remove(key);
            logger.info(key+"JVM锁释放成功");
        }
        catch (Exception ex){
        	
        }
    }

}
