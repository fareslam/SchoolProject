package com.SchoolProject.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ClassModule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="refmodule",insertable=false,updatable=false)
	private Module module;



	private String refmodule;
	
	@ManyToOne
	@JoinColumn(name="classId",insertable=false,updatable=false)
	private Classe classe;
	private String classId;

	public ClassModule(long id, Module module, Classe classe) {
		super();
		this.id = id;
		this.module = module;
		this.classe = classe;
	}

	public ClassModule()
	{
		classe=new Classe();
		module=new Module();
	}
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	@JsonBackReference(value="modcls")
	public Module getModule() {
		return module;
	}


	public void setModule(Module module) {
		this.module = module;
	}

@JsonBackReference(value="clsmod")
	public Classe getClasse() {
		return classe;
	}


	public void setClasse(Classe classe) {
		this.classe = classe;
	}


	public String getRefmodule() {
		return refmodule;
	}

	public void setRefmodule(String refmodule) {
		this.refmodule = refmodule;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}
}
