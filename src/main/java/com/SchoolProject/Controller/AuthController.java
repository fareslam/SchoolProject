package com.SchoolProject.Controller;
import java.util.HashSet;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SchoolProject.Entity.Admin;
import com.SchoolProject.Entity.Classe;
import com.SchoolProject.Entity.Department;
import com.SchoolProject.Entity.ERole;
import com.SchoolProject.Entity.Role;
import com.SchoolProject.Entity.Student;
import com.SchoolProject.Entity.Teacher;
import com.SchoolProject.Entity.User;
import com.SchoolProject.Repository.AdminRepository;
import com.SchoolProject.Repository.RoleRepository;
import com.SchoolProject.Repository.StudentRepository;
import com.SchoolProject.Repository.TeacherRepository;
import com.SchoolProject.Repository.UserRepository;
import com.SchoolProject.Security.JWT.JwtUtils;
import com.SchoolProject.Security.Services.UserDetailsImpl;
import com.SchoolProject.payload.request.LoginRequest;
import com.SchoolProject.payload.request.SignupRequest;
import com.SchoolProject.payload.response.JwtResponse;
import com.SchoolProject.payload.response.MessageResponse;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	

	//******* USER LOGIN ******//
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getCin(), 
												 userDetails.getUsername(), 
												 userDetails.getNom(),
												 userDetails.getPrenom(),
												 userDetails.getTel(),
												 userDetails.getEmail(), 
												 roles));
								
	}

	//******* USER REGISTRATION ******//
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
		if (userRepository.existsByCin(signUpRequest.getCin())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Cin is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		if (userRepository.existsByTel(signUpRequest.getTel())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Telephone Number is already in use!"));
		}
	
		// Create new user's account
		User user = new User(signUpRequest.getCin(),
							 signUpRequest.getUsername(),
							 signUpRequest.getNom(),
							 signUpRequest.getPrenom(),
							 signUpRequest.getTel(),
							 signUpRequest.getEmail(),
							 signUpRequest.getDateNaissance(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);

					
					user.setRoles(roles);
					userRepository.save(user);	
					adminRepository.save(new Admin(user.getCin(),user));
					
					break;
					
				case "teacher":
					
			
					Role teachRole = roleRepository.findByName(ERole.ROLE_TEACHER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(teachRole);
					
					
					Role usRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(usRole);
					
					user.setRoles(roles);
					userRepository.save(user);
					teacherRepository.save(new Teacher(user.getCin(),"",user));

					break;
					
				case "student":
					final  Department d = null;
					final  Classe c = null;
				
					Role stdRole = roleRepository.findByName(ERole.ROLE_STUDENT)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(stdRole);
					
					Role useRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(useRole);
					
					user.setRoles(roles);
					
					userRepository.save(user);
					studentRepository.save(new Student(user.getCin(),"","",c,d,user));

					break;
					
				default:
					Role suserRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(suserRole);
					user.setRoles(roles);
					userRepository.save(user);
				}
			});
		}


	

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}