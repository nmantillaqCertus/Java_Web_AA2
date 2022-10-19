package com.id.spring.app.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
public class Pokemon {
	
	private String idPokemon;
		
	@NotEmpty (message = "Completar el nombre del pokemon")
	private String nombre;	
	private String categoria;
	
	@Size(min = 2, max = 50, message = "Mínimo 5 y máximo 50 caracteres")
	@NotEmpty(message = "Completar el tipo del pokemon")	
	private String tipo;
	
	@NotEmpty(message = "Completar la habilidad del pokemon")
	private String habilidad;
	private String nivelPoder;
	private String uriImagen;
	
			
	public String getIdPokemon() {
		return idPokemon;
	}

	public void setIdPokemon(String idPokemon) {
		this.idPokemon = idPokemon;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getUriImagen() {
		return uriImagen;
	}

	public void setUriImagen(String uriImagen) {
		this.uriImagen = uriImagen;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getHabilidad() {
		return habilidad;
	}
	public void setHabilidad(String habilidad) {
		this.habilidad = habilidad;
	}
	public String getNivelPoder() {
		return nivelPoder;
	}
	public void setNivelPoder(String nivelPoder) {
		this.nivelPoder = nivelPoder;
	}

}
