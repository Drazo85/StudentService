package com.radomir.drazic.radomirdrazicBE.dto;

import java.util.Calendar;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProfessorDto implements Dto {

	private static final long serialVersionUID = 2990310944867175413L;

	private Long id;
	@NotEmpty(message="Firstname is required!")
	private String firstName;
	@NotEmpty(message="Lastname is required!")
	private String lastName;
	@Email(message="Email must have value@mail.com")
	private String email;
	
	private String address;
	
	private CityDto city;
	@Size(min=9, message="Minimum number characters must be 9!")
	private String phoneNumber;
	@NotNull(message="Reelection date is required!")
	@Future(message="Reelection date must be in future!")
	private Calendar reelectionDate;
	@NotNull(message="Job title is required!")
	private TitleDto title;
	
	public ProfessorDto() {
	}
	
	public ProfessorDto(String firstName, String lastName, String email, String address, CityDto city,
			String phoneNumber, Calendar reelectionDate, TitleDto title) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.reelectionDate = reelectionDate;
		this.title = title;
	}

	public ProfessorDto(Long id, String firstName, String lastName, String email, String address, CityDto city,
			String phoneNumber, Calendar reelectionDate, TitleDto title) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.reelectionDate = reelectionDate;
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Calendar getReelectionDate() {
		return reelectionDate;
	}

	public void setReelectionDate(Calendar reelectionDate) {
		this.reelectionDate = reelectionDate;
	}

	public TitleDto getTitle() {
		return title;
	}

	public void setTitle(TitleDto title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, city, email, firstName, id, lastName, phoneNumber, reelectionDate, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfessorDto other = (ProfessorDto) obj;
		return Objects.equals(address, other.address) && Objects.equals(city, other.city)
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(phoneNumber, other.phoneNumber)
				&& Objects.equals(reelectionDate, other.reelectionDate) && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "ProfessorDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", address=" + address + ", city=" + city + ", phoneNumber=" + phoneNumber + ", reelectionDate="
				+ reelectionDate + ", title=" + title + "]";
	}
}
