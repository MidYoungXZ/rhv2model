package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

/**  
* Title: 授信协议基本信息  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class CreditAgreement {
	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 * 授信协议编号
	 */
	private String CreditAgreementNumber;
	/*
	 * 业务管理机构类型
	 */
	private String BusinessManagementType;
	/*
	 * 业务管理机构类型名称
	 */
	private String BusinessManagementTypeDesc;
	/*
	 * 业务管理机构
	 */
	private String BusinessManagement;
	/*
	 * 授信协议标识
	 */
	private String CreditAgreementMark;
	/*
	 * 授信额度用途
	 */
	private String CreditLineUsage;
	/*
	 * 授信额度用途描述
	 */
	private String CreditLineUsageDesc;
	/*
	 * 授信额度
	 */
	private String CreditLine;
	/*
	 * 币种
	 */
	private String Currency;
	/*
	 * 币种描述
	 */
	private String CurrencyDesc;
	/*
	 * 生效日期
	 */
	private String EffectiveDate;
	/*
	 * 到期日期
	 */
	private String ExpirationDate;
	/*
	 * 授信协议状态
	 */
	private String CreditAgreementStatus;
	/*
	 * 授信限额
	 */
	private String CreditLimit;
	/*
	 * 授信限额编号
	 */
	private String CreditLimitNumber;
	/*
	 * 已用额度
	 */
	private String AmountUsed;
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getCreditAgreementNumber() {
		return CreditAgreementNumber;
	}
	public void setCreditAgreementNumber(String creditAgreementNumber) {
		CreditAgreementNumber = creditAgreementNumber;
	}
	public String getBusinessManagementType() {
		return BusinessManagementType;
	}
	public void setBusinessManagementType(String businessManagementType) {
		BusinessManagementType = businessManagementType;
	}
	public String getBusinessManagement() {
		return BusinessManagement;
	}
	public void setBusinessManagement(String businessManagement) {
		BusinessManagement = businessManagement;
	}
	public String getCreditAgreementMark() {
		return CreditAgreementMark;
	}
	public void setCreditAgreementMark(String creditAgreementMark) {
		CreditAgreementMark = creditAgreementMark;
	}
	public String getCreditLineUsage() {
		return CreditLineUsage;
	}
	public void setCreditLineUsage(String creditLineUsage) {
		CreditLineUsage = creditLineUsage;
	}
	public String getCreditLine() {
		return CreditLine;
	}
	public void setCreditLine(String creditLine) {
		CreditLine = creditLine;
	}
	public String getCurrency() {
		return Currency;
	}
	public void setCurrency(String currency) {
		Currency = currency;
	}
	public String getEffectiveDate() {
		return EffectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		EffectiveDate = effectiveDate;
	}
	public String getExpirationDate() {
		return ExpirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		ExpirationDate = expirationDate;
	}
	public String getCreditAgreementStatus() {
		return CreditAgreementStatus;
	}
	public void setCreditAgreementStatus(String creditAgreementStatus) {
		CreditAgreementStatus = creditAgreementStatus;
	}
	public String getCreditLimit() {
		return CreditLimit;
	}
	public void setCreditLimit(String creditLimit) {
		CreditLimit = creditLimit;
	}
	public String getCreditLimitNumber() {
		return CreditLimitNumber;
	}
	public void setCreditLimitNumber(String creditLimitNumber) {
		CreditLimitNumber = creditLimitNumber;
	}
	public String getAmountUsed() {
		return AmountUsed;
	}
	public void setAmountUsed(String amountUsed) {
		AmountUsed = amountUsed;
	}
	@JSONField(serialize=false)
	public String getCreditLineUsageDesc() {
		return CreditLineUsageDesc;
	}
	public void setCreditLineUsageDesc(String creditLineUsageDesc) {
		CreditLineUsageDesc = creditLineUsageDesc;
	}
	@JSONField(serialize=false)
	public String getBusinessManagementTypeDesc() {
		return BusinessManagementTypeDesc;
	}
	public void setBusinessManagementTypeDesc(String businessManagementTypeDesc) {
		BusinessManagementTypeDesc = businessManagementTypeDesc;
	}
	@JSONField(serialize=false)
	public String getCurrencyDesc() {
		return CurrencyDesc;
	}
	public void setCurrencyDesc(String currencyDesc) {
		CurrencyDesc = currencyDesc;
	}
	
}
