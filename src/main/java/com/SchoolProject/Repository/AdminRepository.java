package com.SchoolProject.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.SchoolProject.Entity.Admin;
import com.SchoolProject.Entity.Student;



@Repository
public interface AdminRepository extends JpaRepository<Admin,String>{

	 @Query("select count(m) from Matiere m ")
	    Long NbMatieres(); 

	 @Query("select count(md) from Module md ")
	    Integer NbModules();
	 
	 @Query("select count(t) from Teacher t ")
	    Integer NbTeachers();
	 
	 @Query("select count(s) from Student s ")
	    Long NbStudents(); 

	 @Query("select count(c) from Classe c ")
	    Integer NbClasses();
	 
	 @Query("select count(d) from Department d ")
	    Integer NbDepartments();
	 
	 @Query("select count(u) from User u ")
	    Long NbUser();
}
