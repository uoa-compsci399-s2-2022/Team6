

package com.project.modules.security.service.impl;

import com.project.modules.security.dao.SysUserTokenDao;
import com.project.modules.security.entity.SysUserTokenEntity;
import com.project.modules.security.oauth2.TokenGenerator;
import com.project.modules.security.service.SysUserTokenService;
import com.project.common.constant.Constant;
import com.project.common.service.impl.BaseServiceImpl;
import com.project.common.utils.Result;
import com.project.modules.sys.dto.SysUserDTO;
import com.project.modules.sys.entity.SysUserEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SysUserTokenServiceImpl extends BaseServiceImpl<SysUserTokenDao, SysUserTokenEntity> implements SysUserTokenService {
	/**
	 *
	 */
	private final static int EXPIRE = 3600 * 12;

	@Override
	public Result createToken(Long userId, SysUserDTO user) {
		// User token
		String token;

		//Now
		Date now = new Date();
		//expireTime
		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

		//Check whether a token has been generated
		SysUserTokenEntity tokenEntity = baseDao.getByUserId(userId);
		if(tokenEntity == null){
			//Generate a token
			token = TokenGenerator.generateValue();

			tokenEntity = new SysUserTokenEntity();
			tokenEntity.setUserId(userId);
			tokenEntity.setToken(token);
			tokenEntity.setUpdateDate(now);
			tokenEntity.setExpireDate(expireTime);

			//Save the token
			this.insert(tokenEntity);
		}else{
			//Check whether the token has expired
			if(tokenEntity.getExpireDate().getTime() < System.currentTimeMillis()){
				//If the token expires, a new token is generated
				token = TokenGenerator.generateValue();
			}else {
				token = tokenEntity.getToken();
			}

			tokenEntity.setToken(token);
			tokenEntity.setUpdateDate(now);
			tokenEntity.setExpireDate(expireTime);

			//Update the token
			this.updateById(tokenEntity);
		}

		Map<String, Object> map = new HashMap<>(2);
		map.put(Constant.TOKEN_HEADER, token);
		map.put("expire", EXPIRE);
		map.put("user", user);
		return new Result().ok(map);
	}

	@Override
	public void logout(Long userId) {
		//Generate a token
		String token = TokenGenerator.generateValue();

		//Update token
		baseDao.updateToken(userId, token);
	}
}