package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

/**  
* Title: 借贷账户信息  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class EssentialInformation {
	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 * 账户编号
	 */
	private String AccountNumber;
	/*
	 * 账户类型
	 */
	private String AccountType;
	/*
	 * 业务管理机构类型
	 */
	private String BusinessInstitutions;
	/*
	 * 业务管理机构
	 */
	private String BusinessInstitutionsName;
	/*
	 * 业务管理机构代码
	 */
	private String BusinessCode;
	/*
	 * 账户标识
	 */
	private String AccountIdentification;
	/*
	 * 授信协议编号
	 */
	private String CreditAgreementNumber;
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
	 * 币种
	 */
	private String Currency;
	/*
	 * 币种描述
	 */
	private String CurrencyDesc;
	/*
	 * 借款金额
	 */
	private String LoanAmount;
	/*
	 * 账户授信额度
	 */
	private String CreditLineOfAccount;
	/*
	 * 共享授信额度
	 */
	private String SharedCreditLine;
	/*
	 * 到期日期
	 */
	private String ExpirationDate;
	/*
	 * 还款方式
	 */
	private String RepaymentMethod;
	/*
	 * 还款方式描述
	 */
	private String RepaymentMethodDesc;
	/*
	 * 还款频率
	 */
	private String RepaymentFrequency;
	/*
	 * 还款频率描述
	 */
	private String RepaymentFrequencyDesc;
	/*
	 * 还款期数
	 */
	private String RepaymentPeriod;
	/*
	 * 担保方式
	 */
	private String GuaranteeMethod;
	/*
	 * 担保方式描述
	 */
	private String GuaranteeMethodDesc;
	/*
	 * 贷款发放形式
	 */
	private String LoanIssuance;
	/*
	 * 共同借款标志
	 */
	private String CommonBorrowingMark;
	/*
	 * 共同借款标志描述
	 */
	private String CommonBorrowingMarkDesc;
	/*
	 *债权转移时的还款状态
	 */
	private String RepaymentState;
	/*
	 *债权转移时的还款状态描述
	 */
	private String RepaymentStateDesc;
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}
	public String getAccountType() {
		return AccountType;
	}
	public void setAccountType(String accountType) {
		AccountType = accountType;
	}
	public String getBusinessInstitutions() {
		return BusinessInstitutions;
	}
	public void setBusinessInstitutions(String businessInstitutions) {
		BusinessInstitutions = businessInstitutions;
	}
	public String getBusinessCode() {
		return BusinessCode;
	}
	public void setBusinessCode(String businessCode) {
		BusinessCode = businessCode;
	}
	public String getAccountIdentification() {
		return AccountIdentification;
	}
	public void setAccountIdentification(String accountIdentification) {
		AccountIdentification = accountIdentification;
	}
	public String getCreditAgreementNumber() {
		return CreditAgreementNumber;
	}
	public void setCreditAgreementNumber(String creditAgreementNumber) {
		CreditAgreementNumber = creditAgreementNumber;
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
	public String getCurrency() {
		return Currency;
	}
	public void setCurrency(String currency) {
		Currency = currency;
	}
	public String getLoanAmount() {
		return LoanAmount;
	}
	public void setLoanAmount(String loanAmount) {
		LoanAmount = loanAmount;
	}
	public String getCreditLineOfAccount() {
		return CreditLineOfAccount;
	}
	public void setCreditLineOfAccount(String creditLineOfAccount) {
		CreditLineOfAccount = creditLineOfAccount;
	}
	public String getSharedCreditLine() {
		return SharedCreditLine;
	}
	public void setSharedCreditLine(String sharedCreditLine) {
		SharedCreditLine = sharedCreditLine;
	}
	public String getExpirationDate() {
		return ExpirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		ExpirationDate = expirationDate;
	}
	public String getRepaymentMethod() {
		return RepaymentMethod;
	}
	public void setRepaymentMethod(String repaymentMethod) {
		RepaymentMethod = repaymentMethod;
	}
	public String getRepaymentFrequency() {
		return RepaymentFrequency;
	}
	public void setRepaymentFrequency(String repaymentFrequency) {
		RepaymentFrequency = repaymentFrequency;
	}
	public String getRepaymentPeriod() {
		return RepaymentPeriod;
	}
	public void setRepaymentPeriod(String repaymentPeriod) {
		RepaymentPeriod = repaymentPeriod;
	}
	public String getGuaranteeMethod() {
		return GuaranteeMethod;
	}
	public void setGuaranteeMethod(String guaranteeMethod) {
		GuaranteeMethod = guaranteeMethod;
	}
	public String getLoanIssuance() {
		return LoanIssuance;
	}
	public void setLoanIssuance(String loanIssuance) {
		LoanIssuance = loanIssuance;
	}
	public String getCommonBorrowingMark() {
		return CommonBorrowingMark;
	}
	public void setCommonBorrowingMark(String commonBorrowingMark) {
		CommonBorrowingMark = commonBorrowingMark;
	}
	public String getRepaymentState() {
		return RepaymentState;
	}
	public void setRepaymentState(String repaymentState) {
		RepaymentState = repaymentState;
	}
	@JSONField(serialize=false)
	public String getRepaymentStateDesc() {
		return RepaymentStateDesc;
	}
	public void setRepaymentStateDesc(String repaymentStateDesc) {
		RepaymentStateDesc = repaymentStateDesc;
	}
	@JSONField(serialize=false)
	public String getBusinessTypesDesc() {
		return BusinessTypesDesc;
	}
	public void setBusinessTypesDesc(String businessTypesDesc) {
		BusinessTypesDesc = businessTypesDesc;
	}
	@JSONField(serialize=false)
	public String getRepaymentMethodDesc() {
		return RepaymentMethodDesc;
	}
	public void setRepaymentMethodDesc(String repaymentMethodDesc) {
		RepaymentMethodDesc = repaymentMethodDesc;
	}
	@JSONField(serialize=false)
	public String getRepaymentFrequencyDesc() {
		return RepaymentFrequencyDesc;
	}
	public void setRepaymentFrequencyDesc(String repaymentFrequencyDesc) {
		RepaymentFrequencyDesc = repaymentFrequencyDesc;
	}
	@JSONField(serialize=false)
	public String getGuaranteeMethodDesc() {
		return GuaranteeMethodDesc;
	}
	public void setGuaranteeMethodDesc(String guaranteeMethodDesc) {
		GuaranteeMethodDesc = guaranteeMethodDesc;
	}
	@JSONField(serialize=false)
	public String getCommonBorrowingMarkDesc() {
		return CommonBorrowingMarkDesc;
	}
	public void setCommonBorrowingMarkDesc(String commonBorrowingMarkDesc) {
		CommonBorrowingMarkDesc = commonBorrowingMarkDesc;
	}
	public String getBusinessInstitutionsName() {
		return BusinessInstitutionsName;
	}
	public void setBusinessInstitutionsName(String businessInstitutionsName) {
		BusinessInstitutionsName = businessInstitutionsName;
	}
	@JSONField(serialize=false)
	public String getCurrencyDesc() {
		return CurrencyDesc;
	}
	public void setCurrencyDesc(String currencyDesc) {
		CurrencyDesc = currencyDesc;
	}
	
}
