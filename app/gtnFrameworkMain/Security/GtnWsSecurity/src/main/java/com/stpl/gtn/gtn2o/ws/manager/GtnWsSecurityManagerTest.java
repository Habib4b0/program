package com.stpl.gtn.gtn2o.ws.manager;

import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnWsSecurityManagerTest {
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsSecurityManagerTest.class);

	public static void main(String[] args) {

		String userId = "670";
		String sessionId = "100";
		GtnWsSecurityManager securityManager = new GtnWsSecurityManager();
		String token = securityManager.createToken(userId, sessionId);
		LOGGER.info(token);
	}
}
