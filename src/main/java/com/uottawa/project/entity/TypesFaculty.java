package com.uottawa.project.entity;

public class TypesFaculty {
	
	private Long id;

	private String nameEn;
	
	private String nameFr;
	
	public TypesFaculty() {
		
	}

	public TypesFaculty(Long id, String nameEn, String nameFr) {
		this.id = id;
		this.nameEn = nameEn;
		this.nameFr = nameFr;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getNameFr() {
		return nameFr;
	}

	public void setNameFr(String nameFr) {
		this.nameFr = nameFr;
	}
}
