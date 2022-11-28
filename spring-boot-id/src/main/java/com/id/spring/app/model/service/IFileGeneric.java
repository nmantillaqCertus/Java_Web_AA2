package com.id.spring.app.model.service;

import org.springframework.web.multipart.MultipartFile;

import com.id.spring.app.model.ResponseFile;


public interface IFileGeneric {
	
	public ResponseFile guardarFile(MultipartFile genericFile);	
	
	public ResponseFile guardarFileAPI(String fileBase64,String nombreExtImagen);
	
	public ResponseFile eliminarFile(String nombreFile);

}
