package com.example.erk.model;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5506021338076944351L;

	private String id;

	@Size(min = 14, max = 16)
	private String cardNumber;

	private String cardHolderName;

	@Pattern(regexp = "^((0[1-9])|(1[0-2]))[\\/\\.\\-]*(?!00)\\d\\d?(\\.\\d\\d?)?$")
	private String expiry;

	private String cardType;
}
