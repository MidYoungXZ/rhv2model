package cn.demo.rhv2model.domain.personal;

/**  
* Title: 相关还款责任汇总信息  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class TotalRelatedRepayment {
	/*
	 * 报告编号
	 * */
	private String ReportId;
	/*
	 * 相关还款责任个数
	 * */
	private String RelatedRepayment;
	/*
	 * 借款人身份类别
	 * */
	private String BorrowerIdentityCategory;
	/*
	 * 相关还款责任类型
	 * */
	private String RepaymentResponsibility;
	/*
	 * 账户数
	 * */
	private String AccountNumber;
	/*
	 * 还款责任限额
	 * */
	private String RepaymentLiabilityLimit;
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
	public String getRelatedRepayment() {
		return RelatedRepayment;
	}
	public void setRelatedRepayment(String relatedRepayment) {
		RelatedRepayment = relatedRepayment;
	}
	public String getBorrowerIdentityCategory() {
		return BorrowerIdentityCategory;
	}
	public void setBorrowerIdentityCategory(String borrowerIdentityCategory) {
		BorrowerIdentityCategory = borrowerIdentityCategory;
	}
	public String getRepaymentResponsibility() {
		return RepaymentResponsibility;
	}
	public void setRepaymentResponsibility(String repaymentResponsibility) {
		RepaymentResponsibility = repaymentResponsibility;
	}
	public String getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}
	public String getRepaymentLiabilityLimit() {
		return RepaymentLiabilityLimit;
	}
	public void setRepaymentLiabilityLimit(String repaymentLiabilityLimit) {
		RepaymentLiabilityLimit = repaymentLiabilityLimit;
	}
	public String getBalance() {
		return Balance;
	}
	public void setBalance(String balance) {
		Balance = balance;
	}
	
}
