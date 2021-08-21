package com.example.erk.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.erk.model.reponse.CustomerCreateResponse;
import com.example.erk.model.request.CustomerCreateRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPKMSTServerCodegen", date = "2021-04-16T06:00"
		+ ":39.859Z")
@Api(value = "CustomerController", description = "The CustomerRestController API")
public interface CustomerController {

	@ApiOperation(value = "Api to add a customer.", notes = "", response = CustomerCreateResponse.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = CustomerCreateResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@RequestMapping(value = { "/v1.0/customers" }, produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<CustomerCreateResponse> addNewCustomerUsingPOST(
			@ApiParam(value = "CustomerCreateRequest", required = true) @Valid @RequestBody CustomerCreateRequest customerCreateRequest)
			throws Exception;
}
