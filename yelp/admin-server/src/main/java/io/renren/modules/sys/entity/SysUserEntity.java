

package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * System user
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_user")
public class SysUserEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * username
	 */
	private String username;
	/**
	 * password
	 */
	private String password;
	/**
	 * Name
	 */
	private String realName;
	/**
	 * avatar
	 */
	private String headUrl;
	/**
	 * Gender 0: Male 1: Female 2: Confidential
	 */
	private Integer gender;
	/**
	 * Mail
	 */
	private String email;
	/**
	 * Phone number
	 */
	private String mobile;
	/**
	 * Department ID
	 */
	private Long deptId;
	/**
	 * Super Administrator 0: No 1: Yes
	 */
	private Integer superAdmin;
	/**
	 * Status 0: Deactivate 1: Normal
	 */
	private Integer status;
	/**
	 * Updater
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Long updater;
	/**
	 * Update time
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateDate;
	/**
	 * Department name
	 */
	@TableField(exist=false)
	private String deptName;

}