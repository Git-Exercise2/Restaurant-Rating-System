

package io.renren.modules.sys.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import io.renren.modules.sys.excel.converter.GenderConverter;
import io.renren.modules.sys.excel.converter.StatusConverter;
import lombok.Data;

import java.util.Date;

/**
 * User Management
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Data
public class SysUserExcel {
    @ExcelProperty("username")
    private String username;
    @ExcelProperty("Name")
    private String realName;
    @ExcelProperty(value = "gender", converter = GenderConverter.class)
    private Integer gender;
    @ExcelProperty("Mail")
    private String email;
    @ExcelProperty("Phone number")
    private String mobile;
    @ExcelProperty("Department name")
    private String deptName;
    @ExcelProperty(value = "state", converter = StatusConverter.class)
    private Integer status;
    @ExcelProperty("Remark")
    private String remark;
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("Creation time")
    private Date createDate;

}
