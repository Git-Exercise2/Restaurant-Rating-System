

package io.renren.modules.log.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * Operation log
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@ContentRowHeight(20)
@HeadRowHeight(20)
@ColumnWidth(25)
public class SysLogOperationExcel {
    @ExcelProperty("User Operation")
    private String operation;

    @ExcelProperty("Request URI")
    private String requestUri;

    @ExcelProperty("Request method")
    private String requestMethod;

    @ExcelProperty("Request parameters")
    private String requestParams;

    @ExcelProperty("Request duration (milliseconds)")
    private Integer requestTime;

    @ExcelProperty("User-Agent")
    private String userAgent;

    @ExcelProperty("Operation IP")
    private String ip;

    @ExcelProperty("state")
    private Integer status;

    @ExcelProperty("username")
    private String creatorName;

    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("Creation time")
    private Date createDate;

}
