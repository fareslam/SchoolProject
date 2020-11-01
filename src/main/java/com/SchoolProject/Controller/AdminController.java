package com.SchoolProject.Controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SchoolProject.Entity.Admin;
import com.SchoolProject.Entity.ClassModule;
import com.SchoolProject.Entity.Classe;
import com.SchoolProject.Entity.CorrectMatiere;
import com.SchoolProject.Entity.Department;
import com.SchoolProject.Entity.ERole;
import com.SchoolProject.Entity.Enseignement;
import com.SchoolProject.Entity.Matiere;
import com.SchoolProject.Entity.Module;
import com.SchoolProject.Entity.Reclamation;
import com.SchoolProject.Entity.Resultat;
import com.SchoolProject.Entity.Role;
import com.SchoolProject.Entity.Student;
import com.SchoolProject.Entity.Teacher;
import com.SchoolProject.Entity.User;
import com.SchoolProject.Repository.AdminRepository;
import com.SchoolProject.Repository.ClassModuleRepository;
import com.SchoolProject.Repository.ClasseRepository;
import com.SchoolProject.Repository.CorrectMatiereRepository;
import com.SchoolProject.Repository.DepartmentRepository;
import com.SchoolProject.Repository.EnseignementRepository;
import com.SchoolProject.Repository.MatiereRepository;
import com.SchoolProject.Repository.ModuleRepository;
import com.SchoolProject.Repository.ReclamationRepository;
import com.SchoolProject.Repository.ResultatRepository;
import com.SchoolProject.Repository.RoleRepository;
import com.SchoolProject.Repository.StudentRepository;
import com.SchoolProject.Repository.TeacherRepository;
import com.SchoolProject.Repository.UserRepository;
import com.SchoolProject.exception.ResourceNotFoundException;
import com.SchoolProject.payload.request.SignupRequest;
import com.SchoolProject.payload.response.MessageResponse;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {
	  @Autowired
	   private ClasseRepository cr;
	  @Autowired
	private EnseignementRepository er;  
		@Autowired
		private CorrectMatiereRepository cm;
	@Autowired
	private AdminRepository ar;
	@Autowired
	private ClassModuleRepository cmr;
	@Autowired
	private UserRepository ur;
	@Autowired 
	DepartmentRepository depr;
	@Autowired
	private ResultatRepository rrr;
	@Autowired
	private StudentRepository sr;
	
	@Autowired
	private TeacherRepository tr;
	@Autowired
	private ReclamationRepository rr;
	
	@Autowired
	private ModuleRepository modr;	@Autowired RoleRepository roleRepository;
	
    @Autowired
    private MatiereRepository mat;
    
	@Autowired
	PasswordEncoder encoder;

	//******* NB TOTAL USERS ******//
	@GetMapping("/user/nb")
	public Long nbu()
	{
		return this.ar.NbUser();
	}
	

	//*******************************************MODULE************************************************************

		//******* ADD NEW MODULE ******//
	    @PostMapping("/module/addModule")
	    public ResponseEntity<?> addModule(@Valid @RequestBody Module mod) {
	    

	    	if (modr.existsByRefmod(mod.getRefmod()))
	    	{
	    		return   ResponseEntity.badRequest().body(new MessageResponse("La Référence "+mod.getRefmod()+" est deja existée"));
		    }
	    	this.modr.save(mod);
	    	return  ResponseEntity.ok().body(mod);
	    }
	    
	    
	    //*********UPDATE MODULE*************
	    
		@PutMapping("/module/update/{refmod}")
		public ResponseEntity<?> updateMod(@PathVariable(value = "refmod") String refmod,@Valid @RequestBody Module mod) 
					throws ResourceNotFoundException {
			
			Module m = modr.findByRefmod(refmod)
					.orElseThrow(() -> new ResourceNotFoundException("Aucun module trouvé avec la Ref " +refmod));
		

			
			m.setCoeffmod(mod.getCoeffmod());
			m.setLibellemod(mod.getLibellemod());
			
			
				final Module mf = modr.save(m);
			return ResponseEntity.ok(mf);
		}
	    	
		//******* NB MODULES ******//
		@GetMapping("/module/nb")
		public Integer nbmodules()
		{
			return this.ar.NbModules();
		}
		 
		//******* LISTE DES MODULES ******//
	    @GetMapping("/module/listModules")
	    public List<Module> getAllModules(){
	        return modr.findAll();
	    }

		//******* GET MODULE BY REF ******//
		@GetMapping("/module/{refmod}")
		public Module getModByref(@PathVariable (value = "refmod") String refmod) throws ResourceNotFoundException{
			return this.modr.findByRefmod(refmod)
					.orElseThrow(() -> new ResourceNotFoundException("Module non trouvé avec la Reference :" + refmod));
		}
	    
		//******* DELETE A MODULE ******//
		@DeleteMapping("/module/{refmod}")
		public ResponseEntity<?> deleteMod(@PathVariable(value = "refmod") String refmod)
		 {
			Optional<Module> m = modr.findByRefmod(refmod);
			if (!m.isPresent())
			{
				 return new ResponseEntity<>(new MessageResponse("Module avec la Reférence "+refmod+" est inexistant"), HttpStatus.NOT_FOUND);
			}
		
			modr.deleteById(refmod);

			return new ResponseEntity<>(new MessageResponse("Module avec la reférence "+refmod+" est supprimé avec succés"), HttpStatus.OK);
	}
	  
	//	************************************* MATIERE ***********************************************************************
		//****Update Matiere*****
		@PutMapping("/matiere/update/{refmat}")
		public ResponseEntity<?> updateMat(@PathVariable(value = "refmat") String refmat,@Valid @RequestBody Matiere matt) 
					throws ResourceNotFoundException {
			
			Matiere m = mat.findByRefmat(refmat)
					.orElseThrow(() -> new ResourceNotFoundException("Aucune Matiere trouvé avec la Ref " +refmat));
		

				m.setRefmat(matt.getRefmat());
				m.setCoeffmat(matt.getCoeffmat());
				m.setLibellemat(matt.getLibellemat());

				if (mat.existsByRefmat(m.getRefmat()))
		    	{
					 return new ResponseEntity<>(new MessageResponse("Matiere existe avec la Reférence "+refmat), HttpStatus.BAD_REQUEST);
			    }	

				final Matiere mf = mat.save(m);
			return ResponseEntity.ok(mf);
		} 
		//******* ADD NEW MATIERE ******//
	    @PostMapping("/matiere/addMatiere")
	  
	    public ResponseEntity<?> addMatiere(@Valid @RequestBody Matiere matiere) {
	    

	    	if (mat.existsByRefmat(matiere.getRefmat()))
	    	{
	    		return   ResponseEntity.badRequest().body(new MessageResponse("La Référence "+matiere.getRefmat()+" est deja existée"));
		    }
	    	
	    	if	 (!modr.existsByRefmod(matiere.getModule().getRefmod()) )
	    	{	
	    		return   ResponseEntity.badRequest().body(new MessageResponse("La Référence du module "+matiere.getModule().getRefmod()+" est inexistante"));
	    	}
	    	this.mat.save(matiere);
	    	return  ResponseEntity.ok().body(matiere);
	    }
	    
		//******* NB MATIERES ******//
		@GetMapping("/matiere/nb")
		public Long nbmat()
		{
			return this.ar.NbMatieres();
		}
		

		//******* LISTE DES MATIERES ******//
	    @GetMapping("/matiere/listMatieres")
	    public List<Matiere> getAllMats(){
	        return mat.findAll();
	    }

		//******* GET MATIERE BY REF ******//
		@GetMapping("/matiere/{refmat}")
		public Matiere getModByrf(@PathVariable (value = "refmat") String refmat) throws ResourceNotFoundException{
			return this.mat.findByRefmat(refmat)
					.orElseThrow(() -> new ResourceNotFoundException("Module non trouvé avec la Reference :" + refmat));
		}
		
		//******* DELETE A MATIERE BY REF ******//
		@DeleteMapping("/matiere/{refmat}")
		public ResponseEntity<?> deleteMat(@PathVariable(value = "refmat") String refmat)
				 {
			Optional<Matiere> m = mat.findByRefmat(refmat);
					if (!m.isPresent())
					{
						 return new ResponseEntity<>(new MessageResponse("Matiere inexistante avec la Reférence "+refmat), HttpStatus.NOT_FOUND);
					}
				
						
			mat.deleteById(refmat);
			
			return new ResponseEntity<>(new MessageResponse("Matiere ayant la reférence "+refmat+" est supprimée avec succés"), HttpStatus.OK);
		}	
		
	//*************************************************CLASSE********************************************************************    

		//******* ADD A NEW CLASSE******//
	    @PostMapping("/classe/addClasse")
	    public ResponseEntity<?> addClasse(@Valid @RequestBody Classe classe) {
		    

	    	if (cr.existsByIdclass(classe.getIdclass()))
	    	{
	    		return   ResponseEntity.badRequest().body(new MessageResponse("ID de classe "+classe.getIdclass()+" est deja existée"));
		    }
	    	
	    	if	 (!depr.existsByIddep(classe.getDepartment().getIddep()) )
	    	{	
	    		return   ResponseEntity.badRequest().body(new MessageResponse("ID du Department "+classe.getDepartment().getIddep()+" est inexistant"));
	    	}
	    	this.cr.save(classe);
	    	return  ResponseEntity.ok().body(classe);
	    }

		//******* AFFICHER LES CLASSES ******//
	    @GetMapping("/classe/listClasses")
	    public List<Classe> listclasse() {
			
	    	return this.cr.findAll();
	
}
		//******* NB CLASSES ******//
		@GetMapping("/classe/nb")
		public Integer nbclass()
		{
			return this.ar.NbClasses();
		}
		
		
		//*****************************************CLASS MODULE**********************************************************************
		//******* AFFECTER A UNE CLASSE UN MODULE ******//
	    @PostMapping("/classmodule/affectClassModule")
	    public ResponseEntity<?> addclassemodule(@Valid @RequestBody ClassModule cm) {
			
	    	
			if (!modr.existsByRefmod(cm.getRefmodule()))
			{
				return new ResponseEntity<>(new MessageResponse("Module avec Reference "+cm.getRefmodule()+" inexistant"),HttpStatus.NOT_FOUND);
			}
			if (!cr.existsByIdclass(cm.getClassId()))
			{
				return new ResponseEntity<>(new MessageResponse("Classe avec ID "+cm.getClassId()+" est inexistante"),HttpStatus.NOT_FOUND);
			}
			 this.cmr.save(cm);
			 return new ResponseEntity<>(cm,HttpStatus.CREATED);
	
}
	    
		//******* LISTE DES CLASSES ET LEURS MODULES ******//
	    @GetMapping("/classmodule/listClassModules")
	    public List<ClassModule> listclmod() {
			
	    	return this.cmr.findAll();
	
}
	//*********************************************RECLAMATION***********************************************************	
		

		
		//******* LISTE RECLAMATIONS ******//
		  @GetMapping("/reclam/listReclamations")
		    public ResponseEntity<List<Reclamation>> getAllReclams(){
			  try {
				  List<Reclamation> r=this.rr.findAll();
				
					  return new ResponseEntity<>(r,HttpStatus.OK);
				  
			  }
			  catch (Exception e) {
			      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		  }	
		  
		  //****************************************DEPARTMENT********************************************************
		   
			//******* ADD NEW DEPARTMENT ******//
		    @PostMapping("/department/addDepartment")
		    public ResponseEntity<?> adddep(@Valid @RequestBody Department dep) {
				
		    	if(depr.existsByIddep(dep.getIddep()))
		    	{
		    		return new ResponseEntity<>(new MessageResponse("Department avec ID "+dep.getIddep()+" est existant"),HttpStatus.BAD_REQUEST);
		    	}
		    	depr.save(dep);
		    	return new ResponseEntity<>(dep,HttpStatus.CREATED);
		
	}
			//******* LISTE DEPARTMENTS ******//
		    @GetMapping("/department/listDepartments")
		    public List<Department> listdep() {
				
		    	return this.depr.findAll();
		
	}
			//******* NB DEPARTMENTS ******//
			@GetMapping("/department/nb")
			public Integer nbdept()
			{
				return this.ar.NbDepartments();
			}
			
			
		    
		    //****************************************RESULTAT MODULE********************************************************

			
			//******* AFFECTER LES RESULTAT D'UN MODULE POUR UN ETUDIANT ******//
			@PutMapping("/resultat/affectStudentResultat/{cin}/{idclass}/{refmod}")
			public ResponseEntity<Resultat> updtmodulestudent(@PathVariable(value = "cin") String cin, @PathVariable(value = "idclass") String idclass,@PathVariable(value = "refmod") String refmod,@Valid @RequestBody Resultat r) 
						throws ResourceNotFoundException {
				
				Student st=sr.findByCin(cin)
						.orElseThrow(() -> new ResourceNotFoundException("Aucun Etudiant trouvé avec la CIN " +cin));
				
				Classe classe=cr.findByIdclass(idclass)
						.orElseThrow(() -> new ResourceNotFoundException("Aucune Classe trouvé avec l ID " +idclass));
				
				Module module=modr.findByRefmod(refmod)
						.orElseThrow(() -> new ResourceNotFoundException("Aucune Module trouvé avec la Reference " +refmod));
				
				ClassModule clm=cmr.findByModuleAndClasse(module, classe) 
						.orElseThrow(() -> new ResourceNotFoundException("Aucune Module trouvé avec la classe" ));//on a declare une liste des modules selon une classe
				
		      	float mm=0.0f;
				float xx=0.0f;
				List<Matiere> liste=clm.getModule().getMatieres();
				float somcoeff=0.0f;
				
				if( (st.getClasse().getIdclass().equals(classe.getIdclass())))
				{
			
				if (!(liste.isEmpty()))
					
				{	
								
					for(int j=0;j<liste.size();j++)   	
				{		
						somcoeff+=liste.get(j).getCoeffmat();
						mm+=(float)((liste.get(j).getMoymat())*(liste.get(j).getCoeffmat())  );
				
				}
				 xx=(float)mm/somcoeff;
				 r.setStudent(st);
				 r.setModule(module);
				 r.setMoymodule(xx);		
			}}
				else
				{   
					 r.setStudent(st);
					 r.setModule(module);
					r.setMoymodule(0.0f);}

					final Resultat sttt = rrr.save(r);
				return ResponseEntity.ok(sttt);
			}
			
			
			//******* LISTE RESULTATS ******//
			@GetMapping("/resultat/listResultats")
			public List<Resultat> listres()
			{
				return rrr.findAll();
			}		
			
		//*****************************************Enseignement***************************************************	
			
			//******* AFFECTER TEACHER A UNE CLASSE ******//
			@PostMapping("/enseign/affectTeacherClasse")
			    public ResponseEntity<?> addEnseignement(@Valid @RequestBody Enseignement e) {
				
				if (!tr.existsByCin(e.getCin()))
				{
					return new ResponseEntity<>(new MessageResponse("Teacher avec CIN "+e.getCin()+" inexistant"),HttpStatus.NOT_FOUND);
				}
				if (!cr.existsByIdclass(e.getIdclass()))
				{
					return new ResponseEntity<>(new MessageResponse("Classe avec ID "+e.getIdclass()+" est inexistante"),HttpStatus.NOT_FOUND);
				}
				 this.er.save(e);
				 return new ResponseEntity<>(e,HttpStatus.CREATED);
		}	
			
			
			
	//**********************************************CORRECTION MATIERE***********************************************************		
			//******* AFFECTER TEACHER A UNE MATEIRE ******//
			@PostMapping("/correctmatiere/affectTeacherMatiere")
			    public ResponseEntity<?> addECorrectMat(@Valid @RequestBody CorrectMatiere cmm) {
				
				if (!tr.existsByCin(cmm.getCin()))
				{
					return new ResponseEntity<>(new MessageResponse("Teacher avec CIN "+cmm.getCin()+" inexistant"),HttpStatus.NOT_FOUND);
				}
				if (!mat.existsByRefmat(cmm.getRefmat()))
				{
					return new ResponseEntity<>(new MessageResponse("Matiere avec Ref "+cmm.getRefmat()+" est inexistante"),HttpStatus.NOT_FOUND);
				}
				 cm.save(cmm);
				 return new ResponseEntity<>(cmm,HttpStatus.CREATED);
		}	
			
			//******* AFFICHER LA LISTE DES MATIERES POUR LE TEACHER SELON IDCLASS CHOISI PAR L ADMIN ******//
			@GetMapping("/correctmatiere/listMatieresTeacher/{cin}/{idclass}")
			public List<Matiere> listMatiere(@PathVariable(value="cin") String cin,@PathVariable(value="idclass") String idclass) throws ResourceNotFoundException {
				
				Teacher t=tr.findByCin(cin)
						.orElseThrow(() -> new ResourceNotFoundException("Aucun Prof trouvé avec la CIN " +cin));

				Classe c=cr.findByIdclass(idclass)
						.orElseThrow(() -> new ResourceNotFoundException("Aucune Classe trouvée avec L'ID " +idclass));
				
				
				return this.mat.listMatParClasse(cin, idclass);
			}	
			
			//******* AFFICHER LA LISTE DES MATIERES CORRIGEES PAR TEACHERS ******//
			@GetMapping("/correctmatiere/listCorrectMatieres")
			public List<CorrectMatiere> listProfMatiere()
			{
				return this.cm.findAll();
			}
			
			//************************STUDENT*******************************
			//******* NB STDUENTS ******//
			@GetMapping("/student/nb")
			public Long nbstudent()
			{
				return this.ar.NbStudents();
			}
			
			//*****ADD NEW STUDENT*******//
				@PostMapping("/student/addStudent")
				public ResponseEntity<?> registerStudent(@Valid @RequestBody SignupRequest signUpRequest) {
					if (ur.existsByUsername(signUpRequest.getUsername())) {
						return ResponseEntity
								.badRequest()
								.body(new MessageResponse("Error: Username is already taken!"));
					}
					if (ur.existsByCin(signUpRequest.getCin())) {
						return ResponseEntity
								.badRequest()
								.body(new MessageResponse("Error: Cin is already taken!"));
					}

					if (ur.existsByEmail(signUpRequest.getEmail())) {
						return ResponseEntity
								.badRequest()
								.body(new MessageResponse("Error: Email is already in use!"));
					}
					if (ur.existsByTel(signUpRequest.getTel())) {
						return ResponseEntity
								.badRequest()
								.body(new MessageResponse("Error: Telephone Number is already in use!"));
					}
				
					// Create new user's account
					User user = new User(signUpRequest.getCin(),
										 signUpRequest.getUsername(),
										 signUpRequest.getNom(),
										 signUpRequest.getPrenom(),
										 signUpRequest.getTel(),
										 signUpRequest.getEmail(),
										 signUpRequest.getDateNaissance(),
										 encoder.encode(signUpRequest.getPassword()));


					
					Set<Role> roles = new HashSet<>();
					
				
								final  Department d = null;
								final  Classe c = null;
							
								Role stdRole = roleRepository.findByName(ERole.ROLE_STUDENT)
										.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
								roles.add(stdRole);
								
								Role useRole = roleRepository.findByName(ERole.ROLE_USER)
										.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
								roles.add(useRole);
								
								user.setRoles(roles);
								
								ur.save(user);
								sr.save(new Student(user.getCin(),"","",c,d,user));


					return ResponseEntity.ok(new MessageResponse("Student registered successfully!"));
				}
				//******* AFFECTER LES CLASSE AUX ETUDIANTS ******//
				@PutMapping("/classeStudent/{cin}")
				public ResponseEntity<Student> setStudentt(@PathVariable(value = "cin") String cin,@Valid @RequestBody Student s) 
							throws ResourceNotFoundException {

					Student st = sr.findByCin(cin)
							.orElseThrow(() -> new ResourceNotFoundException("Aucun Student trouvé avec la CIN " +cin));

					st.setIdclass(s.getIdclass());

					final Student sttt = sr.save(st);
					return ResponseEntity.ok(sttt);
				}
			//******* LISTE ETUDIANTS ******//
			  @GetMapping("/student/listStudents")
			    public List<Student> getAllStudents(){
			        return sr.findAll();
			    }
				//******* DELETE STUDENT ******//
				@DeleteMapping("/student/{cin}")
				public Map<String, Boolean> delustudent(@PathVariable(value = "cin") String cin)
						throws ResourceNotFoundException {
					Student a = sr.findByCin(cin)
							.orElseThrow(() -> new ResourceNotFoundException("Etudiant non trouvé avec la CIN : " + cin));
					
				
						sr.delete(a);
					
					Map<String, Boolean> response = new HashMap<>();
					response.put("deleted student", Boolean.TRUE);
					return response;
				}	 
				
				
				
				
				//************************TEACHER*******************************

				//*****ADD NEW TEACHER*******//
					@PostMapping("/addTeacher")
					public ResponseEntity<?> registerTeacher(@Valid @RequestBody SignupRequest signUpRequest) {
						if (ur.existsByUsername(signUpRequest.getUsername())) {
							return ResponseEntity
									.badRequest()
									.body(new MessageResponse("Error: Username is already taken!"));
						}
						if (ur.existsByCin(signUpRequest.getCin())) {
							return ResponseEntity
									.badRequest()
									.body(new MessageResponse("Error: Cin is already taken!"));
						}

						if (ur.existsByEmail(signUpRequest.getEmail())) {
							return ResponseEntity
									.badRequest()
									.body(new MessageResponse("Error: Email is already in use!"));
						}
						if (ur.existsByTel(signUpRequest.getTel())) {
							return ResponseEntity
									.badRequest()
									.body(new MessageResponse("Error: Telephone Number is already in use!"));
						}
					
						// Create new user's account
						User user = new User(signUpRequest.getCin(),
											 signUpRequest.getUsername(),
											 signUpRequest.getNom(),
											 signUpRequest.getPrenom(),
											 signUpRequest.getTel(),
											 signUpRequest.getEmail(),
											 signUpRequest.getDateNaissance(),
											 encoder.encode(signUpRequest.getPassword()));
				
							Set<Role> roles = new HashSet<>();
							Role tchRole = roleRepository.findByName(ERole.ROLE_TEACHER)
											.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
									roles.add(tchRole);
									
									Role useRole = roleRepository.findByName(ERole.ROLE_USER)
											.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
									roles.add(useRole);
									
									user.setRoles(roles);
									
									ur.save(user);
									tr.save(new Teacher(user.getCin(),"",user));


						return ResponseEntity.ok(new MessageResponse("Teacher registered successfully!"));
					}
				
				//******* LISTE TEACHERS ******//	

			  @GetMapping("/teacher/listTeachers")
			    public ResponseEntity<List<Teacher>> getAllTeacherss(){
				  try {
					  List<Teacher> lt=this.tr.findAll();
					
						  return new ResponseEntity<>(lt,HttpStatus.OK);	  
				  }
				  catch (Exception e) {
				      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			    }
			  }	
				//******* DELETE TEACHER ******//
				@DeleteMapping("/teacher/{cin}")
				public Map<String, Boolean> delteach(@PathVariable(value = "cin") String cin)
						throws ResourceNotFoundException {
					Teacher t = tr.findByCin(cin)
							.orElseThrow(() -> new ResourceNotFoundException("Prof non trouvé avec la CIN : " + cin));
					
				
						tr.delete(t);
					
					Map<String, Boolean> response = new HashMap<>();
					response.put("deleted teacher", Boolean.TRUE);
					return response;
				}
			
				//******* NB TEACHERS ******//
				@GetMapping("/teacher/nb")
				public Integer nbteacher()
				{
					return this.ar.NbTeachers();
				}
}
