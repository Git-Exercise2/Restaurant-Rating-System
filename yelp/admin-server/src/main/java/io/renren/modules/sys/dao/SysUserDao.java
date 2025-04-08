

package io.renren.modules.sys.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * System user
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysUserDao extends BaseDao<SysUserEntity> {

	List<SysUserEntity> getList(Map<String, Object> params);

	SysUserEntity getById(Long id);

	SysUserEntity getByUsername(String username);

	int updatePassword(@Param("id") Long id, @Param("newPassword") String newPassword);

	/**
	 * According to department ID, query the number of users
	 */
	int getCountByDeptId(Long deptId);

	/**
	 * Query the user ID list based on the department ID
	 */
	List<Long> getUserIdListByDeptId(List<Long> deptIdList);
}