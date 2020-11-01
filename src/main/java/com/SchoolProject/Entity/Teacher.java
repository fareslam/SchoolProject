package com.SchoolProject.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;




@Entity
@Table(	name = "teacher",uniqueConstraints = { @UniqueConstraint(columnNames = "cin")	})
public class Teacher {
	
	@Id

	private String cin;
	
	private String Grade;


	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy="teacher",fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	Collection<CorrectMatiere> listMatieresCorriges;
	
	@OneToMany(mappedBy="teacher",fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	List<Enseignement> listClassesEnseignees;






	public Teacher(String cin, String grade, User user) 
	{
		this.cin = cin;
		Grade = grade;
		this.user = user;

	}

	public Teacher() {
	
	}
	
	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getGrade() {
		return Grade;
	}

	public void setGrade(String grade) {
		Grade = grade;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@JsonManagedReference(value="teach")
	public Collection<CorrectMatiere> getListMatieresCorriges() {
		return listMatieresCorriges;
	}

	public void setListMatieresCorriges(Collection<CorrectMatiere> listMatieresCorriges) {
		this.listMatieresCorriges = listMatieresCorriges;
	}

	@JsonManagedReference(value="teaching")
	public List<Enseignement> getListClassesEnseignees() {
		return listClassesEnseignees;
	}

	public void setListClassesEnseignees(List<Enseignement> listClassesEnseignees) {
		this.listClassesEnseignees = listClassesEnseignees;
	}


}