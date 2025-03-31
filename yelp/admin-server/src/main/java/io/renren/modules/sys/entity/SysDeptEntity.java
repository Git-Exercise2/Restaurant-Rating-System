

package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.renren.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Department Management
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_dept")
public class SysDeptEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * Superior ID
	 */
	private Long pid;
	/**
	 * All superior IDs are separated by commas
	 */
	private String pids;
	/**
	 * Department name
	 */
	private String name;
	/**
	 * Sort
	 */
	private Integer sort;
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
	/**
	 * Name of superior department
	 */
	@TableField(exist = false)
	private String parentName;

}