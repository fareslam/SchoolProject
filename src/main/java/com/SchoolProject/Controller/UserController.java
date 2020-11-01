package com.SchoolProject.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SchoolProject.Entity.User;
import com.SchoolProject.Repository.UserRepository;
import com.SchoolProject.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
	@Autowired 
	private UserRepository ur;
	@Autowired
	public PasswordEncoder  bCryptPasswordEncoder ;
	
	//******* UPDATE USERNAME & PWD ******//	
	@PutMapping("/{cin}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "cin") String cin,@Valid @RequestBody User s) 
				throws ResourceNotFoundException {
		
		User st = ur.findByCin(cin)
				.orElseThrow(() -> new ResourceNotFoundException("Aucun utilisateur trouvé avec la CIN " +cin));
	

		st.setUsername(s.getUsername());
		st.setPassword(bCryptPasswordEncoder.encode(s.getPassword()));



			final User sttt = ur.save(st);
		return ResponseEntity.ok(sttt);
	}
}
