package com.radomir.drazic.radomirdrazicBE.dto;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class TitleDto implements Dto{

	private static final long serialVersionUID = -9128761288789530518L;

	private Long titleId;

	@NotEmpty(message="Title is required!")
	@Pattern(regexp = "teaching assistant|assistant professor|associate professor|professor", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Title must be Teaching Assistant, Assistant Professor, Associate Professor or Professor")
	private String title;
	
	
	public TitleDto() {
	}


	public TitleDto(String title) {
		this.title = title;
	}
	
	public TitleDto(Long titleId, String title) {
		this.titleId = titleId;
		this.title = title;
	}


	public Long getTitleId() {
		return titleId;
	}


	public void setTitleId(Long titleId) {
		this.titleId = titleId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	@Override
	public int hashCode() {
		return Objects.hash(title, titleId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TitleDto other = (TitleDto) obj;
		return Objects.equals(title, other.title) && Objects.equals(titleId, other.titleId);
	}


	@Override
	public String toString() {
		return "Title [titleId=" + titleId + ", title=" + title + "]";
	}
	
}
