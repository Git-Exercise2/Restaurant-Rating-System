

package io.renren.modules.security.service;

import io.renren.common.service.BaseService;
import io.renren.common.utils.Result;
import io.renren.modules.security.entity.SysUserTokenEntity;

/**
 * User Token
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysUserTokenService extends BaseService<SysUserTokenEntity> {

	/**
	 * Generate token
	 * @param userId User ID
	 */
	Result createToken(Long userId);

	/**
	 * Exit, modify the token value
	 * @param userId User ID
	 */
	void logout(Long userId);

}