

package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Role
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_role")
public class SysRoleEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * Role name
	 */
	private String name;
	/**
	 * Remark
	 */
	private String remark;
	/**
	 * Department ID
	 */
	@TableField(fill = FieldFill.INSERT)
	private Long deptId;
	/**
	 * Updater
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Long updater;
	/**
	 * Update time
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateDate;
}
