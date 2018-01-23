package com.ral.auth.service.impl;

import com.ral.auth.constants.RedisKeyConstants;
import com.ral.auth.dao.TenantMapper;
import com.ral.auth.model.entity.Tenant;
import com.ral.auth.model.entity.TenantExample;
import com.ral.auth.service.ITenantService;
import com.ral.service.redis.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TenantServiceImpl implements ITenantService {

	@Autowired
	private TenantMapper tenantMapper;
	
	@Autowired
	private IRedisService redisService;
	

	@Override
	public Tenant getByTenantId(String tenantId) {
		Tenant tenant = redisService.hget(RedisKeyConstants.TENANT_MAP, tenantId, Tenant.class);
		if(tenant == null){
			tenant = selectByTenantId(tenantId);
		}
		if(tenant != null){
			redisService.hset(RedisKeyConstants.TENANT_MAP,tenantId,tenant);
		}
		return tenant;
	}

	@Transactional
	@Override
	public int update(Tenant tenant) {
		redisService.hdel(RedisKeyConstants.TENANT_MAP, tenant.getTenantId());
		return tenantMapper.updateByPrimaryKeySelective(tenant) ;
	}

	@Override
	public int insert(Tenant tenant) {
		return tenantMapper.insertSelective(tenant);
	}

	private Tenant selectByTenantId(String  tenantId){
		TenantExample example = new TenantExample();
		example.createCriteria().andTenantIdEqualTo(tenantId);
		List<Tenant> tenants = tenantMapper.selectByExample(example);
		if(tenants == null || tenants.isEmpty()){
			return null;
		}
		return tenants.get(0);
	}
}
