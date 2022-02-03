package com.uottawa.project.entity;

import java.sql.Date;

public class MainProducts {
	private Long id;
	private String title;
	private Date date;
	private Integer onGoing;
	private Integer peerReviewed;
	private String doi;
	private String authorsAll;
	private String notes;
	private Long type;

	public MainProducts() {	
	}

	public MainProducts(Long id, String title, Date date, Integer onGoing, Integer peerReviewed, String doi,
			String authorsAll, String notes, Long type) {
		this.id = id;
		this.title = title;
		this.date = date;
		this.onGoing = onGoing;
		this.peerReviewed = peerReviewed;
		this.doi = doi;
		this.authorsAll = authorsAll;
		this.notes = notes;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getOnGoing() {
		return onGoing;
	}

	public void setOnGoing(Integer onGoing) {
		this.onGoing = onGoing;
	}

	public Integer getPeerReviewed() {
		return peerReviewed;
	}

	public void setPeerReviewed(Integer peerReviewed) {
		this.peerReviewed = peerReviewed;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public String getAuthorsAll() {
		return authorsAll;
	}

	public void setAuthorsAll(String authorsAll) {
		this.authorsAll = authorsAll;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}
}
