package com.amazdog.amazdognewsletterapi.repos.statistics;

import com.amazdog.amazdognewsletterapi.entities.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long> {

	@Query("select s from Statistic s where s.tags like %:tags%")
	List<Statistic> findByTags(String tags);
}
