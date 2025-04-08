

package io.renren.modules.log.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Exception log
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_log_error")
public class SysLogErrorEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

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
	 * User Agent
	 */
	private String userAgent;
	/**
	 * Operation IP
	 */
	private String ip;
	/**
	 * Exception information
	 */
	private String errorInfo;

}