package com.congphuong.reactivechatsse.controller;

import com.congphuong.reactivechatsse.config.security.JwtAuthenticationRequest;
import com.congphuong.reactivechatsse.config.security.JwtAuthenticationResponse;
import com.congphuong.reactivechatsse.config.security.JwtTokenUtil;
import com.congphuong.reactivechatsse.entity.RegisterRequest;
import com.congphuong.reactivechatsse.entity.User;
import com.congphuong.reactivechatsse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(path = "/auth", produces = { APPLICATION_JSON_UTF8_VALUE })
public class AuthRestController {

	private UserRepository repo;
	public AuthRestController(UserRepository repo) {
		this.repo = repo;
	}

	@Autowired
    private JwtTokenUtil jwtTokenUtil;


	@RequestMapping(method = POST, value = "/token")
	@CrossOrigin("*")
	public Mono<ResponseEntity<JwtAuthenticationResponse>> token(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
		String username =  authenticationRequest.getUsername();
		String password =  authenticationRequest.getPassword();
		List<String> role = new ArrayList<>();
		role.add("ROLE_ADMIN");
		Date d = new Date();
		User u = new User(null, "abc", "a","a","abc","123",role,true, d);
		repo.save(u);

		return repo.findByUsername(authenticationRequest.getUsername())
			.map(user -> ok().contentType(APPLICATION_JSON_UTF8).body(
					new JwtAuthenticationResponse(jwtTokenUtil.generateToken(user), user.getUsername()))
			)
			.defaultIfEmpty(notFound().build());
	}

	@RequestMapping(method = POST, value = "/register", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CrossOrigin("*")
	public Mono<User> register(@RequestBody @Valid RegisterRequest user) {
		List<String> role = new ArrayList<>();
		role.add("ROLE_USER");
		Date d = new Date();
		User u = new User(null, user.getUsername(), "a","a","abc",user.getPassword(),role,true, d);


		return repo.save(u);
	}
}
