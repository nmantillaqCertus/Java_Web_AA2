package com.id.spring.app.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.id.spring.app.intefaces.PokemonDAO;
import com.id.spring.app.model.Pokemon;
import com.id.spring.app.model.Response;

@Component
public class PokemonService implements IPokemonService {

	@Autowired
	private PokemonDAO pokemonRepository;

	@Override
	public Response<Pokemon> ObtenerListaPokemon() {

		Response<Pokemon> response = new Response<>();
		try {
			response.setMensaje("Los mejores pokemones :D");
			response.setEstado(true);
			response.setListData((List<Pokemon>) pokemonRepository.findAll());

		} catch (Exception e) {
			response.setEstado(false);
			response.setMensaje("Ocurrió un error al obtener los pokemones");
			response.setMensajeError(e.getStackTrace().toString());
		}
		return response;
	}

	public Response<Pokemon> CrearPokemon(Pokemon pokemoncito) {

		Response<Pokemon> response = new Response<>();

		try {
			Pokemon p = pokemonRepository.save(pokemoncito);
			response.setEstado(true);
			response.setData(p);
			response.setListData((List<Pokemon>) pokemonRepository.findAll());
			response.setMensaje("Se creó correctamente el " + p.getNombre());

		} catch (Exception e) {
			response.setEstado(false);
			response.setMensaje("Ocurrió un error guardar/actualizar los pokemones");
			response.setMensajeError(e.getStackTrace().toString());
		}
		return response;
	}

	@Override
	public Response<Pokemon> EditarPokemon(Integer id) {
		Response<Pokemon> response = new Response<>();

		try {
			Optional<Pokemon> p = pokemonRepository.findById(id);

			response.setEstado(true);
			response.setData(p.get());
			response.setMensaje("Actualizando el pokemon " + p.get().getNombre());

		} catch (Exception e) {
			response.setEstado(false);
			response.setMensaje("Ocurrió un error actualizar el pokemon");
			response.setMensajeError(e.getStackTrace().toString());
		}
		return response;
	}

	@Override
	public Response<Pokemon> EliminarPokemon(Integer id) {	
		Response<Pokemon> response = new Response<>();
		
		try {	
			Optional<Pokemon> p = pokemonRepository.findById(id);
			pokemonRepository.deleteById(id);
			//Implementar logica de validación si existe o no el pokemon Alumnos B78			
			response.setEstado(true);
			response.setData(p.get());
			response.setMensaje("El pokemon "+p.get().getNombre()+" ha sido eliminado");
		}catch (Exception e) {
			response.setEstado(false);
			response.setMensaje("Ocurrió un error al eliminar el pokemon");
			response.setMensajeError(e.getStackTrace().toString());	
		}		
		return response;
	}

}
