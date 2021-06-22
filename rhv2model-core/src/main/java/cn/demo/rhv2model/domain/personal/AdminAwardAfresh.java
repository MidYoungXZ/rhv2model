package cn.demo.rhv2model.domain.personal;


/**  
* Title: 行政奖励记录(暂时未使用) 
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class AdminAwardAfresh{
	/*
	 *报告编号
	 */
	private String ReportId;
	/*
	 *编号
	 */
	private String Serino;
	/*
	 *奖励机构
	 */
	private String Organname;
	/*
	 *奖励内容
	 */
	private String Content;
	/*
	 *生效日期
	 */
	private String BeginDate;
	/*
	 *截止日期
	 */
	private String EndDate;
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getSerino() {
		return Serino;
	}
	public void setSerino(String serino) {
		Serino = serino;
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