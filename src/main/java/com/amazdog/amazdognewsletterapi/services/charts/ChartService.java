package com.amazdog.amazdognewsletterapi.services.charts;

import com.amazdog.amazdognewsletterapi.entities.chart.Chart;
import com.amazdog.amazdognewsletterapi.entities.chart.ChartData;
import com.amazdog.amazdognewsletterapi.entities.chart.Subject;

import java.util.List;

public interface ChartService {

	void create(Chart chart);

	List<Chart> findALl();

	List<ChartData> getChartData();

	void remove(Long chartId);

	void addSubject(Long chartId, Subject subject);

	void removeSubject(Long chartId, Subject subject);
}
