package com.ral.service.token;

import com.ral.model.domain.AccessToken;

/**
 * 
 * @author victor
 * @desc token服务接口
 */
public interface ITokenService {

	/**
	 * @param tokenId
	 * @return
	 * @desc 返回Token信息,如果已经失效则直接返回NULL
	 */
	AccessToken checkToken(String tokenId);
	
	
	/**
	 * @param tokenId token唯一标识
	 * @param seconds 失效时间，秒
	 * @return
	 * @desc 返回Token信息,如果已经失效则直接返回NULL,如果token存在将设置新的过期时间
	 */
	AccessToken checkAndRefreshToken(String tokenId, int seconds);
	
	/**
	 * @return
	 * @desc 创建Token, 返回创建的Token对象,创建失败返回Null
	 */
	AccessToken createToken();
	
	/**
	 * @return
	 * @desc 创建知道有效期Token, 返回创建的Token对象,创建失败返回Null
	 */
	AccessToken createToken(int seconds);
	
	/**
	 * @param token 对象
	 * @return
	 * @desc 创建Token, tokenId将作为获取Token唯一标识,必须指定
	 */
	boolean createToken(AccessToken token);
	
	
	/**
	 * @param token 对象
	 * @param seconds 失效时间，秒
	 * @return
	 * @desc 创建Token, tokenId将作为Key,必须保证唯一,,必须指定
	 */
	boolean createToken(AccessToken token, int seconds);
	
	/**
	 * 
	 * @param tokenId token唯一标识
	 * @desc  手动删除Token
	 */
	void removeToken(String tokenId);
	
	/**
	 * 
	 * @param tokenId token唯一标识
	 * @param seconds 有效时间,秒
	 * @desc  刷新Token有效时间
	 */
	void refresh(String tokenId, int seconds);
	

}
