package com.amazdog.amazdognewsletterapi.services.charts;

import com.amazdog.amazdognewsletterapi.entities.dtos.chart.Chart;
import com.amazdog.amazdognewsletterapi.entities.dtos.chart.ChartData;

import java.util.List;

public interface ChartService {

	void create(Chart chart);

	List<Chart> findALl();

	List<ChartData> getChartData();

	void remove(Long chartId);
}
