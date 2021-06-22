package cn.demo.rhv2model.domain.personal;

/**  
* Title: 电话信息  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class PhoneInfo {
	/**
	 * 报告编号
	 * */
	private String ReportId;
	/**
	 * 手机个数
	 * */
	private String NumberMobile;
	/**
	 * 手机号码
	 * */
	private String Mobile;
	/**
	 * 信息更新日期
	 * */
	private String GetTime;
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getNumberMobile() {
		return NumberMobile;
	}
	public void setNumberMobile(String numberMobile) {
		NumberMobile = numberMobile;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getGetTime() {
		return GetTime;
	}
	public void setGetTime(String getTime) {
		GetTime = getTime;
	}
	
}
