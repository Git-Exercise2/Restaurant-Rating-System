

package io.renren.modules.sys.service;

import io.renren.common.service.BaseService;
import io.renren.modules.sys.entity.SysRoleDataScopeEntity;

import java.util.List;

/**
 * Role data permissions
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
public interface SysRoleDataScopeService extends BaseService<SysRoleDataScopeEntity> {

    /**
     * Get the department ID list based on the role ID
     */
    List<Long> getDeptIdList(Long roleId);

    /**
     * Save or modify
     * @param roleId RoleID
     * @param deptIdList Department ID list
     */
    void saveOrUpdate(Long roleId, List<Long> deptIdList);

    /**
     * Delete the role data permission relationship based on the role id
     * @param roleId role ids
     */
    void deleteByRoleIds(Long[] roleId);
}