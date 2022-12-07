package com.radomir.drazic.radomirdrazicBE.dto;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

public class ExamDto implements Dto {

	private static final long serialVersionUID = -5935793007291191362L;

	@NotNull(message = "Term of exam is required!")
	private ExamTermDto examTerm;
	@NotNull(message = "Subject is required!")
	private SubjectDto subject;

	private Long id;
	
	@NotNull(message="Date of exam is required!")
	@Future(message="Date of exam must be in future")
	private Calendar date;
	
	private Set<StudentDto> activeStudents;
	
	
	public ExamDto() {
		
	}

	public ExamDto(@NotNull(message = "Term of exam is required!") ExamTermDto examTerm,
			@NotNull(message = "Subject is required!") SubjectDto subject,
			@NotNull(message = "Date of exam is required!") Calendar date) {
		super();
		this.examTerm = examTerm;
		this.subject = subject;
		this.date = date;
		activeStudents = new HashSet<StudentDto>();
	}

	public ExamDto(@NotNull(message = "Term of exam is required!") ExamTermDto examTerm,
			@NotNull(message = "Subject is required!") SubjectDto subject, Long id,
			@NotNull(message = "Date of exam is required!") Calendar date) {
		super();
		this.examTerm = examTerm;
		this.subject = subject;
		this.id = id;
		this.date = date;
		activeStudents = new HashSet<StudentDto>();
	}

	public ExamTermDto getExamTerm() {
		return examTerm;
	}

	public void setExamTerm(ExamTermDto examTerm) {
		this.examTerm = examTerm;
	}

	public SubjectDto getSubject() {
		return subject;
	}

	public void setSubject(SubjectDto subject) {
		this.subject = subject;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Set<StudentDto> getActiveStudents() {
		return activeStudents;
	}

	public void setActiveStudents(Set<StudentDto> activeStudents) {
		this.activeStudents = activeStudents;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, examTerm, id, subject);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExamDto other = (ExamDto) obj;
		return Objects.equals(date, other.date) && Objects.equals(examTerm, other.examTerm)
				&& Objects.equals(id, other.id) && Objects.equals(subject, other.subject);
	}

	@Override
	public String toString() {
		return "ExamDto [examTerm=" + examTerm + ", subject=" + subject + ", id=" + id + ", date=" + date + "]";
	}
}
