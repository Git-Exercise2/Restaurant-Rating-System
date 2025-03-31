

package io.renren.modules.sys.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.sys.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Menu Management
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysMenuDao extends BaseDao<SysMenuEntity> {

	SysMenuEntity getById(@Param("id") Long id);

	/**
	 * Query all menu lists
	 *
	 * @param menuType Menu Type
	 */
	List<SysMenuEntity> getMenuList(@Param("menuType") Integer menuType);

	/**
	 * Query the user menu list
	 *
	 * @param userId User ID
	 * @param menuType Menu Type
	 */
	List<SysMenuEntity> getUserMenuList(@Param("userId") Long userId, @Param("menuType") Integer menuType);

	/**
	 * Query user permission list
	 * @param userId User ID
	 */
	List<String> getUserPermissionsList(Long userId);

	/**
	 * Query all permission list
	 */
	List<String> getPermissionsList();

	/**
	 * According to the parent menu, query the submenu
	 * @param pid Parent menu ID
	 */
	List<SysMenuEntity> getListPid(Long pid);

}
