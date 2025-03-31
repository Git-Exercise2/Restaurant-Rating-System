package io.renren.modules.demo.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.demo.entity.RestaurantEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-01
 */
@Mapper
public interface RestaurantDao extends BaseDao<RestaurantEntity> {

    int selectStar(@Param("userId") Long userId,@Param("restaurantId")  Long restaurantId);

    void insertStr(@Param("userId") Long userId,@Param("restaurantId")  Long restaurantId);

    void removeStar(@Param("userId") Long userId,@Param("restaurantId")  Long restaurantId);

    List<RestaurantEntity> selectListByStar(@Param("userId") Long userId);

    List<RestaurantEntity> selectListByKey(@Param("key")  String key);
}