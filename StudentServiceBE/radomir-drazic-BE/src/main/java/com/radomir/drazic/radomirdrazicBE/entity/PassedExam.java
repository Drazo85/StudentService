package com.radomir.drazic.radomirdrazicBE.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="passedExam", uniqueConstraints = @UniqueConstraint(columnNames = { "exam", "student"}))
public class PassedExam implements com.radomir.drazic.radomirdrazicBE.entity.Entity {

	private static final long serialVersionUID = -7476388595071642842L;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long passedExamId;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "exam")
	private Exam exam;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "student")
	private Student student;
	@Column
	private Integer grade;
	
	public PassedExam() {
	
	}

	public PassedExam(Exam exam, Student student, Integer grade) {
		this.exam = exam;
		this.student = student;
		this.grade = grade;
	}

	public PassedExam(Long id, Exam exam, Student student, Integer grade) {
		this.passedExamId = id;
		this.exam = exam;
		this.student = student;
		this.grade = grade;
	}

	public Long getPassedExamId() {
		return passedExamId;
	}

	public void setpassedExamId(Long id) {
		this.passedExamId = id;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
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
		PassedExam other = (PassedExam) obj;
		return Objects.equals(exam, other.exam) && Objects.equals(grade, other.grade) && Objects.equals(passedExamId, other.passedExamId)
				&& Objects.equals(student, other.student);
	}

	@Override
	public String toString() {
		return "PassedExams [id=" + passedExamId + ", exam=" + exam + ", student=" + student + ", grade=" + grade + "]";
	}
	
}
