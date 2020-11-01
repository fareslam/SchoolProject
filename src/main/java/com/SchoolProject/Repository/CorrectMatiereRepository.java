package com.SchoolProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SchoolProject.Entity.CorrectMatiere;

@Repository
public interface CorrectMatiereRepository extends JpaRepository<CorrectMatiere,Long>{

}
