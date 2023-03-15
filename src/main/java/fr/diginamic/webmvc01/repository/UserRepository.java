package fr.diginamic.webmvc01.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.diginamic.webmvc01.entities.User;


public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.username = :username")
	Optional<User> findUserWithName(String username);
	
}
