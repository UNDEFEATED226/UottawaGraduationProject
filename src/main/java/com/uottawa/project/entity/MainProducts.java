package com.uottawa.project.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "main_Products")
public class MainProducts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	private String title;
	
	private Date date;
	
	@Column(name="on_going")
	private Integer onGoing;
	
	@Column(name="peer_reviewed")
	private Integer peerReviewed;

	private String doi;

	@Column(name="authors_all")
	private String authorsAll;
	
	private String notes;
	
	//types_Product id
	private Long type;
}
