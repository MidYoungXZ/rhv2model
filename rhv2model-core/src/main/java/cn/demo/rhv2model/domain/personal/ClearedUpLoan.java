package cn.demo.rhv2model.domain.personal;


/**  
* Title:授信协议下已结清贷款(暂时未使用--解析部分注释了) 
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class ClearedUpLoan {
	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 * 最早开立日期
	 */
	private String EarliestOpeningDate;
	/*
	 * 最晚关闭日期
	 */
	private String LatestClosingDate;
	/*
	 * 借款金额
	 */
	private String LoanAmount;
	/*
	 * 账户数
	 */
	private String AccountNumber;
	/*
	 * 是否有贷款曾被认定为呆账
	 */
	private String BadDebt;
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getEarliestOpeningDate() {
		return EarliestOpeningDate;
	}
	public void setEarliestOpeningDate(String earliestOpeningDate) {
		EarliestOpeningDate = earliestOpeningDate;
	}
	public String getLatestClosingDate() {
		return LatestClosingDate;
	}
	public void setLatestClosingDate(String latestClosingDate) {
		LatestClosingDate = latestClosingDate;
	}
	public String getLoanAmount() {
		return LoanAmount;
	}
	public void setLoanAmount(String loanAmount) {
		LoanAmount = loanAmount;
	}
	public String getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}
	public String getBadDebt() {
		return BadDebt;
	}
	public void setBadDebt(String badDebt) {
		BadDebt = badDebt;
	}
	
}
