package com.stpl.gtn.gtn2o.ws.service.security;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Base64;

public class GtnWsGenerateSecretKeyService {

	public void generateSecretKey(String fileName) throws IOException {

		File file = new File(fileName);

		try (FileOutputStream fout = new FileOutputStream(file)) {
			SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
			String encode = Base64.encodeBase64String(secretKey.getEncoded());
			byte[] b = encode.getBytes();
			fout.write(b);
		} catch (Exception e) {
			return;
		}

	}

	public static void main(String[] args) throws IOException {

		GtnWsGenerateSecretKeyService service = new GtnWsGenerateSecretKeyService();
		service.generateSecretKey("D:\\test.txt");
	}
}
