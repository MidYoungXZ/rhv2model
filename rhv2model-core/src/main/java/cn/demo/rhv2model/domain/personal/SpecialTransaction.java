package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

/**  
* Title: 特殊交易  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class SpecialTransaction {
	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 * 特殊交易个数
	 */
	private String SpecialTransactionNumber;
	/*
	 * 特殊交易类型
	 */
	private String SpecialTransactionType;
	/*
	 * 特殊交易类型描述
	 */
	private String SpecialTransactionTypeDesc;
	/*
	 * 特殊交易发生日期
	 */
	private String SpecialTransactionDate;
	/*
	 * 到期日期变更月数
	 */
	private String ExpirationDate;
	/*
	 * 特殊交易发生金额
	 */
	private String SpecialTransactionAmount;
	/*
	 * 特殊交易明细记录
	 */
	private String SpecialTransactionDetails;
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
	public String getSpecialTransactionNumber() {
		return SpecialTransactionNumber;
	}
	public void setSpecialTransactionNumber(String specialTransactionNumber) {
		SpecialTransactionNumber = specialTransactionNumber;
	}
	public String getSpecialTransactionType() {
		return SpecialTransactionType;
	}
	public void setSpecialTransactionType(String specialTransactionType) {
		SpecialTransactionType = specialTransactionType;
	}
	public String getSpecialTransactionDate() {
		return SpecialTransactionDate;
	}
	public void setSpecialTransactionDate(String specialTransactionDate) {
		SpecialTransactionDate = specialTransactionDate;
	}
	public String getExpirationDate() {
		return ExpirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		ExpirationDate = expirationDate;
	}
	public String getSpecialTransactionAmount() {
		return SpecialTransactionAmount;
	}
	public void setSpecialTransactionAmount(String specialTransactionAmount) {
		SpecialTransactionAmount = specialTransactionAmount;
	}
	public String getSpecialTransactionDetails() {
		return SpecialTransactionDetails;
	}
	public void setSpecialTransactionDetails(String specialTransactionDetails) {
		SpecialTransactionDetails = specialTransactionDetails;
	}
	public String getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}
	@JSONField(serialize=false)
	public String getSpecialTransactionTypeDesc() {
		return SpecialTransactionTypeDesc;
	}
	public void setSpecialTransactionTypeDesc(String specialTransactionTypeDesc) {
		SpecialTransactionTypeDesc = specialTransactionTypeDesc;
	}
	
}
