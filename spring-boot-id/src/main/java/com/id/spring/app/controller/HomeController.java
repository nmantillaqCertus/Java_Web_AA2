package com.id.spring.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.id.spring.app.model.Pokemon;
import com.id.spring.app.model.Response;
import com.id.spring.app.model.service.IPokemonService;

@Controller
@RequestMapping("/app")
@SessionAttributes("pokemon")
public class HomeController {

	@Value("${titlePage.param}")
	private String titlePage;

	@Autowired
	private IPokemonService IpService;

	@GetMapping("/home")
	public String Home(Model model) {
		model.addAttribute("titlePage", titlePage);
		model.addAttribute("titulo", "Spring Framework - Pokemon");
		model.addAttribute("Mensaje", "Cursos con alumnos del B78");

		return "home";
	}

	@GetMapping("/listar")
	public String ListarPokemon(Model model) {
		model.addAttribute("titlePage", titlePage);
		model.addAttribute("resumen", "Lista de los mejores pokemones");
		Response<Pokemon> response = IpService.ObtenerListaPokemon();

		if (response.getEstado()) {
			model.addAttribute("titulo", "Spring Framework - Lista de Pokemon");
			model.addAttribute("ListaPokemon", response.getListData());
			model.addAttribute("respuesta", response.getMensaje());
			return "lista";
		} else {
			model.addAttribute("titulo", "Spring Framework - Control de Errores");
			model.addAttribute("respuesta", response.getMensaje());
			model.addAttribute("errores", response.getMensajeError());
			return "errores";
		}
	}

	@GetMapping("/form")
	public String Formulario(Model model) {

		Pokemon pokemoncito = new Pokemon();
		pokemoncito.setIdPokemon(123);
		pokemoncito.setNivelPoder("" + Math.random());

		model.addAttribute("titlePage", titlePage);
		model.addAttribute("titulo", "Spring Framework - Registro de Pokemon");

		model.addAttribute("pokemon", pokemoncito);

		return "formulario";
	}

	@PostMapping("/Nuevoform")
	public String CrearFormulario(
			@Validated Pokemon pokemoncito, 
			BindingResult br, 
			Model model, 
			@RequestParam("fileImagen") MultipartFile imgPokemon,  //EN ARCHIVO
			SessionStatus status) {
		if (br.hasErrors()) {
			return "formulario";
		}
		
		//Logica de file Pokemon
		if (!imgPokemon.isEmpty()) {
			Path rutaImagenesPokemon =  Paths.get("src//main//resources//static//Img_Pokemon");
			String pathGeneric = rutaImagenesPokemon.toFile().getAbsolutePath();
						
			try {
				byte[] bytesImgPokemon = imgPokemon.getBytes();
				Path enlaceCompleto = Paths.get(pathGeneric+"//"+imgPokemon.getOriginalFilename());
				Files.write(enlaceCompleto, bytesImgPokemon);
				
				pokemoncito.setUriImagen(imgPokemon.getOriginalFilename());
				
				
			} catch (IOException e) {
				model.addAttribute("titulo", "Spring Framework - Control de Errores");
				model.addAttribute("respuesta", "Ocurrió un error al procesar el archivo");
				model.addAttribute("errores", e.getStackTrace());
				
				return "errores";
			}
			
			
		}
		
		
		model.addAttribute("titlePage", titlePage);
		Response<Pokemon> response = IpService.CrearPokemon(pokemoncito);

		if (response.getEstado()) {
			model.addAttribute("titulo", "Spring Framework - Lista de Pokemon");
			model.addAttribute("ListaPokemon", response.getListData());
			model.addAttribute("respuesta", response.getMensaje());
			model.addAttribute("resumen", "Lista de los mejores pokemones");

			status.setComplete();
			return "lista";
		} else {
			model.addAttribute("titulo", "Spring Framework - Control de Errores");
			model.addAttribute("respuesta", response.getMensaje());
			model.addAttribute("errores", response.getMensajeError());

			status.setComplete();
			return "errores";
		}
	}

	@GetMapping("/Editar/{id}")
	public String editarPokemon(@PathVariable int id, Model model) {
		
		model.addAttribute("titlePage", titlePage);
		Response<Pokemon> response = IpService.EditarPokemon(id);

		if (response.getEstado()) {
			model.addAttribute("titulo", "Spring Framework - "+response.getMensaje());			
			model.addAttribute("pokemon", response.getData());
			model.addAttribute("respuesta", "Se actualizó el pokemon");
			return "formulario";
		} else {
			model.addAttribute("titulo", "Spring Framework - Control de Errores");
			model.addAttribute("respuesta", response.getMensaje());
			model.addAttribute("errores", response.getMensajeError());

			return "errores";
		}
	}

	@GetMapping("/Eliminar/{id}")
	public String eliminarPokemon(@PathVariable int id, Model model) {
		
		model.addAttribute("titlePage", titlePage);
		Response<Pokemon> response = IpService.EliminarPokemon(id);

		if (response.getEstado()) {
			model.addAttribute("titulo", "Spring Framework - "+response.getMensaje());	
			model.addAttribute("respuesta", response.getMensaje());
			return "redirect:/app/listar";
		} else {
			model.addAttribute("titulo", "Spring Framework - Control de Errores");
			model.addAttribute("respuesta", response.getMensaje());
			model.addAttribute("errores", response.getMensajeError());

			return "errores";
		}		
	}

	@GetMapping("/errores")
	public String Errores(Model model) {

		model.addAttribute("titlePage", titlePage);
		model.addAttribute("titulo", "Spring Framework - Control de Errores");

		model.addAttribute("respuesta", "");
		model.addAttribute("errores", "");

		return "errores";
	}

}
