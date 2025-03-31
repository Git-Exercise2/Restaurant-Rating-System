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
@TableName("discuss")
public class DiscussEntity {

    /**
     * ID
     */
	private Long id;
    /**
     * USER ID
     */
	private Long userId;
    /**
     * USER NAME
     */
	private String userName;
    /**
     * PARENT ID
     */
	private Long parentId;
    /**
     * PARENT USER ID
     */
	private Long parentUserId;
    /**
     * PARENT USER NAME
     */
	private String parentUserName;
    /**
     * RESTAURANT ID
     */
	private Long restaurantId;
    /**
     * RESTAURANT NAME
     */
	private String restaurantName;
    /**
     * DISCUSS CONTENT
     */
	private String content;
    /**
     * CREATE DATE
     */
	private Date createDate;
    /**
     * STATUS
     */
	private String isDelete;
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
    /**
     * 
     */
	private String attr5;
}