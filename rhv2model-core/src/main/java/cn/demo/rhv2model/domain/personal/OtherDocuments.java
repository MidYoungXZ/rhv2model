package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

/**  
* Title: 其他证件类型  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class OtherDocuments {
	
	//报告编号
	private String reportId;
	//
	private String identityIdentification;
	//其他证件类型
	private String documentType;
	//其他证件类型描述
	private String documentTypeDesc;
	//其他证件号码
	private String  identificationNumber;
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getIdentificationNumber() {
		return identificationNumber;
	}
	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	@JSONField(serialize=false)
	public String getDocumentTypeDesc() {
		return documentTypeDesc;
	}
	public void setDocumentTypeDesc(String documentTypeDesc) {
		this.documentTypeDesc = documentTypeDesc;
	}
	public String getIdentityIdentification() {
		return identityIdentification;
	}
	public void setIdentityIdentification(String identityIdentification) {
		this.identityIdentification = identityIdentification;
	}
	
}
