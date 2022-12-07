package com.radomir.drazic.radomirdrazicBE.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class StudentDto implements Dto{

	private static final long serialVersionUID = 3146484588930674353L;

	private Long studentId;
	
	@NotEmpty(message="Number is required!")
	@Size(min=4, max=4, message="Enter four digits only!")
	private String indexNumber;
	@NotNull(message="Year is required!")
	@Min(2000) 
	@Max(2100)
	private Integer indexYear;
	@NotEmpty(message="Firstname is required!")
	private String firstName;
	@NotEmpty(message="Lastname is required!")
	private String lastName;
	@Email(message="Email must have value@mail.com")
	private String email;
	
	private String address;
	
	private CityDto city;
	@NotNull(message="Year of study is required")
	private Integer yearOfStudy;
	
	private List<ExamDto> activeExams;
	
	public StudentDto() {
	}

	public StudentDto(String indexNumber, Integer indexYear, String firstName, String lastName, String email,
			String address, CityDto city, Integer yearOfStudy) {
		this.indexNumber = indexNumber;
		this.indexYear = indexYear;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.city = city;
		this.yearOfStudy = yearOfStudy;
		activeExams = new ArrayList<ExamDto>();
	}

	
	public StudentDto(Long studentId,
			String indexNumber,
			Integer indexYear,
			String firstName,
			String lastName,
			String email,
			String address, CityDto city,Integer yearOfStudy) {
		this.studentId = studentId;
		this.indexNumber = indexNumber;
		this.indexYear = indexYear;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.city = city;
		this.yearOfStudy = yearOfStudy;
		activeExams = new ArrayList<ExamDto>();
	}

	public String getIndexNumber() {
		return indexNumber;
	}


	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}


	public Integer getIndexYear() {
		return indexYear;
	}


	public void setIndexYear(Integer indexYear) {
		this.indexYear = indexYear;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public CityDto getCity() {
		return city;
	}


	public void setCity(CityDto city) {
		this.city = city;
	}


	public Integer getYearOfStudy() {
		return yearOfStudy;
	}


	public void setYearOfStudy(Integer yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}

	public List<ExamDto> getActiveExams() {
		return activeExams;
	}

	public void setActiveExams(List<ExamDto> activeExams) {
		this.activeExams = activeExams;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, city, email, firstName, indexNumber, indexYear, lastName, yearOfStudy);
	}

	public void addActiveExam(ExamDto exam) {
	    this.activeExams.add(exam);
	    exam.getActiveStudents().add(this);
	  }
	  
	public void removeActiveExam(Long id) {
	   ExamDto exam = this.activeExams.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
	    if (exam != null) {
	      this.activeExams.remove(exam);
	      exam.getActiveStudents().remove(this);
	    }
	  }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentDto other = (StudentDto) obj;
		return Objects.equals(address, other.address) && Objects.equals(city, other.city)
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(indexNumber, other.indexNumber) && Objects.equals(indexYear, other.indexYear)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(yearOfStudy, other.yearOfStudy);
	}


	@Override
	public String toString() {
		return "StudentDto [indexNumber=" + indexNumber + ", indexYear=" + indexYear + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", address=" + address + ", city=" + city
				+ ", yearOfStudy=" + yearOfStudy + "]";
	}


	public Long getStudentId() {
		return studentId;
	}


	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	
	
	
}
