package com.radomir.drazic.radomirdrazicBE.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "city")
public class City implements com.radomir.drazic.radomirdrazicBE.entity.Entity {
	
	private static final long serialVersionUID = 1736184126349454543L;
	
	@Id
    @Column(name = "zipcode")
    private String zipCode;
    @Column
    private String name;

    public City() {
    }

    public City(String zipCode, String name) {
        this.zipCode = zipCode;
        this.name = name;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CityEntity{" +
                "zipCode='" + zipCode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City that = (City) o;
        return zipCode.equals(that.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode);
    }
}
