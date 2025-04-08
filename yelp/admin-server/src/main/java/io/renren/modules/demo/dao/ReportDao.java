package io.renren.modules.demo.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.demo.entity.ReportEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ReportDao extends BaseDao<ReportEntity> {
	
}