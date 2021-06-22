package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

/**  
* Title: 被追偿汇总信息  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class Recourse {
	/*
	 * 报告编号
	 * */
	private String ReportId;
	/*
	 * 账户数合计
	 * */
	private String TotalAccountNumber;
	/*
	 * 余额合计
	 * */
	private String BalanceOfBalance;
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
	 * 余额
	 * */
	private String Balance;
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getTotalAccountNumber() {
		return TotalAccountNumber;
	}
	public void setTotalAccountNumber(String totalAccountNumber) {
		TotalAccountNumber = totalAccountNumber;
	}
	public String getBalanceOfBalance() {
		return BalanceOfBalance;
	}
	public void setBalanceOfBalance(String balanceOfBalance) {
		BalanceOfBalance = balanceOfBalance;
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
	public String getBalance() {
		return Balance;
	}
	public void setBalance(String balance) {
		Balance = balance;
	}
	@JSONField(serialize=false)
	public String getBusinessTypeDesc() {
		return BusinessTypeDesc;
	}
	public void setBusinessTypeDesc(String businessTypeDesc) {
		BusinessTypeDesc = businessTypeDesc;
	}
	
}
