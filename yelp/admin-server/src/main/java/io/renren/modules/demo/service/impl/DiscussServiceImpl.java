package io.renren.modules.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.modules.demo.dao.DiscussDao;
import io.renren.modules.demo.dto.DiscussDTO;
import io.renren.modules.demo.entity.DiscussEntity;
import io.renren.modules.demo.service.DiscussService;
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
public class DiscussServiceImpl extends CrudServiceImpl<DiscussDao, DiscussEntity, DiscussDTO> implements DiscussService {

    @Override
    public QueryWrapper<DiscussEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        String restaurant_name = (String)params.get("restaurant_name");

        QueryWrapper<DiscussEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);
        wrapper.eq(StrUtil.isNotBlank(restaurant_name), "restaurant_name", restaurant_name);

        return wrapper;
    }


}