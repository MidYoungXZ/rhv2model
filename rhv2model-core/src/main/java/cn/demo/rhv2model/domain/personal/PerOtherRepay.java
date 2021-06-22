package cn.demo.rhv2model.domain.personal;

/**  
* Title: 为个人其他相关还款责任  
* Description:  
* @author huangzhong 
* @date 2018年12月21日  
*/ 
public class PerOtherRepay {
	
	/**账户数*/
	private String accountNumer;
	/**还款责任限额*/
	private String liabilityLimit ;
	/**余额*/
	private String balance;
	
	public String getAccountNumer() {
		return accountNumer;
	}
	public void setAccountNumer(String accountNumer) {
		this.accountNumer = accountNumer;
	}
	public String getLiabilityLimit() {
		return liabilityLimit;
	}
	public void setLiabilityLimit(String liabilityLimit) {
		this.liabilityLimit = liabilityLimit;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	
	
}
