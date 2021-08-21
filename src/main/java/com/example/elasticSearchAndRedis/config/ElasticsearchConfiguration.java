package com.example.elasticSearchAndRedis.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.example.elasticSearchAndRedis.repository")
//@ConditionalOnProperty(prefix = "elastic", name = "enable", havingValue = "true")
//@RefreshScope
public class ElasticsearchConfiguration {

	@Value("${elastic.cluster.name:adwize}")
	private String clusterName;
	@Value("${elastic.host:127.0.0.1}")
	private String host;
	@Value("${elastic.port:9200}")
	private int elasticPort;

	@Bean("esClient")
	public RestHighLevelClient client() {
		log.info("----Creating bean for elasticSearch.------");
		RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
				RestClient.builder(new HttpHost(host, elasticPort, "http")));
		return restHighLevelClient;
	}
}
