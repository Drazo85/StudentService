package com.radomir.drazic.radomirdrazicBE.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CityDto implements Dto {
	
	private static final long serialVersionUID = -7005536371424989937L;
	
	@NotEmpty(message = "zipCode is required!")
    private String zipCode;
    @NotEmpty(message = "name is required!")
    @Size(min = 2, message = "name must have min 2 characters")
    private String name;

    public CityDto() {
    }

    public CityDto(String zipCode, String name) {
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
        return "CityDto [zipCode=" + zipCode + ", name=" + name + "]";
    }

}
