package com.example.erk.model;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ElasticSearchRequest {

	private String index;
	private QueryBuilder query;
	private SortBuilder sort;
	private List<String> excludeFields;
	private List<String> includeFields;
	private int skipRecords;
	private int maxRecords;

	public ElasticSearchRequest setIndex(String index) {
		this.index = index;
		return this;
	}

	public ElasticSearchRequest setQuery(QueryBuilder query) {
		this.query = query;
		return this;
	}

	public ElasticSearchRequest setSort(SortBuilder sort) {
		this.sort = sort;
		return this;
	}

	public ElasticSearchRequest setExcludeFields(List<String> excludeFields) {
		this.excludeFields = excludeFields;
		return this;
	}

	public ElasticSearchRequest setIncludeFields(List<String> includeFields) {
		this.includeFields = includeFields;
		return this;
	}

	public ElasticSearchRequest setSkipRecords(int skipRecords) {
		this.skipRecords = skipRecords;
		return this;
	}

	public ElasticSearchRequest setMaxRecords(int maxRecords) {
		this.maxRecords = maxRecords;
		return this;
	}
}