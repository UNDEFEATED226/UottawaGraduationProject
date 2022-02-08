package com.uottawa.project.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class MainGrants {
	private Long id;

	private String title;

	private BigDecimal amount;

	private Integer isThroughLRI;

	// types_GrantStauts id
	private Long status;

	private Date submissionDate;

	private Date receivedDate;

	private Date finishedDate;

	// types_GrantSource id
	private Long source;

	private String investigatorsAll;

	private String notes;

	public MainGrants() {

	}

	public MainGrants(Long id, String title, BigDecimal amount, Integer isThroughLRI, Long status, Date submissionDate,
			Date receivedDate, Date finishedDate, Long source, String investigatorsAll, String notes) {
		this.id = id;
		this.title = title;
		this.amount = amount;
		this.isThroughLRI = isThroughLRI;
		this.status = status;
		this.submissionDate = submissionDate;
		this.receivedDate = receivedDate;
		this.finishedDate = finishedDate;
		this.source = source;
		this.investigatorsAll = investigatorsAll;
		this.notes = notes;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getIsThroughLRI() {
		return isThroughLRI;
	}

	public void setIsThroughLRI(Integer isThroughLRI) {
		this.isThroughLRI = isThroughLRI;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public Date getFinishedDate() {
		return finishedDate;
	}

	public void setFinishedDate(Date finishedDate) {
		this.finishedDate = finishedDate;
	}

	public Long getSource() {
		return source;
	}

	public void setSource(Long source) {
		this.source = source;
	}

	public String getInvestigatorsAll() {
		return investigatorsAll;
	}

	public void setInvestigatorsAll(String investigatorsAll) {
		this.investigatorsAll = investigatorsAll;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
