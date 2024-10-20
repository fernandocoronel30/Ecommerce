package org.generation.ecommerce.controller;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;

import org.generation.ecommerce.config.JwtFilter;
import org.generation.ecommerce.dto.Token;
import org.generation.ecommerce.model.Usuario;
import org.generation.ecommerce.service.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping(path="/api/login")
public class LoginController {
	private final UsuarioService usuarioService;	
	
	public LoginController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}//constructor

	@PostMapping
	public Token loginUser(@RequestBody Usuario usuario) throws ServletException {
		if(usuarioService.validateUser(usuario)) {
			System.out.println("Usuario válido " + usuario.getEmail());
			return new Token(generateToken(usuario.getEmail()));
		}//if validateUser
		throw new ServletException("Nombre de usuario o contraseña incorrectos [" + usuario.getEmail() + "]");
	}//loginUser

	private String generateToken(String email) {
		Calendar calendar = Calendar.getInstance();//Fecha y hora de hoy
		calendar.add(Calendar.HOUR, 12);//Prueba en desarrollo
		//calendar.add(Calendar.MINUTE, 30);Se usaria en produccion
		
		return Jwts.builder().setSubject(email).claim("role", "user")
				.setIssuedAt(new Date())
				.setExpiration(calendar.getTime())
				.signWith(SignatureAlgorithm.HS256, JwtFilter.secret)
				.compact();
				
		
		
	}//generateToken	
}//LoginController
