package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**  
* Title: 职业信息  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class Professional implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 * 工作单位
	 */
	private String Employer;
	/*
	 * 单位地址
	 */
	private String EmployerAddress;
	/*
	 * 职业
	 */
	private String Occupation;
	/*
	 * 职业描述
	 */
	private String OccupationDesc;
	/*
	 * 行业
	 */
	private String Industry;
	/*
	 * 行业描述
	 */
	private String IndustryDesc;
	/*
	 * 职务
	 */
	private String Duty;
	/*
	 * 职务描述
	 */
	private String DutyDesc;
	/*
	 * 职称
	 */
	private String Title;
	/*
	 * 职称描述
	 */
	private String TitleDesc;
	/*
	 * 进入本单位年份
	 */
	private String StartYear;
	/*
	 * 信息更新日期
	 */
	private String GetTime;
	/*
	 * 就业状况
	 */
	private String WorkStatus;
	/*
	 * 就业状况描述
	 */
	private String WorkStatusDesc;
	/*
	 * 单位性质
	 */
	private String UnitProperties;
	/*
	 * 单位性质描述
	 */
	private String UnitPropertiesDesc;
	/*
	 * 单位电话
	 */
	private String WorkTelephone;
	
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getEmployer() {
		return Employer;
	}
	public void setEmployer(String employer) {
		Employer = employer;
	}
	public String getEmployerAddress() {
		return EmployerAddress;
	}
	public void setEmployerAddress(String employerAddress) {
		EmployerAddress = employerAddress;
	}
	public String getOccupation() {
		return Occupation;
	}
	public void setOccupation(String occupation) {
		Occupation = occupation;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getStartYear() {
		return StartYear;
	}
	public void setStartYear(String startYear) {
		StartYear = startYear;
	}
	public String getGetTime() {
		return GetTime;
	}
	public void setGetTime(String getTime) {
		GetTime = getTime;
	}
	public String getIndustry() {
		return Industry;
	}
	public void setIndustry(String industry) {
		Industry = industry;
	}
	public String getDuty() {
		return Duty;
	}
	public void setDuty(String duty) {
		Duty = duty;
	}
	public String getWorkStatus() {
		return WorkStatus;
	}
	public void setWorkStatus(String workStatus) {
		WorkStatus = workStatus;
	}
	public String getUnitProperties() {
		return UnitProperties;
	}
	public void setUnitProperties(String unitProperties) {
		UnitProperties = unitProperties;
	}
	public String getWorkTelephone() {
		return WorkTelephone;
	}
	public void setWorkTelephone(String workTelephone) {
		WorkTelephone = workTelephone;
	}
	@JSONField(serialize=false)
	public String getOccupationDesc() {
		return OccupationDesc;
	}
	public void setOccupationDesc(String occupationDesc) {
		OccupationDesc = occupationDesc;
	}
	@JSONField(serialize=false)
	public String getIndustryDesc() {
		return IndustryDesc;
	}
	public void setIndustryDesc(String industryDesc) {
		IndustryDesc = industryDesc;
	}
	@JSONField(serialize=false)
	public String getDutyDesc() {
		return DutyDesc;
	}
	public void setDutyDesc(String dutyDesc) {
		DutyDesc = dutyDesc;
	}
	@JSONField(serialize=false)
	public String getTitleDesc() {
		return TitleDesc;
	}
	public void setTitleDesc(String titleDesc) {
		TitleDesc = titleDesc;
	}
	@JSONField(serialize=false)
	public String getWorkStatusDesc() {
		return WorkStatusDesc;
	}
	public void setWorkStatusDesc(String workStatusDesc) {
		WorkStatusDesc = workStatusDesc;
	}
	@JSONField(serialize=false)
	public String getUnitPropertiesDesc() {
		return UnitPropertiesDesc;
	}
	public void setUnitPropertiesDesc(String unitPropertiesDesc) {
		UnitPropertiesDesc = unitPropertiesDesc;
	}
	
}
