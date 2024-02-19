package com.amazdog.amazdognewsletterapi.repos.charts;

import com.amazdog.amazdognewsletterapi.entities.chart.Chart;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomChartRepositoryImpl implements CustomChartRepository {

	private final EntityManager em;

	public CustomChartRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<Chart> findALl() {
		return em.createQuery("select chart from Chart chart join fetch chart.subjects", Chart.class).getResultList();
	}
}
