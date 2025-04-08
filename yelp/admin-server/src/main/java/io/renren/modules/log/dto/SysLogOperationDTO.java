

package io.renren.modules.log.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Operation log
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Data
@Schema(title = "Operation log")
public class SysLogOperationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@Schema(title = "id")
	private Long id;

	@Schema(title = "User Operation")
	private String operation;

	@Schema(title = "Request URI")
	private String requestUri;

	@Schema(title = "Request method")
	private String requestMethod;

	@Schema(title = "Request parameters")
	private String requestParams;

	@Schema(title = "Request duration (milliseconds)")
	private Integer requestTime;

	@Schema(title = "User Agent")
	private String userAgent;

	@Schema(title = "Operation IP")
	private String ip;

	@Schema(title = "Status 0: Failed 1: Success")
	private Integer status;

	@Schema(title = "username")
	private String creatorName;

	@Schema(title = "Creation time")
	private Date createDate;

}
