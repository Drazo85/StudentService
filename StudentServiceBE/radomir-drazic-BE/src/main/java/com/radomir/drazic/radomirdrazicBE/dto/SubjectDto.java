package com.radomir.drazic.radomirdrazicBE.dto;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class SubjectDto implements Dto {

	private static final long serialVersionUID = 4834500447886777582L;

	private Long subjectId;
	@NotEmpty(message="Name is required!")
	@Size(min=3, message="Name must be at least three characters long!")
	private String name;
	
	private String description;
	@NotNull(message="Number of Esps is required!")
	private Integer noOfESP;
	@NotNull(message="Year of study is required")
	private Integer yearOfStudy;
	@NotEmpty(message="Semester is required, please enter Winter or Summer")
	@Pattern(regexp="Winter|Summer", message="Semester must be winter or summer")
	private String semester;
	@NotNull(message="Professor is required")
	private ProfessorDto professor;
	
	public SubjectDto() {
		
	}


	public SubjectDto(String name, String description, Integer noOfESP, Integer yearOfStudy,
			String semester, ProfessorDto professor) {
		this.name = name;
		this.description = description;
		this.noOfESP = noOfESP;
		this.yearOfStudy = yearOfStudy;
		this.semester = semester;
		this.professor = professor;
	}
	
	public SubjectDto(Long subjectId, String name, String description, Integer noOfESP, Integer yearOfStudy,
			String semester, ProfessorDto professor) {
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


	public ProfessorDto getProfessor() {
		return professor;
	}


	public void setProfessor(ProfessorDto professor) {
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
		SubjectDto other = (SubjectDto) obj;
		return Objects.equals(description, other.description) && Objects.equals(name, other.name)
				&& Objects.equals(noOfESP, other.noOfESP) && Objects.equals(professor, other.professor)
				&& Objects.equals(semester, other.semester) && Objects.equals(subjectId, other.subjectId)
				&& Objects.equals(yearOfStudy, other.yearOfStudy);
	}


	@Override
	public String toString() {
		return "SubjectDto [subjectId=" + subjectId + ", name=" + name + ", description=" + description + ", noOfESP="
				+ noOfESP + ", yearOfStudy=" + yearOfStudy + ", semester=" + semester + ", professor=" + professor
				+ "]";
	}

}
