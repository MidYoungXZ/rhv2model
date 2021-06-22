package cn.demo.rhv2model.domain.personal;


/**  
* Title: 行政处罚记录 
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class AdminPunishment{
	/*
	 *报告编号
	 */
	private String ReportId;
	/*
	 *处罚机构
	 */
	private String Organname;
	/*
	 *处罚内容
	 */
	private String Content;
	/*
	 *处罚金额
	 */
	private String Money;
	/*
	 *生效日期
	 */
	private String BeginDate;
	/*
	 *截止日期
	 */
	private String EndDate;
	/*
	 *行政复议结果
	 */
	private String Result;
	
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getOrganname() {
		return Organname;
	}
	public void setOrganname(String organname) {
		Organname = organname;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getMoney() {
		return Money;
	}
	public void setMoney(String money) {
		Money = money;
	}
	public String getBeginDate() {
		return BeginDate;
	}
	public void setBeginDate(String beginDate) {
		BeginDate = beginDate;
	}
	public String getEndDate() {
		return EndDate;
	}
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
	public String getResult() {
		return Result;
	}
	public void setResult(String result) {
		Result = result;
	}
}