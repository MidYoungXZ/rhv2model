package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

/**  
* Title: 低保救助记录信息  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class Salvation {
	/*
	 *报告编号
	 */
	private String ReportId;
	/*
	 *人员类别
	 */
	private String PersonnelType;
	/*
	 *人员类别描述
	 */
	private String PersonnelTypeDesc;
	/*
	 *所在地代码
	 */
	private String Area;
	/*
	 *所在地名称
	 */
	private String AreaDesc;
	/*
	 *工作单位
	 */
	private String Organname;
	/*
	 *家庭月收入
	 */
	private String Money;
	/*
	 *申请日期
	 */
	private String RegisterDate;
	/*
	 *批准日期
	 */
	private String PassDate;
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
	public String getPersonnelType() {
		return PersonnelType;
	}
	public void setPersonnelType(String personnelType) {
		PersonnelType = personnelType;
	}
	public String getArea() {
		return Area;
	}
	public void setArea(String area) {
		Area = area;
	}
	public String getOrganname() {
		return Organname;
	}
	public void setOrganname(String organname) {
		Organname = organname;
	}
	public String getMoney() {
		return Money;
	}
	public void setMoney(String money) {
		Money = money;
	}
	public String getRegisterDate() {
		return RegisterDate;
	}
	public void setRegisterDate(String registerDate) {
		RegisterDate = registerDate;
	}
	public String getPassDate() {
		return PassDate;
	}
	public void setPassDate(String passDate) {
		PassDate = passDate;
	}
	public String getGetTime() {
		return GetTime;
	}
	public void setGetTime(String getTime) {
		GetTime = getTime;
	}
	@JSONField(serialize=false)
	public String getPersonnelTypeDesc() {
		return PersonnelTypeDesc;
	}
	public void setPersonnelTypeDesc(String personnelTypeDesc) {
		PersonnelTypeDesc = personnelTypeDesc;
	}
	@JSONField(serialize=false)
	public String getAreaDesc() {
		return AreaDesc;
	}
	public void setAreaDesc(String areaDesc) {
		AreaDesc = areaDesc;
	}
}
