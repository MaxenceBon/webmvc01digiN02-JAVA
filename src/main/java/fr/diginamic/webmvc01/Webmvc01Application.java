package fr.diginamic.webmvc01;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RestController
@CrossOrigin(
		origins="http://localhost:4200",
		allowedHeaders = {"Requestor-Type", "Authorization"},
		exposedHeaders = "X-Get-Header")
public class Webmvc01Application implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(Webmvc01Application.class, args);
	}
	/**
	 * Configuration pour le chargement des 
	 * messages Intenationaux
	 * messages.properties
	 * @return
	 */
	@Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = 
        		new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
	
	@GetMapping("/token")
	public Map<String, String> tokenMap(HttpSession session){
		return Collections.singletonMap("token", session.getId());
	}
}
