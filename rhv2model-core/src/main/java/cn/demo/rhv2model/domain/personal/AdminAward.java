package cn.demo.rhv2model.domain.personal;


/**  
* Title:行政奖励记录
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class AdminAward {
	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 * 奖励机构
	 */
	private String Organname;
	/*
	 * 奖励内容
	 */
	private String Content;
	/*
	 * 生效日期
	 */
	private String BeginDate;
	/*
	 * 截止日期
	 */
	private String EndDate;
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
}
