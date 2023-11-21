package com.amazdog.amazdognewsletterapi.controllers;

import com.amazdog.amazdognewsletterapi.entities.Statistic;
import com.amazdog.amazdognewsletterapi.services.statistics.StatisticServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/statistics")
@Validated
public class StatisticController {

	private final StatisticServiceImpl statisticService;

	public StatisticController(StatisticServiceImpl statisticService) {
		this.statisticService = statisticService;
	}

	@PostMapping()
	public ResponseEntity<?> createStatistic(@RequestBody @Valid Statistic statistic) {
		statisticService.create(statistic);
		return ResponseEntity.status(HttpStatus.OK).body("Statistic creado con éxito");
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findStatisticById(@PathVariable Long id) {
		Optional<Statistic> statistic = statisticService.findById(id);

		if (statistic.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay resultados para statistic con id " + id);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(statistic.get());
		}
	}

	@GetMapping()
	public ResponseEntity<List<Statistic>> findStatisticByTags(@RequestParam("tags") String tags) {
		return ResponseEntity.status(HttpStatus.OK).body(statisticService.findByTags(tags));
	}

}
