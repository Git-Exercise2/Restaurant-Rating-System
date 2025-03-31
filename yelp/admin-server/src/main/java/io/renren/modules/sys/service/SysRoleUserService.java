

package io.renren.modules.sys.service;

import io.renren.common.service.BaseService;
import io.renren.modules.sys.entity.SysRoleUserEntity;

import java.util.List;

/**
 * Role User Relationship
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
public interface SysRoleUserService extends BaseService<SysRoleUserEntity> {

    /**
     * Save or modify
     * @param userId User ID
     * @param roleIdList RoleID List
     */
    void saveOrUpdate(Long userId, List<Long> roleIdList);

    /**
     * Delete the role user relationship based on role ids
     * @param roleIds roleids
     */
    void deleteByRoleIds(Long[] roleIds);

    /**
     * Delete the role user relationship based on user id
     * @param userIds User ids
     */
    void deleteByUserIds(Long[] userIds);

    /**
     * Role ID list
     * @param userId User ID
     */
    List<Long> getRoleIdList(Long userId);
}