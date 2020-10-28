package com.example.elasticSearchAndRedis.service.impl;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import com.example.elasticSearchAndRedis.model.Customer;
import com.example.elasticSearchAndRedis.service.CustomerRedisRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomerRedisRepositoryImpl implements CustomerRedisRepository {

    private static final String TABLE_NAME = "Customer";


    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public CustomerRedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private HashOperations<String, Long, Customer> hashOperation;


    @PostConstruct
    private void initializeHashOperations() {
        log.info("::::::redisTemplate {}", redisTemplate);
        hashOperation = redisTemplate.opsForHash();
    }

    @Override
    public void save(Customer customer) {
        // TODO Auto-generated method stub
        hashOperation.put(TABLE_NAME, customer.getId(), customer);
    }

    @Override
    public Customer find(Long id) {
        // TODO Auto-generated method stub
        return hashOperation.get(TABLE_NAME, id);
    }

}
