package cn.demo.rhv2model.domain.personal;


/**  
* Title: ReportTable  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class ReportTable {
	
	//表名
	private String tableName;
	//列名
	private Cols cols;
	//
	private Rows rows;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Cols getCols() {
		return cols;
	}

	public void setCols(Cols cols) {
		this.cols = cols;
	}

	public Rows getRows() {
		return rows;
	}

	public void setRows(Rows rows) {
		this.rows = rows;
	}

}
