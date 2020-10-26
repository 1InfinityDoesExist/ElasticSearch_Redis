package com.example.elasticSearchAndRedis.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import lombok.Data;

@Data
@Document(indexName = "budodai", type = "customer", shards = 2, replicas = 0)
public class Customer {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
