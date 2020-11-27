package com.min.hb.student.dtos;

import java.io.Serializable;

public class StudentAnswerDto implements Serializable{
	
	private static final long serialVersionUID = 5376328026243120240L;
	
	private String test_num;
	private String student_code;
	private String test_code;
	private String user_id;
	private String student_score;
	private String student_answer;
	
	public StudentAnswerDto() {
		super();
	}

	public StudentAnswerDto(String test_num, String student_code, String test_code, String user_id,
			String student_score, String student_answer) {
		super();
		this.test_num = test_num;
		this.student_code = student_code;
		this.test_code = test_code;
		this.user_id = user_id;
		this.student_score = student_score;
		this.student_answer = student_answer;
	}

	public String getTest_num() {
		return test_num;
	}

	public void setTest_num(String test_num) {
		this.test_num = test_num;
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

	public String getStudent_score() {
		return student_score;
	}

	public void setStudent_score(String student_score) {
		this.student_score = student_score;
	}

	public String getStudent_answer() {
		return student_answer;
	}

	public void setStudent_answer(String student_answer) {
		this.student_answer = student_answer;
	}

	@Override
	public String toString() {
		return "StudentAnswerDto [test_num=" + test_num + ", student_code=" + student_code + ", test_code=" + test_code
				+ ", user_id=" + user_id + ", student_score=" + student_score + ", student_answer=" + student_answer
				+ "]";
	}
	
}
