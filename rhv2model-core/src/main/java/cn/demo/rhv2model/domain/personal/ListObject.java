package cn.demo.rhv2model.domain.personal;

import java.util.List;

/**  
* Title: ListObject  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class ListObject {
	//年份
	private String year;
	//年份下的List
	private List objectList;
	public List getObjectList() {
		return objectList;
	}
	public void setObjectList(List objectList) {
		this.objectList = objectList;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
}
