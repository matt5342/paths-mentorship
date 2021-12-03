package com.pathsmentorship.pathsbackend.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pathsmentorship.pathsbackend.models.AccessCode;
import com.pathsmentorship.pathsbackend.models.ERole;
import com.pathsmentorship.pathsbackend.models.Role;
import com.pathsmentorship.pathsbackend.models.School;
import com.pathsmentorship.pathsbackend.models.User;
import com.pathsmentorship.pathsbackend.payload.request.LoginRequest;
import com.pathsmentorship.pathsbackend.payload.request.SignupRequest;
import com.pathsmentorship.pathsbackend.payload.response.JwtResponse;
import com.pathsmentorship.pathsbackend.payload.response.MessageResponse;
import com.pathsmentorship.pathsbackend.repository.AccessCodeRepository;
import com.pathsmentorship.pathsbackend.repository.RoleRepository;
import com.pathsmentorship.pathsbackend.repository.SchoolRepository;
import com.pathsmentorship.pathsbackend.repository.UserRepository;
import com.pathsmentorship.pathsbackend.security.jwt.JwtUtils;
import com.pathsmentorship.pathsbackend.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	SchoolRepository schoolRepository;
	
	@Autowired
	AccessCodeRepository accessCodeRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		User currentUser = userRepository.findByUsername(loginRequest.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + loginRequest.getUsername()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
				
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 userDetails.getFirstName(),
												 currentUser.getLastName(),
												 userDetails.getSchool().getName(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		if (!accessCodeRepository.existsByName(signUpRequest.getAccessCode())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Invalid Access Code!"));
		}

		// Create new user's account
//		System.out.println(signUpRequest);
//		System.out.println("Sign Up request lastName: " + signUpRequest.getLastName());
//		System.out.println("Sign Up request username: " + signUpRequest.getUsername());
//		System.out.println("Sign up request Password: " + signUpRequest.getPassword());

		User user = new User();
		user.setUsername(signUpRequest.getUsername());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(encoder.encode(signUpRequest.getPassword()));
		user.setLastName(signUpRequest.getLastName());
		user.setFirstName(signUpRequest.getFirstName());

		
//		String pwStr = encoder.encode(signUpRequest.getPassword());
//		user.setPassword(pwStr);
//		System.out.println(user);
		
//		System.out.println("user to save encoded pw: " + user.getPassword());
		
		// parse access code
		String strAccessCode = signUpRequest.getAccessCode();
		AccessCode accessCode = accessCodeRepository.findByName(strAccessCode)
								.orElseThrow(() -> new RuntimeException("Error: Access Code not found"));
		School school = new School();
		
		
		// find school name from access code
		school = schoolRepository.findByName(accessCode.getSchoolName())
				.orElseThrow(() -> new RuntimeException("Error: School is not found"));
		
		user.setSchool(school);
//		System.out.println(user);
		
		String strRole = accessCode.getRoleName();
		Set<Role> roles = new HashSet<>();
		// find role names from access code
		if (strRole == null) {
			Role studentRole = roleRepository.findByName(ERole.ROLE_STUDENT)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(studentRole);
		} else {
				switch (strRole) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mentor":
					Role mentorRole = roleRepository.findByName(ERole.ROLE_MENTOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(mentorRole);

					break;
				case "parent":
					Role parentRole = roleRepository.findByName(ERole.ROLE_PARENT)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(parentRole);

					break;
				default:
					Role studentRole = roleRepository.findByName(ERole.ROLE_STUDENT)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(studentRole);
				}
			}
		

		user.setRoles(roles);
		
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
