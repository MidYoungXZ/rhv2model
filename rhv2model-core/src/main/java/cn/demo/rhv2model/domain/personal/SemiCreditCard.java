package cn.demo.rhv2model.domain.personal;

/**  
* Title: 准贷记卡账户汇总信息  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class SemiCreditCard {
	/*
	 * 报告编号
	 * */
	private String ReportId;
	/*
	 * 发卡机构（法人）数
	 * */
	private String CardIssuer;
	/*
	 * 账户数
	 * */
	private String AccountNumber;
	/*
	 * 授信总额
	 * */
	private String TotalCredit;
	/*
	 * 单家行最高授信额
	 * */
	private String HighestCredit;
	/*
	 * 单家行最低授信额
	 * */
	private String MinimumCredit;
	/*
	 * 透支余额
	 * */
	private String AmountUsed;
	/*
	 * 最近6个月平均透支余额
	 * */
	private String AverageRepayment;
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getCardIssuer() {
		return CardIssuer;
	}
	public void setCardIssuer(String cardIssuer) {
		CardIssuer = cardIssuer;
	}
	public String getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}
	public String getTotalCredit() {
		return TotalCredit;
	}
	public void setTotalCredit(String totalCredit) {
		TotalCredit = totalCredit;
	}
	public String getHighestCredit() {
		return HighestCredit;
	}
	public void setHighestCredit(String highestCredit) {
		HighestCredit = highestCredit;
	}
	public String getMinimumCredit() {
		return MinimumCredit;
	}
	public void setMinimumCredit(String minimumCredit) {
		MinimumCredit = minimumCredit;
	}
	public String getAmountUsed() {
		return AmountUsed;
	}
	public void setAmountUsed(String amountUsed) {
		AmountUsed = amountUsed;
	}
	public String getAverageRepayment() {
		return AverageRepayment;
	}
	public void setAverageRepayment(String averageRepayment) {
		AverageRepayment = averageRepayment;
	}
	
}
