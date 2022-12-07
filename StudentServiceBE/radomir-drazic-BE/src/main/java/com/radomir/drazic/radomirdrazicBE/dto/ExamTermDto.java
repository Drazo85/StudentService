package com.radomir.drazic.radomirdrazicBE.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class ExamTermDto implements Dto {

	private static final long serialVersionUID = 715143634047233526L;
	
	private Long examTermId;

	@NotEmpty(message="Name is required!")
	private String name;
	@NotNull(message="Start date of exam term is required!")
	@FutureOrPresent(message="Start date must be in future")
	private Calendar startDate;
	@NotNull(message="End date of exam term is required!")
	@Future(message="End date must be in future")
	private Calendar endDate;
	@NotNull(message = "Please enter exam term status!")
	private Boolean isActive;
	private List<ExamDto> exams;
	
	public ExamTermDto() {
	}

	public ExamTermDto(String name, Calendar startDate, Calendar endDate) {//, Boolean isActive) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isActive = isActive();
		System.out.println(isActive());
		this.exams = new ArrayList<ExamDto>();
	}

	public ExamTermDto(Long examTermId, String name, Calendar startDate, Calendar endDate) {//, Boolean isActive) {
		this.examTermId = examTermId;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isActive = isActive();
		this.exams = new ArrayList<ExamDto>();
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

	public List<ExamDto> getExams() {
		return exams;
	}

	public void setExams(List<ExamDto> exams) {
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
		ExamTermDto other = (ExamTermDto) obj;
		return Objects.equals(endDate, other.endDate) && Objects.equals(examTermId, other.examTermId)
				&& Objects.equals(exams, other.exams) && Objects.equals(isActive, other.isActive)
				&& Objects.equals(name, other.name) && Objects.equals(startDate, other.startDate);
	}

	@Override
	public String toString() {
		return "ExamTermDto [examTermId=" + examTermId + ", name=" + name + ", startDate=" + startDate + ", endDate="
				+ endDate + ", isActive=" + isActive + ", exams=" + exams + "]";
	}

	
}
