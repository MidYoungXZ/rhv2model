package cn.demo.rhv2model.domain.personal;

/**  
* Title: 大额专项分期信息  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class LargeAmountSpecial {
	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 * 大额专项分期笔数
	 */
	private String StagingPenNumber;
	/*
	 * 大额专项分期额度
	 */
	private String InstallmentQuota;
	/*
	 * 分期额度生效日期
	 */
	private String EffectiveDate;
	/*
	 * 分期额度到期日期
	 */
	private String ExpirationDate;
	/*
	 * 已用分期金额
	 */
	private String AmountUsed;
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
	public String getStagingPenNumber() {
		return StagingPenNumber;
	}
	public void setStagingPenNumber(String stagingPenNumber) {
		StagingPenNumber = stagingPenNumber;
	}
	public String getInstallmentQuota() {
		return InstallmentQuota;
	}
	public void setInstallmentQuota(String installmentQuota) {
		InstallmentQuota = installmentQuota;
	}
	public String getEffectiveDate() {
		return EffectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		EffectiveDate = effectiveDate;
	}
	public String getExpirationDate() {
		return ExpirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		ExpirationDate = expirationDate;
	}
	public String getAmountUsed() {
		return AmountUsed;
	}
	public void setAmountUsed(String amountUsed) {
		AmountUsed = amountUsed;
	}
	public String getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}
	
}
