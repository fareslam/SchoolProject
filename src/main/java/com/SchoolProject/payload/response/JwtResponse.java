package com.SchoolProject.payload.response;

import java.util.List;


public class JwtResponse {
	private String Token;
/*	private String type = "Bearer";*/
	private String cin;
	private String username;
	private String nom;
	private String prenom;
	private long tel;
	private String email;
	private List<String> roles;

	public JwtResponse(String Token, String cin, String username,String nom,String prenom,long tel, String email, List<String> roles) {
		this.Token = Token;
		this.cin = cin;
		this.username = username;
		this.nom=nom;
		this.prenom=prenom;
		this.tel=tel;
		this.email = email;
		this.roles = roles;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public long getTel() {
		return tel;
	}

	public void setTel(long tel) {
		this.tel = tel;
	}

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		this.Token = token;
	}
/*
	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}*/

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}
}
