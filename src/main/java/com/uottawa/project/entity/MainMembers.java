package com.uottawa.project.entity;

import java.time.LocalDate;

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

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="main_Members")
public class MainMembers {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;

	@Column(name="business_name")
	private String businessName;
	
	@Column(name="date_joined")
	private LocalDate date_joined;
	
	@Column(name="interview_date")
	private LocalDate interviewDate;
	
	@Column(name="date_guest_joined")
	private LocalDate dateGuestJoined;
	
	@Column(name="date_in_active")
	private LocalDate dateInActive;
	
	private String email;
	
	private String address;
	
	private String city;
	
	private String province;
	
	private String country;
	
	@Column(name="postal_code")
	private String postalCode;
	
	@Column(name="business_phone")
	private String businessPhone;
	
	@Column(name="mobile_phone")
	private String mobilePhone;
	
	@Column(name="interview_notes")
	private String interviewNotes;
	
	@Column(name="is_active")
	private Integer isActive;
	
	@Column(name="is_guest")
	private Integer isGuest;
	
	private Integer category;
	
	@Column(name="keywords_EN")
	private String keywordsEN;
	
	@Column(name="keywords_FR")
	private String keywordsFR;
	
	@Column(name="problems_EN")
	private String problemsEN;
	
	@Column(name="problems_FR")
	private String problemsFR;
	
	private String dream;
	
	private String notes;
	
	@Column(name="how_can_we_help")
	private String howCanWeHelp;
	
	//types_Faculty id,to be implemented
	private Long faculty;
	
	@Column(name="partnerships_1_notes")
	private String partnerships1Notes;
	
	@Column(name="partnerships_2_notes")
	private String partnerships2Notes;
	
	@Column(name="partnerships_3_notes")
	private String partnerships3Notes;
	
	@Column(name="partnerships_future_notes")
	private String partnershipsFutureNotes;

	@Column(name="future_promotion_notes")
	private String futurePromotionNotes;
	
	//types_FrequencyIndicator id, to be implemented
	@Column(name="indicators_frequency")
	private Long indicatorsFrequency;
	
	@Column(name="other_comments")
	private String otherComments;
	
	@Column(name="interviewer_notes")
	private String interviewerNotes;
	
	private String cv;
}
