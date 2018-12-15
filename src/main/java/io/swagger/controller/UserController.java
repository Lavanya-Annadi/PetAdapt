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
import io.swagger.model.Statistics;
import io.swagger.model.User;
import io.swagger.repository.OrderRepository;
import io.swagger.repository.SessionRepository;
import io.swagger.repository.StatisticsRepository;
import io.swagger.repository.UserRepository;
import springfox.documentation.service.LoginEndpoint;

@RestController
@Api(value = "user", description = " ",tags= {"user"})
@Transactional
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	OrderRepository orderRepository;
	

	@Autowired
	SessionRepository sessionRepository;
  @Autowired
  StatisticsRepository statisticrepository;
	// Add user
	
	@ApiResponses(value = { @ApiResponse(code = 405, message = "Invalid input") })
	@RequestMapping(value = "/user", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" }, method = RequestMethod.POST)
	ResponseEntity<User> addUser(
			
			@ApiParam(value = "user object that needs to be added to the store", required = true) @Valid @RequestBody User body)

	{
		Map<String, String> map = new HashMap<>();
		
		//io.swagger.model.Statistics stat1 =new io.swagger.model.Statistics();
//		String methodname="adduser";
//		
//		Statistics statistics =statisticrepository.findByServicename(methodname);
//	   int methodcalls=	statistics.getMethodcalls().intValue();
//	    System.out.println("stat1"+statistics.toString());
//	   System.out.println("stat menthodcall"+statistics.getMethodcalls());
//	 System.out.println("stat menthodcall++"+methodcalls++);
//		
//		statistics.setmethodcalls(methodcalls++);	
//	   statisticrepository.save(statistics);
		//}
	   return new ResponseEntity<User>(userRepository.save(body), HttpStatus.CREATED);
	
	}

	// Update user
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "user not found"),
			@ApiResponse(code = 405, message = "Validation exception") })
	@RequestMapping(value = "/user", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" }, method = RequestMethod.PUT)
	ResponseEntity<User> updateUser(
			@ApiParam(value = "user object that needs to be updated to the store", required = true) @Valid @RequestBody User body) {
		return new ResponseEntity<User>(userRepository.saveAndFlush(body), HttpStatus.OK);
	}

	// Get user by id

	@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = User.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "user not found") })
	@RequestMapping(value = "/user/{id}",
	produces = { "application/json",
		"application/xml" },
	method = RequestMethod.GET)
	ResponseEntity<Optional<User>> getuserById(
			@ApiParam(value = "ID of user to return", required = true) @PathVariable("id") Long id) {
		return new ResponseEntity<Optional<User>>(userRepository.findById(id), HttpStatus.OK);
	}
	
//	get all users
	
	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = User.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "user not found") })
	@RequestMapping(value = "/user/All",
	produces = { "application/json",
		"application/xml" },
	method = RequestMethod.GET)
	ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<List<User>>(userRepository.findAll(), HttpStatus.OK);
	}
	
	

	// Delete user
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "User not found") })
	@RequestMapping(value = "/user/{userId}", produces = { "application/json",
			"application/xml" }, method = RequestMethod.DELETE)
	ResponseEntity<Void> deleteUser(
			@ApiParam(value = "user id to delete", required = true) @PathVariable("userId") Long id,
			@ApiParam(value = "") @RequestHeader(value = "api_key", required = false) String apiKey) {
		userRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	// Get all orders of a user

	@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = Order.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "user not found") })
	@RequestMapping(value = "/user/{id}/orders", produces = { "application/json",
			"application/xml" }, method = RequestMethod.GET)
	ResponseEntity<List<Order>> makeOrder(
			@ApiParam(value = "user id to fetch orders", required = true) @PathVariable("id") Long id

	) {

		return new ResponseEntity<List<Order>>(orderRepository.findByUser(userRepository.findById(id).get()),
				HttpStatus.OK);
	}

	
	
	// User login

	@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = HttpSession.class),
			@ApiResponse(code = 400, message = "Invalid email supplied"),
			@ApiResponse(code = 404, message = "user not found") })
	@RequestMapping(value = "/user/login", 
	produces = { "application/json",
		"application/xml" },
	method = RequestMethod.POST)
	ResponseEntity<io.swagger.model.Session> login(
			@ApiParam(value = "email to login", required = true) @RequestParam("email") String email,
			@ApiParam(value = "password", required = true) @RequestParam("password") String password,
			HttpSession session, HttpServletRequest request) {
        
		
		User user = userRepository.findByEmail(email);
		System.out.println(user.toString());
		if (password.equals(user.getPassword())) {
			session.putValue("login", true);
			io.swagger.model.Session session_model = new io.swagger.model.Session();
			session_model.setUser(user);
			session_model.setSessionId(session.getId());
			session_model.setIpAddress(request.getRemoteAddr());
			session_model.setStatus(true);
			sessionRepository.save(session_model);
			//System.out.println(session_model.toString());
		
			String methodname="login";
			Statistics statistics =statisticrepository.findByServicename(methodname);
			   int methodcalls=	statistics.getMethodcalls().intValue();
			    System.out.println("stat1"+statistics.toString());
			   System.out.println("stat menthodcall"+statistics.getMethodcalls());
			 System.out.println("stat menthodcall++"+methodcalls++);
				
				statistics.setmethodcalls(methodcalls++);	
			   statisticrepository.save(statistics);
			return new ResponseEntity<io.swagger.model.Session>(session_model, HttpStatus.OK);
		} else
			return null;
	}

	// Logout

	@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = String.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "user not found") })
	@RequestMapping(value = "user/logout", produces = { "application/json",
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
