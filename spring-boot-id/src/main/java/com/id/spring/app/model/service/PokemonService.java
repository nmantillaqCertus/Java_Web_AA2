package com.id.spring.app.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.id.spring.app.model.Pokemon;


@Component
public class PokemonService implements IPokemonService{

	@Override
	public List<Pokemon> ObtenerListaPokemon() {
		//Creando nueva instanci de un pokemon (pokemoncito)
		Pokemon pokemoncito = new Pokemon();
		Pokemon pokemoncito2 = new Pokemon();
		Pokemon pokemoncito3 = new Pokemon();
		
		List<Pokemon> listaPokemones = new ArrayList<>();
		
		pokemoncito.setNombre("Pikachu 2023");
		pokemoncito.setTipo("Eléctrico");
		pokemoncito.setHabilidad("Ataque de trueno");
		pokemoncito.setNivelPoder("5000");
		
		pokemoncito2.setNombre("Pikachu 2025");
		pokemoncito2.setTipo("Eléctrico");
		pokemoncito2.setHabilidad("Ataque de trueno");
		pokemoncito2.setNivelPoder("15000");
		
		pokemoncito3.setNombre("Pikachu 2030");
		pokemoncito3.setTipo("Eléctrico");
		pokemoncito3.setHabilidad("Ataque de trueno");
		pokemoncito3.setNivelPoder("50000");
		
		listaPokemones.add(pokemoncito);
		listaPokemones.add(pokemoncito2);
		listaPokemones.add(pokemoncito3);
		
		return listaPokemones;
	}
	
	@Override
	public String CrearPokemon(Pokemon pokemoncito) {
		
		//Codigo de envio a la DB
		
		return "Guardado Correctamente";
	}
	
	


}
