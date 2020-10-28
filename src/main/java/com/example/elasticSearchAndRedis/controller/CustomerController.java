package com.example.elasticSearchAndRedis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.elasticSearchAndRedis.model.Customer;
import com.example.elasticSearchAndRedis.service.CustomerService;
import com.example.elasticSearchAndRedis.service.impl.CustomerRedisRepositoryImpl;

@RestController
@RequestMapping(value = "/search")
public class CustomerController {

    @Autowired(required = true)
    private CustomerService customerService;

    @Autowired(required = true)
    private CustomerRedisRepositoryImpl customerRedisRepository;

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
        customerService.saveCustomerDetails(customer);
        return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ModelMap().addAttribute("msg", "Persisted into data success."));
    }

    @GetMapping(value = "/get")
    public ResponseEntity<?> getAllCustomer() {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                        new ModelMap().addAttribute("response", customerService.getAllCustomer()));
    }

    @GetMapping(value = "/get/{firstName}")
    public ResponseEntity<?> getCustomerById(
                    @PathVariable(value = "firstName", required = true) String firstName) {
        return ResponseEntity.status(HttpStatus.OK).body(new ModelMap().addAttribute("response",
                        customerService.getCustomerById(firstName)));
    }

    /*
     * 
     * Redis Controller
     */

    @PostMapping(value = "/redis/save/customer")
    public ResponseEntity<?> saveCustomerInRedis(@RequestBody Customer customer) {
        customerRedisRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                        new ModelMap().addAttribute("msg", "Successfully saved in redis db."));
    }

    @Cacheable(key = "#id", value = "customers", unless = "#result.id < 1200")
    @GetMapping(value = "/redis/get/customer/{id}")
    public ResponseEntity<?> getCustomerFromRedis(
                    @PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                        .body(new ModelMap().addAttribute("msg", customerRedisRepository.find(id)));
    }


}
