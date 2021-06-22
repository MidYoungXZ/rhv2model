package cn.demo.rhv2model.domain.personal;

import com.alibaba.fastjson.annotation.JSONField;

/**  
* Title: 评分信息  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class ScoreElements{
	/*
	 *报告编号
	 */
	private String ReportId;
	/*
	 *数字解读
	 */
	private String DigitalInterpretation;
	/*
	 *相对位置
	 */
	private String RelativePosition;
	/*
	 *分数说明条数
	 */
	private String FractionalExplanationNumber;
	/*
	 *分数说明
	 */
	private String FractionalExplanation;
	/*
	 *分数说明描述
	 */
	private String FractionalExplanationDesc;
	public String getReportId() {
		return ReportId;
	}

	public void setReportId(String reportId) {
		ReportId = reportId;
	}

	public String getDigitalInterpretation() {
		return DigitalInterpretation;
	}

	public void setDigitalInterpretation(String digitalInterpretation) {
		DigitalInterpretation = digitalInterpretation;
	}

	public String getRelativePosition() {
		return RelativePosition;
	}

	public void setRelativePosition(String relativePosition) {
		RelativePosition = relativePosition;
	}

	public String getFractionalExplanationNumber() {
		return FractionalExplanationNumber;
	}

	public void setFractionalExplanationNumber(String fractionalExplanationNumber) {
		FractionalExplanationNumber = fractionalExplanationNumber;
	}

	public String getFractionalExplanation() {
		return FractionalExplanation;
	}

	public void setFractionalExplanation(String fractionalExplanation) {
		FractionalExplanation = fractionalExplanation;
	}
	@JSONField(serialize=false)
	public String getFractionalExplanationDesc() {
		return FractionalExplanationDesc;
	}

	public void setFractionalExplanationDesc(String fractionalExplanationDesc) {
		FractionalExplanationDesc = fractionalExplanationDesc;
	}
	
}