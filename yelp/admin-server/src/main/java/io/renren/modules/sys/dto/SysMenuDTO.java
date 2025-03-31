

package io.renren.modules.sys.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.renren.common.utils.TreeNode;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.util.Date;

/**
 * Menu Management
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "Menu Management")
public class SysMenuDTO extends TreeNode<SysMenuDTO> implements Serializable {
    private static final long serialVersionUID = 1L;

	@Schema(title = "id")
	@Null(message="{id.null}", groups = AddGroup.class)
	@NotNull(message="{id.require}", groups = UpdateGroup.class)
	private Long id;

	@Schema(title = "Superior ID")
	@NotNull(message="{sysmenu.pid.require}", groups = DefaultGroup.class)
	private Long pid;

	@Schema(title = "Menu name")
	@NotBlank(message="sysmenu.name.require", groups = DefaultGroup.class)
	private String name;

	@Schema(title = "Menu URL")
	private String url;

	@Schema(title = "Type 0: Menu 1: Button")
	@Range(min=0, max=1, message = "{sysmenu.type.range}", groups = DefaultGroup.class)
	private Integer menuType;

	@Schema(title = "Menu icon")
	private String icon;

	@Schema(title = "Authorization (multiple separated by commas, such as: sys:user:list, sys:user:save)")
	private String permissions;

	@Schema(title = "Sort")
	@Min(value = 0, message = "{sort.number}", groups = DefaultGroup.class)
	private Integer sort;

	@Schema(title = "Creation time")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date createDate;

	@Schema(title = "上级Menu name")
	private String parentName;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getPid() {
		return pid;
	}

	@Override
	public void setPid(Long pid) {
		this.pid = pid;
	}

}
