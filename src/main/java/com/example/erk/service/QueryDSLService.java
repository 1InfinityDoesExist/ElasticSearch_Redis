package com.example.erk.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.erk.model.Customer;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;

@Service
public interface QueryDSLService {

	public List<Customer> searchMultiField(String firstname, int age, Pageable pageable) throws IOException;

	public List<Customer> getCustomerSerachData(String input) throws IOException;

	public List<Customer> multiMatchQuery(String text) throws IOException;

}
