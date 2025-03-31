

package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Data Dictionary
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_dict_data")
public class SysDictDataEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * Dictionary Type ID
	 */
	private Long dictTypeId;
	/**
	 * Dictionary tags
	 */
	private String dictLabel;
	/**
	 * Dictionary value
	 */
	private String dictValue;
	/**
	 * 备注
	 */
	private String remark;
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
}