package com.radomir.drazic.radomirdrazicBE.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="examTerm")
public class ExamTerm implements com.radomir.drazic.radomirdrazicBE.entity.Entity {

	private static final long serialVersionUID = -1188221310479038L;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long examTermId;
	@Column
	private String name;
	@Column
	private Calendar startDate;
	@Column
	private Calendar endDate;
	@Column
	private Boolean isActive;
	@OneToMany(mappedBy="examTerm")
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE) 
	private List<Exam> exams;
	
	public ExamTerm() {
	}
	
	public ExamTerm(String name, Calendar startDate, Calendar endDate) {//, Boolean isActive) {//, List<Exam> exams) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isActive = isActive();
		this.exams = new ArrayList<Exam>();
	}

	public ExamTerm(Long examTermId, String name, Calendar startDate, Calendar endDate) {//, Boolean isActive) {//, List<Exam> exams) {
		this.examTermId = examTermId;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isActive = isActive();
		this.exams = new ArrayList<Exam>();
	}

	public Long getExamTermId() {
		return examTermId;
	}

	public void setExamTermId(Long examTermId) {
		this.examTermId = examTermId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public List<Exam> getExams() {
		return exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}
	
	public boolean isActive() {
		Calendar today = Calendar.getInstance();
		if(today.after(startDate) && today.before(endDate)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(endDate, examTermId, exams, isActive, name, startDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExamTerm other = (ExamTerm) obj;
		return Objects.equals(endDate, other.endDate) && Objects.equals(examTermId, other.examTermId)
				&& Objects.equals(exams, other.exams) && Objects.equals(isActive, other.isActive)
				&& Objects.equals(name, other.name) && Objects.equals(startDate, other.startDate);
	}

	@Override
	public String toString() {
		return "ExamTerm [examTermId=" + examTermId + ", name=" + name + ", startDate=" + startDate + ", endDate="
				+ endDate + ", isActive=" + isActive + ", exams=" + exams + "]";
	}
}
