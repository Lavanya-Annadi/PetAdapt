package io.swagger.controller;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Address;
import io.swagger.repository.AddressRepository;
import io.swagger.repository.OrderRepository;
import io.swagger.repository.SessionRepository;

@RestController
@Api(value = "address", description = " ",tags= {"address"})
@Transactional
public class AddressController {

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	SessionRepository sessionRepository;

	// Add address
	@ApiResponses(value = { @ApiResponse(code = 405, message = "Invalid input") })
	@RequestMapping(value = "/address", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" }, method = RequestMethod.POST)
	ResponseEntity<Address> addAddress(
			@ApiParam(value = "address object that needs to be added to the store", required = true) @Valid @RequestBody Address body)
			
	{
		return new ResponseEntity<Address>(addressRepository.save(body), HttpStatus.CREATED);
	}

	// Update address
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "address not found"),
			@ApiResponse(code = 405, message = "Validation exception") })
	@RequestMapping(value = "/address", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" }, method = RequestMethod.PUT)
	ResponseEntity<Address> updateAddress(
			@ApiParam(value = "address object that needs to be Updated to the store", required = true) @Valid @RequestBody Address body) {
		return new ResponseEntity<Address>(addressRepository.save(body), HttpStatus.OK);
	}

	// Get address by id

	@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = Address.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "address not found") })
	@RequestMapping(value = "/address/{id}", produces = { "application/json",
			"application/xml" }, method = RequestMethod.GET)
	ResponseEntity<Optional<Address>> getaddressById(
			@ApiParam(value = "ID of address to return", required = true) @PathVariable("id") Long id) {
		return new ResponseEntity<Optional<Address>>(addressRepository.findById(id), HttpStatus.OK);
	}

	// Delete address
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Address not found") })
	@RequestMapping(value = "/address/{addressId}", produces = { "application/json",
			"application/xml" }, method = RequestMethod.DELETE)
	ResponseEntity<Void> deleteAddress(
			@ApiParam(value = "address id to delete", required = true) @PathVariable("addressId") Long id,
			@ApiParam(value = "") @RequestHeader(value = "api_key", required = false) String apiKey) {
		addressRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}

}
