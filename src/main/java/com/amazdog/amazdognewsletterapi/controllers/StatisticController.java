package com.amazdog.amazdognewsletterapi.controllers;

import com.amazdog.amazdognewsletterapi.entities.Statistic;
import com.amazdog.amazdognewsletterapi.services.statistics.StatisticService;
import com.amazdog.amazdognewsletterapi.services.statistics.StatisticServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/statistics")
@Validated
public class StatisticController {

	private final StatisticService statisticService;

	public StatisticController(StatisticServiceImpl statisticService) {
		this.statisticService = statisticService;
	}

	@PostMapping()
	public ResponseEntity<String> createStatistic(@RequestBody @Valid Statistic statistic) {
		statisticService.create(statistic);
		return ResponseEntity.status(HttpStatus.OK).build();
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

	@GetMapping(params = "tags")
	public ResponseEntity<List<Statistic>> findStatisticsByTags(@RequestParam("tags") String tags) {
		return ResponseEntity.status(HttpStatus.OK).body(statisticService.findByTags(tags));
	}

	@GetMapping(params = {"subject", "page", "size"})
	public ResponseEntity<Page<Statistic>> findStatisticsBySubject
			(@RequestParam("subject") String subject,
			 @RequestParam int page,
			 @RequestParam int size) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(statisticService.findAllBySubjectOrderByDate(subject, PageRequest.of(page, size)));
	}

	@GetMapping(value = "/subjects", params = {"page", "size"})
	public ResponseEntity<Page<String>> findAllSubjects(@RequestParam int page, @RequestParam int size) {
		return ResponseEntity.status(HttpStatus.OK).body(statisticService.findAllDistinctSubjects(PageRequest.of(page, size)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		statisticService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping(params = "subject")
	public ResponseEntity<?> deleteAllBySubject(@RequestParam("subject") String subject) {
		statisticService.deleteAllBySubject(subject);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
