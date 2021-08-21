package com.example.erk.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.example.erk.model.reponse.CustomerCreateResponse;
import com.example.erk.model.request.CustomerCreateRequest;

@Service
public interface CustomerService {

	CustomerCreateResponse addNewCustomerUsingPOST(@Valid CustomerCreateRequest customerCreateRequest);

}
