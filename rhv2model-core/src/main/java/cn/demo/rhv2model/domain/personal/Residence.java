package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

/**  
* Title: 居住信息  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class Residence {
	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 * 居住地址
	 */
	private String Address;
	/*
	 * 居住状况
	 */
	private String ResidenceType;
	/*
	 * 居住状况描述
	 */
	private String ResidenceTypeDesc;
	/*
	 * 信息更新日期
	 */
	private String GetTime;
	/*
	 * 住宅电话
	 */
	private String HomePhone;
	
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getResidenceType() {
		return ResidenceType;
	}
	public void setResidenceType(String residenceType) {
		ResidenceType = residenceType;
	}
	public String getGetTime() {
		return GetTime;
	}
	public void setGetTime(String getTime) {
		GetTime = getTime;
	}
	public String getHomePhone() {
		return HomePhone;
	}
	public void setHomePhone(String homePhone) {
		HomePhone = homePhone;
	}
	@JSONField(serialize=false)
	public String getResidenceTypeDesc() {
		return ResidenceTypeDesc;
	}
	public void setResidenceTypeDesc(String residenceTypeDesc) {
		ResidenceTypeDesc = residenceTypeDesc;
	}
	
}
