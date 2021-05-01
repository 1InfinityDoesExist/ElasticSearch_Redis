package com.example.elasticSearchAndRedis.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.elasticSearchAndRedis.model.Customer;

@Repository
public interface ESRepository extends ElasticsearchRepository<Customer, String> {

}
