package io.swagger.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Order;
import io.swagger.model.HealthCare;
import io.swagger.repository.OrderRepository;
import io.swagger.repository.SessionRepository;
import io.swagger.repository.HealthCareRepository;

@RestController
@Api(value = "health care", description = " ",tags= {"health care"})
@Transactional
public class HealthCareController {

	@Autowired
	HealthCareRepository healthCareRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	SessionRepository sessionRepository;

	// Add healthCare
	@ApiResponses(value = { @ApiResponse(code = 405, message = "Invalid input") })
	@RequestMapping(value = "/healthCare", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" }, method = RequestMethod.POST)
	ResponseEntity<HealthCare> addHealthCare(
			@ApiParam(value = "healthCare object that needs to be added to the store", required = true) @Valid @RequestBody HealthCare body)
			
	{
		return new ResponseEntity<HealthCare>(healthCareRepository.save(body), HttpStatus.CREATED);
	}

	// Update healthCare
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "healthCare not found"),
			@ApiResponse(code = 405, message = "Validation exception") })
	@RequestMapping(value = "/healthCare", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" }, method = RequestMethod.PUT)
	ResponseEntity<HealthCare> updateHealthCare(
			@ApiParam(value = "healthCare object that needs to be added to the store", required = true) @Valid @RequestBody HealthCare body) {
		return new ResponseEntity<HealthCare>(healthCareRepository.save(body), HttpStatus.OK);
	}

	// Get healthCare by id

	@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = HealthCare.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "healthCare not found") })
	@RequestMapping(value = "/healthCare/{id}", produces = { "application/json",
			"application/xml" }, method = RequestMethod.GET)
	ResponseEntity<Optional<HealthCare>> gethealthCareById(
			@ApiParam(value = "ID of healthCare to return", required = true) @PathVariable("id") Long id) {
		return new ResponseEntity<Optional<HealthCare>>(healthCareRepository.findById(id), HttpStatus.OK);
	}

	// Delete healthCare
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "HealthCare not found") })
	@RequestMapping(value = "/healthCare/{healthCareId}", produces = { "application/json",
			"application/xml" }, method = RequestMethod.DELETE)
	ResponseEntity<Void> deleteHealthCare(
			@ApiParam(value = "healthCare id to delete", required = true) @PathVariable("healthCareId") Long id,
			@ApiParam(value = "") @RequestHeader(value = "api_key", required = false) String apiKey) {
		healthCareRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}

}
