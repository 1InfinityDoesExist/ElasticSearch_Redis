package com.example.erk.model;

import java.util.List;

import lombok.Data;

@Data
public class SearchResponse {

	private long totalRecords;
	private long totalPages;
	private int currentPage;
	private int sizePerPage;
	private List<Customer> customers;

	public SearchResponse(long totalRecords, int currentPage, int sizePerPage, List<Customer> customers) {
		this.totalRecords = totalRecords;
		this.currentPage = currentPage;
		this.sizePerPage = sizePerPage;
		this.customers = customers;
		this.totalPages = totalPages();
	}

	private long totalPages() {
		return ((totalRecords / sizePerPage) + (totalRecords % sizePerPage == 0 ? 0 : 1));
	}
}