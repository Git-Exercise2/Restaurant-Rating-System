

package io.renren.modules.sys.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.sys.entity.SysDeptEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Department Management
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysDeptDao extends BaseDao<SysDeptEntity> {

    List<SysDeptEntity> getList(Map<String, Object> params);

    SysDeptEntity getById(Long id);

    /**
     * Get the id and pid list of all departments
     */
    List<SysDeptEntity> getIdAndPidList();

    /**
     * Get a list of all sub-department IDs based on the department ID
     * @param id Department ID
     */
    List<Long> getSubDeptIdList(String id);

}