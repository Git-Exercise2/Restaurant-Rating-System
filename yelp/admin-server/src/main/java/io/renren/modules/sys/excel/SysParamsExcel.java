

package io.renren.modules.sys.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * Parameter management
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Data
public class SysParamsExcel {
    @ExcelProperty("Parameter encoding")
    private String paramCode;
    @ExcelProperty("Parameter value")
    private String paramValue;
    @ExcelProperty("Remark")
    private String remark;

}