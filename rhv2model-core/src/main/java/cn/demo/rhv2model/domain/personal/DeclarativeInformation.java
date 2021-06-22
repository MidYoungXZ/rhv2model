package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

/**  
* Title: 标注及声明信息(授信协议) 
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class DeclarativeInformation {
	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 * 标注及声明个数
	 */
	private String Declarations;
	/*
	 * 标注及声明类型
	 */
	private String DeclarationsType;
	/*
	 * 标注及声明类型描述
	 */
	private String DeclarationsTypeDesc;
	/*
	 * 标注或声明内容
	 */
	private String StatementContent;
	/*
	 * 添加日期 
	 */
	private String AddDate;
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
	public String getDeclarations() {
		return Declarations;
	}
	public void setDeclarations(String declarations) {
		Declarations = declarations;
	}
	public String getDeclarationsType() {
		return DeclarationsType;
	}
	public void setDeclarationsType(String declarationsType) {
		DeclarationsType = declarationsType;
	}
	public String getStatementContent() {
		return StatementContent;
	}
	public void setStatementContent(String statementContent) {
		StatementContent = statementContent;
	}
	public String getAddDate() {
		return AddDate;
	}
	public void setAddDate(String addDate) {
		AddDate = addDate;
	}
	public String getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}
	@JSONField(serialize=false)
	public String getDeclarationsTypeDesc() {
		return DeclarationsTypeDesc;
	}
	public void setDeclarationsTypeDesc(String declarationsTypeDesc) {
		DeclarationsTypeDesc = declarationsTypeDesc;
	}
	
}
