package com.SchoolProject.Repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.SchoolProject.Entity.User;




@Repository
public interface UserRepository  extends JpaRepository<User,String>{
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	Boolean existsByTel(Long tel);
	
	Boolean existsByCin(String cin);

	Optional<User> findByCin(String cin);
	


}
