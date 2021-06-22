package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

/**  
* Title: 相关还款信息  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class RelatedRepaymentLiabilities {
	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 * 主借款人身份类别
	 */
	private String PrincipalBorrowerStatus;
	/*
	 * 业务管理机构类型
	 */
	private String BusinessManagementType;
	/*
	 * 业务管理机构
	 */
	private String BusinessManagementTypeDesc;
	/*
	 * 业务管理机构
	 */
	private String BusinessManagement;
	/*
	 * 业务种类
	 */
	private String BusinessTypes;
	/*
	 * 业务种类描述
	 */
	private String BusinessTypesDesc;
	/*
	 * 开立日期
	 */
	private String IssuanceDate;
	/*
	 * 到期日期
	 */
	private String ExpirationDate;
	/*
	 * 相关还款责任人类型
	 */
	private String RepaymentType;
	/*
	 * 相关还款责任人类型描述
	 */
	private String RepaymentTypeDesc;
	/*
	 * 相关还款责任限额
	 */
	private String RepaymentLimit;
	/*
	 * 币种
	 */
	private String Currency;
	/*
	 * 币种描述
	 */
	private String CurrencyDesc;
	/*
	 * 余额
	 */
	private String Balance;
	/*
	 * 五级分类
	 */
	private String FiveLevelClassification;
	/*
	 * 五级分类
	 */
	private String FiveLevelClassificationDesc;
	/*
	 * 账户类型
	 */
	private String AccountType;
	/*
	 * 还款状态
	 */
	private String RepaymentState;
	/*
	 * 还款状态描述
	 */
	private String RepaymentStateDesc;
	/*
	 * 逾期月数
	 */
	private String OverdueMonths;
	/*
	 * 信息报告日期
	 */
	private String DateOfReport;
	/*
	 * 保证合同编号
	 */
	private String GuaranteeNumber ;
	/*
	 * 相关还款责任金额
	 * */
	private String RelateRepayAmount;
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getPrincipalBorrowerStatus() {
		return PrincipalBorrowerStatus;
	}
	public void setPrincipalBorrowerStatus(String principalBorrowerStatus) {
		PrincipalBorrowerStatus = principalBorrowerStatus;
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
	public String getBusinessTypes() {
		return BusinessTypes;
	}
	public void setBusinessTypes(String businessTypes) {
		BusinessTypes = businessTypes;
	}
	public String getIssuanceDate() {
		return IssuanceDate;
	}
	public void setIssuanceDate(String issuanceDate) {
		IssuanceDate = issuanceDate;
	}
	public String getExpirationDate() {
		return ExpirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		ExpirationDate = expirationDate;
	}
	public String getRepaymentType() {
		return RepaymentType;
	}
	public void setRepaymentType(String repaymentType) {
		RepaymentType = repaymentType;
	}
	public String getRepaymentLimit() {
		return RepaymentLimit;
	}
	public void setRepaymentLimit(String repaymentLimit) {
		RepaymentLimit = repaymentLimit;
	}
	public String getCurrency() {
		return Currency;
	}
	public void setCurrency(String currency) {
		Currency = currency;
	}
	public String getBalance() {
		return Balance;
	}
	public void setBalance(String balance) {
		Balance = balance;
	}
	public String getFiveLevelClassification() {
		return FiveLevelClassification;
	}
	public void setFiveLevelClassification(String fiveLevelClassification) {
		FiveLevelClassification = fiveLevelClassification;
	}
	public String getAccountType() {
		return AccountType;
	}
	public void setAccountType(String accountType) {
		AccountType = accountType;
	}
	public String getRepaymentState() {
		return RepaymentState;
	}
	public void setRepaymentState(String repaymentState) {
		RepaymentState = repaymentState;
	}
	public String getOverdueMonths() {
		return OverdueMonths;
	}
	public void setOverdueMonths(String overdueMonths) {
		OverdueMonths = overdueMonths;
	}
	public String getDateOfReport() {
		return DateOfReport;
	}
	public void setDateOfReport(String dateOfReport) {
		DateOfReport = dateOfReport;
	}
	public String getGuaranteeNumber() {
		return GuaranteeNumber;
	}
	public void setGuaranteeNumber(String guaranteeNumber) {
		GuaranteeNumber = guaranteeNumber;
	}
	public String getRelateRepayAmount() {
		return RelateRepayAmount;
	}
	public void setRelateRepayAmount(String relateRepayAmount) {
		RelateRepayAmount = relateRepayAmount;
	}
	@JSONField(serialize=false)
	public String getBusinessTypesDesc() {
		return BusinessTypesDesc;
	}
	public void setBusinessTypesDesc(String businessTypesDesc) {
		BusinessTypesDesc = businessTypesDesc;
	}
	@JSONField(serialize=false)
	public String getRepaymentTypeDesc() {
		return RepaymentTypeDesc;
	}
	public void setRepaymentTypeDesc(String repaymentTypeDesc) {
		RepaymentTypeDesc = repaymentTypeDesc;
	}
	@JSONField(serialize=false)
	public String getFiveLevelClassificationDesc() {
		return FiveLevelClassificationDesc;
	}
	public void setFiveLevelClassificationDesc(String fiveLevelClassificationDesc) {
		FiveLevelClassificationDesc = fiveLevelClassificationDesc;
	}
	@JSONField(serialize=false)
	public String getRepaymentStateDesc() {
		return RepaymentStateDesc;
	}
	public void setRepaymentStateDesc(String repaymentStateDesc) {
		RepaymentStateDesc = repaymentStateDesc;
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
