package com.amazdog.amazdognewsletterapi.services.statistics;

import com.amazdog.amazdognewsletterapi.entities.Statistic;
import com.amazdog.amazdognewsletterapi.repos.statistics.StatisticRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StatisticServiceImpl implements StatisticService {

	private final StatisticRepository statisticRepository;


	public StatisticServiceImpl(StatisticRepository statisticRepository) {
		this.statisticRepository = statisticRepository;
	}

	public void create(Statistic statistic) {
		statistic.setCreatedOn(LocalDateTime.now());
		statisticRepository.save(statistic);
	}

	@Override
	public Optional<Statistic> findById(Long id) {
		return statisticRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		statisticRepository.deleteById(id);
	}

	@Override
	public void deleteAllBySubject(String subject) {
		statisticRepository.deleteAllBySubject(subject);
	}

	@Override
	public List<Statistic> findByTags(String tags) {
		return statisticRepository.findByTags(tags);
	}

	@Override
	public Page<Statistic> findAllBySubjectOrderByDate(String subject, Pageable pageable) {
		return statisticRepository.findAllBySubjectOrderByDate(subject, pageable);
	}

	@Override
	public List<Statistic> findAllBySubjectOrderByDate(String subject) {
		return statisticRepository.findAllBySubjectOrderByDate(subject);
	}

	@Override
	public Page<String> findAllDistinctSubjects(Pageable pageable) {
		return statisticRepository.findAllDistinctSubjects(pageable);
	}
}
