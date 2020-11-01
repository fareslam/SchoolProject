package com.SchoolProject.Repository;
import com.SchoolProject.Entity.Module;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;


@Repository
public interface ModuleRepository extends JpaRepository<Module, String> {
	
	/* @Query("select count(m.refmod) from Module m where m.refmod like %?1")
	    Integer GetRefmodule(String ReferenceM);*/
	 
	Optional<Module> findByRefmod(String refmod);
    
	Boolean existsByRefmod(String refmod);
	
	

}
