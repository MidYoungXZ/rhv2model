package cn.demo.rhv2model.domain.personal;

import java.util.List;

/**  
* Title: 所有相关还款责任的list集合  
* Description:  
* @author huangzhong 
* @date 2018年12月21日  
*/ 
public class RelateAllList {
	
	/**个人担保责任*/
	private List<PerGuarantee> perGuaranteeList;
	/**个人相关还款责任*/
	private List<PerOtherRepay> perOtherRepayList;
	/**企业担保责任*/
	private List<EntGuarantee> entGuaranteeList;
	/**企业相关还款责任*/
	private List<EntOtherRepay> entOtherRepayList;
	
	public List<PerGuarantee> getPerGuaranteeList() {
		return perGuaranteeList;
	}
	public void setPerGuaranteeList(List<PerGuarantee> perGuaranteeList) {
		this.perGuaranteeList = perGuaranteeList;
	}
	public List<PerOtherRepay> getPerOtherRepayList() {
		return perOtherRepayList;
	}
	public void setPerOtherRepayList(List<PerOtherRepay> perOtherRepayList) {
		this.perOtherRepayList = perOtherRepayList;
	}
	public List<EntGuarantee> getEntGuaranteeList() {
		return entGuaranteeList;
	}
	public void setEntGuaranteeList(List<EntGuarantee> entGuaranteeList) {
		this.entGuaranteeList = entGuaranteeList;
	}
	public List<EntOtherRepay> getEntOtherRepayList() {
		return entOtherRepayList;
	}
	public void setEntOtherRepayList(List<EntOtherRepay> entOtherRepayList) {
		this.entOtherRepayList = entOtherRepayList;
	}
	
	
}
