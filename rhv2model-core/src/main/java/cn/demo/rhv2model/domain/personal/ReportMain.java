package cn.demo.rhv2model.domain.personal;

/**  
* Title: 报告主表  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class ReportMain {
	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 * 姓名
	 */
	private String Name;
	/*
	 * 证件类型
	 */
	private String CertType;
	/*
	 * 证件号码
	 */
	private String CertNo;
	/*
	 * 人行报告xml内容
	 */
	private String ReportContent;
	/*
	 * 人行报告html内容
	 */
	private String creditJson;
	/*
	 * 人行报告获取时间
	 */
	private String ReportAcquireTime;
	/*
	 * 解析状态
	 */
	private String ParseStatus;
	/*
	 * 是否最新记录
	 */
	private String IsLatest;

	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getCertType() {
		return CertType;
	}
	public void setCertType(String certType) {
		CertType = certType;
	}
	public String getCertNo() {
		return CertNo;
	}
	public void setCertNo(String certNo) {
		CertNo = certNo;
	}
	public String getReportContent() {
		return ReportContent;
	}
	public void setReportContent(String reportContent) {
		ReportContent = reportContent;
	}
	public String getReportAcquireTime() {
		return ReportAcquireTime;
	}
	public void setReportAcquireTime(String reportAcquireTime) {
		ReportAcquireTime = reportAcquireTime;
	}
	public String getParseStatus() {
		return ParseStatus;
	}
	public void setParseStatus(String parseStatus) {
		ParseStatus = parseStatus;
	}
	public String getIsLatest() {
		return IsLatest;
	}
	public void setIsLatest(String isLatest) {
		IsLatest = isLatest;
	}
	public String getCreditJson() {
		return creditJson;
	}
	public void setCreditJson(String creditJson) {
		this.creditJson = creditJson;
	}
	
}
