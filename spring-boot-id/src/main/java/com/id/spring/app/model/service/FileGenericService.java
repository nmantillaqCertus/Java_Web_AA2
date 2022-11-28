package com.id.spring.app.model.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.id.spring.app.config.SpringConfig;
import com.id.spring.app.helper.IHelper;
import com.id.spring.app.model.ResponseFile;

@Component
public class FileGenericService implements IFileGeneric {

	@Autowired
	private SpringConfig rutaFiles;
	
	@Autowired
	private IHelper helper;
	
	@Override

	public ResponseFile guardarFile(MultipartFile genericFile) {
		ResponseFile responseFile = new ResponseFile();
		
		String NewNameFile = UUID.randomUUID().toString();		
		String extesionFile = org.springframework.util.StringUtils
				.getFilenameExtension(genericFile.getOriginalFilename());
		
		try {
			byte[] bytesImgPokemon = genericFile.getBytes();
			Path enlaceCompleto = Paths.get(rutaFiles.rutaGenrica() + "//" + NewNameFile + "." + extesionFile);
			Files.write(enlaceCompleto, bytesImgPokemon);
			
			responseFile.setEstado(true);
			responseFile.setNombreFile(NewNameFile + "." + extesionFile);
			
		} catch (IOException e) {
			responseFile.setEstado(false);
			responseFile.setNombreFile(NewNameFile + "." + extesionFile);
			responseFile.setMensajeError(e.getStackTrace().toString());
		}		
		return responseFile;
	}
	
	@Override
	public ResponseFile guardarFileAPI(String fileBase64,String nombreExtImagen) {
		ResponseFile responseFile = new ResponseFile();
		
		String NewNameFile = UUID.randomUUID().toString();
		Optional<Object> extesionFile = Optional.ofNullable(nombreExtImagen)
												.filter( f-> f.contains("."))
												.map(f->f.substring( nombreExtImagen.lastIndexOf(".")+1));
							
		try {
			byte[] bytesImgPokemon = helper.procesarFile(fileBase64);
			Path enlaceCompleto = Paths.get(rutaFiles.rutaGenrica() + "//" + NewNameFile + "." + extesionFile.get().toString());
			Files.write(enlaceCompleto, bytesImgPokemon);
			
			responseFile.setEstado(true);
			responseFile.setNombreFile(NewNameFile + "." + extesionFile.get().toString());
			
		} catch (IOException e) {
			responseFile.setEstado(false);
			responseFile.setNombreFile(NewNameFile + "." + extesionFile.get().toString());
			responseFile.setMensajeError(e.getStackTrace().toString());
		}		
		return responseFile;
	}
	
	@Override
	public ResponseFile eliminarFile(String nombreFile) {
		ResponseFile responseFile = new ResponseFile();
		
		try {
			Path rutaFileElimar = Paths.get(rutaFiles.rutaGenrica() + "\\" + nombreFile);
			File fileElimar = rutaFileElimar.toFile();
			if (fileElimar.exists()) {
				fileElimar.delete();				
				responseFile.setEstado(true);
				responseFile.setNombreFile(nombreFile);
			}else {
				responseFile.setEstado(false);
				responseFile.setNombreFile(nombreFile);
				responseFile.setMensajeError("El archivo "+nombreFile+"no se ha eliminado porque no existe.");
			}
			
		} catch (Exception e) {
			responseFile.setEstado(false);
			responseFile.setNombreFile(nombreFile);
			responseFile.setMensajeError(e.getStackTrace().toString());
		}
				
		return responseFile;
	}

}
