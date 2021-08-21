package com.example.erk.model.reponse;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerCreateResponse implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -664524584074062728L;
	private String id;
	private String msg;
}
