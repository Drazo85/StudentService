package com.radomir.drazic.radomirdrazicBE.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@javax.persistence.Entity
@Table(name = "user")
public class User implements Entity {

 
	private static final long serialVersionUID = 8020083632807711179L;
	
	@Column(name = "firstname")
    String firstName;
    @Column(name = "lastname")
    String lastName;
    @Id
    String username;
    String password;


    @OneToMany(mappedBy = "username", fetch = FetchType.LAZY)
    private List<Authorities> authoritiesList;

    public User() {
    }

    public User(String firstName, String lastName, String username, String password, List<Authorities> authoritiesList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.authoritiesList = authoritiesList;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Authorities> getAuthoritiesList() {
        return authoritiesList;
    }

    public void setAuthoritiesList(List<Authorities> authoritiesList) {
        this.authoritiesList = authoritiesList;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authoritiesList=" + authoritiesList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(authoritiesList, that.authoritiesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, username, password, authoritiesList);
    }
}

