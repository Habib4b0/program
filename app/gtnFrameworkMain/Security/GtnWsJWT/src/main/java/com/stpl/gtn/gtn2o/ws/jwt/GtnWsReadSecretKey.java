package com.stpl.gtn.gtn2o.ws.jwt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GtnWsReadSecretKey {
	private static final String FILENAME = "E:\\filename.txt";

	public String getsecretkey() {
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

			String secretkey;

			while ((secretkey = br.readLine()) != null) {
				return secretkey;

			}

		} catch (IOException e) {
			System.out.println("asfj");
		}
		return null;
	}

}
