package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

/**  
* Title: 强制执行信息  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class ForceExecution {
	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 * 执行法院
	 */
	private String Court;
	/*
	 * 执行案由
	 */
	private String CaseReason;
	/*
	 * 立案日期
	 */
	private String RegisterDate;
	/*
	 * 结案方式
	 */
	private String ClosedType;
	/*
	 * 结案方式描述
	 */
	private String ClosedTypeDesc;
	/*
	 * 案件状态
	 */
	private String CaseState;
	/*
	 * 结案日期
	 */
	private String ClosedDate;
	/*
	 * 申请执行标的
	 */
	private String EnforceObject;
	/*
	 * 申请执行标的金额
	 */
	private String EnforceObjectMoney;
	/*
	 * 已执行标的
	 */
	private String AlreadyEnforceObject;
	/*
	 * 已执行标的金额
	 */
	private String AlreadyEnforceObjectMoney;
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
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
	public String getCaseState() {
		return CaseState;
	}
	public void setCaseState(String caseState) {
		CaseState = caseState;
	}
	public String getClosedDate() {
		return ClosedDate;
	}
	public void setClosedDate(String closedDate) {
		ClosedDate = closedDate;
	}
	public String getEnforceObject() {
		return EnforceObject;
	}
	public void setEnforceObject(String enforceObject) {
		EnforceObject = enforceObject;
	}
	public String getEnforceObjectMoney() {
		return EnforceObjectMoney;
	}
	public void setEnforceObjectMoney(String enforceObjectMoney) {
		EnforceObjectMoney = enforceObjectMoney;
	}
	public String getAlreadyEnforceObject() {
		return AlreadyEnforceObject;
	}
	public void setAlreadyEnforceObject(String alreadyEnforceObject) {
		AlreadyEnforceObject = alreadyEnforceObject;
	}
	public String getAlreadyEnforceObjectMoney() {
		return AlreadyEnforceObjectMoney;
	}
	public void setAlreadyEnforceObjectMoney(String alreadyEnforceObjectMoney) {
		AlreadyEnforceObjectMoney = alreadyEnforceObjectMoney;
	}
	@JSONField(serialize=false)
	public String getClosedTypeDesc() {
		return ClosedTypeDesc;
	}
	public void setClosedTypeDesc(String closedTypeDesc) {
		ClosedTypeDesc = closedTypeDesc;
	}
}
