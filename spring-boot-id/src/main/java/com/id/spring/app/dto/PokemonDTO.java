package com.id.spring.app.dto;

public class PokemonDTO {
	private int idPokemon;	
	private String nombre;	
	private String categoria;	
	private String tipo;
	private String habilidad;
	private String nivelPoder;
	private String uriImagen;
	private String nombreExtImagen;
	private String fileBase64;
		
	public String getNombreExtImagen() {
		return nombreExtImagen;
	}
	public void setNombreExtImagen(String nombreExtImagen) {
		this.nombreExtImagen = nombreExtImagen;
	}
	public int getIdPokemon() {
		return idPokemon;
	}
	public void setIdPokemon(int idPokemon) {
		this.idPokemon = idPokemon;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
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
	public String getUriImagen() {
		return uriImagen;
	} 
	public void setUriImagen(String uriImagen) {
		this.uriImagen = uriImagen;
	}
	public String getFileBase64() {
		return fileBase64;
	}
	public void setFileBase64(String fileBase64) {
		this.fileBase64 = fileBase64;
	}
	
	

}
