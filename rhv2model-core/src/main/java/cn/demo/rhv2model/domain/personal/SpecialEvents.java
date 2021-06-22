package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

/**  
* Title: 特殊事件  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class SpecialEvents {
	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 * 特殊事件说明个数
	 */
	private String SpecialEventDescription;
	/*
	 * 特殊事件发生月份
	 */
	private String SpecialEventsMonth;
	/*
	 * 特殊事件类型
	 */
	private String SpecialEventType;
	/*
	 * 特殊事件类型描述
	 */
	private String SpecialEventTypeDesc;
	/*
	 * 账户编号
	 */
	private String AccountNumber;
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getSpecialEventDescription() {
		return SpecialEventDescription;
	}
	public void setSpecialEventDescription(String specialEventDescription) {
		SpecialEventDescription = specialEventDescription;
	}
	public String getSpecialEventsMonth() {
		return SpecialEventsMonth;
	}
	public void setSpecialEventsMonth(String specialEventsMonth) {
		SpecialEventsMonth = specialEventsMonth;
	}
	public String getSpecialEventType() {
		return SpecialEventType;
	}
	public void setSpecialEventType(String specialEventType) {
		SpecialEventType = specialEventType;
	}
	public String getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}
	@JSONField(serialize=false)
	public String getSpecialEventTypeDesc() {
		return SpecialEventTypeDesc;
	}
	public void setSpecialEventTypeDesc(String specialEventTypeDesc) {
		SpecialEventTypeDesc = specialEventTypeDesc;
	}
	
}
