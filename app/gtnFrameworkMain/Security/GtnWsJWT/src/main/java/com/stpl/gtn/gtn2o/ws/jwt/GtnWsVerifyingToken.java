package com.stpl.gtn.gtn2o.ws.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class GtnWsVerifyingToken {
	public void parseJWT(String jwt) {
		GtnWsReadSecretKey readSecretKey = new GtnWsReadSecretKey();
		Claims claims = Jwts.parser().setSigningKey(readSecretKey.getsecretkey().getBytes()).parseClaimsJws(jwt)
				.getBody();
		System.out.println("ID: " + claims.getId());
		System.out.println(claims.getExpiration());

	}

}
