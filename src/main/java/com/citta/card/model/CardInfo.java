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
@Table(name="card_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CardInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cardinfo_seq")
	@Column(name="id")
	private long id;
	
	@Column(name="bin_number")
	private long binNumber;
	
	private String brand;
	
	private String type;
	
	private String issuer;
	
	@Column(name="country_code")
	private String countryCode;
	
	@Column(name="country_name")
	private String countryName;

	@Column(name="black_list_flag")
	private boolean blackListFlag;
}
