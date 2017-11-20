package com.stpl.gtn.gtn2o.ws.service.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class GtnWsSecurityJWTService {

	public String createJWT(String sessionID, String userID, long expTime) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		byte[] secretKey = DatatypeConverter.parseBase64Binary(GtnWsSecuritySecretKey.getsecretkey());
		Key signingKey = new SecretKeySpec(secretKey, signatureAlgorithm.getJcaName());
		JwtBuilder builder = Jwts.builder().setId(sessionID).setIssuer(userID).setExpiration(now)
				.signWith(signatureAlgorithm, signingKey);
		if (expTime >= 0) {
			long expMillis = nowMillis + expTime + 2000;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		return builder.compact();
	}

	public Claims parseJWT(String jwt) {
		byte[] secretKey = DatatypeConverter.parseBase64Binary(GtnWsSecuritySecretKey.getsecretkey());
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();

	}
	
	public static void main (String[] args){
		System.setProperty("com.stpl.gtnframework.base.path", "D:/rajadurai/project/gtnJbossServer/gtnBasePath");
		GtnWsSecurityJWTService service = new GtnWsSecurityJWTService();
		service.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1MTEiLCJpc3MiOiI3Njc0OTciLCJleHAiOjE0OTI2ODEyMjF9.As6-YagwOmI1LE_lq1wqRbxhai7yDGzcqBh2nvWVX4E");
	}

}
