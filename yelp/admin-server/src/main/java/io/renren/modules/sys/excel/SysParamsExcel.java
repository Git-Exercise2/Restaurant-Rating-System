

package io.renren.modules.sys.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;


@Data
public class SysParamsExcel {
    @ExcelProperty("Parameter encoding")
    private String paramCode;
    @ExcelProperty("Parameter value")
    private String paramValue;
    @ExcelProperty("Remark")
    private String remark;

}