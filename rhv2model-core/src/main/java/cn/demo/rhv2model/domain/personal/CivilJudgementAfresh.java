package cn.demo.rhv2model.domain.personal;


/**  
* Title: 民事判决记录（暂时未用）  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class CivilJudgementAfresh{
	/*
	 *报告编号
	 */
	private String ReportId;
	/*
	 *编号
	 */
	private String Serino;
	/*
	 *立案法院
	 */
	private String Court;
	/*
	 *案由
	 */
	private String CaseReason;
	/*
	 *立案日期
	 */
	private String RegisterDate;
	/*
	 *结案方式
	 */
	private String ClosedType;
	/*
	 *判决/调解结果
	 */
	private String CaseResult;
	/*
	 *判决/调解生效日期
	 */
	private String CaseValidatedate;
	/*
	 *诉讼标的
	 */
	private String SuitObject;
	/*
	 *诉讼标的金额
	 */
	private String SuitObjectMoney;
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
	public String getCourt() {
		return Court;
	}
	public void setCourt(String court) {
		Court = court;
	}
	public String getCaseReason() {
		return CaseReason;
	}
	public void setCaseReason(String caseReason) {
		CaseReason = caseReason;
	}
	public String getRegisterDate() {
		return RegisterDate;
	}
	public void setRegisterDate(String registerDate) {
		RegisterDate = registerDate;
	}
	public String getClosedType() {
		return ClosedType;
	}
	public void setClosedType(String closedType) {
		ClosedType = closedType;
	}
	public String getCaseResult() {
		return CaseResult;
	}
	public void setCaseResult(String caseResult) {
		CaseResult = caseResult;
	}
	public String getCaseValidatedate() {
		return CaseValidatedate;
	}
	public void setCaseValidatedate(String caseValidatedate) {
		CaseValidatedate = caseValidatedate;
	}
	public String getSuitObject() {
		return SuitObject;
	}
	public void setSuitObject(String suitObject) {
		SuitObject = suitObject;
	}
	public String getSuitObjectMoney() {
		return SuitObjectMoney;
	}
	public void setSuitObjectMoney(String suitObjectMoney) {
		SuitObjectMoney = suitObjectMoney;
	}
}