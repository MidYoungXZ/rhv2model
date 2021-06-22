package cn.demo.rhv2model.domain.personal;

/**  
* Title: 欠税记录信息  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class TaxArrear {
	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 * 主管税务机关
	 */
	private String Organname;
	
	/*
	 * 欠税总额
	 */
	private String TaxArreaAmount;
	/*
	 * 欠税统计日期
	 */
	private String Revenuedate;
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
	public String getTaxArreaAmount() {
		return TaxArreaAmount;
	}
	public void setTaxArreaAmount(String taxArreaAmount) {
		TaxArreaAmount = taxArreaAmount;
	}
	public String getRevenuedate() {
		return Revenuedate;
	}
	public void setRevenuedate(String revenuedate) {
		Revenuedate = revenuedate;
	}
}
