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
public class CorrectMatiere {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private Date datecorrect;
	
	
	@ManyToOne
	@JoinColumn(name="cin",insertable=false,updatable=false)
	private Teacher teacher;
	private String cin;
	

	@ManyToOne
	@JoinColumn(name="refmat",insertable=false,updatable=false)
	private Matiere matiere;
	
	private String refmat;

	public String getRefmat() {
		return refmat;
	}
	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}
	public void setRefmat(String refmat) {
		this.refmat = refmat;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDatecorrect() {
		return datecorrect;
	}

	public void setDatecorrect(Date datecorrect) {
		this.datecorrect = datecorrect;
	}

	@JsonBackReference(value="teach")
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	@JsonBackReference(value="matcorrect")
	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public CorrectMatiere(long id, Date datecorrect, Teacher teacher, Matiere matiere) {
	
		this.id = id;
		this.datecorrect = datecorrect;
		this.teacher = teacher;
		this.matiere = matiere;
	}
	public CorrectMatiere()
	{

	}

}
