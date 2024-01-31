package com.amazdog.amazdognewsletterapi.controllers;

import com.amazdog.amazdognewsletterapi.entities.Statistic;
import com.amazdog.amazdognewsletterapi.entities.dtos.chart.Chart;
import com.amazdog.amazdognewsletterapi.entities.dtos.chart.ChartData;
import com.amazdog.amazdognewsletterapi.entities.post.Post;
import com.amazdog.amazdognewsletterapi.services.charts.ChartService;
import com.amazdog.amazdognewsletterapi.services.posts.PostService;
import com.amazdog.amazdognewsletterapi.services.statistics.StatisticService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourcesController {

	private final ChartService chartService;

	private final StatisticService statisticService;

	private final PostService postService;

	public ResourcesController(ChartService chartService, StatisticService statisticService, PostService postService) {
		this.chartService = chartService;
		this.statisticService = statisticService;
		this.postService = postService;
	}

	@GetMapping("/charts")
	public ResponseEntity<List<Chart>> findAllCharts() {
		return ResponseEntity.status(HttpStatus.OK).body(chartService.findALl());
	}

	@GetMapping("/charts/data")
	public ResponseEntity<List<ChartData>> getChartData() {
		return ResponseEntity.status(HttpStatus.OK).body(chartService.getChartData());
	}

	@GetMapping(path = "/subjects", params = "subject")
	public ResponseEntity<List<Statistic>> findStatisticsBySubject(@RequestParam("subject") String subject) {
		return ResponseEntity.status(HttpStatus.OK).body(statisticService.findAllBySubjectOrderByDate(subject));
	}

	@GetMapping("/news/active")
	public ResponseEntity<List<Post>> findAllByActiveIsTrue() {
		return ResponseEntity.status(HttpStatus.OK).body(postService.findAllByActiveIsTrue());
	}

	@GetMapping("/news/active/{id}")
	public ResponseEntity<Post> findActiveNewsById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(postService.findActiveNews(id));
	}
}
