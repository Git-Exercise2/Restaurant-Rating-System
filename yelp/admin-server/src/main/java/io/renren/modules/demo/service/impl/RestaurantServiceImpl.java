package io.renren.modules.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.modules.demo.dao.RestaurantDao;
import io.renren.modules.demo.dto.RestaurantDTO;
import io.renren.modules.demo.entity.RestaurantEntity;
import io.renren.modules.demo.service.RestaurantService;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-01
 */
@Service
public class RestaurantServiceImpl extends CrudServiceImpl<RestaurantDao, RestaurantEntity, RestaurantDTO> implements RestaurantService {

    @Override
    public QueryWrapper<RestaurantEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        String name = (String)params.get("name");

        QueryWrapper<RestaurantEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);
        wrapper.like(StrUtil.isNotBlank(name), "name", name);

        return wrapper;
    }


}