package com.SchoolProject.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(	name = "department", uniqueConstraints= {@UniqueConstraint(columnNames = "iddep")})

public class Department {
	@Id
	private String iddep;
	private String nomdep;
	

	@OneToMany(mappedBy="department",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Student> etudiants;

	@OneToMany(mappedBy="department",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Classe> listeClasses;
	


	public String getIddep() {
		return iddep;
	}

	public void setIddep(String iddep) {
		this.iddep = iddep;
	}

	public Department(String iddep, String nomdep) {
		super();
		this.iddep = iddep;
		this.nomdep = nomdep;

	}
	public Department() {}

	public String getNomdep() {
		return nomdep;
	}

	@JsonIgnore
	@JsonManagedReference(value="depstudent")
	public List<Student> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Student> etudiants) {
		this.etudiants = etudiants;
	}


	public void setNomdep(String nomdep) {
		this.nomdep = nomdep;
	}
	@JsonIgnore
	@JsonManagedReference(value="classeDep")
	public List<Classe> getListeClasses() {
		return listeClasses;
	}

	public void setListeClasses(List<Classe> listeClasses) {
		this.listeClasses = listeClasses;
	}
	
	
}
