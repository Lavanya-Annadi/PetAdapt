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
import io.swagger.model.Provider;
import io.swagger.repository.OrderRepository;
import io.swagger.repository.SessionRepository;
import io.swagger.repository.ProviderRepository;

@RestController
@Api(value = "provider", description = " ",tags= {"provider"})
@Transactional
public class ProviderController {

	@Autowired
	ProviderRepository providerRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	SessionRepository sessionRepository;

	// Add provider
	@ApiResponses(value = { @ApiResponse(code = 405, message = "Invalid input") })
	@RequestMapping(value = "/provider", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" }, method = RequestMethod.POST)
	ResponseEntity<Provider> addProvider(
			@ApiParam(value = "provider object that needs to be added to the store", required = true) @Valid @RequestBody Provider body)

	{
		return new ResponseEntity<Provider>(providerRepository.save(body), HttpStatus.CREATED);
	}

	// Update provider
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "provider not found"),
			@ApiResponse(code = 405, message = "Validation exception") })
	@RequestMapping(value = "/provider", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" }, method = RequestMethod.PUT)
	ResponseEntity<Provider> updateProvider(
			@ApiParam(value = "provider object that needs to be updated to the store", required = true) @Valid @RequestBody Provider body) {
		return new ResponseEntity<Provider>(providerRepository.save(body), HttpStatus.OK);
	}

	// Get provider by id

	@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = Provider.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "provider not found") })
	@RequestMapping(value = "/provider/{id}", produces = { "application/json",
			"application/xml" }, method = RequestMethod.GET)
	ResponseEntity<Optional<Provider>> getproviderById(
			@ApiParam(value = "ID of provider to return", required = true) @PathVariable("id") Long id) {
		return new ResponseEntity<Optional<Provider>>(providerRepository.findById(id), HttpStatus.OK);
	}

	// Delete provider
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Provider not found") })
	@RequestMapping(value = "/provider/{providerId}", produces = { "application/json",
			"application/xml" }, method = RequestMethod.DELETE)
	ResponseEntity<Void> deleteProvider(
			@ApiParam(value = "provider id to delete", required = true) @PathVariable("providerId") Long id,
			@ApiParam(value = "") @RequestHeader(value = "api_key", required = false) String apiKey) {
		providerRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	// Get all orders of a provider

	@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = Order.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "provider not found") })
	@RequestMapping(value = "/provider/{id}/orders", produces = { "application/json",
			"application/xml" }, method = RequestMethod.GET)
	ResponseEntity<List<Order>> makeOrder(
			@ApiParam(value = "provider id to fetch orders", required = true) @PathVariable("id") Long id

	) {

		return new ResponseEntity<List<Order>>(orderRepository.findByProvider(providerRepository.findById(id).get()),
				HttpStatus.OK);
	}

	// Provider login

	@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = HttpSession.class),
			@ApiResponse(code = 400, message = "Invalid email supplied"),
			@ApiResponse(code = 404, message = "provider not found") })
	@RequestMapping(value = "/provider/login", produces = { "application/json",
			"application/xml" }, method = RequestMethod.POST)
	ResponseEntity<io.swagger.model.Session> login(
			@ApiParam(value = "email to login", required = true) @RequestParam("email") String email,
			@ApiParam(value = "password", required = true) @RequestParam("password") String password,
			HttpSession session, HttpServletRequest request) {
		Provider provider = providerRepository.findByEmail(email);
		if (password.equals(provider.getPassword())) {
			session.putValue("login", true);
			io.swagger.model.Session session_model = new io.swagger.model.Session();
			session_model.setProvider(provider);
			session_model.setSessionId(session.getId());
			session_model.setIpAddress(request.getRemoteAddr());
			session_model.setStatus(true);
			sessionRepository.save(session_model);

			return new ResponseEntity<io.swagger.model.Session>(session_model, HttpStatus.OK);
		} else
			return null;
	}

	// Logout

	@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = String.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "provider not found") })
	@RequestMapping(value = "provider/logout", produces = { "application/json",
			"application/xml" }, method = RequestMethod.GET)
	ResponseEntity<Map> logout(

			HttpSession session) {
		Map<String, String> map = new HashMap<>();
		map.put("message", "Successfull");
		map.put("status_code", "200");
		io.swagger.model.Session session_logout = sessionRepository.findBySessionId(session.getId());
		if (session_logout != null && session_logout.getStatus() == true) {
			session_logout.setStatus(false);
			sessionRepository.save(session_logout);
			session.invalidate();

			return new ResponseEntity<Map>(map, HttpStatus.OK);

		}
		return new ResponseEntity<Map>(map, HttpStatus.OK);

	}

}
