package com.amazdog.amazdognewsletterapi.controllers.user;

import com.amazdog.amazdognewsletterapi.entities.chart.Chart;
import com.amazdog.amazdognewsletterapi.services.charts.ChartService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/charts")
@Validated
public class ChartController {

	private final ChartService chartService;

	public ChartController(ChartService chartService) {
		this.chartService = chartService;
	}

	@PostMapping
	public ResponseEntity<String> createChart(@RequestBody @Valid Chart chart) {
		chartService.create(chart);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteChart(@PathVariable Long id) {
		chartService.remove(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
