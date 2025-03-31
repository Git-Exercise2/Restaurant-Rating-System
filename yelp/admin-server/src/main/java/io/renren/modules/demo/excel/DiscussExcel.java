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
public class DiscussExcel {
    @ExcelProperty(value = "ID")
    private Long id;
    @ExcelProperty(value = "USER ID")
    private Long userId;
    @ExcelProperty(value = "USER NAME")
    private String userName;
    @ExcelProperty(value = "PARENT ID")
    private Long parentId;
    @ExcelProperty(value = "PARENT USER ID")
    private Long parentUserId;
    @ExcelProperty(value = "PARENT USER NAME")
    private String parentUserName;
    @ExcelProperty(value = "RESTAURANT ID")
    private Long restaurantId;
    @ExcelProperty(value = "RESTAURANT NAME")
    private String restaurantName;
    @ExcelProperty(value = "DISCUSS CONTENT")
    private String content;
    @ExcelProperty(value = "CREATE DATE")
    private Date createDate;
    @ExcelProperty(value = "STATUS")
    private String isDelete;
    @ExcelProperty(value = "")
    private String attr1;
    @ExcelProperty(value = "")
    private String attr2;
    @ExcelProperty(value = "")
    private String attr3;
    @ExcelProperty(value = "")
    private String attr4;
    @ExcelProperty(value = "")
    private String attr5;

}