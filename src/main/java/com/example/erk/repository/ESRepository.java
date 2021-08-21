package com.example.erk.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.erk.model.Customer;

@Repository
public interface ESRepository extends ElasticsearchRepository<Customer, String> {

}
