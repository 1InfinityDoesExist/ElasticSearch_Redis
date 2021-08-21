package com.example.erk.entity;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.erk.model.CardDetails;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@CompoundIndexes({ @CompoundIndex(def = "{'email':1, 'isDeleted':1}", name = "email_1_isDeleted_1") })
@Data
@Document("customer")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2250888228598164890L;
	@Id
	private String id;

	private String firstName;

	private String lastName;

	@Email
	@Size(max = 50)
	private String email;

	private int age;

	private boolean isDeleted;

	private CardDetails cardDetails;
}
