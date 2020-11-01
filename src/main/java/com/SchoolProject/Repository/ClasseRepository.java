package com.SchoolProject.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SchoolProject.Entity.Classe;
import com.SchoolProject.Entity.Student;

@Repository
public interface ClasseRepository extends JpaRepository<Classe,String>{

	Optional<Classe> findByIdclass(String idclass);

		Boolean existsByIdclass(String idclass);
}
