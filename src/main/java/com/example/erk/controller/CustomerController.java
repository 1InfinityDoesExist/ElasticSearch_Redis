package com.example.erk.controller;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.erk.model.Customer;
import com.example.erk.repository.ESRepository;
import com.example.erk.service.QueryDSLService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/search")
public class CustomerController {
	@Autowired
	private QueryDSLService service;

	@Autowired
	private ESRepository esRepository;

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

	@PostMapping("/save")
	public ResponseEntity<?> saveDataToEs(@RequestBody Customer customer) {
		return ResponseEntity.status(HttpStatus.CREATED).body(esRepository.save(customer));
	}

}
