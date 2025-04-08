

package io.renren.modules.security.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * System user token
 */
@Data
@TableName("sys_user_token")
public class SysUserTokenEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * User ID
	 */
	private Long userId;
	/**
	 * User token
	 */
	private String token;
	/**
	 * Expiration time
	 */
	private Date expireDate;
	/**
	 * Update time
	 */
	private Date updateDate;
	/**
	 * Creation time
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createDate;

}