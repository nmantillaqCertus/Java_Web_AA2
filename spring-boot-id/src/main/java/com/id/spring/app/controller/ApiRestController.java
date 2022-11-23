package com.id.spring.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.id.spring.app.model.Pokemon;
import com.id.spring.app.model.Response;
import com.id.spring.app.model.service.IPokemonService;

@RestController
@RequestMapping("/api")
public class ApiRestController {
	
	@Autowired
	private IPokemonService IpService;
	
	@GetMapping("/listar")
	public Response<Pokemon> listarPokemon(){				
		Response<Pokemon> resPokemon = IpService.ObtenerListaPokemon();
		return resPokemon;
	}
	
	@PutMapping("/crear")
	public Response<Pokemon> crearPokemon(@RequestBody Pokemon pokemon){	
		Response<Pokemon> response = new Response<>();
		try {
			response = IpService.CrearPokemon(pokemon, null);			
		} catch (Exception e) {
			response.setEstado(false);
			response.setMensaje("Se produjo un erro al intentar crear el pokemon");
			response.setMensajeError(e.getMessage());
		}		
		return response;
	}
	
	@PutMapping("/actualizar/{id}")
	public Response<Pokemon> actualizarPokemon(@RequestBody Pokemon pokemon, @PathVariable int id){	
		Response<Pokemon> response = new Response<>();
		try {
			pokemon.setIdPokemon(IpService.EditarPokemon(id).getData().getIdPokemon());
			response = IpService.CrearPokemon(pokemon, null);			
		} catch (Exception e) {
			response.setEstado(false);
			response.setMensaje("Se produjo un erro al intentar actualizar el pokemon");
			response.setMensajeError(e.getMessage());
		}
		
		return response;
	} 
	
	@DeleteMapping("/eliminar/{idPokemonEliminar}")
	public Response<Pokemon> eliminarPokemon(@PathVariable int idPokemonEliminar){		
		Response<Pokemon> response = IpService.EliminarPokemon(idPokemonEliminar);	
		return response;
	}
	

}
