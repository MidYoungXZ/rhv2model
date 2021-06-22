package cn.demo.rhv2model.domain.personal;

/**  
* Title: 授信协议下已结清贷款历史逾期信息  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class Overdue {
	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 * 起始年月
	 */
	private String StartingYear;
	/*
	 * 截止年月
	 */
	private String CutOffYear;
	/*
	 * 月份
	 */
	private String Month;
	/*
	 * 逾期持续月数
	 */
	private String OverdueMonths;
	/*
	 * 逾期总额
	 */
	private String TotalOverdue;
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getStartingYear() {
		return StartingYear;
	}
	public void setStartingYear(String startingYear) {
		StartingYear = startingYear;
	}
	public String getCutOffYear() {
		return CutOffYear;
	}
	public void setCutOffYear(String cutOffYear) {
		CutOffYear = cutOffYear;
	}
	public String getMonth() {
		return Month;
	}
	public void setMonth(String month) {
		Month = month;
	}
	public String getOverdueMonths() {
		return OverdueMonths;
	}
	public void setOverdueMonths(String overdueMonths) {
		OverdueMonths = overdueMonths;
	}
	public String getTotalOverdue() {
		return TotalOverdue;
	}
	public void setTotalOverdue(String totalOverdue) {
		TotalOverdue = totalOverdue;
	}
	
}
