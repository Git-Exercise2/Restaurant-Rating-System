

package io.renren.modules.sys.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Dictionary Type
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@Schema(title = "Dictionary Type")
public class SysDictTypeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@Schema(title = "id")
	@Null(message="{id.null}", groups = AddGroup.class)
	@NotNull(message="{id.require}", groups = UpdateGroup.class)
	private Long id;

	@Schema(title = "Dictionary Type")
	@NotBlank(message="{sysdict.type.require}", groups = DefaultGroup.class)
	private String dictType;

	@Schema(title = "Dictionary name")
	@NotBlank(message="{sysdict.name.require}", groups = DefaultGroup.class)
	private String dictName;

	@Schema(title = "Remark")
	private String remark;

	@Schema(title = "Sort")
	@Min(value = 0, message = "{sort.number}", groups = DefaultGroup.class)
	private Integer sort;

	@Schema(title = "Creation time")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date createDate;

	@Schema(title = "Update time")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date updateDate;
}
