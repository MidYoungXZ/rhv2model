package cn.demo.rhv2model.domain.personal;

/**  
* Title: 为个人担保责任 
* Description:  
* @author huangzhong 
* @date 2018年12月21日  
*/ 
public class PerGuarantee {
	
	/**账户数*/
	private String accountNumer;
	/**担保金额*/
	private String guaranteeAmount;
	/**余额*/
	private String balance;
	
	public String getAccountNumer() {
		return accountNumer;
	}
	public void setAccountNumer(String accountNumer) {
		this.accountNumer = accountNumer;
	}
	public String getGuaranteeAmount() {
		return guaranteeAmount;
	}
	public void setGuaranteeAmount(String guaranteeAmount) {
		this.guaranteeAmount = guaranteeAmount;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	
}
