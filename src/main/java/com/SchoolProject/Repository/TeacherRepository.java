package com.SchoolProject.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SchoolProject.Entity.Classe;
import com.SchoolProject.Entity.Matiere;
import com.SchoolProject.Entity.Teacher;

@Repository
public interface TeacherRepository  extends JpaRepository<Teacher,String>{
	Optional<Teacher> findByCin(String c);
	
	@Query("select c FROM Classe c,Teacher t,Enseignement e WHERE t.cin=e.cin AND e.idclass=c.idclass AND t.cin =:cin") 
	public List<Classe> listClasses(@Param("cin") String cinteacher);
	
	@Query("select DISTINCT(s.cin),s.idclass,s.user.nom,s.user.prenom,m.notedc,m.noteds,m.moymat FROM Student s,Matiere m,Classe c,Teacher t,Enseignement e,CorrectMatiere cm "
			+ "WHERE t.cin=e.cin AND e.idclass=c.idclass AND m.refmat=cm.refmat AND t.cin=cm.cin AND s.idclass IS NOT NULL AND cm.cin=e.cin AND c.idclass =:idclass AND t.cin =:cin") 
	public List<?> affectNotes(@Param("cin") String cinteacher,@Param("idclass") String idclass);
	
	
	Boolean existsByCin(String cin);
	
	

}
