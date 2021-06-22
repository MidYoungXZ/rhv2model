package cn.demo.rhv2model.domain.personal;

/**  
* Title: 查询记录汇总  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class QueryRecordSummary {
	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 * 最近 1 个月内的查询机构数（贷款审批）
	 */
	private String LoanApproval;
	/*
	 * 最近 1 个月内的查询机构数（信用卡审批）
	 */
	private String CreditCardApproval;
	/*
	 * 最近 1 个月内的查询次数（贷款审批）
	 */
	private String LoanApprovalNumber;
	/*
	 * 最近 1 个月内的查询次数（信用卡审批）
	 */
	private String CreditCardApprovalNumber;
	/*
	 * 最近 1 个月内的查询次数（本人查询）
	 */
	private String IqueryTimes;
	/*
	 * 最近 2 年内的查询次数（贷后管理）
	 */
	private String EnquiryAfterLoanManagement;
	/*
	 * 最近 2 年内的查询次数（担保资格审查）
	 */
	private String GuaranteeQualificationExamination;
	/*
	 * 最近 2 年内的查询次数（特约商户实名审查）
	 */
	private String SpecialMerchantEnquiries;
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getLoanApproval() {
		return LoanApproval;
	}
	public void setLoanApproval(String loanApproval) {
		LoanApproval = loanApproval;
	}
	public String getCreditCardApproval() {
		return CreditCardApproval;
	}
	public void setCreditCardApproval(String creditCardApproval) {
		CreditCardApproval = creditCardApproval;
	}
	public String getLoanApprovalNumber() {
		return LoanApprovalNumber;
	}
	public void setLoanApprovalNumber(String loanApprovalNumber) {
		LoanApprovalNumber = loanApprovalNumber;
	}
	public String getCreditCardApprovalNumber() {
		return CreditCardApprovalNumber;
	}
	public void setCreditCardApprovalNumber(String creditCardApprovalNumber) {
		CreditCardApprovalNumber = creditCardApprovalNumber;
	}
	public String getIqueryTimes() {
		return IqueryTimes;
	}
	public void setIqueryTimes(String iqueryTimes) {
		IqueryTimes = iqueryTimes;
	}
	public String getEnquiryAfterLoanManagement() {
		return EnquiryAfterLoanManagement;
	}
	public void setEnquiryAfterLoanManagement(String enquiryAfterLoanManagement) {
		EnquiryAfterLoanManagement = enquiryAfterLoanManagement;
	}
	public String getGuaranteeQualificationExamination() {
		return GuaranteeQualificationExamination;
	}
	public void setGuaranteeQualificationExamination(String guaranteeQualificationExamination) {
		GuaranteeQualificationExamination = guaranteeQualificationExamination;
	}
	public String getSpecialMerchantEnquiries() {
		return SpecialMerchantEnquiries;
	}
	public void setSpecialMerchantEnquiries(String specialMerchantEnquiries) {
		SpecialMerchantEnquiries = specialMerchantEnquiries;
	}
	
}
