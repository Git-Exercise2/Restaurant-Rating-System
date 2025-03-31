

package io.renren.modules.security.service.impl;

import io.renren.common.constant.Constant;
import io.renren.common.service.impl.BaseServiceImpl;
import io.renren.modules.security.oauth2.TokenGenerator;
import io.renren.common.utils.Result;
import io.renren.modules.security.dao.SysUserTokenDao;
import io.renren.modules.security.entity.SysUserTokenEntity;
import io.renren.modules.security.service.SysUserTokenService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SysUserTokenServiceImpl extends BaseServiceImpl<SysUserTokenDao, SysUserTokenEntity> implements SysUserTokenService {
	/**
	 * Expired in 12 hours
	 */
	private final static int EXPIRE = 3600 * 12;

	@Override
	public Result createToken(Long userId) {
		//usertoken
		String token;

		//Current time
		Date now = new Date();
		//Expiration time
		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

		//Determine whether it has been generatedtoken
		SysUserTokenEntity tokenEntity = baseDao.getByUserId(userId);
		if(tokenEntity == null){
			//Generate atoken
			token = TokenGenerator.generateValue();

			tokenEntity = new SysUserTokenEntity();
			tokenEntity.setUserId(userId);
			tokenEntity.setToken(token);
			tokenEntity.setUpdateDate(now);
			tokenEntity.setExpireDate(expireTime);

			//savetoken
			this.insert(tokenEntity);
		}else{
			//judgetoken是否过期
			if(tokenEntity.getExpireDate().getTime() < System.currentTimeMillis()){
				//Token expires，重新生成token
				token = TokenGenerator.generateValue();
			}else {
				token = tokenEntity.getToken();
			}

			tokenEntity.setToken(token);
			tokenEntity.setUpdateDate(now);
			tokenEntity.setExpireDate(expireTime);

			//renewtoken
			this.updateById(tokenEntity);
		}

		Map<String, Object> map = new HashMap<>(2);
		map.put(Constant.TOKEN_HEADER, token);
		map.put("expire", EXPIRE);
		return new Result().ok(map);
	}

	@Override
	public void logout(Long userId) {
		//Generate atoken
		String token = TokenGenerator.generateValue();

		//Revisetoken
		baseDao.updateToken(userId, token);
	}
}