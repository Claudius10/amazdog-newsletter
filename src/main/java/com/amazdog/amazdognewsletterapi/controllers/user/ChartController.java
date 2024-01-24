package com.amazdog.amazdognewsletterapi.controllers.user;

import com.amazdog.amazdognewsletterapi.entities.chart.Chart;
import com.amazdog.amazdognewsletterapi.entities.chart.Subject;
import com.amazdog.amazdognewsletterapi.services.charts.ChartService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
		return ResponseEntity.status(HttpStatus.OK).body("Gráfico creado con éxito");
	}

	@PostMapping("/{id}/subject")
	public ResponseEntity<String> addSubject(@PathVariable Long id, @RequestBody @Valid Subject subject) {
		chartService.addSubject(id, subject);
		return ResponseEntity.status(HttpStatus.OK).body("Tema añadido con éxito");
	}

	@DeleteMapping("/{id}/subject")
	public ResponseEntity<String> deleteSubject(@PathVariable Long id, @RequestBody @Valid Subject subject) {
		chartService.removeSubject(id, subject);
		return ResponseEntity.status(HttpStatus.OK).body("Tema eliminado con éxito");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteChart(@PathVariable Long id) {
		chartService.remove(id);
		return ResponseEntity.status(HttpStatus.OK).body("Gráfico eliminado con éxito");
	}
}
