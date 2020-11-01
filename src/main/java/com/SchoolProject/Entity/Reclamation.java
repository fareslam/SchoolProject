package com.SchoolProject.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@Entity

public class Reclamation {
	
	@Id
	private String refreclam;
	
	private String description;
	

	@ManyToOne
	@JoinColumn(name ="refstudent")
	private Student student;
	

	@ManyToOne
	@JoinColumn(name ="refmatiere")
	private Matiere matiere;
	
	
	public Reclamation(String refReclam, String description, Student student, Matiere matiere) {
		
		refreclam = refReclam;
		this.description = description;
		this.student = student;
		this.matiere = matiere;
	}
	
	public Reclamation() 
	{
		this.student=new Student();
		this.matiere=new Matiere();
	}
	
	public String getRefreclam() {
		return refreclam;
	}

	public void setRefreclam(String refReclam) {
		refreclam = refReclam;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonBackReference(value="studentreclam")
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@JsonBackReference(value="reclammatiere")
	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}


}
