package com.min.hb.student.dtos;

import java.io.Serializable;

public class StudentInfoDto implements Serializable{

	private static final long serialVersionUID = -3462979866970622206L;

	private String student_code;
	private String test_code;
	private String user_id;
	private String student_deptm;
	private String student_name;
	private String student_email;
	private String student_ip;
	private String student_uuid;
	private String test_link;
	private String test_flag;
	private String test_sumpoint;
	
	
	public StudentInfoDto() {
		super();
	}


	public StudentInfoDto(String student_code, String test_code, String user_id, String student_deptm,
			String student_name, String student_email, String student_ip, String student_uuid, String test_link,
			String test_flag, String test_sumpoint) {
		super();
		this.student_code = student_code;
		this.test_code = test_code;
		this.user_id = user_id;
		this.student_deptm = student_deptm;
		this.student_name = student_name;
		this.student_email = student_email;
		this.student_ip = student_ip;
		this.student_uuid = student_uuid;
		this.test_link = test_link;
		this.test_flag = test_flag;
		this.test_sumpoint = test_sumpoint;
	}


	public String getStudent_code() {
		return student_code;
	}


	public void setStudent_code(String student_code) {
		this.student_code = student_code;
	}


	public String getTest_code() {
		return test_code;
	}


	public void setTest_code(String test_code) {
		this.test_code = test_code;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getStudent_deptm() {
		return student_deptm;
	}


	public void setStudent_deptm(String student_deptm) {
		this.student_deptm = student_deptm;
	}


	public String getStudent_name() {
		return student_name;
	}


	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}


	public String getStudent_email() {
		return student_email;
	}


	public void setStudent_email(String student_email) {
		this.student_email = student_email;
	}


	public String getStudent_ip() {
		return student_ip;
	}


	public void setStudent_ip(String student_ip) {
		this.student_ip = student_ip;
	}


	public String getStudent_uuid() {
		return student_uuid;
	}


	public void setStudent_uuid(String student_uuid) {
		this.student_uuid = student_uuid;
	}


	public String getTest_link() {
		return test_link;
	}


	public void setTest_link(String test_link) {
		this.test_link = test_link;
	}


	public String getTest_flag() {
		return test_flag;
	}


	public void setTest_flag(String test_flag) {
		this.test_flag = test_flag;
	}


	public String getTest_sumpoint() {
		return test_sumpoint;
	}


	public void setTest_sumpoint(String test_sumpoint) {
		this.test_sumpoint = test_sumpoint;
	}


	@Override
	public String toString() {
		return "StudentInfoDto [student_code=" + student_code + ", test_code=" + test_code + ", user_id=" + user_id
				+ ", student_deptm=" + student_deptm + ", student_name=" + student_name + ", student_email="
				+ student_email + ", student_ip=" + student_ip + ", student_uuid=" + student_uuid + ", test_link="
				+ test_link + ", test_flag=" + test_flag + ", test_sumpoint=" + test_sumpoint + "]";
	}
	
}
