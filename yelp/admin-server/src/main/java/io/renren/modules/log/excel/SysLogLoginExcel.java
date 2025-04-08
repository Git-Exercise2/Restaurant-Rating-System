

package io.renren.modules.log.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import io.renren.modules.log.excel.converter.SysLogOperationConverter;
import io.renren.modules.log.excel.converter.SysLogStatusConverter;
import lombok.Data;

import java.util.Date;

/**
 * Login log
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@ContentRowHeight(20)
@HeadRowHeight(20)
@ColumnWidth(25)
public class SysLogLoginExcel {
    @ExcelProperty(value = "Operation Type", converter = SysLogOperationConverter.class)
    private Integer operation;

    @ExcelProperty(value = "state", converter = SysLogStatusConverter.class)
    private Integer status;

    @ExcelProperty("User-Agent")
    private String userAgent;

    @ExcelProperty("Operation IP")
    private String ip;

    @ExcelProperty("username")
    private String creatorName;

    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("Creation time")
    private Date createDate;

}
