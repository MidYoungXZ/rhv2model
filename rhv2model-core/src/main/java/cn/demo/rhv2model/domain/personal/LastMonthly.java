package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

/**  
* Title: 近一次月度表现信息  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class LastMonthly {
	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 *	月份
	 */
	private String Month;
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
	 * 余额
	 */
	private String Balance;
	/*
	 * 已用额度
	 */
	private String AmountUsed;
	/*
	 * 未出单的大额专项分期余额
	 */
	private String StagingBalance;
	/*
	 * 五级分类
	 */
	private String Classification;
	/*
	 * 五级分类描述
	 */
	private String ClassificationDesc;
	/*
	 * 剩余还款期数
	 */
	private String RemainingRepayment;
	/*
	 *结算/应还款日
	 */
	private String SettlementDay;
	/*
	 * 本月应还款
	 */
	private String Reimbursement;
	/*
	 * 本月实还款
	 */
	private String Repayments;
	/*
	 * 最近一次还款日期
	 */
	private String LastRepaymentDate;
	/*
	 * 当前逾期期数
	 */
	private String CurrentOverduePeriod;
	/*
	 * 当前逾期总额
	 */
	private String CurrentOverdueTotal;
	/*
	 *逾期 31—60天 未还本金
	 */
	private String Overdue31To60Amount;
	/*
	 * 逾期 61—90天 未还本金
	 */
	private String Overdue61To90Amount;
	/*
	 * 逾期 91—180天 未还本金
	 */
	private String Overdue91To180Amount;
	/*
	 * 逾期 180天以上 未还本金
	 */
	private String OverdueOver180Amount;
	/*
	 * 透支 180 天以上 未付余额
	 */
	private String OverdraftDay;
	/*
	 * 最近 6 个月平均使用额度
	 */
	private String AverageUsageQuota;
	/*
	 * 最近 6 个月平均透支余额
	 */
	private String AverageOverdraftBalance;
	/*
	 * 最大使用额度
	 */
	private String AmountOfUse;
	/*
	 * 最大透支余额
	 */
	private String OverdraftBalance;
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
	public String getMonth() {
		return Month;
	}
	public void setMonth(String month) {
		Month = month;
	}
	public String getAccountState() {
		return AccountState;
	}
	public void setAccountState(String accountState) {
		AccountState = accountState;
	}
	public String getBalance() {
		return Balance;
	}
	public void setBalance(String balance) {
		Balance = balance;
	}
	public String getAmountUsed() {
		return AmountUsed;
	}
	public void setAmountUsed(String amountUsed) {
		AmountUsed = amountUsed;
	}
	public String getStagingBalance() {
		return StagingBalance;
	}
	public void setStagingBalance(String stagingBalance) {
		StagingBalance = stagingBalance;
	}
	public String getClassification() {
		return Classification;
	}
	public void setClassification(String classification) {
		Classification = classification;
	}
	public String getRemainingRepayment() {
		return RemainingRepayment;
	}
	public void setRemainingRepayment(String remainingRepayment) {
		RemainingRepayment = remainingRepayment;
	}
	public String getSettlementDay() {
		return SettlementDay;
	}
	public void setSettlementDay(String settlementDay) {
		SettlementDay = settlementDay;
	}
	public String getReimbursement() {
		return Reimbursement;
	}
	public void setReimbursement(String reimbursement) {
		Reimbursement = reimbursement;
	}
	public String getRepayments() {
		return Repayments;
	}
	public void setRepayments(String repayments) {
		Repayments = repayments;
	}
	public String getLastRepaymentDate() {
		return LastRepaymentDate;
	}
	public void setLastRepaymentDate(String lastRepaymentDate) {
		LastRepaymentDate = lastRepaymentDate;
	}
	public String getCurrentOverduePeriod() {
		return CurrentOverduePeriod;
	}
	public void setCurrentOverduePeriod(String currentOverduePeriod) {
		CurrentOverduePeriod = currentOverduePeriod;
	}
	public String getCurrentOverdueTotal() {
		return CurrentOverdueTotal;
	}
	public void setCurrentOverdueTotal(String currentOverdueTotal) {
		CurrentOverdueTotal = currentOverdueTotal;
	}
	public String getOverdue31To60Amount() {
		return Overdue31To60Amount;
	}
	public void setOverdue31To60Amount(String overdue31To60Amount) {
		Overdue31To60Amount = overdue31To60Amount;
	}
	public String getOverdue61To90Amount() {
		return Overdue61To90Amount;
	}
	public void setOverdue61To90Amount(String overdue61To90Amount) {
		Overdue61To90Amount = overdue61To90Amount;
	}
	public String getOverdue91To180Amount() {
		return Overdue91To180Amount;
	}
	public void setOverdue91To180Amount(String overdue91To180Amount) {
		Overdue91To180Amount = overdue91To180Amount;
	}
	public String getOverdueOver180Amount() {
		return OverdueOver180Amount;
	}
	public void setOverdueOver180Amount(String overdueOver180Amount) {
		OverdueOver180Amount = overdueOver180Amount;
	}
	public String getOverdraftDay() {
		return OverdraftDay;
	}
	public void setOverdraftDay(String overdraftDay) {
		OverdraftDay = overdraftDay;
	}
	public String getAverageUsageQuota() {
		return AverageUsageQuota;
	}
	public void setAverageUsageQuota(String averageUsageQuota) {
		AverageUsageQuota = averageUsageQuota;
	}
	public String getAverageOverdraftBalance() {
		return AverageOverdraftBalance;
	}
	public void setAverageOverdraftBalance(String averageOverdraftBalance) {
		AverageOverdraftBalance = averageOverdraftBalance;
	}
	public String getAmountOfUse() {
		return AmountOfUse;
	}
	public void setAmountOfUse(String amountOfUse) {
		AmountOfUse = amountOfUse;
	}
	public String getOverdraftBalance() {
		return OverdraftBalance;
	}
	public void setOverdraftBalance(String overdraftBalance) {
		OverdraftBalance = overdraftBalance;
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
