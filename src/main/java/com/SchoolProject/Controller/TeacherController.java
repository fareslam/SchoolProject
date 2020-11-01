package com.SchoolProject.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SchoolProject.Entity.Classe;
import com.SchoolProject.Entity.Matiere;
import com.SchoolProject.Entity.Resultat;
import com.SchoolProject.Entity.Student;
import com.SchoolProject.Entity.Teacher;
import com.SchoolProject.Repository.ClasseRepository;
import com.SchoolProject.Repository.CorrectMatiereRepository;
import com.SchoolProject.Repository.DepartmentRepository;
import com.SchoolProject.Repository.MatiereRepository;

import com.SchoolProject.Repository.StudentRepository;
import com.SchoolProject.Repository.TeacherRepository;
import com.SchoolProject.Repository.UserRepository;
import com.SchoolProject.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/teacher")
@CrossOrigin
public class TeacherController {
	@Autowired 
	private StudentRepository sr;
	@Autowired
	private TeacherRepository tr;
	@Autowired
	private CorrectMatiereRepository cmr;
	@Autowired
	private MatiereRepository mr;
	@Autowired
	private ClasseRepository cr;
	
//TEST NEW METHOD//
	@GetMapping("/test/{cin}/{idclass}")
	public List<?> xx (@PathVariable(value="cin") String cin,@PathVariable(value="idclass") String idclass) throws ResourceNotFoundException
	{
		Classe classe=cr.findByIdclass(idclass)
				.orElseThrow(() -> new ResourceNotFoundException("Aucune Classe trouvé avec l ID " +idclass));
		
		Teacher t=tr.findByCin(cin)
				.orElseThrow(() -> new ResourceNotFoundException("Aucun Prof trouvé avec la CIN " +cin));
		
		return tr.affectNotes(cin, idclass); 
	}
	
	
	
	//******* AFFECTER LES NOTES DC DS MOY DES MATIERES AUX ETUDIANTS ******//	
	@PutMapping("/{cin}/{idclass}/{cins}")
	public List<Matiere> updtmatstudent(@PathVariable(value="cin") String cin,@PathVariable(value="idclass") String idclass,@PathVariable(value="cins") String cins,@RequestBody List<Matiere> listmat)  throws ResourceNotFoundException{	
	
		Classe classe=cr.findByIdclass(idclass)
				.orElseThrow(() -> new ResourceNotFoundException("Aucune Classe trouvé avec l ID " +idclass));
		
		Teacher t=tr.findByCin(cin)
				.orElseThrow(() -> new ResourceNotFoundException("Aucun Prof trouvé avec la CIN " +cin));
		
		Student s=sr.findByCin(cins)
				.orElseThrow(() -> new ResourceNotFoundException("Aucun Student trouvé avec la CIN " +cins));
	
		List<Matiere> list=mr.listMat(cin,idclass,cins);
		
for(int j=0;j<listmat.size();j++) {
	for(int i=0;i<list.size();i++) {	
	
		list.get(i).setNotedc(listmat.get(j).getNotedc());
	list.get(i).setNoteds(listmat.get(j).getNoteds());
	list.get(i).setMoymat(((2*listmat.get(j).getNoteds())+listmat.get(j).getNotedc())/3);
	
}
	
}

		return 	mr.saveAll(list);
}


	
	
	//******* AFFECTER GRADE******//	
	@PutMapping("/{cin}")
	public ResponseEntity<Teacher> setteachert(@PathVariable(value = "cin") String cin,@Valid @RequestBody Teacher t) 
				throws ResourceNotFoundException {

		Teacher ttr = tr.findByCin(cin)
				.orElseThrow(() -> new ResourceNotFoundException("Aucun prof trouvé avec la CIN " +cin));
		
		
		ttr.setGrade(t.getGrade());


		final Teacher tt = tr.save(ttr);
		return ResponseEntity.ok(tt);
	}
	
	
	
	
	//******* AFFICHAGE LISTE ETUDIANTS PAR CLASSE DE TEACHER******//	
	@GetMapping("/{cin}/{idclass}")
	public List<Student> updtmattudent(@PathVariable(value="cin") String cin,@PathVariable(value="idclass") String idclass)  throws ResourceNotFoundException{	
		
		Classe classe=cr.findByIdclass(idclass)
				.orElseThrow(() -> new ResourceNotFoundException("Aucune Classe trouvé avec l ID " +idclass));
		
		Teacher t=tr.findByCin(cin)
				.orElseThrow(() -> new ResourceNotFoundException("Aucun Prof trouvé avec la CIN " +cin));

		return 	mr.listEtud(cin,idclass);
}	
	
	//******* AFFICHAGE LISTE CLASSES DE TEACHER******//	
	@GetMapping("/listeclasse/{cin}")
	public List<Classe> listeclasse(@PathVariable(value="cin") String cin)  throws ResourceNotFoundException{	
		
	
		Teacher t=tr.findByCin(cin)
				.orElseThrow(() -> new ResourceNotFoundException("Aucun Prof trouvé avec la CIN " +cin));

		return 	tr.listClasses(cin);
}	
	
	
}