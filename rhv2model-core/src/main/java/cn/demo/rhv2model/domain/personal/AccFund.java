package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

/**  
* Title: 公积金  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class AccFund{
	/*
	 *报告编号
	 */
	private String ReportId;
	/*
	 *参缴地
	 */
	private String Area;
	/*
	 *参缴地具体地区
	 */
	private String AreaDesc;
	/*
	 *参缴日期
	 */
	private String RegisterDate;
	/*
	 *初缴月份
	 */
	private String FirstMonth;
	/*
	 *缴至月份
	 */
	private String ToMonth;
	/*
	 *缴费状态
	 */
	private String State;
	/*
	 *缴费状态描述
	 */
	private String StateDesc;
	/*
	 *月缴存额
	 */
	private String Pay;
	/*
	 *个人缴存比例
	 */
	private String OwnPercent;
	/*
	 *单位缴存比例
	 */
	private String ComPercent;
	/*
	 *缴费单位
	 */
	private String Organname;
	/*
	 *信息更新日期
	 */
	private String GetTime;
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getArea() {
		return Area;
	}
	public void setArea(String area) {
		Area = area;
	}
	public String getRegisterDate() {
		return RegisterDate;
	}
	public void setRegisterDate(String registerDate) {
		RegisterDate = registerDate;
	}
	public String getFirstMonth() {
		return FirstMonth;
	}
	public void setFirstMonth(String firstMonth) {
		FirstMonth = firstMonth;
	}
	public String getToMonth() {
		return ToMonth;
	}
	public void setToMonth(String toMonth) {
		ToMonth = toMonth;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getPay() {
		return Pay;
	}
	public void setPay(String pay) {
		Pay = pay;
	}
	public String getOwnPercent() {
		return OwnPercent;
	}
	public void setOwnPercent(String ownPercent) {
		OwnPercent = ownPercent;
	}
	public String getComPercent() {
		return ComPercent;
	}
	public void setComPercent(String comPercent) {
		ComPercent = comPercent;
	}
	public String getOrganname() {
		return Organname;
	}
	public void setOrganname(String organname) {
		Organname = organname;
	}
	public String getGetTime() {
		return GetTime;
	}
	public void setGetTime(String getTime) {
		GetTime = getTime;
	}
	@JSONField(serialize=false)
	public String getStateDesc() {
		return StateDesc;
	}
	public void setStateDesc(String stateDesc) {
		StateDesc = stateDesc;
	}
	@JSONField(serialize=false)
	public String getAreaDesc() {
		return AreaDesc;
	}
	public void setAreaDesc(String areaDesc) {
		AreaDesc = areaDesc;
	}
}