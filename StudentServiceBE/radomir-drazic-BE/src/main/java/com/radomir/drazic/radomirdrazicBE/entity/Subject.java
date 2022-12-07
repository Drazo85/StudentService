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




@Entity
@Table(name="subject")

public class Subject implements com.radomir.drazic.radomirdrazicBE.entity.Entity{
	
	private static final long serialVersionUID = -1737412076845140890L;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subjectId;
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private Integer noOfESP;
	@Column
	private Integer yearOfStudy;
	@Column
	private String semester;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "professor")
	private Professor professor;
	
	public Subject() {
		
	}

	public Subject(String name, String description, Integer noOfESP, Integer yearOfStudy,
			String semester, Professor professor) {
		this.name = name;
		this.description = description;
		this.noOfESP = noOfESP;
		this.yearOfStudy = yearOfStudy;
		this.semester = semester;
		this.professor = professor;
	}

	public Subject(Long subjectId, String name, String description, Integer noOfESP, Integer yearOfStudy,
			String semester, Professor professor) {
		this.subjectId = subjectId;
		this.name = name;
		this.description = description;
		this.noOfESP = noOfESP;
		this.yearOfStudy = yearOfStudy;
		this.semester = semester;
		this.professor = professor;
	}


	public Long getSubjectId() {
		return subjectId;
	}


	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Integer getNoOfESP() {
		return noOfESP;
	}


	public void setNoOfESP(Integer noOfESP) {
		this.noOfESP = noOfESP;
	}


	public Integer getYearOfStudy() {
		return yearOfStudy;
	}


	public void setYearOfStudy(Integer yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}


	public String getSemester() {
		return semester;
	}


	public void setSemester(String semester) {
		this.semester = semester;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}


	@Override
	public int hashCode() {
		return Objects.hash(description, name, noOfESP, professor, semester, subjectId, yearOfStudy);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subject other = (Subject) obj;
		return Objects.equals(description, other.description) && Objects.equals(name, other.name)
				&& Objects.equals(noOfESP, other.noOfESP) && Objects.equals(professor, other.professor)
				&& Objects.equals(semester, other.semester) && Objects.equals(subjectId, other.subjectId)
				&& Objects.equals(yearOfStudy, other.yearOfStudy);
	}

	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", name=" + name + ", description=" + description + ", noOfESP="
				+ noOfESP + ", yearOfStudy=" + yearOfStudy + ", semester=" + semester + ", professor=" + professor
				+ "]";
	}
	
}
