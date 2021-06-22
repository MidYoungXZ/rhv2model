package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

/**  
* Title:个人信息
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class Identity {
	/*
	 * 报告编号
	 */
	private String reportId;
	/*
	 * 性别
	 */
	private String gender;
	/*
	 * 性别描述
	 */
	private String genderDesc;
	/*
	 * 出生日期
	 */
	private String birthday;
	
	/*
	 * 学历
	 */
	private String eduLevel;
	/*
	 * 学历描述
	 */
	private String eduLevelDesc;
	/*
	 * 学位
	 */
	private String eduDegree;
	/*
	 * 学位描述
	 */
	private String eduDegreeDesc;
	/*
	 * 通讯地址
	 */
	private String postAddress;
	/*
	 * 电子邮箱
	 */
	private String email;
	/*
	 * 就业状况
	 */
	private String employmentStatus;
	/*
	 * 国籍代码
	 */
	private String nationality;
	/*
	 * 国籍描述
	 */
	private String nationalityDesc;
	/*
	 * 就业状况描述
	 */
	private String employmentStatusDesc;
	/*
	 * 户籍地址
	 */
	private String householdAddress;

	/**
	 * 婚姻状况
	 */
	private String maritalStatus;

	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEduLevel() {
		return eduLevel;
	}
	public void setEduLevel(String eduLevel) {
		this.eduLevel = eduLevel;
	}
	public String getEduDegree() {
		return eduDegree;
	}
	public void setEduDegree(String eduDegree) {
		this.eduDegree = eduDegree;
	}
	public String getPostAddress() {
		return postAddress;
	}
	public void setPostAddress(String postAddress) {
		this.postAddress = postAddress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmploymentStatus() {
		return employmentStatus;
	}
	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getHouseholdAddress() {
		return householdAddress;
	}
	public void setHouseholdAddress(String householdAddress) {
		this.householdAddress = householdAddress;
	}
	@JSONField(serialize=false)
	public String getGenderDesc() {
		return genderDesc;
	}
	public void setGenderDesc(String genderDesc) {
		this.genderDesc = genderDesc;
	}
	@JSONField(serialize=false)
	public String getEduLevelDesc() {
		return eduLevelDesc;
	}
	public void setEduLevelDesc(String eduLevelDesc) {
		this.eduLevelDesc = eduLevelDesc;
	}
	@JSONField(serialize=false)
	public String getEduDegreeDesc() {
		return eduDegreeDesc;
	}
	public void setEduDegreeDesc(String eduDegreeDesc) {
		this.eduDegreeDesc = eduDegreeDesc;
	}
	@JSONField(serialize=false)
	public String getEmploymentStatusDesc() {
		return employmentStatusDesc;
	}
	public void setEmploymentStatusDesc(String employmentStatusDesc) {
		this.employmentStatusDesc = employmentStatusDesc;
	}
	@JSONField(serialize=false)
	public String getNationalityDesc() {
		return nationalityDesc;
	}
	public void setNationalityDesc(String nationalityDesc) {
		this.nationalityDesc = nationalityDesc;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
}
