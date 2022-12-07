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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="student")
public class Student implements com.radomir.drazic.radomirdrazicBE.entity.Entity {
	
	private static final long serialVersionUID = 159872627844392599L;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long studentId;
	@Column
	private String indexNumber;
	@Column
	private Integer indexYear;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column(unique = true)
	private String email;
	@Column
	private String address;
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "city")
	private City city;
	@Column
	private Integer yearOfStudy;
	
	@ManyToMany(cascade = {CascadeType.MERGE})
	@JoinTable(
	   name="ActiveExams",
	   joinColumns=@JoinColumn(name="student_id"),
	   inverseJoinColumns=@JoinColumn(name="id"))
	@JsonBackReference
	private List<Exam> activeExams;
	
	public Student() {
	}


	public Student(String indexNumber, Integer indexYear, String firstName, String lastName, String email,
			String address, City city, Integer yearOfStudy) {
		this.indexNumber = indexNumber;
		this.indexYear = indexYear;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.city = city;
		this.yearOfStudy = yearOfStudy;
		activeExams = new ArrayList<Exam>();
	}
	
	public Student(Long id, String indexNumber, Integer indexYear, String firstName, String lastName, String email,
			String address, City city, Integer yearOfStudy) {
		this.studentId = id;
		this.indexNumber = indexNumber;
		this.indexYear = indexYear;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.city = city;
		this.yearOfStudy = yearOfStudy;
		activeExams = new ArrayList<Exam>();
	}

	public Long getStudentId() {
		return studentId;
	}


	public void setStudentId(Long studentId) {
		this.studentId = studentId;
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


	public City getCity() {
		return city;
	}


	public void setCity(City city) {
		this.city = city;
	}


	public Integer getYearOfStudy() {
		return yearOfStudy;
	}


	public void setYearOfStudy(Integer yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}
	
	public List<Exam> getActiveExams() {
		return activeExams;
	}

	public void setActiveExams(List<Exam> activeExams) {
		this.activeExams = activeExams;
	}

	public void addActiveExam(Exam exam) {
		
	    this.activeExams.add(exam);
	    exam.getActiveStudents().add(this);
	  }
	  
	public void removeActiveExam(Long id) {
	    Exam exam = this.activeExams.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
	    if (exam != null) {
	      this.activeExams.remove(exam);
	      exam.getActiveStudents().remove(this);
	    }
	  }
	
	public void removeExamFromActiveByDate(Exam exam) {
		Calendar today = Calendar.getInstance();
		today.add(Calendar.MONTH, 1);
		System.out.println(today);
		if(today.after(exam.getDate())) {
			this.activeExams.remove(exam);
			exam.getActiveStudents().remove(this);
		}
	}
	  
	@Override
	public int hashCode() {
		return Objects.hash(address, city, email, firstName, indexNumber, indexYear, lastName, yearOfStudy);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(address, other.address) && Objects.equals(city, other.city)
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(indexNumber, other.indexNumber) && Objects.equals(indexYear, other.indexYear)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(yearOfStudy, other.yearOfStudy);
	}


	@Override
	public String toString() {
		return "Student [indexNumber=" + indexNumber + ", indexYear=" + indexYear + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", address=" + address + ", city=" + city
				+ ", yearOfStudy=" + yearOfStudy + "]";
	}
	
}
