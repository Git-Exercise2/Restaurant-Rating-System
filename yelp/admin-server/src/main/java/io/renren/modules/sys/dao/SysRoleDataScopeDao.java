

package io.renren.modules.sys.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.sys.entity.SysRoleDataScopeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Role data permissions
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Mapper
public interface SysRoleDataScopeDao extends BaseDao<SysRoleDataScopeEntity> {

    /**
     * Get the department ID list based on the role ID
     */
    List<Long> getDeptIdList(Long roleId);

    /**
     * Get the user's department data permission list
     */
    List<Long> getDataScopeList(Long userId);

    /**
     * Delete the role data permission relationship based on the role id
     * @param roleIds roleids
     */
    void deleteByRoleIds(Long[] roleIds);
}