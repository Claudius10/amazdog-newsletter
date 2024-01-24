package com.amazdog.amazdognewsletterapi.repos.charts;

import com.amazdog.amazdognewsletterapi.entities.chart.Chart;

import java.util.List;

public interface CustomChartRepository {

	List<Chart> findALl();
}
