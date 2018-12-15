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
import io.swagger.model.Pet;
import io.swagger.model.HealthCare;
import io.swagger.model.Medic;
import io.swagger.repository.HealthCareRepository;
import io.swagger.repository.SessionRepository;
import io.swagger.repository.MedicRepository;
import io.swagger.repository.PetRepository;

@RestController

@Api(value = "medic", description = " ",tags= {"medic"})
@Transactional
public class MedicController {

	@Autowired
	MedicRepository medicRepository;
	
	@Autowired
	PetRepository petRepository;

	@Autowired
	HealthCareRepository healthCareRepository;

	@Autowired
	SessionRepository sessionRepository;

	// Add medic
	@ApiResponses(value = { @ApiResponse(code = 405, message = "Invalid input") })
	@RequestMapping(value = "/medic", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" }, method = RequestMethod.POST)
	ResponseEntity<Medic> addMedic(
			@ApiParam(value = "medic object that needs to be added to the store", required = true) @Valid @RequestBody Medic body)

	{
		return new ResponseEntity<Medic>(medicRepository.save(body), HttpStatus.CREATED);
	}

	// Update medic
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "medic not found"),
			@ApiResponse(code = 405, message = "Validation exception") })
	@RequestMapping(value = "/medic", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" }, method = RequestMethod.PUT)
	ResponseEntity<Medic> updateMedic(
			@ApiParam(value = "medic object that needs to be updated to the store", required = true) @Valid @RequestBody Medic body) {
		return new ResponseEntity<Medic>(medicRepository.save(body), HttpStatus.OK);
	}

	// Get medic by id

	@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = Medic.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "medic not found") })
	@RequestMapping(value = "/medic/{id}", produces = { "application/json",
			"application/xml" }, method = RequestMethod.GET)
	ResponseEntity<Optional<Medic>> getmedicById(
			@ApiParam(value = "ID of medic to return", required = true) @PathVariable("id") Long id) {
		return new ResponseEntity<Optional<Medic>>(medicRepository.findById(id), HttpStatus.OK);
	}

	// Delete medic
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Medic not found") })
	@RequestMapping(value = "/medic/{medicId}", produces = { "application/json",
			"application/xml" }, method = RequestMethod.DELETE)
	ResponseEntity<Void> deleteMedic(
			@ApiParam(value = "medic id to delete", required = true) @PathVariable("medicId") Long id
			) {
		medicRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	// Get all health care of a medic

	@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = Order.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "medic not found") })
	@RequestMapping(value = "/medic/{id}/healthcare", produces = { "application/json",
			"application/xml" }, method = RequestMethod.GET)
	ResponseEntity<List<HealthCare>> makeOrder(
			@ApiParam(value = "medic id to fetch health care", required = true) @PathVariable("id") Long id

	) {

		return new ResponseEntity<List<HealthCare>>(healthCareRepository.findByMedic(medicRepository.findById(id).get()),
				HttpStatus.OK);
	}
	
	
	// save health care

		@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = Order.class),
				@ApiResponse(code = 400, message = "Invalid ID supplied"),
				@ApiResponse(code = 404, message = "medic not found") })
		@RequestMapping(value = "/medic/{id}/healthcare/{petId}", produces = { "application/json",
				"application/xml" }, method = RequestMethod.POST)
		ResponseEntity<HealthCare> heathCare(
				@ApiParam(value = "medic id ", required = true) @PathVariable("id") Long id,
				@ApiParam(value = "pet id ", required = true) @PathVariable("petId") Long petId,
				@ApiParam(value = "health care object that needs to be added to the store", required = true) @Valid @RequestBody HealthCare body

		) {
			Medic medic = medicRepository.findById(id).get();
			Pet pet = petRepository.findById(petId).get();
			body.setPet(pet);
			body.setMedic(medic);
			return new ResponseEntity<HealthCare>(healthCareRepository.save(body),
					HttpStatus.OK);
		}


	// Medic login

	@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = HttpSession.class),
			@ApiResponse(code = 400, message = "Invalid email supplied"),
			@ApiResponse(code = 404, message = "medic not found") })
	@RequestMapping(value = "/medic/login", produces = { "application/json",
			"application/xml" }, method = RequestMethod.POST)
	ResponseEntity<io.swagger.model.Session> login(
			@ApiParam(value = "email to login", required = true) @RequestParam("email") String email,
			@ApiParam(value = "password", required = true) @RequestParam("password") String password,
			HttpSession session, HttpServletRequest request) {
		Medic medic = medicRepository.findByEmail(email);
		if (password.equals(medic.getPassword())) {
			session.putValue("login", true);
			io.swagger.model.Session session_model = new io.swagger.model.Session();
			session_model.setMedic(medic);
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
			@ApiResponse(code = 404, message = "medic not found") })
	@RequestMapping(value = "medic/logout", produces = { "application/json",
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
