package com.ral.service.lock;

public interface IRedisLock extends ILock{
	
	String getLockKeyPrev();
}
