package io.renren.modules.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-01
 */
@Data
public class ReportExcel {
    @ExcelProperty(value = "ID")
    private Long id;
    @ExcelProperty(value = "DISCUSS ID")
    private Integer discussId;
    @ExcelProperty(value = "REPORT USER ID")
    private Long reportUserId;
    @ExcelProperty(value = "REPORT USER")
    private String reportUserName;
    @ExcelProperty(value = "REPORT TYPE")
    private String reportType;
    @ExcelProperty(value = "REPORT CONTENT")
    private String reportContent;
    @ExcelProperty(value = "REPORT HANDLING TYPE")
    private String reportHandleType;
    @ExcelProperty(value = "REPORT HANDLING")
    private String reportHandle;
    @ExcelProperty(value = "AUDIT STATUS")
    private String audit;
    @ExcelProperty(value = "AUDIT CONTENT")
    private String auditContent;
    @ExcelProperty(value = "CREATE DATE")
    private Date createDate;
    @ExcelProperty(value = "UPDATE DATE")
    private Date updateDate;
    @ExcelProperty(value = "")
    private String attr1;
    @ExcelProperty(value = "")
    private String attr2;
    @ExcelProperty(value = "")
    private String attr3;
    @ExcelProperty(value = "")
    private String attr4;

}