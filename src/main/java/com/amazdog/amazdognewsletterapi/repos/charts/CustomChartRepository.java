package com.amazdog.amazdognewsletterapi.repos.charts;

import com.amazdog.amazdognewsletterapi.entities.dtos.chart.Chart;

import java.util.List;

public interface CustomChartRepository {

	List<Chart> findALl();
}
