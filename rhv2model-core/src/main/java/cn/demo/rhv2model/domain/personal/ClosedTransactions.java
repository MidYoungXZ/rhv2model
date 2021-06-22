package cn.demo.rhv2model.domain.personal;


/**  
* Title: 信协议下已结清贷款特殊交易(暂时未使用--解析部分注释了)  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class ClosedTransactions {
	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 * 特殊交易个数
	 */
	private String SpecialTransactionNumber;
	/*
	 * 特殊交易类型
	 */
	private String SpecialTransactionType;
	/*
	 * 特殊交易发生日期
	 */
	private String SpecialTransactionDate;
	/*
	 * 到期日期变更月数
	 */
	private String ExpirationDate;
	/*
	 * 特殊交易发生金额
	 */
	private String SpecialTransactionAmount;
	/*
	 * 特殊交易明细记录
	 */
	private String SpecialTransactionDetails;
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getSpecialTransactionNumber() {
		return SpecialTransactionNumber;
	}
	public void setSpecialTransactionNumber(String specialTransactionNumber) {
		SpecialTransactionNumber = specialTransactionNumber;
	}
	public String getSpecialTransactionType() {
		return SpecialTransactionType;
	}
	public void setSpecialTransactionType(String specialTransactionType) {
		SpecialTransactionType = specialTransactionType;
	}
	public String getSpecialTransactionDate() {
		return SpecialTransactionDate;
	}
	public void setSpecialTransactionDate(String specialTransactionDate) {
		SpecialTransactionDate = specialTransactionDate;
	}
	public String getExpirationDate() {
		return ExpirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		ExpirationDate = expirationDate;
	}
	public String getSpecialTransactionAmount() {
		return SpecialTransactionAmount;
	}
	public void setSpecialTransactionAmount(String specialTransactionAmount) {
		SpecialTransactionAmount = specialTransactionAmount;
	}
	public String getSpecialTransactionDetails() {
		return SpecialTransactionDetails;
	}
	public void setSpecialTransactionDetails(String specialTransactionDetails) {
		SpecialTransactionDetails = specialTransactionDetails;
	}
	
}
