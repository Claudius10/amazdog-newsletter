package com.amazdog.amazdognewsletterapi.repos.charts;

import com.amazdog.amazdognewsletterapi.entities.dtos.chart.Chart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChartRepository extends JpaRepository<Chart, Long>, CustomChartRepository {
}
