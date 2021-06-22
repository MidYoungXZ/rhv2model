package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

/**  
* Title: 后付费业务欠费  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class Arrears {
	/*
	 * 报告编号
	 * */
	private String ReportId;
	/*
	 * 后付费业务类型数量
	 * */
	private String TypeOfPaymentBusiness;
	/*
	 * 后付费业务类型
	 * */
	private String PostPaymentBusiness;
	/*
	 * 后付费业务类型描述
	 * */
	private String PostPaymentBusinessDesc;
	/*
	 * 欠费账户数
	 * */
	private String ArrearsAccount;
	/*
	 * 欠费金额
	 * */
	private String AmountOfArrears;
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getTypeOfPaymentBusiness() {
		return TypeOfPaymentBusiness;
	}
	public void setTypeOfPaymentBusiness(String typeOfPaymentBusiness) {
		TypeOfPaymentBusiness = typeOfPaymentBusiness;
	}
	public String getPostPaymentBusiness() {
		return PostPaymentBusiness;
	}
	public void setPostPaymentBusiness(String postPaymentBusiness) {
		PostPaymentBusiness = postPaymentBusiness;
	}
	public String getArrearsAccount() {
		return ArrearsAccount;
	}
	public void setArrearsAccount(String arrearsAccount) {
		ArrearsAccount = arrearsAccount;
	}
	public String getAmountOfArrears() {
		return AmountOfArrears;
	}
	public void setAmountOfArrears(String amountOfArrears) {
		AmountOfArrears = amountOfArrears;
	}
	@JSONField(serialize=false)
	public String getPostPaymentBusinessDesc() {
		return PostPaymentBusinessDesc;
	}
	public void setPostPaymentBusinessDesc(String postPaymentBusinessDesc) {
		PostPaymentBusinessDesc = postPaymentBusinessDesc;
	}
	
}
