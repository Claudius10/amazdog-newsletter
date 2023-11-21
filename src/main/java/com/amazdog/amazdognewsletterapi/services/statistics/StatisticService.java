package com.amazdog.amazdognewsletterapi.services.statistics;

import com.amazdog.amazdognewsletterapi.entities.Statistic;

import java.util.List;
import java.util.Optional;

public interface StatisticService {

	void create(Statistic statistic);

	Optional<Statistic> findById(Long id);

	List<Statistic> findByTags(String tags);
}
