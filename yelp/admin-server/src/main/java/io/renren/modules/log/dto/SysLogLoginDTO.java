

package io.renren.modules.log.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Login log
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Data
@Schema(title = "Login log")
public class SysLogLoginDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@Schema(title = "id")
	private Long id;

	@Schema(title = "User Operation 0: User Login 1: User Logout")
	private Integer operation;

	@Schema(title = "Status 0: Failed 1: Success 2: Account is locked")
	private Integer status;

	@Schema(title = "User Agent")
	private String userAgent;

	@Schema(title = "Operation IP")
	private String ip;

	@Schema(title = "username")
	private String creatorName;

	@Schema(title = "Creation time")
	private Date createDate;

}
