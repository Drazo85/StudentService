package com.radomir.drazic.radomirdrazicBE.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="exam", uniqueConstraints = @UniqueConstraint(columnNames = { "examTerm", "subject"}))

public class Exam implements com.radomir.drazic.radomirdrazicBE.entity.Entity {

	private static final long serialVersionUID = 5469865331092720500L;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "examTerm")
	private ExamTerm examTerm;
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "subject")
	private Subject subject;
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private Calendar date;
	@ManyToMany(
		      cascade = {
	          CascadeType.MERGE
	      },mappedBy = "activeExams")
	@JsonBackReference
	private List<Student> activeStudents;
	
	public Exam() {
		
	}

	public Exam(ExamTerm examTerm, Subject subject, Calendar date) {
		super();
		this.examTerm = examTerm;
		this.subject = subject;
		this.date = date;
		activeStudents = new ArrayList<Student>();
	}

	public Exam(ExamTerm examTerm, Subject subject, Long id, Calendar date) {
		super();
		this.examTerm = examTerm;
		this.subject = subject;
		this.id = id;
		this.date = date;
		activeStudents = new ArrayList<Student>();
	}

	public ExamTerm getExamTerm() {
		return examTerm;
	}

	public void setExamTerm(ExamTerm examTerm) {
		this.examTerm = examTerm;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Student> getActiveStudents() {
		return activeStudents;
	}

	public void setActiveStudents(List<Student> activeStudents) {
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
		Exam other = (Exam) obj;
		return Objects.equals(date, other.date) && Objects.equals(examTerm, other.examTerm)
				&& Objects.equals(id, other.id) && Objects.equals(subject, other.subject);
	}

	@Override
	public String toString() {
		return "Exam [examTerm=" + examTerm + ", subject=" + subject + ", id=" + id + ", date=" + date + "]";
	}
}
