package cn.demo.rhv2model.domain.personal;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**  
* Title: ReportTables  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
@XmlRootElement(name = "reportTables")
public class ReportTables {
	
	//表list
	private List<ReportTable> reportTable;

	public List<ReportTable> getReportTable() {
		return reportTable;
	}

	public void setReportTable(List<ReportTable> reportTable) {
		this.reportTable = reportTable;
	}

}
