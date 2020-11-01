package com.SchoolProject.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(	name = "matiere", 
uniqueConstraints = { 
	@UniqueConstraint(columnNames = "refmat"),

	})
public class Matiere {
    @Id
    private String refmat;
    private float coeffmat;
    private String libellemat;


	private float noteds;
    private float notedc;
    private float moymat;


	@ManyToOne
    @JoinColumn(name = "moduleid")
    private Module module;

    @OneToMany(mappedBy = "matiere",fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	   Collection<Reclamation> listReclams;
  

    @OneToMany(mappedBy = "matiere",fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	   Collection<CorrectMatiere> listmatierecorriges;
    

	public Matiere(String r,float coeffmat,String lm,float moy,Module module) {
		super();
		this.refmat=r;
		this.coeffmat = coeffmat;
		this.noteds = 0.0f;
		this.libellemat=lm;
		this.notedc = 0.0f;
		this.moymat=moy;
		this.module = module;
	}
	public Matiere() {
		this.module=new Module();
	}
    
    
    @JsonBackReference(value="modulemat")
    public Module getModule() {
        return module;
    }

    public float getMoymat() {
    	
   
		return moymat;
	}

    public String getLibellemat() {
		return libellemat;
	}
	public void setLibellemat(String libellemat) {
		this.libellemat = libellemat;
	}
	public void setMoymat(float moymat) {
		this.moymat = moymat;
	}
	
	public String getRefmat() {
		return refmat;
	}

	
    @JsonManagedReference(value="matcorrect")
	public Collection<CorrectMatiere> getListmatierecorriges() {
		return listmatierecorriges;
	}
	public void setListmatierecorriges(Collection<CorrectMatiere> listmatierecorriges) {
		this.listmatierecorriges = listmatierecorriges;
	}
	
	public void setRefmat(String refmat) {
		this.refmat = refmat;
	}

	public float getCoeffmat() {
		return coeffmat;
	}

	public void setCoeffmat(float coeffmat) {
		this.coeffmat = coeffmat;
	}

	@JsonManagedReference(value="reclammatiere")
	public Collection<Reclamation> getListReclams() {
		return listReclams;
	}
	public void setListReclams(Collection<Reclamation> listReclams) {
		this.listReclams = listReclams;
	}
	
	public float getNoteds() {
		return noteds;
	}

	public void setNoteds(float noteds) {
		this.noteds = noteds;
	}

	public float getNotedc() {
		return notedc;
	}

	public void setNotedc(float notedc) {
		this.notedc = notedc;
	}

	public void setModule(Module module) {
		this.module = module;
	}



}
