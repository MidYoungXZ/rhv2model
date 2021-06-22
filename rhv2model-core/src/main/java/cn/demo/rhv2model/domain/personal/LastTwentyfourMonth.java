package cn.demo.rhv2model.domain.personal;

/**  
* Title: 最近24个月还款记录信息  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class LastTwentyfourMonth {
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
	 * 月份
	 */
	private String Month;
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
	public String getMonth() {
		return Month;
	}
	public void setMonth(String month) {
		Month = month;
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
