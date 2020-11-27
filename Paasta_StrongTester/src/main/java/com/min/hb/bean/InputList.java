package com.min.hb.bean;

import java.util.List;

import com.min.hb.student.dtos.StudentInfoDto;

public class InputList {

	private List<StudentInfoDto> lists;

	public void setLists(List<StudentInfoDto> lists) {
		this.lists = lists;
	}

	//출력 리스트 폼
	private String listForm(StudentInfoDto stdto) {
		StringBuffer sb = new StringBuffer();

		sb.append(" <tr>");
		sb.append("<td><input type='checkbox' class='checkbox' name='std_id' ");
		sb.append("   value='"+stdto.getStudent_code()+"' /></td>");
		sb.append("<td>"+stdto.getStudent_code()+"</td>");
		sb.append("<td>"+stdto.getStudent_deptm()+"</td>");
		sb.append("<td>"+stdto.getStudent_name()+"</td>");
		sb.append("<td>"+stdto.getStudent_email()+"</td>");
		sb.append("<td class='data-flag'>"+stdto.getTest_flag()+"</td>");
		sb.append("<td>");
		sb.append("<input type='submit' onclick='checkIt(\""+stdto.getStudent_code()+"\");'");
		sb.append("class='student__button showInfo' name='stdId'");
		sb.append("value='"+stdto.getStudent_code()+"' />");
		sb.append("</td>");
		sb.append("<td><button type='button' class='setting__buttons edit__btn'");
		sb.append("value='"+stdto.getStudent_code()+"'>");
		sb.append("<i class='fas fa-edit'></i>");
		sb.append("</button></td>");
		sb.append("<td><button class='setting__buttons delete__btn'");
		sb.append("type='button' value='"+stdto.getStudent_code()+"'>");
		sb.append("<i class='fas fa-trash-alt'></i>");
		sb.append("</button></td>");
		sb.append("</tr>");
		
		return sb.toString();
	}
	
	public String getListForm() {
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < lists.size(); i++) {
			sb.append(listForm(lists.get(i)));
		}
		return sb.toString();
	}
	
}
