

package io.renren.modules.sys.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.sys.entity.SysParamsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Parameter management
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Mapper
public interface SysParamsDao extends BaseDao<SysParamsEntity> {
    /**
     * According to parameter encoding, query value
     * @param paramCode Parameter encoding
     * @return parameter value
     */
    String getValueByCode(String paramCode);

    /**
     * Get the parameter encoding list
     * @param ids ids
     * @return Return to the parameter encoding list
     */
    List<String> getParamCodeList(Long[] ids);

    /**
     * Update value according to parameter encoding
     * @param paramCode Parameter encoding
     * @param paramValue parameter value
     */
    int updateValueByCode(@Param("paramCode") String paramCode, @Param("paramValue") String paramValue);
}
