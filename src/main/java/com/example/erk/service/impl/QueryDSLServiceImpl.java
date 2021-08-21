package com.example.erk.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.example.erk.model.Customer;
import com.example.erk.service.QueryDSLService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class QueryDSLServiceImpl implements QueryDSLService {

	private RestHighLevelClient client;

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	public QueryDSLServiceImpl(@Qualifier("client") RestHighLevelClient esClient) {
		this.client = esClient;
		OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@Override
	public List<Customer> searchMultiField(String firstname, int age, Pageable pageable) throws IOException {
		int skipRecords = pageable.getPageSize() * pageable.getPageNumber();
		int maxRecords = pageable.getPageSize();
		log.info("SkipRecords : {}", skipRecords);
		log.info("MaxRecords : {}", maxRecords);
		QueryBuilder qb = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("firstName", firstname))
				.must(QueryBuilders.matchQuery("age", age));
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().query(qb).explain(true).from(skipRecords)
				.size(maxRecords);

		pageable.getSort().iterator().forEachRemaining(sort -> {
			String property = sort.getProperty();
			if (!property.endsWith(".keyword")) {
				property = property.concat(".keyword");
			}
			searchSourceBuilder.sort(property,
					Objects.equals(sort.getDirection(), Direction.DESC) ? SortOrder.DESC : SortOrder.ASC);
		});
		SearchHits searchHits = client
				.search(new SearchRequest("budodai").source(searchSourceBuilder), RequestOptions.DEFAULT).getHits();

		long totalRecords = searchHits.getTotalHits().value;
		log.info("::::::totalHits : {}", totalRecords);
		return Arrays.stream(searchHits.getHits()).map(documentFields -> {
			try {
				Customer customer = OBJECT_MAPPER.readValue(documentFields.getSourceAsString(), Customer.class);
				customer.setId(documentFields.getId());
				return customer;
			} catch (IOException e) {
				log.error("unable to convert document to class :: ", e);
			}
			return null;
		}).filter(Objects::nonNull).collect(Collectors.toList());

	}

	/**
	 * 
	 */
	@Override
	public List<Customer> getCustomerSerachData(String input) throws IOException {
		String search = ".*" + input + ".*";
		QueryBuilder qb = QueryBuilders.regexpQuery("firstName", search);
		SearchSourceBuilder builder = new SearchSourceBuilder().query(qb).explain(true);
		SearchHits searchHits = client.search(new SearchRequest("budodai").source(builder), RequestOptions.DEFAULT)
				.getHits();
		return Arrays.stream(searchHits.getHits()).map(doc -> {
			try {
				Customer cust = OBJECT_MAPPER.readValue(doc.getSourceAsString(), Customer.class);
				cust.setId(doc.getId());
				return cust;
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());

	}

	/**
	 * 
	 */
	@Override
	public List<Customer> multiMatchQuery(String text) throws IOException {
		QueryBuilder qb = QueryBuilders.multiMatchQuery(text).field("firstName").field("lastName");
		SearchSourceBuilder builder = new SearchSourceBuilder().query(qb);
		SearchHits hits = client.search(new SearchRequest("budodai").source(builder), RequestOptions.DEFAULT).getHits();
		return Arrays.stream(hits.getHits()).map(document -> {
			try {
				Customer customer = OBJECT_MAPPER.readValue(document.getSourceAsString(), Customer.class);
				customer.setId(document.getId());
				return customer;
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return null;
		}).filter(Objects::nonNull).collect(Collectors.toList());
	}

}
