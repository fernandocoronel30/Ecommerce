package org.generation.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.generation.ecommerce.dto.ChangePassword;
import org.generation.ecommerce.model.Usuario;
import org.generation.ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService{
	//private static final ArrayList<Usuario> lista = new ArrayList<Usuario>();
	@Autowired
	private PasswordEncoder encoder;
	
	private final UsuarioRepository usuarioRepository;
	
	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		//lista.add(new Usuario("user1@email.com", "User123."));
		//lista.add(new Usuario("user2@email.com", "User456++"));
		this.usuarioRepository = usuarioRepository;
	}// constructor
	
	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}//getAllUsuarios

	public Usuario getUsuario(Long userId) {
		return usuarioRepository.findById(userId).orElseThrow(
				() -> new IllegalArgumentException("El usuario con el id [" + userId + "] no existe")
				);
	}//getUsuario

	public Usuario deleteUsuario(Long userId) {
		Usuario user = null;
			if(usuarioRepository.existsById(userId)) {
				user = usuarioRepository.findById(userId).get();
				usuarioRepository.deleteById(userId);
			}//if exist
		return user;
	}//deleteUsuario

	public Usuario updateUsuario(Long userId, ChangePassword changePassword) {
		Usuario user = null;		
			if(usuarioRepository.existsById(userId)) {
				Usuario usuario = usuarioRepository.findById(userId).get();
//				if(usuario.getPassword().equals(changePassword.getPassword())) {
				if(encoder.matches(changePassword.getPassword(), usuario.getPassword())) {
					usuario.setPassword(encoder.encode(changePassword.getNpassword()));
					user = usuario; 
					usuarioRepository.save(usuario);
				}//if
			}//if exist	
		return user;		
	}//updateUusario
	
	public Usuario addUsuario(Usuario usuario) {
		Optional<Usuario> user = usuarioRepository.findByEmail(usuario.getEmail());
		if(user.isEmpty()) {
			usuario.setPassword(encoder.encode(usuario.getPassword()));
			return usuarioRepository.save(usuario);
		}else{
			System.out.println("El usuario [" + usuario.getEmail() + "] ya se encuentra registrado");
			return usuario;
		}//if isEmpty
	}//addUsuario

	public boolean validateUser(Usuario usuario) {
		Optional<Usuario> user = usuarioRepository.findByEmail(usuario.getEmail());
		if(user.isPresent()) {
			Usuario tmpUser = user.get();
			if(encoder.matches(usuario.getPassword(), tmpUser.getPassword())) {
				return true;
			}//if matches
		}////if present
		return false;
	}//validateUser

}//class UsuarioService
