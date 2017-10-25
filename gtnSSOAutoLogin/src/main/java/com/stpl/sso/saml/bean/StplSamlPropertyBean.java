/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.sso.saml.bean;

import org.opensaml.xml.security.x509.BasicX509Credential;

/**
 *
 * @author Abishek.Ram
 */
public class StplSamlPropertyBean {

    private String certificatePath;
    private String idpURL;
    private BasicX509Credential issuerPublicCertificate;
    private boolean logSamlResponse;
    private String issuer;
    private String relayState;

    public String getCertificatePath() {
        return certificatePath;
    }

    public void setCertificatePath(String certificatePath) {
        this.certificatePath = certificatePath;
    }

    public String getIdpURL() {
        return idpURL;
    }

    public void setIdpURL(String idpURL) {
        this.idpURL = idpURL;
    }

    public BasicX509Credential getIssuerPublicCertificate() {
        return issuerPublicCertificate;
    }

    public void setIssuerPublicCertificate(BasicX509Credential issuerPublicCertificate) {
        this.issuerPublicCertificate = issuerPublicCertificate;
    }

    public boolean isLogSamlResponse() {
        return logSamlResponse;
    }

    public void setLogSamlResponse(boolean logSamlResponse) {
        this.logSamlResponse = logSamlResponse;
    }

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getRelayState() {
		return relayState;
	}

	public void setRelayState(String relayState) {
		this.relayState = relayState;
	}
    
    
    
}
