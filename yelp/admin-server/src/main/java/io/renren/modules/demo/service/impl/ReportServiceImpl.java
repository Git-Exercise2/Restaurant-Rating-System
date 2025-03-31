package io.renren.modules.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.modules.demo.dao.ReportDao;
import io.renren.modules.demo.dto.ReportDTO;
import io.renren.modules.demo.entity.ReportEntity;
import io.renren.modules.demo.service.ReportService;
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
public class ReportServiceImpl extends CrudServiceImpl<ReportDao, ReportEntity, ReportDTO> implements ReportService {

    @Override
    public QueryWrapper<ReportEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        Long report_user_id = (Long)params.get("report_user_id");

        QueryWrapper<ReportEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(id), "id", id);
        wrapper.eq(report_user_id != null, "report_user_id", report_user_id);

        return wrapper;
    }


}