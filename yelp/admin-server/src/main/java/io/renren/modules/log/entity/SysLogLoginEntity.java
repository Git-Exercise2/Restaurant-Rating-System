

package io.renren.modules.log.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Login log
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_log_login")
public class SysLogLoginEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * User Operation 0: User Login 1: User Logout
	 */
	private Integer operation;
	/**
	 * Status 0: Failed 1: Success 2: Account is locked
	 */
	private Integer status;
	/**
	 * User Agent
	 */
	private String userAgent;
	/**
	 * Operation IP
	 */
	private String ip;
	/**
	 * username
	 */
	private String creatorName;

}