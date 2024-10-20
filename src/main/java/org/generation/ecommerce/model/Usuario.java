package org.generation.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	@Column(unique=true, nullable=false)	
	private String email;
	@Column(unique=true, nullable=false)	
	private String password;
	
	//private static Long total = Long.valueOf(0);

	public Usuario(String email, String password) {
		this.email = email;
		this.password = password;
		//Usuario.total++;
		//id = Usuario.total;
	}//constructor
	
	public Usuario(){
		//Usuario.total++;
		//id = Usuario.total;
	}//constructor

	public Long getId() {
		return id;
	}//getId

	public void setId(Long id) {
		this.id = id;
	}//setId

	public String getEmail() {
		return email;
	}//getEmail

	public void setEmail(String email) {
		this.email = email;
	}//setEmail

	public String getPassword() {
		return password;
	}//getPassword

	public void setPassword(String password) {
		this.password = password;
	}//setPassword

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", password=" + password + ", getId()=" + getId()
				+ ", getEmail()=" + getEmail() + ", getPassword()=" + getPassword() + "]";
	}//toString
	
	
	
}//class Usuario
