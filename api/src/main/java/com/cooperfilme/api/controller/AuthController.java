package com.cooperfilme.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooperfilme.api.controller.exception.ObjectBadRequestException;
import com.cooperfilme.api.dto.LoginDTO;
import com.cooperfilme.api.dto.TokenDTO;
import com.cooperfilme.api.service.AuthService;
import com.cooperfilme.api.service.exception.IllegalParameterException;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDTO login){
		
		try {
			
			var token = this.authService.login(login);
			
			return ResponseEntity.ok(token);
			
		}catch(IllegalParameterException ex) {
			throw new ObjectBadRequestException(ex.getMessage());
			
		}
		
	}
	
}
