package com.example.elasticSearchAndRedis.service;

import org.springframework.stereotype.Service;
import com.example.elasticSearchAndRedis.model.Customer;

@Service
public interface CustomerRedisRepository {

    public void save(Customer customer);

    public Customer find(Long id);
}
