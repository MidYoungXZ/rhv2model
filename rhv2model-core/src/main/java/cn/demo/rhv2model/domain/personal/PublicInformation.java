package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

/**  
* Title: 公共信息概要  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class PublicInformation {
	/*
	 * 报告编号
	 * */
	private String ReportId;
	/*
	 * 公共信息类型数量
	 * */
	private String TypeOfPublicInformation;
	/*
	 * 公共信息类型
	 * */
	private String PublicInformation;
	/*
	 * 公共信息类型描述
	 * */
	private String PublicInformationDesc;
	/*
	 * 记录数
	 * */
	private String RecordNumber;
	/*
	 * 涉及金额
	 * */
	private String AmountInvolved;
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getTypeOfPublicInformation() {
		return TypeOfPublicInformation;
	}
	public void setTypeOfPublicInformation(String typeOfPublicInformation) {
		TypeOfPublicInformation = typeOfPublicInformation;
	}
	public String getPublicInformation() {
		return PublicInformation;
	}
	public void setPublicInformation(String publicInformation) {
		PublicInformation = publicInformation;
	}
	public String getRecordNumber() {
		return RecordNumber;
	}
	public void setRecordNumber(String recordNumber) {
		RecordNumber = recordNumber;
	}
	public String getAmountInvolved() {
		return AmountInvolved;
	}
	public void setAmountInvolved(String amountInvolved) {
		AmountInvolved = amountInvolved;
	}
	@JSONField(serialize=false)
	public String getPublicInformationDesc() {
		return PublicInformationDesc;
	}
	public void setPublicInformationDesc(String publicInformationDesc) {
		PublicInformationDesc = publicInformationDesc;
	}
	
}
