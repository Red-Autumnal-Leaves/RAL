package com.ral.model.domain;

import java.io.Serializable;

public class LockMeta implements Serializable{
	
	private static final long serialVersionUID = 140085406085415951L;

	/***
     * 锁定时间
     */
    private Long lockTime;
    /***
     * 过期时间
     */
    private int expireTime;

    public Long getLockTime() {
        return lockTime;
    }

    public void setLockTime(Long lockTime) {
        this.lockTime = lockTime;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }
    
    public boolean isLose(){
    	return this.getExpireTime()*1000 + this.getLockTime() <= System.currentTimeMillis();
    }
}
