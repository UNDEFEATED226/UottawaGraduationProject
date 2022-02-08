package com.uottawa.project.entity;

public class TypesPartnershipType {
	private Long id;
	
	private String typeEn;
	
	private String typeFr;

	public TypesPartnershipType() {
		
	}
	
	public TypesPartnershipType(Long id, String typeEn, String typeFr) {
		this.id = id;
		this.typeEn = typeEn;
		this.typeFr = typeFr;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeEn() {
		return typeEn;
	}

	public void setTypeEn(String typeEn) {
		this.typeEn = typeEn;
	}

	public String getTypeFr() {
		return typeFr;
	}

	public void setTypeFr(String typeFr) {
		this.typeFr = typeFr;
	}
}
