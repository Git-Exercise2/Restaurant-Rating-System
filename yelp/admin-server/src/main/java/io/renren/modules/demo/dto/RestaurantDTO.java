package io.renren.modules.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-01
 */
@Data
@Schema(name = "")
public class RestaurantDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "")
	private Long id;

	@SchemaProperty(name = "RESTAURANT NAME")
	private String name;

	@SchemaProperty(name = "RESTAURANT ADDRESS")
	private String address;

	@SchemaProperty(name = "LATITUDE")
	private String latitude;

	@SchemaProperty(name = "LONGITUDE")
	private String longitude;

	@SchemaProperty(name = "RATING")
	private String rating;

	@SchemaProperty(name = "REVIEW COUNT")
	private Integer reviewCount;

	@SchemaProperty(name = "PRICE LEVEL")
	private String priceLevel;

	@SchemaProperty(name = "CATEGORIES")
	private String categories;

	@SchemaProperty(name = "IMAGE URL")
	private String imageUrl;

	@SchemaProperty(name = "RESTAURANT LINK")
	private String url;

	@SchemaProperty(name = "RESTAURANT PHONE")
	private String phone;

	@SchemaProperty(name = "CREATE DATE")
	private Date createDate;

	@SchemaProperty(name = "")
	private String attr1;

	@SchemaProperty(name = "")
	private String attr2;

	@SchemaProperty(name = "")
	private String attr3;

	@SchemaProperty(name = "")
	private String attr4;


}
