package org.generation.ecommerce.dto;

public class ChangePassword {
	private String password;
	private String npassword;
	
	public ChangePassword(String password, String npassword) {
		this.password = password;
		this.npassword = npassword;
	}//constructor
	
	public ChangePassword() {}//constructor

	public String getPassword() {
		return password;
	}//getPassword

	public void setPassword(String password) {
		this.password = password;
	}//setPassword

	public String getNpassword() {
		return npassword;
	}//getNpassword

	public void setNpassword(String npassword) {
		this.npassword = npassword;
	}//setNpassword

	@Override
	public String toString() {
		return "ChangePassword [password=" + password + ", npassword=" + npassword + ", getPassword()=" + getPassword()
				+ ", getNpassword()=" + getNpassword() + "]";
	}//toString	
	
}//class ChangePassword
