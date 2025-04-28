package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleDetailsDTO;
import com.devsuperior.dsmeta.dto.SaleSellerTotalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SaleSellerTotalDTO>> getSummary(@RequestParam(required = false) String minDate,
															   @RequestParam(required = false) String maxDate) {
		List<SaleSellerTotalDTO> dto = service.findSellerAndTotalAmount(minDate, maxDate);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleDetailsDTO>> getReport(@RequestParam(required = false) String minDate,
														  @RequestParam(required = false) String maxDate,
														  @RequestParam(name = "name", defaultValue = "") String name,
														  Pageable pageable) {
		Page<SaleDetailsDTO> dto = service.findByDateAndName(minDate, maxDate, name, pageable);
		return ResponseEntity.ok().body(dto);
	}
}
