package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

/**  
* Title: 查询记录  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class QueryRecord {
	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 * 查询日期
	 */
	private String QueryDate;
	/*
	 * 查询机构类型
	 */
	private String InquiringOrganizationType;
	/*
	 * 查询机构类型描述
	 */
	private String InquiringOrganizationTypeDesc;
	/*
	 * 查询机构
	 */
	private String InquiringOrganization;
	/*
	 * 查询原因
	 */
	private String InquiryCause;
	/**查询原因描述*/
	private String InquiryCauseDesc;
	
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getQueryDate() {
		return QueryDate;
	}
	public void setQueryDate(String queryDate) {
		QueryDate = queryDate;
	}
	public String getInquiringOrganizationType() {
		return InquiringOrganizationType;
	}
	public void setInquiringOrganizationType(String inquiringOrganizationType) {
		InquiringOrganizationType = inquiringOrganizationType;
	}
	public String getInquiringOrganization() {
		return InquiringOrganization;
	}
	public void setInquiringOrganization(String inquiringOrganization) {
		InquiringOrganization = inquiringOrganization;
	}
	public String getInquiryCause() {
		return InquiryCause;
	}
	public void setInquiryCause(String inquiryCause) {
		InquiryCause = inquiryCause;
	}
	@JSONField(serialize=false)
	public String getInquiryCauseDesc() {
		return InquiryCauseDesc;
	}
	public void setInquiryCauseDesc(String inquiryCauseDesc) {
		InquiryCauseDesc = inquiryCauseDesc;
	}
	@JSONField(serialize=false)
	public String getInquiringOrganizationTypeDesc() {
		return InquiringOrganizationTypeDesc;
	}
	public void setInquiringOrganizationTypeDesc(String inquiringOrganizationTypeDesc) {
		InquiringOrganizationTypeDesc = inquiringOrganizationTypeDesc;
	}
}
