package com.amazdog.amazdognewsletterapi.services.statistics;

import com.amazdog.amazdognewsletterapi.entities.Statistic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StatisticService {

	void create(Statistic statistic);

	Optional<Statistic> findById(Long id);

	void deleteById(Long id);

	void deleteAllBySubject(String subject);

	// these need pagination
	List<Statistic> findByTags(String tags);

	Page<Statistic> findAllBySubjectOrderByDate(String subject, Pageable pageable);

	List<Statistic> findAllBySubjectOrderByDate(String subject);

	Page<String> findAllDistinctSubjects(Pageable pageable);
}
