

package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Parameter management
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_params")
public class SysParamsEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * Parameter encoding
	 */
	private String paramCode;
	/**
	 * Parameter value
	 */
	private String paramValue;
	/**
	 * Type 0: System Parameter 1: Non-system Parameter
	 */
	private Integer paramType;
	/**
	 * Remark
	 */
	private String remark;
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