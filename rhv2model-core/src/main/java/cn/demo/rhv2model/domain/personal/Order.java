package cn.demo.rhv2model.domain.personal;

import java.util.Date;

/**  
* Title: 订单表  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class Order {
	/*
	 * 订单编号
	 */
	private  String OrderId;
	/*
	 * 申请流水号
	 */
	private String ApplySerino;
	/*
	 * 业务系统请求时间
	 */
	private Date ApplyTime;
	/*
	 * 业务系统编码
	 */
	private String SysID;
	/*
	 * 业务系统查询机构号
	 */
	private String SysQueryOrgId;
	/*
	 * 业务系统查询用户ID
	 */
	private String SysQueryUserId;
	/*
	 * 人行登陆账号
	 */
	private String UserID;
	/*
	 * 人行登录密码
	 */
	private String Password;
	public String getOrderId() {
		return OrderId;
	}
	public void setOrderId(String orderId) {
		OrderId = orderId;
	}
	public String getApplySerino() {
		return ApplySerino;
	}
	public void setApplySerino(String applySerino) {
		ApplySerino = applySerino;
	}
	public Date getApplyTime() {
		return ApplyTime;
	}
	public void setApplyTime(Date applyTime) {
		ApplyTime = applyTime;
	}
	public String getSysID() {
		return SysID;
	}
	public void setSysID(String sysID) {
		SysID = sysID;
	}
	public String getSysQueryOrgId() {
		return SysQueryOrgId;
	}
	public void setSysQueryOrgId(String sysQueryOrgId) {
		SysQueryOrgId = sysQueryOrgId;
	}
	public String getSysQueryUserId() {
		return SysQueryUserId;
	}
	public void setSysQueryUserId(String sysQueryUserId) {
		SysQueryUserId = sysQueryUserId;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
}
