package fr.diginamic.webmvc01.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import fr.diginamic.webmvc01.services.UserService;

public class AppAuthProvider extends DaoAuthenticationProvider {

	@Autowired
	UserService userDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		// Controler l'objet authentication : on aura le contenu de la saisie du login et pws du formulaire
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
		
		String name = auth.getName();
		String password = auth.getCredentials().toString();
		
		UserDetails user = userDetailsService.loadUserByUsername(name);
		if(user == null) {
			throw new BadCredentialsException("Username/Password doesn't match for " + auth.getPrincipal());
		}
		if(!password.equals(user.getPassword())) {
			throw new BadCredentialsException("Username/Password doesn't match for " + auth.getPrincipal());
		}
		
		return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}
	
}
