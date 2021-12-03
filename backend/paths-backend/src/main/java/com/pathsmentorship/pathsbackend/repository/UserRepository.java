package com.pathsmentorship.pathsbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pathsmentorship.pathsbackend.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


	

	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
//	List<User> findAll();
	
	Optional<User> findById(Long id);
	
//
//	public String deleteById(Long id) {
//		User user = userRepository.findById(id)
//				.orElseThrow(() -> new UsernameNotFoundException("No user found with the id " + id));
//		entityManager.createQuery("delete from users_roles where id = :id")
//			.setParameter("id", id)
//			.executeUpdate();
//		entityManager.createQuery("delete from users where id = :id")
//			.setParameter("id", id)
//			.executeUpdate();
//		return "User with id " + id + " deleted successfully.";
//	}
}
