package com.id.spring.app.model.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.id.spring.app.config.SpringConfig;
import com.id.spring.app.intefaces.PokemonDAO;
import com.id.spring.app.model.Pokemon;
import com.id.spring.app.model.Response;

@Component
public class PokemonService implements IPokemonService {

	@Autowired
	private PokemonDAO pokemonRepository;

	@Autowired
	private SpringConfig rutaFiles;

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

		try {

			if (!filePokemon.isEmpty()) {
				try {

					if (pokemoncito.getUriImagen() != null) {

						Path rutaFileElimar = Paths.get(rutaFiles.rutaGenrica() + "\\" + pokemoncito.getUriImagen());
						File fileElimar = rutaFileElimar.toFile();
						if (fileElimar.exists()) {
							fileElimar.delete();
						}
					}

					String NewNameFile = UUID.randomUUID().toString();
					String extesionFile = org.springframework.util.StringUtils
							.getFilenameExtension(filePokemon.getOriginalFilename());

					byte[] bytesImgPokemon = filePokemon.getBytes();
					Path enlaceCompleto = Paths.get(rutaFiles.rutaGenrica() + "//" + NewNameFile + "." + extesionFile);
					Files.write(enlaceCompleto, bytesImgPokemon);

					pokemoncito.setUriImagen(NewNameFile + "." + extesionFile);

				} catch (IOException e) {
					response.setEstado(false);
					response.setMensaje("IMG-ERROR");
					response.setMensajeError(e.getStackTrace().toString());
					return response;
				}
			}

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

			if (p.get() != null) {

				if (p.get().getUriImagen() != null) {

					Path rutaFileElimar = Paths.get(rutaFiles.rutaGenrica() + "\\" + p.get().getUriImagen());
					File fileElimar = rutaFileElimar.toFile();

					if (fileElimar.exists()) {
						fileElimar.delete();
					}
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
