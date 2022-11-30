package com.id.spring.app.helper;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.springframework.stereotype.Service;

@Service
public class Helper implements IHelper {

	@Override
	public byte[] procesarFile(String fileBase64) {				
		byte[] FileProcesado = null;		
		try {
			FileProcesado = Base64.getDecoder().decode( new String(fileBase64).getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return FileProcesado;
	}

}
