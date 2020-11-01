package com.SchoolProject.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SchoolProject.Entity.Department;


@Repository
public interface DepartmentRepository extends JpaRepository<Department,String>{

	Optional<Department> findByIddep(String d);

	Boolean existsByIddep(String iddep);

}
