

package io.renren.modules.sys.service;

import io.renren.common.page.PageData;
import io.renren.common.service.BaseService;
import io.renren.modules.sys.dto.SysUserDTO;
import io.renren.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;


/**
 * System user
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysUserService extends BaseService<SysUserEntity> {

	PageData<SysUserDTO> page(Map<String, Object> params);

	List<SysUserDTO> list(Map<String, Object> params);

	SysUserDTO get(Long id);

	SysUserDTO getByUsername(String username);

	void save(SysUserDTO dto);

	void update(SysUserDTO dto);

	void delete(Long[] ids);

	/**
	 * Change password
	 * @param id User ID
	 * @param newPassword new password
	 */
	void updatePassword(Long id, String newPassword);

	/**
	 * According to department ID, query the number of users
	 */
	int getCountByDeptId(Long deptId);

	/**
	 * According to department ID, query the user ID list
	 */
	List<Long> getUserIdListByDeptId(List<Long> deptIdList);

}
