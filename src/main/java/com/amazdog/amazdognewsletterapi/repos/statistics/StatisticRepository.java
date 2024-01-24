package com.amazdog.amazdognewsletterapi.repos.statistics;

import com.amazdog.amazdognewsletterapi.entities.Statistic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long>, CustomStatisticRepository {

	@Query("select s from Statistic s where s.tags like %:tags%")
	List<Statistic> findByTags(String tags);

	Page<Statistic> findAllBySubjectOrderByDate(String subject, Pageable pageable);

	List<Statistic> findAllBySubjectOrderByDate(String subject);

	@Query("select distinct s.subject from Statistic s")
	Page<String> findAllDistinctSubjects(Pageable pageable);

	void deleteAllBySubject(String subject);
}
