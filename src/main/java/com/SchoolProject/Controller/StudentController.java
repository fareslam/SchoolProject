	package com.SchoolProject.Controller;

import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
import com.SchoolProject.Entity.Classe;
import com.SchoolProject.Entity.Matiere;
import com.SchoolProject.Entity.Reclamation;
import com.SchoolProject.Entity.Student;

import com.SchoolProject.Repository.AdminRepository;
import com.SchoolProject.Repository.DepartmentRepository;
import com.SchoolProject.Repository.MatiereRepository;
import com.SchoolProject.Repository.ReclamationRepository;
import com.SchoolProject.Repository.StudentRepository;
import com.SchoolProject.Repository.UserRepository;
import com.SchoolProject.exception.ResourceNotFoundException;
import com.SchoolProject.payload.response.MessageResponse;

@RestController
@RequestMapping("/api/student")
@CrossOrigin
public class StudentController {
	
	@Autowired 
	private StudentRepository sr;

	@Autowired 
	private AdminRepository ar;
	
    @Autowired
	private DepartmentRepository depr;
    
    @Autowired
    private MatiereRepository mr;
    @Autowired
    private ReclamationRepository rr;


	



	
	
	//******* CHOISIR NIVEAU SPEC ET DEPT ******//	
	@PutMapping("/{cin}")
	public ResponseEntity<Student> setStudentt(@PathVariable(value = "cin") String cin,@Valid @RequestBody Student s) 
				throws ResourceNotFoundException {

		Student st = sr.findByCin(cin)
				.orElseThrow(() -> new ResourceNotFoundException("Aucun Student trouvé avec la CIN " +cin));
		
		
		st.setNiveau(s.getNiveau());
		st.setSpecialite(s.getSpecialite());
		st.setDepartement(s.getDepartment());

		final Student sttt = sr.save(st);
		return ResponseEntity.ok(sttt);
	}
	
	//******* AFFICHAGE LISTE MATIERE DE LETUDIANT ******//	
	@GetMapping("/listemat/{cin}")
	public List<Matiere> listeclasse(@PathVariable(value="cin") String cin)  throws ResourceNotFoundException{	
		
	
		Student t=sr.findByCin(cin)
				.orElseThrow(() -> new ResourceNotFoundException("Aucun Etudiant trouvé avec la CIN " +cin));

		return 	mr.listMatParEtudiant(cin);
}	

	
	

	//******* ADD NEW RECLAMATION ******//
	@PostMapping("/reclam/addReclam")
	    public ResponseEntity<?> addReclam(@Valid @RequestBody Reclamation r) {
		
		if	 (!mr.existsByRefmat(r.getMatiere().getRefmat()) )
    	{	
    		return   ResponseEntity.badRequest().body(new MessageResponse("Matiere inexistante avec la Refernce "+r.getMatiere().getRefmat()));
    	}
		if	 (!sr.existsByCin(r.getStudent().getCin()) )
    	{	
    		return   ResponseEntity.badRequest().body(new MessageResponse("Student non trouvé avec la CIN "+r.getStudent().getCin()));
    	}
        String refreclam = "";
        
                int suiteRef = 0;
         
               do{
                   Random random = new Random();
                   int min = 1000;
                   int max = 100000;
                    suiteRef = random.nextInt((max-min)+1)+min ;
                    refreclam = "REFRECLAM"+suiteRef;
                    System.out.println(refreclam);
               }while (refreclam.equals(r.getRefreclam())); 
               r.setRefreclam(refreclam);
               
               rr.save(r);
    
          return new ResponseEntity<>(r,HttpStatus.CREATED);
	    }
	
}
