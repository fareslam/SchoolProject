package com.SchoolProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.SchoolProject.Entity.Reclamation;


@Repository
public interface ReclamationRepository extends JpaRepository <Reclamation, String> {
	
	 @Query("select count(r.refreclam) from Reclamation r where r.refreclam like %?1")
	    Integer GetReference(String ReferenceR);
}
