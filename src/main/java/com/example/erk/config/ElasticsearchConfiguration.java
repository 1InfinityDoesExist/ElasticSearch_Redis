package com.example.erk.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "elastic", name = "enable", havingValue = "true")
public class ElasticsearchConfiguration {

	@Value("${elastic.host:127.0.0.1}")
	private String host;
	@Value("${elastic.port:9200}")
	private int elasticPort;

	@Bean
	public RestHighLevelClient client() {
		log.info("----Creating bean for elasticSearch.------");
		RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
				RestClient.builder(new HttpHost(host, elasticPort, "http")));
		return restHighLevelClient;
	}
}
