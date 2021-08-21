package com.example.erk.service.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import com.example.erk.entity.Customer;
import com.example.erk.model.reponse.CustomerCreateResponse;
import com.example.erk.model.request.CustomerCreateRequest;
import com.example.erk.repository.CustomerRepository;
import com.example.erk.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CacheManager cacheManager;

	@Override
	public CustomerCreateResponse addNewCustomerUsingPOST(@Valid CustomerCreateRequest customerCreateRequest) {
		log.info("-----CustomerServiceImpl Class, addNewCustomerUsingPOST method.");

		Customer customer = new Customer();
		customer.setAge(customerCreateRequest.getAge());
		customer.setEmail(customerCreateRequest.getEmail());
		customer.setFirstName(customerCreateRequest.getFirstName());
		customer.setLastName(customerCreateRequest.getLastName());
		customer.setCardDetails(customerCreateRequest.getCardDetails());

		customerRepository.save(customer);

		Cache cache = cacheManager.getCache("Customer");
		if (cache != null) {
			log.info("Cache  != null");
			cache.putIfAbsent(customer.getId(), customer);
		}

		CustomerCreateResponse response = new CustomerCreateResponse();

		response.setId(customer.getId());
		response.setMsg("Created");
		return response;
	}
}
