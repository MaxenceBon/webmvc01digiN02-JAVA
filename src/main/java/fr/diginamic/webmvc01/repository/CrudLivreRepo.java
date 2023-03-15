package fr.diginamic.webmvc01.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.diginamic.webmvc01.entities.Client;
import fr.diginamic.webmvc01.entities.Emprunt;
import fr.diginamic.webmvc01.entities.Livre;


/**
 * T : entities à gérer de la BDD
 * ID : type d'objet de ma KEY PRIMARY
 * comme j'hérite d'une interface spring : org.springframework.data.repository.CrudRepository
 * ça devient un Bean Spring
 * @author chris
 *
 */
public interface CrudLivreRepo extends CrudRepository<Livre, Integer> {
	

}
