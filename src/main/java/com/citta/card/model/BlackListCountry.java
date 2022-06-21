package com.citta.card.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="black_list_country")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class BlackListCountry {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blacklist_country_seq")
	@Column(name="id")
	private long id;
	
	private String code;
	
	private String name;
}
