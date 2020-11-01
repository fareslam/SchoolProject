package com.SchoolProject.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Resultat {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private Date dateresultat;
	
	private float moymodule;

	@ManyToOne
	@JoinColumn(name = "cin")
	private Student student;
	
	

	@ManyToOne
	@JoinColumn(name = "moduleid")
	private Module module;

	
	public Resultat(long id, Date dateresultat,float moy, Student student, Module module) {
		
		this.id=id;
		this.dateresultat = dateresultat;
		this.moymodule=moy;
		this.student = student;
		this.module = module;
	}

	public float getMoymodule() {
		return moymodule;
	}

	public void setMoymodule(float moymodule) {
		this.moymodule = moymodule;
	}

	public Resultat() {
		this.student=new Student();
		this.module=new Module();
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateresultat() {
		return dateresultat;
	}

	public void setDateresultat(Date dateresultat) {
		this.dateresultat = dateresultat;
	}
	
	@JsonBackReference(value="student")
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	@JsonBackReference(value="module")
	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}


	
}
