

package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Menu Management
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_menu")
public class SysMenuEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * Parent menu ID, first-level menu is 0
	 */
	private Long pid;
	/**
	 * Menu name
	 */
	private String name;
	/**
	 * Menu URL
	 */
	private String url;
	/**
	 * Authorization (multiple separated by commas, such as: sys:user:list, sys:user:save)
	 */
	private String permissions;
	/**
	 * Type 0: Menu 1: Button
	 */
	private Integer menuType;
	/**
	 * Menu icon
	 */
	private String icon;
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
	 * Name of the menu
	 */
	@TableField(exist = false)
	private String parentName;

}