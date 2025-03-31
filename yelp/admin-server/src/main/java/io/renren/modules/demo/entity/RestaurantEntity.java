package io.renren.modules.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-01
 */
@Data
@TableName("restaurant")
public class RestaurantEntity {

    /**
     * 
     */
	private Long id;
    /**
     * RESTAURANT NAME
     */
	private String name;
    /**
     * RESTAURANT ADDRESS
     */
	private String address;
    /**
     * LATITUDE
     */
	private String latitude;
    /**
     * LONGITUDE
     */
	private String longitude;
    /**
     * RATING
     */
	private String rating;
    /**
     * REVIEW COUNT
     */
	private Integer reviewCount;
    /**
     * PRICE LEVEL
     */
	private String priceLevel;
    /**
     * CATEGORIES
     */
	private String categories;
    /**
     * IMAGE URL
     */
	private String imageUrl;
    /**
     * RESTAURANT LINK
     */
	private String url;
    /**
     * RESTAURANT PHONE
     */
	private String phone;
    /**
     * CREATE DATE
     */
	private Date createDate;
    /**
     * 
     */
	private String attr1;
    /**
     * 
     */
	private String attr2;
    /**
     * 
     */
	private String attr3;
    /**
     * 
     */
	private String attr4;
}