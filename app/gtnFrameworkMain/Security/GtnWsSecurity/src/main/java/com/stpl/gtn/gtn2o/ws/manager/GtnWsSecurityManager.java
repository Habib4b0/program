package com.stpl.gtn.gtn2o.ws.manager;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.service.jwt.GtnWsSecurityJWTService;
import com.stpl.gtn.gtn2o.ws.service.property.GtnWsSecurityPropertyReaderService;

import io.jsonwebtoken.Claims;

public class GtnWsSecurityManager {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsSecurityManager.class);
	

	public GtnWsSecurityManager() {
	}


	public String createToken(String userId, String sessionId) {

		GtnWsSecurityToken gtnWsSecurityToken = new GtnWsSecurityToken();
		GtnWsSecurityJWTService gtnWsJWtService = new GtnWsSecurityJWTService();
		gtnWsSecurityToken.setUserId(userId);
		gtnWsSecurityToken.setSessionId(sessionId);
		String jwtToken = gtnWsJWtService.createJWT(sessionId, userId,
				GtnWsSecurityPropertyReaderService.getProperyBean().getExpirationTime());
		gtnWsSecurityToken.setGtnToken(jwtToken);
		return gtnWsSecurityToken.getGtnToken();

	}

	public boolean verifyToken(String userId, String sessionId, String gtnToken) {
		try {

			GtnWsSecurityJWTService gtnWsJWtService = new GtnWsSecurityJWTService();

			Claims claims = parseJwtToken(gtnToken, gtnWsJWtService);

			if (claims == null) {
				return false;
			}
			if (!(claims.getId().equals(sessionId))) {
				return false;
			}

			return claims.getIssuer().equals(userId);
		} catch (Exception e) {
			return false;
		}

	}

	private Claims parseJwtToken(String gtnWsSecurityToken, GtnWsSecurityJWTService gtnWsJWtService) {
		Claims claims = null;
		try {
			claims = gtnWsJWtService.parseJWT(gtnWsSecurityToken);
		} catch (Exception e) {
			logger.error("Exception in parsing JWT ", e);
		}
		return claims;
	}

}