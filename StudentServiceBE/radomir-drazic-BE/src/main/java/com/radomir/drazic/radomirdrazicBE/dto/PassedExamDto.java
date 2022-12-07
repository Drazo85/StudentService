package com.radomir.drazic.radomirdrazicBE.dto;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;


public class PassedExamDto implements Dto {

	private static final long serialVersionUID = 6295606012344459567L;

	private Long passedExamId;
	
	@NotNull(message="Exam must not be empty")
	private ExamDto exam;
	@NotNull(message="Student must not be empty")
	private StudentDto student;
	@NotNull(message="Grade must not be empty")
	@Range(min = 6, max = 10, message="Grade must be between 6 and 10")
	private Integer grade;
	
	public PassedExamDto() {
	
	}

	public PassedExamDto(ExamDto exam, StudentDto student, Integer grade) {
		this.exam = exam;
		this.student = student;
		this.grade = grade;
	}

	public PassedExamDto(Long id, ExamDto exam, StudentDto student, Integer grade) {
		this.passedExamId = id;
		this.exam = exam;
		this.student = student;
		this.grade = grade;
	}

	public Long getPassedExamId() {
		return passedExamId;
	}

	public void setPassedExamId(Long id) {
		this.passedExamId = id;
	}

	public ExamDto getExam() {
		return exam;
	}

	public void setExam(ExamDto exam) {
		this.exam = exam;
	}

	public StudentDto getStudent() {
		return student;
	}

	public void setStudent(StudentDto student) {
		this.student = student;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(exam, grade, passedExamId, student);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PassedExamDto other = (PassedExamDto) obj;
		return Objects.equals(exam, other.exam) && Objects.equals(grade, other.grade) && Objects.equals(passedExamId, other.passedExamId)
				&& Objects.equals(student, other.student);
	}

	@Override
	public String toString() {
		return "PassedExamDto [id=" + passedExamId + ", exam=" + exam + ", student=" + student + ", grade=" + grade + "]";
	}
	
	
}
