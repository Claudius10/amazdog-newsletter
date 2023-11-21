package com.amazdog.amazdognewsletterapi.services.statistics;

import com.amazdog.amazdognewsletterapi.entities.Statistic;
import com.amazdog.amazdognewsletterapi.repos.statistics.StatisticRepository;
import jakarta.transaction.Transactional;
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
	public List<Statistic> findByTags(String tags) {
		return statisticRepository.findByTags(tags);
	}
}
