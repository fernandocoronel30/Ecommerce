package org.generation.ecommerce.controller;

import java.util.List;

import org.generation.ecommerce.dto.ChangePassword;
import org.generation.ecommerce.model.Usuario;
import org.generation.ecommerce.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/usuarios/") // http://localhost:8080/api/usuarios/
public class UsuarioController {
	private final UsuarioService usuarioService;
	
	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}//constructor
	
	@GetMapping
	public List<Usuario> getUsuarios(){
		return usuarioService.getAllUsuarios();
	}//getUsuarios
	
	@GetMapping(path="{userId}")//localhost:8080/api/usuarios/1
	public Usuario getUsuario(@PathVariable("userId") Long userId) {
		return usuarioService.getUsuario(userId);
	}//getUsuarios
	
	@PostMapping
	public Usuario addUsuario(@RequestBody Usuario usuario) {
		return usuarioService.addUsuario(usuario);
	}//addUsuario
		
	@DeleteMapping(path="{userId}")
	public Usuario deleteUsuario(@PathVariable("userId") Long userId) {
		return usuarioService.deleteUsuario(userId);
	}//deleteUsuario
	
	@PutMapping(path="{userId}")//localhost:8080/api/usuarios/1
	public Usuario updateUsuario(@RequestBody ChangePassword changePassword,
			@PathVariable("userId") Long id) {
		return usuarioService.updateUsuario(id, changePassword);
	}//updateUsuario
	
	
}//class UsuarioController
