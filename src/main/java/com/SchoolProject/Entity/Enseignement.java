package com.SchoolProject.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Enseignement {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="cin",insertable=false,updatable=false)
	private Teacher teacher;
	private String cin;
	
	@ManyToOne
	@JoinColumn(name="idclass",insertable=false,updatable=false)
	private Classe classe;
	private String idclass;
	

	public Enseignement(long id, Teacher teacher, Classe classe) {
		super();
		this.id = id;
		this.teacher = teacher;
		this.classe = classe;
	}
	public Enseignement() {

	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

@JsonBackReference(value="teaching")
	public Teacher getTeacher() {
		return teacher;
	}

public String getCin() {
	return cin;
}

public void setCin(String cin) {
	this.cin = cin;
}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getIdclass() {
		return idclass;
	}
	public void setIdclass(String idclass) {
		this.idclass = idclass;
	}
	@JsonBackReference(value="classing")
	public Classe getClasse() {
		return classe;
	}


	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	
	

}
