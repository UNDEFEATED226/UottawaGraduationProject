package com.uottawa.project.entity;

public class TypesTraineeLevel {
	private Long id;

	private String levelEn;
	
	private String levelFr;
	
	public TypesTraineeLevel() {
		
	}

	public TypesTraineeLevel(Long id, String levelEn, String levelFr) {
		this.id = id;
		this.levelEn = levelEn;
		this.levelFr = levelFr;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLevelEn() {
		return levelEn;
	}

	public void setLevelEn(String levelEn) {
		this.levelEn = levelEn;
	}

	public String getLevelFr() {
		return levelFr;
	}

	public void setLevelFr(String levelFr) {
		this.levelFr = levelFr;
	}
}
