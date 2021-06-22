package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

/**  
* Title: 信贷交易信息概要 
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class CreditTransaction {
	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 * 账户数合计
	 */
	private String TotalAccountNumber;
	/*
	 * 业务类型数量
	 */
	private String BusinessTypeNumber;
	/*
	 * 业务类型
	 */
	private String BusinessType;
	/*
	 * 业务类型描述
	 */
	private String BusinessTypeDesc;
	/*
	 * 业务大类
	 */
	private String BusinessClass;
	/*
	 * 账户数
	 */
	private String AccountNumber;
	/*
	 * 首笔业务发放月份
	 */
	private String FirstBusiness;
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
	public String getBusinessClass() {
		return BusinessClass;
	}
	public void setBusinessClass(String businessClass) {
		BusinessClass = businessClass;
	}
	public String getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}
	public String getFirstBusiness() {
		return FirstBusiness;
	}
	public void setFirstBusiness(String firstBusiness) {
		FirstBusiness = firstBusiness;
	}
	@JSONField(serialize=false)
	public String getBusinessTypeDesc() {
		return BusinessTypeDesc;
	}
	public void setBusinessTypeDesc(String businessTypeDesc) {
		BusinessTypeDesc = businessTypeDesc;
	}
	
}
