package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

/**  
* Title: 逾期（透支）  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class BeOverDue {
	/*
	 * 报告编号
	 * */
	private String ReportId;
	/*
	 * 业务类型数量
	 * */
	private String BusinessTypeNumber;
	/*
	 * 业务类型
	 * */
	private String BusinessType;
	/*
	 * 业务类型描述
	 * */
	private String BusinessTypeDesc;
	/*
	 * 账户数
	 * */
	private String AccountNumber;
	/*
	 * 月份数
	 * */
	private String MonthNumber;
	/*
	 * 单月最高逾期（透支）总额
	 * */
	private String MaximumOverdue;
	/*
	 * 最长逾期（透支）月数
	 * */
	private String TheLongestOverdue;
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getBusinessTypeNumber() {
		return BusinessTypeNumber;
	}
	public void setBusinessTypeNumber(String businessTypeNumber) {
		BusinessTypeNumber = businessTypeNumber;
	}
	public String getBusinessType() {
		return BusinessType;
	}
	public void setBusinessType(String businessType) {
		BusinessType = businessType;
	}
	public String getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}
	public String getMonthNumber() {
		return MonthNumber;
	}
	public void setMonthNumber(String monthNumber) {
		MonthNumber = monthNumber;
	}
	public String getMaximumOverdue() {
		return MaximumOverdue;
	}
	public void setMaximumOverdue(String maximumOverdue) {
		MaximumOverdue = maximumOverdue;
	}
	public String getTheLongestOverdue() {
		return TheLongestOverdue;
	}
	public void setTheLongestOverdue(String theLongestOverdue) {
		TheLongestOverdue = theLongestOverdue;
	}
	@JSONField(serialize=false)
	public String getBusinessTypeDesc() {
		return BusinessTypeDesc;
	}
	public void setBusinessTypeDesc(String businessTypeDesc) {
		BusinessTypeDesc = businessTypeDesc;
	}
	
}
