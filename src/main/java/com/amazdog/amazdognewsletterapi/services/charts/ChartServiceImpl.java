package com.amazdog.amazdognewsletterapi.services.charts;

import com.amazdog.amazdognewsletterapi.entities.Statistic;
import com.amazdog.amazdognewsletterapi.entities.chart.Chart;
import com.amazdog.amazdognewsletterapi.entities.chart.ChartData;
import com.amazdog.amazdognewsletterapi.entities.chart.Subject;
import com.amazdog.amazdognewsletterapi.repos.charts.ChartRepository;
import com.amazdog.amazdognewsletterapi.services.statistics.StatisticService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ChartServiceImpl implements ChartService {

	private final ChartRepository chartRepository;

	private final StatisticService statisticService;

	public ChartServiceImpl(ChartRepository chartRepository, StatisticService statisticService) {
		this.chartRepository = chartRepository;
		this.statisticService = statisticService;
	}

	@Override
	public void create(Chart chart) {
		chartRepository.save(chart);
	}

	@Override
	public List<Chart> findALl() {
		return chartRepository.findALl();
	}

	@Override
	public List<ChartData> getChartData() {
		List<ChartData> chartData = new ArrayList<>();
		List<Chart> charts = findALl();
		List<List<Statistic>> statisticsList = new ArrayList<>();

		for (Chart chart : charts) {
			for (Subject subject : chart.getSubjects()) {
				List<Statistic> statistics = statisticService.findAllBySubjectOrderByDate(subject.getName());
				statisticsList.add(new ArrayList<>(statistics));
			}
			chartData.add(new ChartData(chart.getTitle(), chart.getType(), statisticsList));
			statisticsList.clear();
		}
		return chartData;
	}

	@Override
	public void remove(Long chartId) {
		chartRepository.deleteById(chartId);
	}
}
