package com.SchoolProject.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SchoolProject.Entity.Role;
import com.SchoolProject.Entity.ERole;


@Repository
public interface RoleRepository  extends JpaRepository<Role,Long>{
	Optional<Role> findByName(ERole name);
}
