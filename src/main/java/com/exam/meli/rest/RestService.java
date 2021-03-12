package com.exam.meli.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.meli.dto.DnaDTO;
import com.exam.meli.dto.StatsDTO;
import com.exam.meli.service.impl.DnaService;

@RestController
public class RestService
{
	@Autowired
	DnaService dnaService;

	@PostMapping(path = "/mutant", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> isMutant(@RequestBody DnaDTO dna) throws Exception
	{
		ResponseEntity<String> response = null;
		if (dnaService.isMutant(dna.getDna()))
		{
			response = new ResponseEntity<>(HttpStatus.OK);
		} else
		{
			response = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return response;
	}

	@GetMapping("/stats")
	public ResponseEntity<StatsDTO> stats()
	{
		return new ResponseEntity<StatsDTO>(dnaService.consultarEstadistics(), HttpStatus.OK);
	}
}
