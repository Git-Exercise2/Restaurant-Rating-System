package io.renren.modules.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-01
 */
@Data
@Schema(name = "")
public class ReportDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "ID")
	private Long id;

	@SchemaProperty(name = "DISCUSS ID")
	private Long discussId;

	@SchemaProperty(name = "REPORT USER ID")
	private Long reportUserId;

	@SchemaProperty(name = "REPORT USER")
	private String reportUserName;

	@SchemaProperty(name = "REPORT TYPE")
	private String reportType;

	@SchemaProperty(name = "REPORT CONTENT")
	private String reportContent;

	@SchemaProperty(name = "REPORT HANDLING TYPE")
	private String reportHandleType;

	@SchemaProperty(name = "REPORT HANDLING")
	private String reportHandle;

	@SchemaProperty(name = "AUDIT STATUS")
	private String audit;

	@SchemaProperty(name = "AUDIT CONTENT")
	private String auditContent;

	@SchemaProperty(name = "CREATE DATE")
	private Date createDate;

	@SchemaProperty(name = "UPDATE DATE")
	private Date updateDate;

	@SchemaProperty(name = "WHO REPORT")
	private String attr1;

	@SchemaProperty(name = "")
	private String attr2;

	@SchemaProperty(name = "")
	private String attr3;

	@SchemaProperty(name = "")
	private String attr4;


}
