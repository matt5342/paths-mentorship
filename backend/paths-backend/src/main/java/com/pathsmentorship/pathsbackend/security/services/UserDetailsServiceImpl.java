package com.pathsmentorship.pathsbackend.security.services;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pathsmentorship.pathsbackend.models.User;
import com.pathsmentorship.pathsbackend.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
		
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

//		System.out.println("loadUserByUsername Username: " + user.getUsername());
//		System.out.println("loadUserByUsername Password: " + user.getPassword());

		
		return UserDetailsImpl.build(user);
	}
	

}
