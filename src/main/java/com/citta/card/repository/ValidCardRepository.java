package com.citta.card.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.citta.card.model.ValidCard;

@Transactional
@EnableJpaRepositories
@Repository
public interface ValidCardRepository extends JpaRepository<ValidCard, Long> {

}
