
package com.SchoolProject.Security.Services;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.SchoolProject.Entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;



public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String cin;

	private String username;
	
	private String nom;
	
	private String prenom;

	private long tel;
	
	@JsonFormat(pattern= "yyyy-MM-dd")
	private Date dateNaissance;
	
	private String email;

	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(String cin, String username,String nom,String prenom, long tel,String email,Date dt, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.cin = cin;
		this.username = username;
		this.nom=nom;
		this.prenom=prenom;
		this.tel=tel;
		this.email = email;
		this.dateNaissance=dt;
		this.password = password;
		this.authorities = authorities;
	}



	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				user.getCin(),
				user.getUsername(), 
				user.getNom(),
				user.getPrenom(),
				user.getTel(),
				user.getEmail(),
				user.getDateNaissance(),
				user.getPassword(), 
				authorities);
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getCin() {
		return cin;
	}

	public String getEmail() {
		return email;
	}
	public String getNom() {
		return nom;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public long getTel() {
		return tel;
	}

	public void setTel(long tel) {
		this.tel = tel;
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

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(cin, user.cin);
	}
}
