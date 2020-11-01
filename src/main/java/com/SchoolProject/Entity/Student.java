package com.SchoolProject.Entity;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(	name = "student",uniqueConstraints = { 
	
		@UniqueConstraint(columnNames = "cin")
		})


public class Student {
	
	@Id
	private String cin;
	
	 
	private String niveau;
	 
	private String specialite;
	

	
		@ManyToOne
		@JoinColumn(name="depId")
		private Department department;


		@ManyToOne
		@JoinColumn(name="idclass",insertable=false,updatable=false)
		private Classe classe;
		private String idclass;




	@OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade=CascadeType.ALL)	 
   List<Resultat> listResutltats;

    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	   List<Reclamation> listReclamations;


	@OneToOne(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	


	
		public Student() 
		{ 
				department=new Department();
				classe=new Classe();
		}
	
	
	public Student(String cin, String niv, String spec,Classe cl,Department dep, User user) {
	
		this.cin = cin;
		niveau = niv;
		specialite = spec;
		this.classe=cl;
		this.department=dep;
		this.user = user;

	}
	

	public String getIdclass() {
			return idclass;
		}


		public void setIdclass(String idclass) {
			this.idclass = idclass;
		}



	@JsonBackReference(value="etudiantclass")
	public Classe getClasse() {
			return classe;
		}


		public void setClasse(Classe classe) {
			this.classe = classe;
		}



	
	@JsonBackReference(value="depstudent")
    public Department getDepartment() {
		return department;
	}


	public void setDepartement(Department departement) {
		this.department = departement;
	}
	
	
	public String getCin() {
		return cin;
	}
	


	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public String getSpecialite() {
		return specialite;
	}

	  @JsonManagedReference(value="student")
	public List<Resultat> getListResutltats() {
		return listResutltats;
	}
	public void setListResutltats(List<Resultat> listResutltats) {
		this.listResutltats = listResutltats;
	}
	
	
	
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	  @JsonManagedReference(value="studentreclam")
	public List<Reclamation> getListReclamations() {
		return listReclamations;
	}


	public void setListReclamations(List<Reclamation> listReclamations) {
		this.listReclamations = listReclamations;
	}


	
	
}