package com.stpl.gtn.gtn2o.ws.service.jwt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.stpl.gtn.gtn2o.ws.GtnFileNameUtils;
import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.service.property.GtnWsSecurityPropertyReaderService;

public class GtnWsSecuritySecretKey {
	private static volatile String secretKey = getsecretkey();
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsSecuritySecretKey.class);

	private GtnWsSecuritySecretKey() {
		super();
	}

	public static String getsecretkey() {

		if (secretKey == null) {
			try (BufferedReader bufferedReader = new BufferedReader(
					new FileReader(GtnFileNameUtils.getFile(GtnFrameworkPropertyManager.getGtnFrameWorkBasePath()
							+ GtnWsSecurityPropertyReaderService.getProperyBean().getFilename())))) {
				readSecretKey(bufferedReader);
				return secretKey;
			} catch (FileNotFoundException e1) {
				LOGGER.error("Secret key file not found  - ", e1);
			} catch (IOException e1) {
				LOGGER.error("I/O exception in reading Secret file  - ", e1);
			}

		}

		return secretKey;
	}

	private static void readSecretKey(BufferedReader bufferedReader) {
		try {
			secretKey = bufferedReader.readLine();
		} catch (Exception e) {
			LOGGER.error("Exception in reading Key - ", e);
		}
	}
}