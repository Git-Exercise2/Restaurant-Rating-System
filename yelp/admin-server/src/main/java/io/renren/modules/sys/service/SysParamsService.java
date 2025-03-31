

package io.renren.modules.sys.service;


import io.renren.common.page.PageData;
import io.renren.common.service.BaseService;
import io.renren.modules.sys.dto.SysParamsDTO;
import io.renren.modules.sys.entity.SysParamsEntity;

import java.util.List;
import java.util.Map;

/**
 * Parameter management
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
public interface SysParamsService extends BaseService<SysParamsEntity> {

    PageData<SysParamsDTO> page(Map<String, Object> params);

    List<SysParamsDTO> list(Map<String, Object> params);

    SysParamsDTO get(Long id);

    void save(SysParamsDTO dto);

    void update(SysParamsDTO dto);

    void delete(Long[] ids);

    /**
     * Obtain the value of the parameter according to the parameter encoding
     *
     * @param paramCode Parameter encoding
     */
    String getValue(String paramCode);

    /**
     * Get the value Object object according to parameter encoding
     * @param paramCode Parameter encoding
     * @param clazz Object object
     */
    <T> T getValueObject(String paramCode, Class<T> clazz);

    /**
     * Update value according to parameter encoding
     * @param paramCode Parameter encoding
     * @param paramValue parameter value
     */
    int updateValueByCode(String paramCode, String paramValue);
}
