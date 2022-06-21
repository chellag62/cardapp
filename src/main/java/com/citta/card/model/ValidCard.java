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
@Table(name="valid_card")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ValidCard {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "valid_card_seq")
	@Column(name="id")
	private long id;
	
	@Column(name="card_number")
	private String cardNumber;
	
	@Column(name="country_code")
	private String countryCode;
}
