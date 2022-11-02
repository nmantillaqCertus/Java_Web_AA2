package com.id.spring.app.model.service;

import java.util.List;
import java.util.Optional;

import com.id.spring.app.model.Pokemon;

public interface IPokemonService {
	
	public List<Pokemon> ObtenerListaPokemon();
	
	public String CrearPokemon(Pokemon pokemoncito );
	
	public Optional<Pokemon> EditarPokemon(Integer id);
}
