package fr.diginamic.webmvc01.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("api")
public class ClientsControllerRest {
	@Autowired
	CrudClientRepo crc;
	
	@GetMapping
	public Iterable<Client> clients() {
		return crc.findAll();
	}
	
	@PostMapping("client")
	public Client add(@RequestBody @Valid Client client) {
		return crc.save(client);
	}
	
	@DeleteMapping("client")
	public void delete(@RequestBody Integer id) {
		crc.deleteById(id);
	}
}
