package com.example.erk.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.erk.entity.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

}
