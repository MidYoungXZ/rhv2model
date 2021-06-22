package cn.demo.rhv2model.domain.personal;

/**  
* Title: 最近5年记录信息  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class LastFiveYears {
	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 * 起始年月
	 */
	private String BeginningYear;
	/*
	 * 截止年月
	 */
	private String EndYear;
	/*
	 * 月数
	 */
	private String Mom;
	/*
	 * 月份
	 */
	private String Month;
	/*
	 * 逾期（透支）总额
	 */
	private String TotalOverdue;
	/*
	 * 还款状态
	 */
	private String RepaymentState;
	/*
	 * 账户编号
	 */
	private String AccountNumber;
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getBeginningYear() {
		return BeginningYear;
	}
	public void setBeginningYear(String beginningYear) {
		BeginningYear = beginningYear;
	}
	public String getEndYear() {
		return EndYear;
	}
	public void setEndYear(String endYear) {
		EndYear = endYear;
	}
	public String getMom() {
		return Mom;
	}
	public void setMom(String mom) {
		Mom = mom;
	}
	public String getMonth() {
		return Month;
	}
	public void setMonth(String month) {
		Month = month;
	}
	public String getTotalOverdue() {
		return TotalOverdue;
	}
	public void setTotalOverdue(String totalOverdue) {
		TotalOverdue = totalOverdue;
	}
	public String getRepaymentState() {
		return RepaymentState;
	}
	public void setRepaymentState(String repaymentState) {
		RepaymentState = repaymentState;
	}
	public String getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}
	
}
