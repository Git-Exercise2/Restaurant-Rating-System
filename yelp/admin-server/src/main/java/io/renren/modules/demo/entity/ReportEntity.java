package io.renren.modules.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-01
 */
@Data
@TableName("report")
public class ReportEntity {

    /**
     * ID
     */
	private Long id;
    /**
     * DISCUSS ID
     */
	private Long discussId;
    /**
     * REPORT USER ID
     */
	private Long reportUserId;
    /**
     * REPORT USER
     */
	private String reportUserName;
    /**
     * REPORT TYPE
     */
	private String reportType;
    /**
     * REPORT CONTENT
     */
	private String reportContent;
    /**
     * REPORT HANDLING TYPE
     */
	private String reportHandleType;
    /**
     * REPORT HANDLING
     */
	private String reportHandle;
    /**
     * AUDIT STATUS
     */
	private String audit;
    /**
     * AUDIT CONTENT
     */
	private String auditContent;
    /**
     * CREATE DATE
     */
	private Date createDate;
    /**
     * UPDATE DATE
     */
	private Date updateDate;
    /**
     * 
     */
	private String attr1;
    /**
     * 
     */
	private String attr2;
    /**
     * 
     */
	private String attr3;
    /**
     * 
     */
	private String attr4;
}