package fr.diginamic.webmvc01.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.webmvc01.entities.User;
import fr.diginamic.webmvc01.repository.UserRepository;

@RestController
@CrossOrigin
@RequestMapping("api")
public class ApiController {

	@Autowired
	UserRepository userRepo;
	
	@GetMapping("users")
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
}
