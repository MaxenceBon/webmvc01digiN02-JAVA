package fr.diginamic.webmvc01.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.diginamic.webmvc01.entities.Client;
import fr.diginamic.webmvc01.entities.Emprunt;
import fr.diginamic.webmvc01.entities.Livre;
import fr.diginamic.webmvc01.repository.CrudClientRepo;
import fr.diginamic.webmvc01.repository.CrudEmpruntRepo;

@Controller
@RequestMapping("/client")
public class ClientsController {
	@Autowired
	CrudClientRepo cr;
	
	@Autowired
	CrudEmpruntRepo er;
	
	public ClientsController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/clients")
	public String findall(Model model) {
		model.addAttribute("clients", (List<Client>) cr.findAll());
		model.addAttribute("cr", cr);
		model.addAttribute("er", er);
		model.addAttribute("titre","Liste des clients");
		return "clients/Liste";
	}
	
	@GetMapping("/add")
	public String getAdd(Model model) {
		model.addAttribute("clientForm", new Client() );
		model.addAttribute("titre","Ajout client");
		return "clients/add";
	}
	
	@PostMapping("/update")
	public String update(Model model,
			@Valid @ModelAttribute("clientForm") Client clientForm,
			BindingResult errors) throws Exception
	{
		if(errors.hasErrors())
		{
			throw( new Exception(errors.getObjectName()));
		}
		cr.save(clientForm);
		return "redirect:/client/clients";
	}
	
	@GetMapping("/update/{id}")
	public String getUpdate(@PathVariable("id") Integer pid,Model model) throws Exception {
		Optional<Client> c = cr.findById(pid);
		if(c.isEmpty()) {
			/**
			 * Je déclenche l'exception ClientError
			 * package fr.diginamic.Rest01.exceptions;
			 * Exception Fonctionnelle = métier
			 */
			throw( new Exception("Client id :"+pid+" non trouvé !"));
		}
		model.addAttribute("clientForm", c );
		model.addAttribute("titre","Modification du client");
		return "clients/update";
	} 
	 
	@PostMapping("/add")
	public String add(Model model,
			@Valid @ModelAttribute("clientForm") Client clientForm,
			BindingResult errors) throws Exception
	{
		if(errors.hasErrors())
		{
			throw( new Exception(errors.getObjectName()));
		}
		cr.save(clientForm);
		return "redirect:/client/clients";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer pid) throws Exception {
		Optional<Client> c = cr.findById(pid);
		if(c.isEmpty()) {
			/**
			 * Je déclenche l'exception ClientError
			 * package fr.diginamic.Rest01.exceptions;
			 * Exception Fonctionnelle = métier
			 */
			throw( new Exception("Client id :"+pid+" non trouvé !"));
		}
		/**
		 * Suppression des emprunts
		 * 	 */
		List<Emprunt> le = (List<Emprunt>) cr.findByEmprunt(pid);
		for(Emprunt e : le) {
			Iterable<Livre> il = er.findByLivre(e);
			for(Livre l : il) {
				l.getEmpruntLivres().clear();
			}
			er.deleteById(e.getId());
		}
		cr.deleteById(pid);
		return "redirect:/client/clients";
	}

}
