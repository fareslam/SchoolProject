package com.SchoolProject.Entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.List;

@Entity
@Table(	name = "module", 
uniqueConstraints = { 
	@UniqueConstraint(columnNames = "refmod"),

	})
public class Module {
    @Id
    @NotBlank
	@Size(max = 20)
    private String refmod;
    private String libellemod;
    private float coeffmod;
	
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "module", fetch = FetchType.LAZY)
    private List<Matiere> matieres;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "module", fetch = FetchType.LAZY)
    private List<Resultat> listResultats;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "module", fetch = FetchType.LAZY)
    private List<ClassModule> listClassesModules;

	public Module(String rm,String libellemod,float mo) {
		super();
		this.refmod=rm;
		this.libellemod = libellemod;
		this.coeffmod=mo;
		

	}
	public Module() {
	
	}
	

	@JsonManagedReference(value="modcls")
	public List<ClassModule> getListClassesModules() {
		return listClassesModules;
	}
	public void setListClassesModules(List<ClassModule> listClassesModules) {
		this.listClassesModules = listClassesModules;
	}
	public String getRefmod() {
		return refmod;
	}

	public void setRefmod(String refmod) {
		this.refmod = refmod;
	}

	public String getLibellemod() {
		return libellemod;
	}

	public void setLibellemod(String libellemod) {
		this.libellemod = libellemod;
	}
    
	

    public float getCoeffmod() {
		return coeffmod;
	}

	public void setCoeffmod(float coeffmod) {
		this.coeffmod = coeffmod;
	}

	  @JsonManagedReference(value="module")
	    public List<Resultat> getListResultats() {
			return listResultats;
		}

		public void setListResultats(List<Resultat> listResultats) {
			this.listResultats = listResultats;
		}

		@JsonManagedReference(value="modulemat")
	    public List<Matiere> getMatieres() {
	        return matieres;
	    }

	    public void setMatieres(List<Matiere> matieres) {
	        this.matieres = matieres;
	    }
	
	
	
	
    
    }



