package com.example.erk.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.erk.entity.Customer;
import com.example.erk.service.QueryDSLService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/search")
public class SearchController {
	@Autowired
	private QueryDSLService service;

	@GetMapping("/searchMultiField/{firstname}/{age}")
	public List<Customer> serachByMultiField(@PathVariable String firstname, @PathVariable int age, Pageable pageable)
			throws IOException {
		log.info("-----Inside CustomerController Class, searchByMultiFiled method-----");
		return service.searchMultiField(firstname, age, pageable);
	}

	@GetMapping("/customSearch/{firstName}")
	public List<Customer> getCustomerByField(@PathVariable String firstName) throws IOException {
		return service.getCustomerSerachData(firstName);
	}

	@GetMapping("/{text}")
	public List<Customer> doMultimatchQuery(@PathVariable String text) throws IOException {
		return service.multiMatchQuery(text);
	}

	@GetMapping("/matchAllQuery/{query}")
	public ResponseEntity<?> matchaAllQuery(@PathVariable String query) throws IOException {
		return ResponseEntity.status(HttpStatus.OK).body(service.matchAllQuery(query));
	}

	@GetMapping("/match/{query}")
	public ResponseEntity<?> matchQuery(@PathVariable String query) throws IOException {
		return ResponseEntity.status(HttpStatus.OK).body(service.match(query));
	}

	@GetMapping("/multiMatch")
	public ResponseEntity<?> multiMatchQuery(@RequestParam String query) throws IOException {
		return ResponseEntity.status(HttpStatus.OK).body(service.multiMatch(query));
	}

	@GetMapping("/regex")
	public ResponseEntity<?> regexQuery(@RequestParam String query) throws IOException {
		return ResponseEntity.status(HttpStatus.OK).body(service.regexQuery(query));
	}

}
