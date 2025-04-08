

package io.renren.modules.sys.service;

import io.renren.common.service.BaseService;
import io.renren.modules.sys.dto.SysDeptDTO;
import io.renren.modules.sys.entity.SysDeptEntity;

import java.util.List;
import java.util.Map;

/**
 * Department Management
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysDeptService extends BaseService<SysDeptEntity> {

	List<SysDeptDTO> list(Map<String, Object> params);

	SysDeptDTO get(Long id);

	void save(SysDeptDTO dto);

	void update(SysDeptDTO dto);

	void delete(Long id);

	/**
	 * Obtain the list of this department and sub-department IDs based on the department ID
	 * @param id Department ID
	 */
	List<Long> getSubDeptIdList(Long id);
}