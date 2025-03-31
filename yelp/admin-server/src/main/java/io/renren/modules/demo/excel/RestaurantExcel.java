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
public class RestaurantExcel {
    @ExcelProperty(value = "")
    private Long id;
    @ExcelProperty(value = "RESTAURANT NAME")
    private String name;
    @ExcelProperty(value = "RESTAURANT ADDRESS")
    private String address;
    @ExcelProperty(value = "LATITUDE")
    private String latitude;
    @ExcelProperty(value = "LONGITUDE")
    private String longitude;
    @ExcelProperty(value = "RATING")
    private String rating;
    @ExcelProperty(value = "REVIEW COUNT")
    private String reviewCount;
    @ExcelProperty(value = "PRICE LEVEL")
    private String priceLevel;
    @ExcelProperty(value = "CATEGORIES")
    private String categories;
    @ExcelProperty(value = "IMAGE URL")
    private String imageUrl;
    @ExcelProperty(value = "RESTAURANT LINK")
    private String url;
    @ExcelProperty(value = "RESTAURANT PHONE")
    private String phone;
    @ExcelProperty(value = "CREATE DATE")
    private Date createDate;
    @ExcelProperty(value = "")
    private String attr1;
    @ExcelProperty(value = "")
    private String attr2;
    @ExcelProperty(value = "")
    private String attr3;
    @ExcelProperty(value = "")
    private String attr4;

}