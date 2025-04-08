

package io.renren.modules.log.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Exception log
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Data
@Schema(title = "Exception log")
public class SysLogErrorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@Schema(title = "id")
	private Long id;
	@Schema(title = "Request URI")
	private String requestUri;
	@Schema(title = "Request method")
	private String requestMethod;
	@Schema(title = "Request parameters")
	private String requestParams;
	@Schema(title = "User Agent")
	private String userAgent;
	@Schema(title = "Operation IP")
	private String ip;
	@Schema(title = "Exception information")
	private String errorInfo;
	@Schema(title = "Creation time")
	private Date createDate;

}
