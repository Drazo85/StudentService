package com.radomir.drazic.radomirdrazicBE.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="title")
public class Title implements com.radomir.drazic.radomirdrazicBE.entity.Entity {
	
	private static final long serialVersionUID = 6040551328264243771L;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long titleId;
	@Column(unique = true)
	private String title;
	
	
	public Title() {
	}

	public Title(String title) {
		this.title = title;
	}
	
	public Title(Long titleId, String title) {
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
		Title other = (Title) obj;
		return Objects.equals(title, other.title) && Objects.equals(titleId, other.titleId);
	}


	@Override
	public String toString() {
		return "Title [titleId=" + titleId + ", title=" + title + "]";
	}

}
