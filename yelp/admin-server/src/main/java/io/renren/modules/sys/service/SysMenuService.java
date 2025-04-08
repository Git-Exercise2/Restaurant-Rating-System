

package io.renren.modules.sys.service;

import io.renren.common.service.BaseService;
import io.renren.modules.security.user.UserDetail;
import io.renren.modules.sys.dto.SysMenuDTO;
import io.renren.modules.sys.entity.SysMenuEntity;

import java.util.List;


/**
 * Menu Management
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysMenuService extends BaseService<SysMenuEntity> {

	SysMenuDTO get(Long id);

	void save(SysMenuDTO dto);

	void update(SysMenuDTO dto);

	void delete(Long id);

	/**
	 * Menu list
	 *
	 * @param menuType Menu Type
	 */
	List<SysMenuDTO> getAllMenuList(Integer menuType);

	/**
	 * User menu list
	 *
	 * @param user
	 * @param menuType Menu Type
	 */
	List<SysMenuDTO> getUserMenuList(UserDetail user, Integer menuType);

	/**
	 * According to the parent menu, query the submenu
	 * @param pid Parent menu ID
	 */
	List<SysMenuDTO> getListPid(Long pid);
}
