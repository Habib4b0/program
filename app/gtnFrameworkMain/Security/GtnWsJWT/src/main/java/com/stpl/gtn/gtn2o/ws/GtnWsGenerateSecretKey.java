package com.stpl.gtn.gtn2o.ws;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

public class GtnWsGenerateSecretKey {
	private static final String FILENAME = "filename.txt";

	public static void main(String as[]) {
		SecretKey secretKey = null;
		try {
			secretKey = KeyGenerator.getInstance("AES").generateKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String encode = Base64.encodeBase64String(secretKey.getEncoded());
		// try (BufferedWriter bufferedWriter = new BufferedWriter(new
		// FileWriter(FILENAME))) {
		// bufferedWriter.write(encode);
		// System.out.println(encode);
		// System.out.println("Done");
		//
		// } catch (IOException e) {
		//
		// e.printStackTrace();
		//
		// }
		File file = new File(FILENAME);
		try {
			FileUtils.writeStringToFile(file, encode);
			System.out.println("Done" + encode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
