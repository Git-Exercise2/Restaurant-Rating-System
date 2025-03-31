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
public class DiscussDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "ID")
	private Long id;

	@SchemaProperty(name = "USER ID")
	private Long userId;

	@SchemaProperty(name = "USER NAME")
	private String userName;

	@SchemaProperty(name = "PARENT ID")
	private Long parentId;

	@SchemaProperty(name = "PARENT USER ID")
	private Long parentUserId;

	@SchemaProperty(name = "PARENT USER NAME")
	private String parentUserName;

	@SchemaProperty(name = "RESTAURANT ID")
	private Long restaurantId;

	@SchemaProperty(name = "RESTAURANT NAME")
	private String restaurantName;

	@SchemaProperty(name = "DISCUSS CONTENT")
	private String content;

	@SchemaProperty(name = "CREATE DATE")
	private Date createDate;

	@SchemaProperty(name = "STATUS")
	private String isDelete;

	@SchemaProperty(name = "")
	private String attr1;

	@SchemaProperty(name = "")
	private String attr2;

	@SchemaProperty(name = "")
	private String attr3;

	@SchemaProperty(name = "")
	private String attr4;

	@SchemaProperty(name = "")
	private String attr5;


}
