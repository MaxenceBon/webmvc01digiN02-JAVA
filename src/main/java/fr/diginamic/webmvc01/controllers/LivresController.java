package fr.diginamic.webmvc01.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.diginamic.webmvc01.entities.Client;
import fr.diginamic.webmvc01.entities.Emprunt;
import fr.diginamic.webmvc01.entities.Livre;
import fr.diginamic.webmvc01.repository.CrudEmpruntRepo;
import fr.diginamic.webmvc01.repository.CrudLivreRepo;

@Controller
@RequestMapping("/livre")
public class LivresController {
	@Autowired
	CrudLivreRepo lr;
	
	@Autowired
	CrudEmpruntRepo er;
	
	public LivresController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/livres")
	public String findall(Model model) {
		model.addAttribute("livres", (List<Livre>) lr.findAll());
		model.addAttribute("titre","Liste des Livres");
		return "livres/Liste";
	}
	@GetMapping("/add")
	public String addT(Model model) {
		model.addAttribute("livres", (List<Livre>) lr.findAll());
		model.addAttribute("titre","Liste des Livres");
		return "livres/Liste";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer pid) throws Exception {
		Optional<Livre> c = lr.findById(pid);
		if(c.isEmpty()) {
			/**
			 * Je déclenche l'exception ClientError
			 * package fr.diginamic.Rest01.exceptions;
			 * Exception Fonctionnelle = métier
			 */
			throw( new Exception("Livre id :"+pid+" non trouvé !"));
		}
		/**
		 * Je supprimer tous les emprunts liés aux livres pour
		 * pouvoir supprimer mon livre
		 */
		/**
		 * Suppression du lien avant de supprimer un livre
		 * c.get() : le livre en cours
		 */
		c.get().getEmpruntLivres().clear();
		//Libére les liens entre livre et emprunt et ça supprime dans la table
		//COMPO.
		/*
		 * for(Emprunt e : c.get().getEmpruntLivres()) {
			er.deleteById(e.getId());
		}
		*/
		lr.deleteById(pid); //Suprimer physiquement mon livre
		return "redirect:/livre/livres";
	}
}
