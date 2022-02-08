package com.uottawa.project.entity;

public class MainPartners {
	private Long id;
	
	private String name;

	// types_PartnerShipType id
	private Long type;

	// main_Events id
	private Long scope;

	private String notes;

	public MainPartners() {
	}

	public MainPartners(Long id, String name, Long type, Long scope, String notes) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.scope = scope;
		this.notes = notes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getScope() {
		return scope;
	}

	public void setScope(Long scope) {
		this.scope = scope;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
