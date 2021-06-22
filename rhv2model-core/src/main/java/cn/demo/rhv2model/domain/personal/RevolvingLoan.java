package cn.demo.rhv2model.domain.personal;

/**  
* Title: 循环额度下贷款汇总信息  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class RevolvingLoan {
	/*
	 * 报告编号
	 * */
	private String ReportId;
	/*
	 * 管理机构（法人）数
	 * */
	private String ManagementAgency;
	/*
	 * 账户数
	 * */
	private String AccountNumber;
	/*
	 * 授信总额
	 * */
	private String TotalCredit;
	/*
	 * 余额
	 * */
	private String Balance;
	/*
	 * 最近6个月平均应还款
	 * */
	private String AverageRepayment;
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getManagementAgency() {
		return ManagementAgency;
	}
	public void setManagementAgency(String managementAgency) {
		ManagementAgency = managementAgency;
	}
	public String getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}
	public String getTotalCredit() {
		return TotalCredit;
	}
	public void setTotalCredit(String totalCredit) {
		TotalCredit = totalCredit;
	}
	public String getBalance() {
		return Balance;
	}
	public void setBalance(String balance) {
		Balance = balance;
	}
	public String getAverageRepayment() {
		return AverageRepayment;
	}
	public void setAverageRepayment(String averageRepayment) {
		AverageRepayment = averageRepayment;
	}
	
	
}
