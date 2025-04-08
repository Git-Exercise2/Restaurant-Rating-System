

package io.renren.modules.security.service;

import io.renren.modules.security.user.UserDetail;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.security.entity.SysUserTokenEntity;

import java.util.List;
import java.util.Set;

/**
 * Shiro-related interface
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface ShiroService {
    /**
     * Get user permission list
     */
    Set<String> getUserPermissions(UserDetail user);

    SysUserTokenEntity getByToken(String token);

    /**
     * Query the user based on the user ID
     * @param userId
     */
    SysUserEntity getUser(Long userId);

    /**
     * Obtain the corresponding department data permissions of the user
     * @param userId User ID
     * @return Return to the department ID list
     */
    List<Long> getDataScopeList(Long userId);
}
