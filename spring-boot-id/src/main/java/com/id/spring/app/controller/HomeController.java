package com.id.spring.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.id.spring.app.model.service.IPokemonService;

@Controller
@RequestMapping("/app")
public class HomeController {
	
	@Value("${titlePage.param}")
	private String titlePage;
	
	@Value("${hey.param}")
	private String hey;
	
	//PokemonService pokemonService = new PokemonService(); // Clásico de Java
	
	//@Autowired 
	//PokemonService pokemonService; //Spring
	
	@Autowired 
	private IPokemonService IpService; // Spring - Buenas prácticas
	
	
	
	@GetMapping("/home")
	public String Home(Model model) {
		
		model.addAttribute("titlePage", titlePage);
		model.addAttribute("hey", hey);
		model.addAttribute("ListaPokemon", IpService.quienEsEsePokemon2022());
				
		return "home";
	}

}
