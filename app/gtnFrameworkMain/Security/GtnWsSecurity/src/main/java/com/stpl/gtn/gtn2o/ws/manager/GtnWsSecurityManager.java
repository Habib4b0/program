package com.stpl.gtn.gtn2o.ws.manager;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.service.jwt.GtnWsSecurityJWTService;
import com.stpl.gtn.gtn2o.ws.service.property.GtnWsSecurityPropertyReaderService;
import com.stpl.gtn.gtn2o.ws.service.security.GtnWsSecurityService;

import io.jsonwebtoken.Claims;

public class GtnWsSecurityManager {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsSecurityManager.class);

	private GtnWsSecurityService gtnWsSecurityService = null;

	public GtnWsSecurityManager() {
		this.gtnWsSecurityService = new GtnWsSecurityService();
	}

	public GtnWsSecurityService getGtnSecurityService() {
		return gtnWsSecurityService;
	}

	public void setGtnSecurityService(GtnWsSecurityService gtnWsSecurityService) {
		this.gtnWsSecurityService = gtnWsSecurityService;
	}

	public String createToken(String userId, String sessionId) {

		String token = checkCachedToken(userId, sessionId);
		if (token == null) {
			GtnWsSecurityToken gtnWsSecurityToken = new GtnWsSecurityToken();
			GtnWsSecurityJWTService gtnWsJWtService = new GtnWsSecurityJWTService();
			gtnWsSecurityToken.setUserId(userId);
			gtnWsSecurityToken.setSessionId(sessionId);
			String jwtToken = gtnWsJWtService.createJWT(sessionId, userId,
					GtnWsSecurityPropertyReaderService.getProperyBean().getExpirationTime());
			gtnWsSecurityToken.setGtnToken(jwtToken);
			gtnWsSecurityService.insertSecurityToken(gtnWsSecurityToken);
			token = gtnWsSecurityToken.getGtnToken();
		}

		return token;

	}

	public boolean verifyToken(String userId, String sessionId, String gtnToken) {
		try {
			GtnWsSecurityToken gtnWsSecurityToken = gtnWsSecurityService.getSecurityToken(userId, sessionId);

			if (gtnWsSecurityToken == null) {
				return false;
			}

			if ((gtnToken == null) || (gtnWsSecurityToken.getGtnToken() == null)) {
				return false;
			}

			if (gtnToken.equals(gtnWsSecurityToken.getGtnToken())) {

				GtnWsSecurityJWTService gtnWsJWtService = new GtnWsSecurityJWTService();

				Claims claims = parseJwtToken(gtnWsSecurityToken, gtnWsJWtService);

				if (claims == null) {
					return false;
				}
				if (!(claims.getId().equals(sessionId))) {
					return false;
				}

				return claims.getIssuer().equals(userId);
			}
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	private Claims parseJwtToken(GtnWsSecurityToken gtnWsSecurityToken, GtnWsSecurityJWTService gtnWsJWtService) {
		Claims claims = null;
		try {
			claims = gtnWsJWtService.parseJWT(gtnWsSecurityToken.getGtnToken());
		} catch (Exception e) {
			logger.error("Exception in parsing JWT ", e);
		}
		return claims;
	}

	public void deleteToken(String userId, String sessionId) {
		gtnWsSecurityService.deleteSecurityToken(userId, sessionId);

	}

	public String checkCachedToken(String userId, String sessionId) {
		GtnWsSecurityToken gtnWsSecurityToken = gtnWsSecurityService.getSecurityToken(userId, sessionId);
		if (gtnWsSecurityToken == null) {
			return null;
		}
		long timeDifference = gtnWsSecurityService.getTimeDifferenceInMilliSecond(gtnWsSecurityToken.getCreationDate());
		if (timeDifference < (GtnWsSecurityPropertyReaderService.getProperyBean().getExpirationTime() - 1000)) {
			return gtnWsSecurityToken.getGtnToken();
		}
		deleteToken(userId, sessionId);
		return null;

	}

}