package com.example.erk.controller.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.erk.controller.CustomerController;
import com.example.erk.model.reponse.CustomerCreateResponse;
import com.example.erk.model.request.CustomerCreateRequest;
import com.example.erk.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CustomerControllerImpl implements CustomerController {

	@Autowired
	private CustomerService customerService;

	@Override
	public ResponseEntity<CustomerCreateResponse> addNewCustomerUsingPOST(
			@Valid CustomerCreateRequest customerCreateRequest) throws Exception {
		log.info("-----CustomerController Class, addNewCustomerUsingPOST method ()");
		CustomerCreateResponse response = customerService.addNewCustomerUsingPOST(customerCreateRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
