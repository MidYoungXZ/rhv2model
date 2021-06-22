package cn.demo.rhv2model.domain.personal;

import java.util.List;

/**  
* Title: 其他标注及声明List  
* Description:  
* @author huangzhong 
* @date 2018年11月26日  
*/ 
public class AnnotationsList {
	
	//异议标注条数
	private String dissentingAnnotationcount;
	//个人声明条数
	private String personalAnnotationcount;
	//本人声明的list
	private List<OtherAnnotations> personalAnnotationsLists;
	//异议标注的list
	private List<OtherAnnotations> dissentingAnnotationsLists;
	public String getDissentingAnnotationcount() {
		return dissentingAnnotationcount;
	}
	public void setDissentingAnnotationcount(String dissentingAnnotationcount) {
		this.dissentingAnnotationcount = dissentingAnnotationcount;
	}
	public String getPersonalAnnotationcount() {
		return personalAnnotationcount;
	}
	public void setPersonalAnnotationcount(String personalAnnotationcount) {
		this.personalAnnotationcount = personalAnnotationcount;
	}
	public List<OtherAnnotations> getPersonalAnnotationsLists() {
		return personalAnnotationsLists;
	}
	public void setPersonalAnnotationsLists(List<OtherAnnotations> personalAnnotationsLists) {
		this.personalAnnotationsLists = personalAnnotationsLists;
	}
	public List<OtherAnnotations> getDissentingAnnotationsLists() {
		return dissentingAnnotationsLists;
	}
	public void setDissentingAnnotationsLists(List<OtherAnnotations> dissentingAnnotationsLists) {
		this.dissentingAnnotationsLists = dissentingAnnotationsLists;
	}	
}
