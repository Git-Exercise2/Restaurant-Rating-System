

package io.renren.modules.sys.service;

import io.renren.common.service.BaseService;
import io.renren.modules.sys.entity.SysRoleMenuEntity;

import java.util.List;


/**
 * Character and menu correspondence
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysRoleMenuService extends BaseService<SysRoleMenuEntity> {

	/**
	 * Get menu ID list based on role ID
	 */
	List<Long> getMenuIdList(Long roleId);

	/**
	 * Save or modify
	 * @param roleId RoleID
	 * @param menuIdList Menu ID list
	 */
	void saveOrUpdate(Long roleId, List<Long> menuIdList);

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