package cn.demo.rhv2model.domain.personal;

/**  
* Title: 其他声明  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class OtherAnnotations {
	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 * 对象类型
	 */
	private String ObjectType;
	/*
	 * 对象标识
	 */
	private String ObjectIdentification;
	/*
	 * 标注及声明个数
	 */
	private String DeclarationTypeNumber;
	/*
	 * 标注及声明类型
	 */
	private String DeclarationsType;
	/*
	 * 标注或声明内容
	 */
	private String Declarations;
	/*
	 * 添加日期 
	 */
	private String AddDate;
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getObjectType() {
		return ObjectType;
	}
	public void setObjectType(String objectType) {
		ObjectType = objectType;
	}
	public String getObjectIdentification() {
		return ObjectIdentification;
	}
	public void setObjectIdentification(String objectIdentification) {
		ObjectIdentification = objectIdentification;
	}
	public String getDeclarationTypeNumber() {
		return DeclarationTypeNumber;
	}
	public void setDeclarationTypeNumber(String declarationTypeNumber) {
		DeclarationTypeNumber = declarationTypeNumber;
	}
	public String getDeclarationsType() {
		return DeclarationsType;
	}
	public void setDeclarationsType(String declarationsType) {
		DeclarationsType = declarationsType;
	}
	public String getDeclarations() {
		return Declarations;
	}
	public void setDeclarations(String declarations) {
		Declarations = declarations;
	}
	public String getAddDate() {
		return AddDate;
	}
	public void setAddDate(String addDate) {
		AddDate = addDate;
	}
}
