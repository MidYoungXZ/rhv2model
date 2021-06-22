package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

/**  
* Title: 执业资格记录  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class Competence {
	/*
	 * 报告编号
	 */
	private String ReportId;
	/*
	 *	执业资格名称
	 */
	private String CompetencyName;
	/*
	 * 等级
	 */
	private String Grade;
	/*
	 * 等级描述
	 */
	private String GradeDesc;
	/*
	 * 获得日期
	 */
	private String AwardDate;
	/*
	 * 到期日期
	 */
	private String EndDate;
	/*
	 * 吊销日期
	 */
	private String RevokeDate;
	/*
	 * 颁发机构
	 */
	private String Organname;
	/*
	 * 机构所在地
	 */
	private String Area;
	/*
	 * 机构所在地翻译
	 */
	private String AreaDesc;
	public String getReportId() {
		return ReportId;
	}
	public void setReportId(String reportId) {
		ReportId = reportId;
	}
	public String getCompetencyName() {
		return CompetencyName;
	}
	public void setCompetencyName(String competencyName) {
		CompetencyName = competencyName;
	}
	public String getGrade() {
		return Grade;
	}
	public void setGrade(String grade) {
		Grade = grade;
	}
	public String getAwardDate() {
		return AwardDate;
	}
	public void setAwardDate(String awardDate) {
		AwardDate = awardDate;
	}
	public String getEndDate() {
		return EndDate;
	}
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
	public String getRevokeDate() {
		return RevokeDate;
	}
	public void setRevokeDate(String revokeDate) {
		RevokeDate = revokeDate;
	}
	public String getOrganname() {
		return Organname;
	}
	public void setOrganname(String organname) {
		Organname = organname;
	}
	public String getArea() {
		return Area;
	}
	public void setArea(String area) {
		Area = area;
	}
	@JSONField(serialize=false)
	public String getGradeDesc() {
		return GradeDesc;
	}
	public void setGradeDesc(String gradeDesc) {
		GradeDesc = gradeDesc;
	}
	@JSONField(serialize=false)
	public String getAreaDesc() {
		return AreaDesc;
	}
	public void setAreaDesc(String areaDesc) {
		AreaDesc = areaDesc;
	}
}
