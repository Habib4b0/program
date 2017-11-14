package com.stpl.gtn.gtn2o.ws.jwt;

public class GtnWsJWTMain {
	public static void main(String[] args) {

		GtnWsGenerateToken generateToken = new GtnWsGenerateToken();
		GtnWsVerifyingToken verifyingToken = new GtnWsVerifyingToken();
		String result = generateToken.createJWT("101", 3000L);
		Thread thread = new Thread();
		try {
			thread.sleep(1000);
			verifyingToken.parseJWT(result);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("token was expired");
		}

	}

}
