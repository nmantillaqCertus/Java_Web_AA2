package com.id.spring.app.model.service;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.id.spring.app.dto.PokemonDTO;
import com.id.spring.app.model.Pokemon;
import com.id.spring.app.model.Response;
import com.id.spring.app.model.ResponseFile;
import com.id.spring.app.repository.PokemonDAO;

@Component
public class PokemonService implements IPokemonService {

	@Autowired
	private PokemonDAO pokemonRepository;
		
	@Autowired
	private IFileGeneric fileGeneric;

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

	public Response<Pokemon> CrearPokemon(Pokemon pokemoncito, MultipartFile filePokemon) {

		Response<Pokemon> response = new Response<>();
		ResponseFile responseFile = new ResponseFile();

		try {
			if (filePokemon!= null) {
				
				if (pokemoncito.getUriImagen() != null) { //Actualización de archivo
					fileGeneric.eliminarFile(pokemoncito.getUriImagen());						
				}
				
				responseFile = fileGeneric.guardarFile(filePokemon);
				if (responseFile.getEstado()) {
					pokemoncito.setUriImagen(responseFile.getNombreFile());
				}else {
					response.setEstado(responseFile.getEstado());
					response.setMensaje("IMG-ERROR - error al procesar el archivo "+responseFile.getNombreFile());
					response.setMensajeError(responseFile.getMensajeError());
					return response;
				}
			}

			Pokemon p = pokemonRepository.save(pokemoncito);
			response.setEstado(true);
			response.setData(p);
			response.setListData((List<Pokemon>) pokemonRepository.findAll());
			
			response.setMensaje("Se creó correctamente el pokemon: " + p.getNombre());

		} catch (Exception e) {
			response.setEstado(false);
			response.setMensaje("Ocurrió un error guardar/actualizar los pokemones");
			response.setMensajeError(e.getStackTrace().toString());
		}
		return response;
	}

	
	public Response<Pokemon> CrearPokemonAPI(PokemonDTO pokemoncito) {

		Response<Pokemon> response = new Response<>();
		ResponseFile responseFile = new ResponseFile();
		Pokemon pk = new Pokemon();

		try {
			if (!pokemoncito.getFileBase64().isEmpty()) {
				
				if (pokemoncito.getUriImagen() != null) {
					fileGeneric.eliminarFile(pokemoncito.getUriImagen());						
				}
				
				responseFile = fileGeneric.guardarFileAPI(pokemoncito.getFileBase64(), pokemoncito.getNombreExtImagen());
				if (responseFile.getEstado()) {
					pokemoncito.setUriImagen(responseFile.getNombreFile());
				}else {
					response.setEstado(responseFile.getEstado());
					response.setMensaje("IMG-ERROR - error al procesar el archivo "+responseFile.getNombreFile());
					response.setMensajeError(responseFile.getMensajeError());
					return response;
				}
			}
			
			pk.setIdPokemon(pokemoncito.getIdPokemon());
			pk.setNombre(pokemoncito.getNombre());
			pk.setCategoria(pokemoncito.getCategoria());
			pk.setTipo(pokemoncito.getTipo());
			pk.setHabilidad(pokemoncito.getHabilidad());
			pk.setNivelPoder(pokemoncito.getNivelPoder());
			pk.setUriImagen(pokemoncito.getUriImagen());

			Pokemon p = pokemonRepository.save(pk);
			
			response.setEstado(true);
			response.setData(p);
			response.setListData((List<Pokemon>) pokemonRepository.findAll());			
			response.setMensaje("Se creó correctamente el pokemon: " + p.getNombre());

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

			if (p.get() != null) {
				if (p.get().getUriImagen() != null) {					
					fileGeneric.eliminarFile(p.get().getUriImagen());	
				}

				pokemonRepository.deleteById(id);
				response.setEstado(true);
				response.setData(p.get());				
				response.setMensaje("El pokemon " + p.get().getNombre() + " ha sido eliminado");
			} else {
				response.setEstado(false);
				response.setMensaje("El pokemon a eliminar no existe");
			}

		} catch (Exception e) {
			response.setEstado(false);
			response.setMensaje("Ocurrió un error al eliminar el pokemon");
			response.setMensajeError(e.getStackTrace().toString());
		}
		return response;
	}

}
