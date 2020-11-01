package com.SchoolProject.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="classe", uniqueConstraints= {@UniqueConstraint(columnNames = "idclass")})
public class Classe {
	@Id
	private String idclass;
	
	private String nomclass;
	
	
	@OneToMany(mappedBy="classe",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Student> studentsList;

	@OneToMany(mappedBy="classe",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	private List<ClassModule> listeClassModules;
	
	@ManyToOne
	@JoinColumn(name="DepId")
	private Department department;

	@OneToMany(mappedBy="classe",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	List<Enseignement> listeEnseignants;


	public Classe(String idclass, String nomclass,Department d) {
		super();
		this.idclass = idclass;
		this.nomclass = nomclass;
		this.department=d;
	}

	public Classe() {
		department =new Department();
	}
	
	public String getIdclass() {
		return idclass;
	}

	public void setIdclass(String idclass) {
		this.idclass = idclass;
	}

	public String getNomclass() {
		return nomclass;
	}

	public void setNomclass(String nomclass) {
		this.nomclass = nomclass;
	}

	@JsonManagedReference(value="etudiantclass")
	public List<Student> getStudentsList() {
		return studentsList;
	}
	public void setStudentsList(List<Student> listEtudiants) {
		this.studentsList = listEtudiants;
	}



	@JsonBackReference(value="classeDep")
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}


	@JsonManagedReference(value="clsmod")
	public List<ClassModule> getListeClassModules() {
		return listeClassModules;
	}

	public void setListeClassModules(List<ClassModule> listeClassModules) {
		this.listeClassModules = listeClassModules;
	}

	@JsonManagedReference(value="classing")
	public List<Enseignement> getListeEnseignants() {
		return listeEnseignants;
	}

	public void setListeEnseignants(List<Enseignement> listeEnseignants) {
		this.listeEnseignants = listeEnseignants;
	}
	
	

}
