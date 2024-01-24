package com.amazdog.amazdognewsletterapi.repos.statistics;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class CustomStatisticRepositoryImpl implements CustomStatisticRepository {

	@PersistenceContext
	private EntityManager em;
}
