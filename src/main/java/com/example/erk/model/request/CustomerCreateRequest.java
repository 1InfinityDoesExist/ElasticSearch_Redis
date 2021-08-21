package com.example.erk.model.request;

import java.io.Serializable;

import com.example.erk.model.CardDetails;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerCreateRequest implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 5154787438295798596L;

	private String firstName;
	private String lastName;
	private String email;
	private int age;
	private CardDetails cardDetails;
}
