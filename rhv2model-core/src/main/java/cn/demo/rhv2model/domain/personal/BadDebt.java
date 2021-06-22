package cn.demo.rhv2model.domain.personal;


/**  
* Title: 呆账  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class BadDebt {
	/*
	 * 报告编号
	 * */
	private String ReportId;
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
	
}
