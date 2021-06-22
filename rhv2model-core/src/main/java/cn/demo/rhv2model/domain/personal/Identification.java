package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;


/**  
* Title: 报告头  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class Identification {
	/*
	 *报告编号
	 */
	private String reportId;
	/*
	 *人行报告编号
	 */
	private String reportSN;
	/*
	 *查询请求时间
	 */
	private String queryTime;
	/*
	 *报告时间
	 */
	private String reportCreateTime;
	/*
	 *被查询者姓名
	 */
	private String name;
	/*
	 *被查询者证件类型
	 */
	private String certtype;
	/*
	 *被查询者证件类型描述
	 */
	private String certtypeDesc;
	/*
	 *被查询者证件号码
	 */
	private String certno;
	/*
	 *查询机构代码
	 */
	private String queryOrg;
	/*
	 *查询机构名称
	 */
	private String queryOrgName;
	/*
	 *查询原因代码
	 */
	private String reasonCode;
	/*
	 *查询原因描述
	 */
	private String reasonCodeDesc;
	/*
	 *防欺诈警示标志
	 */
	private String antiFraudSign;
	/*
	 *防欺诈警示联系电话
	 */
	private String antiFraudTelephone;
	/*
	 *防欺诈警示生效日期
	 */
	private String effectiveDate;
	/*
	 *防欺诈警示截止日期
	 */
	private String closingDate;
	/*
	 *异议标注数目
	 */
	private String dissentingAnnotation;
	private List otherDocumentsList;//其他证件list
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getReportSN() {
		return reportSN;
	}
	public void setReportSN(String reportSN) {
		this.reportSN = reportSN;
	}
	public String getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}
	public String getReportCreateTime() {
		return reportCreateTime;
	}
	public void setReportCreateTime(String reportCreateTime) {
		this.reportCreateTime = reportCreateTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCerttype() {
		return certtype;
	}
	public void setCerttype(String certtype) {
		this.certtype = certtype;
	}
	public String getCertno() {
		return certno;
	}
	public void setCertno(String certno) {
		this.certno = certno;
	}
	public String getQueryOrg() {
		return queryOrg;
	}
	public void setQueryOrg(String queryOrg) {
		this.queryOrg = queryOrg;
	}
	public String getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	public String getAntiFraudSign() {
		return antiFraudSign;
	}
	public void setAntiFraudSign(String antiFraudSign) {
		this.antiFraudSign = antiFraudSign;
	}
	public String getAntiFraudTelephone() {
		return antiFraudTelephone;
	}
	public void setAntiFraudTelephone(String antiFraudTelephone) {
		this.antiFraudTelephone = antiFraudTelephone;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getClosingDate() {
		return closingDate;
	}
	public void setClosingDate(String closingDate) {
		this.closingDate = closingDate;
	}
	public String getDissentingAnnotation() {
		return dissentingAnnotation;
	}
	public void setDissentingAnnotation(String dissentingAnnotation) {
		this.dissentingAnnotation = dissentingAnnotation;
	}
	@JSONField(serialize=false)
	public List getOtherDocumentsList() {
		return otherDocumentsList;
	}
	public void setOtherDocumentsList(List otherDocumentsList) {
		this.otherDocumentsList = otherDocumentsList;
	}
	@JSONField(serialize=false)
	public String getReasonCodeDesc() {
		return reasonCodeDesc;
	}
	public void setReasonCodeDesc(String reasonCodeDesc) {
		this.reasonCodeDesc = reasonCodeDesc;
	}
	@JSONField(serialize=false)
	public String getCerttypeDesc() {
		return certtypeDesc;
	}
	public void setCerttypeDesc(String certtypeDesc) {
		this.certtypeDesc = certtypeDesc;
	}
	@JSONField(serialize=false)
	public String getQueryOrgName() {
		return queryOrgName;
	}
	public void setQueryOrgName(String queryOrgName) {
		this.queryOrgName = queryOrgName;
	}
}
