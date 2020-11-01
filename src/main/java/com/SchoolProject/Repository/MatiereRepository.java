package com.SchoolProject.Repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SchoolProject.Entity.Matiere;
import com.SchoolProject.Entity.Student;



@Repository
public interface MatiereRepository extends JpaRepository<Matiere, String> {

	/* @Query("select count(m.refmat) from Matiere m where m.refmat like %?1")
	    Integer GetRefmat(String ReferenceMat);*/
	 
	Optional<Matiere> findByRefmat(String refmat);
 
	@Query("select m FROM Matiere m,Student s,Classe c,Teacher t,Enseignement e,CorrectMatiere cm WHERE m.refmat=cm.refmat AND c.idclass=e.idclass AND s.idclass=c.idclass AND t.cin=cm.cin and cm.cin=e.cin AND t.cin =:cin AND c.idclass =:idclass AND s.cin =:cins") 
	public List<Matiere> listMat(@Param("cin") String cinteacher,@Param("idclass") String idclass,@Param("cins") String cinstudent);


	@Query("select s FROM Student s,Classe c,Teacher t,Enseignement e,CorrectMatiere cm WHERE c.idclass=e.idclass AND s.idclass=c.idclass AND t.cin=cm.cin and cm.cin=e.cin AND t.cin =:cin AND c.idclass =:idclass") 
	public List<Student> listEtud(@Param("cin") String cinteacher,@Param("idclass") String idclass);

	
	@Query("select m FROM Matiere m,Classe c,Teacher t,Enseignement e WHERE c.idclass=e.idclass AND e.cin=t.cin AND t.cin =:cin AND c.idclass =:idclass") 
	public List<Matiere> listMatParClasse(@Param("cin") String cinteacher,@Param("idclass") String idclass);
	
	
	
	@Query("select m FROM Matiere m,Classe c,Student s,Module md,ClassModule cm WHERE s.idclass=cm.classId AND cm.classId=c.idclass AND cm.refmodule=md.refmod AND cm.classId=s.idclass AND s.cin =:cin GROUP BY s.cin") 
	public List<Matiere> listMatParEtudiant(@Param("cin") String cinetudiant);
	
	Boolean existsByRefmat(String refmat);
	
}
