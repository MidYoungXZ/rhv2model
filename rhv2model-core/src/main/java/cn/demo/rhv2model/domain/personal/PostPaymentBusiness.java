package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

/**  
* Title: 后付费业务  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class PostPaymentBusiness {
	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 * 后付费账户类型
	 */
	private String PaymentAccountType;
	/*
	 * 机构名称
	 */
	private String OrganizationName;
	/*
	 * 业务类型
	 */
	private String BusinessType;
	/*
	 * 业务类型描述
	 */
	private String BusinessTypeDesc;
	/*
	 * 业务开通日期
	 */
	private String BusinessOpeningDate;
	/*
	 * 当前缴费状态
	 */
	private String CurrentPaymentStatus;
	/*
	 * 当前缴费状态描述
	 */
	private String CurrentPaymentStatusDesc;
	/*
	 * 当前欠费金额
	 */
	private String CurrentArrearsAmount;
	/*
	 * 记账年月
	 */
	private String YearOfAccount;
	/*
	 * 最近 24 个月缴费记录
	 */
	private String PaymentRecord;
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getPaymentAccountType() {
		return PaymentAccountType;
	}
	public void setPaymentAccountType(String paymentAccountType) {
		PaymentAccountType = paymentAccountType;
	}
	public String getOrganizationName() {
		return OrganizationName;
	}
	public void setOrganizationName(String organizationName) {
		OrganizationName = organizationName;
	}
	public String getBusinessType() {
		return BusinessType;
	}
	public void setBusinessType(String businessType) {
		BusinessType = businessType;
	}
	public String getBusinessOpeningDate() {
		return BusinessOpeningDate;
	}
	public void setBusinessOpeningDate(String businessOpeningDate) {
		BusinessOpeningDate = businessOpeningDate;
	}
	public String getCurrentPaymentStatus() {
		return CurrentPaymentStatus;
	}
	public void setCurrentPaymentStatus(String currentPaymentStatus) {
		CurrentPaymentStatus = currentPaymentStatus;
	}
	public String getCurrentArrearsAmount() {
		return CurrentArrearsAmount;
	}
	public void setCurrentArrearsAmount(String currentArrearsAmount) {
		CurrentArrearsAmount = currentArrearsAmount;
	}
	public String getYearOfAccount() {
		return YearOfAccount;
	}
	public void setYearOfAccount(String yearOfAccount) {
		YearOfAccount = yearOfAccount;
	}
	public String getPaymentRecord() {
		return PaymentRecord;
	}
	public void setPaymentRecord(String paymentRecord) {
		PaymentRecord = paymentRecord;
	}
	@JSONField(serialize=false)
	public String getBusinessTypeDesc() {
		return BusinessTypeDesc;
	}
	public void setBusinessTypeDesc(String businessTypeDesc) {
		BusinessTypeDesc = businessTypeDesc;
	}
	@JSONField(serialize=false)
	public String getCurrentPaymentStatusDesc() {
		return CurrentPaymentStatusDesc;
	}
	public void setCurrentPaymentStatusDesc(String currentPaymentStatusDesc) {
		CurrentPaymentStatusDesc = currentPaymentStatusDesc;
	}
	
}
