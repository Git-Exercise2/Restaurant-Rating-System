

package io.renren.modules.log.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Operation log
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_log_operation")
public class SysLogOperationEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * User operation
	 */
	private String operation;
	/**
	 * Request URI
	 */
	private String requestUri;
	/**
	 * Request method
	 */
	private String requestMethod;
	/**
	 * Request parameters
	 */
	private String requestParams;
	/**
	 * Request duration (milliseconds)
	 */
	private Integer requestTime;
	/**
	 * User Agent
	 */
	private String userAgent;
	/**
	 * Operation IP
	 */
	private String ip;
	/**
	 * Status 0: Failed 1: Success
	 */
	private Integer status;
	/**
	 * username
	 */
	private String creatorName;
}