package com.id.spring.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.id.spring.app.model.service.IPokemonService;

@Controller
@RequestMapping("/app")
public class HomeController {
	
	@Value("${titlePage.param}")
	private String titlePage;
	
		
	@Autowired 
	private IPokemonService IpService;
		
	@GetMapping("/home")
	public String Home(Model model) {
		
		model.addAttribute("titlePage", titlePage);
		model.addAttribute("titulo", "Iniciando con Spring Boot");
		model.addAttribute("ListaPokemon", IpService.ObtenerListaPokemon());
				
		return "home";
	}
	
	@GetMapping("/form")
	public String Formulario(Model model) {
		
		//creandoi el pokemon
		//IpService.CrearPokemon(Pokemon pokemon)
		
		model.addAttribute("titlePage", titlePage);
		model.addAttribute("titulo", "Formulario con Spring Boot");
				
		return "formulario";
	}
	
	@PostMapping("/form")
	public String CrearFormulario(Model model) {
		
		//creandoi el pokemon
		//IpService.CrearPokemon(Pokemon pokemon)
		
		model.addAttribute("titlePage", titlePage);
		model.addAttribute("titulo", "Formulario con Spring Boot");
				
		return "home";
	}

}
