package com.example.elasticSearchAndRedis.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.example.elasticSearchAndRedis.repository")
public class ElasticsearchConfiguration {
	@Value("${elastic.cluster.name:adwize}")
	private String clusterName;
	@Value("${elastic.host:127.0.0.1}")
	private String host;
	@Value("${elastic.port:9200}")
	private int elasticPort;

	@Bean
	public RestHighLevelClient client() throws Exception {
		log.info("creating elastic search client...");
		log.debug("connecting to host {}", host);
		RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
				RestClient.builder(new HttpHost(host, elasticPort, "http")));
		log.info("created elastic search client");
		return restHighLevelClient;

	}
}
