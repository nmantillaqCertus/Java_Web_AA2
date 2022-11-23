package com.id.spring.app.model;

public class ResponseFile {
	private Boolean estado;
	private String nombreFile;
	private String mensajeError;
	
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public String getNombreFile() {
		return nombreFile;
	}
	public void setNombreFile(String nombreFile) {
		this.nombreFile = nombreFile;
	}
	public String getMensajeError() {
		return mensajeError;
	}
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
}
