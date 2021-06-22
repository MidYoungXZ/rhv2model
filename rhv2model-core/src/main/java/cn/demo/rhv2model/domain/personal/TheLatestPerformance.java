package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

/**  
* Title: 最新表现信息 
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class TheLatestPerformance {
	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 * 账户状态
	 */
	private String AccountState;
	/*
	 * 账户状态描述(D1)
	 */
	private String D1AccountStateDesc;
	/*
	 * 账户状态描述(R1)
	 */
	private String R1AccountStateDesc;
	/*
	 * 账户状态描述(R2或者R3)
	 */
	private String R2ORR3AccountStateDesc;
	/*
	 * 账户状态描述(R4)
	 */
	private String R4AccountStateDesc;
	/*
	 * 账户状态描述(C1)
	 */
	private String C1AccountStateDesc;
	/*
	 * 关闭日期
	 */
	private String ClosingDate;
	/*
	 * 转出月份
	 */
	private String TransferMonth;
	/*
	 * 余额
	 */
	private String Balance;
	/*
	 * 最近一次还款日期
	 */
	private String LastRepaymentDate;
	/*
	 * 最近一次还款金额
	 */
	private String LatestRepaymentAmount;
	/*
	 * 五级分类
	 */
	private String Classification;
	/*
	 * 五级分类描述
	 */
	private String ClassificationDesc;
	/*
	 * 还款状态
	 */
	private String RepaymentState;
	/*
	 * 信息报告日期
	 */
	private String DateOfReport;
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
	public String getAccountState() {
		return AccountState;
	}
	public void setAccountState(String accountState) {
		AccountState = accountState;
	}
	public String getClosingDate() {
		return ClosingDate;
	}
	public void setClosingDate(String closingDate) {
		ClosingDate = closingDate;
	}
	public String getTransferMonth() {
		return TransferMonth;
	}
	public void setTransferMonth(String transferMonth) {
		TransferMonth = transferMonth;
	}
	public String getBalance() {
		return Balance;
	}
	public void setBalance(String balance) {
		Balance = balance;
	}
	public String getLastRepaymentDate() {
		return LastRepaymentDate;
	}
	public void setLastRepaymentDate(String lastRepaymentDate) {
		LastRepaymentDate = lastRepaymentDate;
	}
	public String getLatestRepaymentAmount() {
		return LatestRepaymentAmount;
	}
	public void setLatestRepaymentAmount(String latestRepaymentAmount) {
		LatestRepaymentAmount = latestRepaymentAmount;
	}
	public String getClassification() {
		return Classification;
	}
	public void setClassification(String classification) {
		Classification = classification;
	}
	public String getRepaymentState() {
		return RepaymentState;
	}
	public void setRepaymentState(String repaymentState) {
		RepaymentState = repaymentState;
	}
	public String getDateOfReport() {
		return DateOfReport;
	}
	public void setDateOfReport(String dateOfReport) {
		DateOfReport = dateOfReport;
	}
	public String getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}
	@JSONField(serialize=false)
	public String getD1AccountStateDesc() {
		return D1AccountStateDesc;
	}
	public void setD1AccountStateDesc(String d1AccountStateDesc) {
		D1AccountStateDesc = d1AccountStateDesc;
	}
	@JSONField(serialize=false)
	public String getR1AccountStateDesc() {
		return R1AccountStateDesc;
	}
	public void setR1AccountStateDesc(String r1AccountStateDesc) {
		R1AccountStateDesc = r1AccountStateDesc;
	}
	@JSONField(serialize=false)
	public String getR2ORR3AccountStateDesc() {
		return R2ORR3AccountStateDesc;
	}
	public void setR2ORR3AccountStateDesc(String r2orr3AccountStateDesc) {
		R2ORR3AccountStateDesc = r2orr3AccountStateDesc;
	}
	@JSONField(serialize=false)
	public String getR4AccountStateDesc() {
		return R4AccountStateDesc;
	}
	public void setR4AccountStateDesc(String r4AccountStateDesc) {
		R4AccountStateDesc = r4AccountStateDesc;
	}
	@JSONField(serialize=false)
	public String getC1AccountStateDesc() {
		return C1AccountStateDesc;
	}
	public void setC1AccountStateDesc(String c1AccountStateDesc) {
		C1AccountStateDesc = c1AccountStateDesc;
	}
	@JSONField(serialize=false)
	public String getClassificationDesc() {
		return ClassificationDesc;
	}
	public void setClassificationDesc(String classificationDesc) {
		ClassificationDesc = classificationDesc;
	}
	
}
