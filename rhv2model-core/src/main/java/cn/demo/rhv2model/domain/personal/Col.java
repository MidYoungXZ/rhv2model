package cn.demo.rhv2model.domain.personal;


/**  
* Title: 列的信息  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class Col {
	/*
	 * 列名
	 */
	private String colName;
	/*
	 * 列值
	 */
	private String colValue;

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getColValue() {
		return colValue;
	}

	public void setColValue(String colValue) {
		this.colValue = colValue;
	}
}
