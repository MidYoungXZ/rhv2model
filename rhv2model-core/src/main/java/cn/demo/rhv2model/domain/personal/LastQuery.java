package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

/**  
* Title: 上一次查询记录信息 
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class LastQuery {
	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 * 上一次查询时间
	 */
	private String LastQueryDate;
	/*
	 * 上一次查询机构机构类型
	 */
	private String LastQueryOrganizationType;
	/*
	 * 上一次查询机构机构类型描述
	 */
	private String LastQueryOrganizationTypeDesc;
	/*
	 * 上一次查询机构代码
	 */
	private String LastQueryCode;
	/*
	 * 上一次查询原因
	 */
	private String LastQueryReason;
	/*
	 * 上一次查询原因描述
	 */
	private String LastQueryReasonDesc;
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getLastQueryDate() {
		return LastQueryDate;
	}
	public void setLastQueryDate(String lastQueryDate) {
		LastQueryDate = lastQueryDate;
	}
	public String getLastQueryOrganizationType() {
		return LastQueryOrganizationType;
	}
	public void setLastQueryOrganizationType(String lastQueryOrganizationType) {
		LastQueryOrganizationType = lastQueryOrganizationType;
	}
	public String getLastQueryCode() {
		return LastQueryCode;
	}
	public void setLastQueryCode(String lastQueryCode) {
		LastQueryCode = lastQueryCode;
	}
	public String getLastQueryReason() {
		return LastQueryReason;
	}
	public void setLastQueryReason(String lastQueryReason) {
		LastQueryReason = lastQueryReason;
	}
	@JSONField(serialize=false)
	public String getLastQueryOrganizationTypeDesc() {
		return LastQueryOrganizationTypeDesc;
	}
	public void setLastQueryOrganizationTypeDesc(String lastQueryOrganizationTypeDesc) {
		LastQueryOrganizationTypeDesc = lastQueryOrganizationTypeDesc;
	}
	@JSONField(serialize=false)
	public String getLastQueryReasonDesc() {
		return LastQueryReasonDesc;
	}
	public void setLastQueryReasonDesc(String lastQueryReasonDesc) {
		LastQueryReasonDesc = lastQueryReasonDesc;
	}
	
}
