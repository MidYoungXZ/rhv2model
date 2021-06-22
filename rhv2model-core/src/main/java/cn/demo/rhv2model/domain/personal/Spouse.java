package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

/**  
* Title: 婚姻信息  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class Spouse {
	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 * 婚姻状况
	 */
	private String MaritalStatus;
	/*
	 * 配偶姓名
	 */
	private String Name;
	/*
	 * 配偶证件类型
	 */
	private String Certtype;
	/*
	 * 配偶证件类型描述
	 */
	private String CerttypeDesc;
	/*
	 * 配偶证件号码
	 */
	private String Certno;
	/*
	 * 配偶工作单位
	 */
	private String Employer;
	/*
	 * 配偶联系电话
	 */
	private String TelephoneNo;
	
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getCerttype() {
		return Certtype;
	}
	public void setCerttype(String certtype) {
		Certtype = certtype;
	}
	public String getCertno() {
		return Certno;
	}
	public void setCertno(String certno) {
		Certno = certno;
	}
	public String getEmployer() {
		return Employer;
	}
	public void setEmployer(String employer) {
		Employer = employer;
	}
	public String getTelephoneNo() {
		return TelephoneNo;
	}
	public void setTelephoneNo(String telephoneNo) {
		TelephoneNo = telephoneNo;
	}
	public String getMaritalStatus() {
		return MaritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		MaritalStatus = maritalStatus;
	}
	@JSONField(serialize=false)
	public String getCerttypeDesc() {
		return CerttypeDesc;
	}
	public void setCerttypeDesc(String certtypeDesc) {
		CerttypeDesc = certtypeDesc;
	}
	
}
