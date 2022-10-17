package com.id.spring.app.model;

import javax.validation.constraints.NotEmpty;
public class Pokemon {
	
	@NotEmpty (message = "no debe ser vacío")
	private String nombre;
	
	@NotEmpty (message = "no debe ser vacío")
	private String categoria;
	
	@NotEmpty (message = "no debe ser vacío")
	private String tipo;
	
	@NotEmpty (message = "no debe ser vacío")	
	private String habilidad;
	
	@NotEmpty (message = "no debe ser vacío")
	private String nivelPoder;
	
	private String uriImagen;
	
	
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
