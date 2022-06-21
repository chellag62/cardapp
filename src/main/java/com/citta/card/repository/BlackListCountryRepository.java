package com.citta.card.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.citta.card.model.BlackListCountry;
import com.citta.card.model.CardInfo;

@Transactional
@EnableJpaRepositories
@Repository
public interface BlackListCountryRepository extends JpaRepository<BlackListCountry, Long> {
	
	@Query(value = "select b from BlackListCountry b where b.code = :countryCode")
	BlackListCountry findByCode(@Param("countryCode") String countryCode);
}
