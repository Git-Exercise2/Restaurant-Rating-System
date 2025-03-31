

package io.renren.modules.sys.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.sys.entity.SysRoleUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Role User Relationship
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Mapper
public interface SysRoleUserDao extends BaseDao<SysRoleUserEntity> {

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
     *
     * @return
     */
    List<Long> getRoleIdList(Long userId);
}