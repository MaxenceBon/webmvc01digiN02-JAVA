package fr.diginamic.webmvc01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.webmvc01.entities.Client;
import fr.diginamic.webmvc01.repository.CrudClientRepo;

/**
 * @CrossOrigin à ajouter pour être utiliser par les APP WEB
 * en ReactJs, Angular , VueJs ... 
 * @author chris
 *
 */
@RestController
@CrossOrigin
@RequestMapping("api/clients")
public class ClientsControllerRest {
	@Autowired
	CrudClientRepo crc;
	
	@GetMapping
	public Iterable<Client> clients() {
		return crc.findAll();
	}
}
