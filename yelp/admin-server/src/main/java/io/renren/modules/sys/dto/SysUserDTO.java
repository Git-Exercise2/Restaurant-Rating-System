

package io.renren.modules.sys.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * User Management
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Data
@Schema(title = "User Management")
public class SysUserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@Schema(title = "id")
	@Null(message="{id.null}", groups = AddGroup.class)
	@NotNull(message="{id.require}", groups = UpdateGroup.class)
	private Long id;

	@Schema(title = "username", required = true)
	@NotBlank(message="{sysuser.username.require}", groups = DefaultGroup.class)
	private String username;

	@Schema(title = "password")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotBlank(message="{sysuser.password.require}", groups = AddGroup.class)
	private String password;

	@Schema(title = "Name", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank(message="{sysuser.realName.require}", groups = DefaultGroup.class)
	private String realName;

	@Schema(title = "avatar")
	private String headUrl;

	@Schema(title = "Gender 0: Male 1: Female 2: Confidential", required = true)
	@Range(min=0, max=2, message = "{sysuser.gender.range}", groups = DefaultGroup.class)
	private Integer gender;

	@Schema(title = "Mail")
	@Email(message="{sysuser.email.error}", groups = DefaultGroup.class)
	private String email;

	@Schema(title = "Phone number")
	private String mobile;

	@Schema(title = "Department ID", required = true)
	@NotNull(message="{sysuser.deptId.require}", groups = DefaultGroup.class)
	private Long deptId;

	@Schema(title = "Status 0: Deactivate 1: Normal", required = true)
	@Range(min=0, max=1, message = "{sysuser.status.range}", groups = DefaultGroup.class)
	private Integer status;

	@Schema(title = "Creation time")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date createDate;

	@Schema(title = "Super Administrator 0: No 1: Yes")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer superAdmin;

	@Schema(title = "Role ID list")
	private List<Long> roleIdList;

	@Schema(title = "Department name")
	private String deptName;

}
