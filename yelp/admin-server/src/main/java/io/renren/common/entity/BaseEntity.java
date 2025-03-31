

package io.renren.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Basic entity class, all entities need to inherit
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public abstract class BaseEntity implements Serializable {
    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * Creator
     */
    @TableField(fill = FieldFill.INSERT)
    private Long  creator;
    /**
     * Creation time
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createDate;
}