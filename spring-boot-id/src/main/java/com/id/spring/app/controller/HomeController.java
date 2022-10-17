package com.id.spring.app.controller;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.id.spring.app.model.Pokemon;
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
		
		model.addAttribute("titlePage", titlePage);
		model.addAttribute("titulo", "Formulario con Spring Boot");
				
		return "formulario";
	}
	
	@PostMapping("/Nuevoform")
	public String CrearFormulario(@Validated Pokemon pokemoncito, BindingResult br ,Model model) {
		
		if(br.hasErrors()) {
			Map<String, String> MapErrores =  new HashMap<>();
			br.getFieldErrors().forEach(error -> {				
				String ClaveCampo = error.getField();
				String ValorError = "El campo ".concat(ClaveCampo).concat(" ").concat(error.getDefaultMessage());
				
				MapErrores.put(ClaveCampo, ValorError);
			});			
			model.addAttribute("Errores", MapErrores);			
			return "home";
		}
		
		List<Pokemon> pokemonX = new ArrayList<>();
		pokemonX.add(pokemoncito);
		
		String respuesta = IpService.CrearPokemon(pokemoncito);
				
		model.addAttribute("titlePage", titlePage);
		model.addAttribute("titulo", "Formulario con Spring Boot");
		model.addAttribute("ListaPokemon", pokemonX);
		
		model.addAttribute("respuesta", respuesta);
		
		
		return "home";
		
	}

}
