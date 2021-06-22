package cn.demo.rhv2model.domain.personal;

import java.util.List;

/**  
* Title: Row  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class Row {
	
	//列list
	private List<Col> col;

	public List<Col> getCol() {
		return col;
	}

	public void setCol(List<Col> col) {
		this.col = col;
	}
}
