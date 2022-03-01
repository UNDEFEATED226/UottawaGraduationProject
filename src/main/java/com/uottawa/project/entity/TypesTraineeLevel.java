package com.uottawa.project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
@Table(name = "types_TraineeLevel")
public class TypesTraineeLevel {
	
	@Id
	@GeneratedValue
	private Long id;

	private String levelEn;
	
	private String levelFr;
}
