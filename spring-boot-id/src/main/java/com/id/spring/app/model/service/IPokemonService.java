package com.id.spring.app.model.service;


import org.springframework.web.multipart.MultipartFile;

import com.id.spring.app.dto.PokemonDTO;
import com.id.spring.app.model.Pokemon;
import com.id.spring.app.model.Response;

public interface IPokemonService {
	
	public Response<Pokemon> ObtenerListaPokemon();
	
	public Response<Pokemon> CrearPokemon(Pokemon pokemoncito , MultipartFile filePokemon);
	
	public Response<Pokemon> CrearPokemonAPI(PokemonDTO pokemoncito);
	
	public Response<Pokemon> EditarPokemon(Integer id);
	
	public Response<Pokemon> EliminarPokemon(Integer id);
}
