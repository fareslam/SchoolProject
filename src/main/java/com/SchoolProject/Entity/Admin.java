package com.SchoolProject.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name ="admin")
public class Admin {
	
	@Id
	@Column(name="ID_ADMIN")
	private String cin;
	

	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval=true )
	@JoinColumn(name = "user_id")
	private User user;


	public Admin(String cin, User user) {
		
		this.cin = cin;
		this.user = user;
	}
	
	public Admin()
	{}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
