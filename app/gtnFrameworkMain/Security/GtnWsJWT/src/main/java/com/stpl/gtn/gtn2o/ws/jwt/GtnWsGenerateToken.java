package com.stpl.gtn.gtn2o.ws.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class GtnWsGenerateToken {

	public String createJWT(String id, long expdate) {
		GtnWsReadSecretKey readSecretKey = new GtnWsReadSecretKey();

		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		System.out.println(now);
		byte[] secretKey = readSecretKey.getsecretkey().getBytes();
		Key signingKey = new SecretKeySpec(secretKey, signatureAlgorithm.getJcaName());
		JwtBuilder builder = Jwts.builder().setId(id).setExpiration(now).signWith(signatureAlgorithm, signingKey);
		if (expdate >= 0) {
			long expMillis = nowMillis + expdate;
			Date exp = new Date(expMillis);
			System.out.println(exp);
			builder.setExpiration(exp);
		}

		return builder.compact();
	}

}
