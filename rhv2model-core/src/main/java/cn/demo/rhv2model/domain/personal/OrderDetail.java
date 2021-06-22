package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

/**  
* Title: 订单明细表 
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class OrderDetail {
	
	//订单子编号
	private String orderDetailId;
	//订单编号
	private String orderId;
	//姓名
	private String name;
	//证件类型
	private String certType;
	//证件号码
	private String certNo;
	//查询原因
	private String queryReason;
	//查询类型
	private String queryType;
	//查询时效
	private String queryTimeLimit;
	//报告类型
	private String reportModelType;
	//解析状态
	private String queryStatus;
	//数据来源
	private String dataSource;
	//报告编号
	private String reportId;
	//查询表格信息
	private String reportTables;
	//报告类型
	private String reportType;
	//解析状态描述
	private String queryStatusDesc;

	public String getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getQueryReason() {
		return queryReason;
	}

	public void setQueryReason(String queryReason) {
		this.queryReason = queryReason;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getQueryTimeLimit() {
		return queryTimeLimit;
	}

	public void setQueryTimeLimit(String queryTimeLimit) {
		this.queryTimeLimit = queryTimeLimit;
	}

	public String getReportModelType() {
		return reportModelType;
	}

	public void setReportModelType(String reportModelType) {
		this.reportModelType = reportModelType;
	}

	public String getQueryStatus() {
		return queryStatus;
	}

	public void setQueryStatus(String queryStatus) {
		this.queryStatus = queryStatus;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getReportTables() {
		return reportTables;
	}

	public void setReportTables(String reportTables) {
		this.reportTables = reportTables;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	@JSONField(serialize=false)
	public String getQueryStatusDesc() {
		return queryStatusDesc;
	}

	public void setQueryStatusDesc(String queryStatusDesc) {
		this.queryStatusDesc = queryStatusDesc;
	}

}
