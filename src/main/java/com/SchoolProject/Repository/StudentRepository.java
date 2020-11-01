package com.SchoolProject.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SchoolProject.Entity.Student;


@Repository
public interface StudentRepository  extends JpaRepository<Student,String>{
	Optional<Student> findByCin(String c);
	Boolean existsByCin(String cin);
}

