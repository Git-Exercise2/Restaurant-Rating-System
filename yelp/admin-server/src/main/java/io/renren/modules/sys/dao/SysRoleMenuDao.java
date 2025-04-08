

package io.renren.modules.sys.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.sys.entity.SysRoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Character and menu correspondence
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysRoleMenuDao extends BaseDao<SysRoleMenuEntity> {

	/**
	 * Get menu ID list based on role ID
	 */
	List<Long> getMenuIdList(Long roleId);

	/**
	 * Delete the role menu relationship based on the role id
	 * @param roleIds roleids
	 */
	void deleteByRoleIds(Long[] roleIds);

	/**
	 * Delete the role menu relationship according to the menu id
	 * @param menuId Menuid
	 */
	void deleteByMenuId(Long menuId);
}
