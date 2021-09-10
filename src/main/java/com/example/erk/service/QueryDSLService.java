package com.example.erk.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.erk.entity.Customer;

@Service
public interface QueryDSLService {

	public List<Customer> searchMultiField(String firstname, int age, Pageable pageable) throws IOException;

	public List<Customer> getCustomerSerachData(String input) throws IOException;

	public List<Customer> multiMatchQuery(String text) throws IOException;

	public List<Customer> matchAllQuery(String query) throws IOException;

	public List<Customer> match(String query) throws IOException;

	public List<Customer> multiMatch(String query) throws IOException;

	public List<Customer> regexQuery(String query) throws IOException;

}
