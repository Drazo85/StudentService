package com.radomir.drazic.radomirdrazicBE.entity;

import java.util.Calendar;
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
@Table(name="professor")
public class Professor implements com.radomir.drazic.radomirdrazicBE.entity.Entity {

	private static final long serialVersionUID = 1429771353094951621L;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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
	private String phoneNumber;
	@Column
	private Calendar reelectionDate;
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "title")
	private Title title;
	
	public Professor() {
	}
	
	public Professor(String firstName, String lastName, String email, String address, City city,
			String phoneNumber, Calendar reelectionDate, Title title) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.reelectionDate = reelectionDate;
		this.title = title;
	}

	public Professor(Long id, String firstName, String lastName, String email, String address, City city,
			String phoneNumber, Calendar reelectionDate, Title title) {
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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
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

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
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
		Professor other = (Professor) obj;
		return Objects.equals(address, other.address) && Objects.equals(city, other.city)
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(phoneNumber, other.phoneNumber)
				&& Objects.equals(reelectionDate, other.reelectionDate) && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Professor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", address=" + address + ", city=" + city + ", phoneNumber=" + phoneNumber + ", reelectionDate="
				+ reelectionDate + ", title=" + title + "]";
	}
	
}
