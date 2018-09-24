/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.sso.autologin.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.X509EncodedKeySpec;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.opensaml.xml.security.x509.BasicX509Credential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.stpl.sso.autologin.SSOConstants;
import com.stpl.sso.autologin.bean.StpSSOPropertybean;
import com.stpl.sso.kerberos.spnego.SpnegoHttpFilter.Constants;
import com.stpl.sso.kerberos.spnego.bean.StplSpnegoPropertyBean;
import com.stpl.sso.saml.bean.StplSamlPropertyBean;
import java.util.Arrays;

/**
 *
 * @author Abishek.Ram
 */
public class StplConfigReader {

	private static final StplConfigReader _INSTANCE = new StplConfigReader();
	private static final Logger LOGGER = LoggerFactory.getLogger(StplConfigReader.class);
	private StpSSOPropertybean propertyBean = new StpSSOPropertybean();

	private StplConfigReader() {
		readFile();
	}

	public static StplConfigReader getInstance() {
		return _INSTANCE;
	}

	public StpSSOPropertybean getPropertyBean() {
		return propertyBean;
	}

	private void readFile() {
		try (InputStream input = new FileInputStream(new File(SSOConstants.CONFIGPATH));) {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(input);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("gtnSSOConfig");
			Node nNode = nList.item(0);
			Element eElement = (Element) nNode;
			String ssoType = getData(eElement, "ssotype");
			propertyBean.setSsoType(ssoType);
			if (SSOConstants.SAML.equals(ssoType)) {
				loadSamlBean(eElement);
			} else {
				loadKerberosBean(eElement);
			}

		} catch (ParserConfigurationException | SAXException | IOException ex) {
			LOGGER.error("Error in Reading Config ", ex);
		}
	}

	private void loadKerberosBean(Element eElement) {
		StplSpnegoPropertyBean spnegoPropertyBean = new StplSpnegoPropertyBean();
		propertyBean.setSpnegoPropertyBean(spnegoPropertyBean);
		spnegoPropertyBean.setAllowBasic(Boolean.parseBoolean(getData(eElement, Constants.ALLOW_BASIC)));
		spnegoPropertyBean.setAllowLocalHost(Boolean.parseBoolean(getData(eElement, Constants.ALLOW_LOCALHOST)));
		spnegoPropertyBean.setAllowUnsecureBasic(Boolean.parseBoolean(getData(eElement, Constants.ALLOW_UNSEC_BASIC)));
		spnegoPropertyBean.setCliendModule(getData(eElement, Constants.CLIENT_MODULE));
		spnegoPropertyBean.setKrbConf(getData(eElement, Constants.KRB5_CONF));
		spnegoPropertyBean.setLoginConf(getData(eElement, Constants.LOGIN_CONF));
		spnegoPropertyBean.setServerModule(getData(eElement, Constants.SERVER_MODULE));
		spnegoPropertyBean.setPromtNtlm(Boolean.parseBoolean(getData(eElement, Constants.PROMPT_NTLM)));
		spnegoPropertyBean.setLogLevel(Integer.valueOf(getData(eElement, Constants.LOGGER_LEVEL)));
		spnegoPropertyBean.setUserName(getData(eElement, Constants.PREAUTH_USERNAME));
		spnegoPropertyBean.setPassWord(getData(eElement, Constants.PREAUTH_PASSWORD));

	}

	private String getData(Element elem, String tag) {
		NodeList dataList = elem.getElementsByTagName(tag);
		if (dataList.getLength() > 0) {
			String value = dataList.item(0).getTextContent();
			return value;
		}
		return "";
	}

	private void loadSamlBean(Element parentElement) {
		StplSamlPropertyBean samlPropertyBean = new StplSamlPropertyBean();
		propertyBean.setSamlPropertyBean(samlPropertyBean);
		Element samlElement = (Element) parentElement.getElementsByTagName("saml").item(0);
		
		samlPropertyBean.setCertificatePath(getData(samlElement, SSOConstants.ISSUER_PUBLIC_CERTIFICATE));
		createPublicKeyCredential(samlPropertyBean);
		samlPropertyBean.setIdpURL(getData(samlElement, SSOConstants.IDP_URL));
		samlPropertyBean.setLogSamlResponse(Boolean.parseBoolean(getData(samlElement, SSOConstants.LOG_SAML_RESPONSE)));
		samlPropertyBean.setIssuer(getData(samlElement, SSOConstants.ISSUER));
		samlPropertyBean.setIsSAMLRequestNeeded(getData(samlElement, SSOConstants.ISSAMLREQUIRED));
		samlPropertyBean.setAssertionConsumerServiceUrl(getData(samlElement, SSOConstants.ACSURL));
		samlPropertyBean.setLogoutUrl(getData(samlElement, SSOConstants.LOGOUTURL));
		samlPropertyBean.setRelayState(getData(samlElement, SSOConstants.RELAYSTATE));
		samlPropertyBean.setSpecialCharArray(getData(samlElement, Constants.SPECIALCHAR_EMAIL).split(","));
	}

	private void createPublicKeyCredential(StplSamlPropertyBean samlPropertyBean) {
		File certificateFile = new File(samlPropertyBean.getCertificatePath());
		X509Certificate certificate = null;
		try (InputStream inputStream2 = new FileInputStream(certificateFile)) {
			CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
			certificate = (X509Certificate) certificateFactory.generateCertificate(inputStream2);
			X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(certificate.getPublicKey().getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			System.out.println("Security Provider: " + keyFactory.getProvider().toString());
			PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
			System.out.println("Public Key created");
			BasicX509Credential publicCredential = new BasicX509Credential();
			publicCredential.setPublicKey(publicKey);
			samlPropertyBean.setIssuerPublicCertificate(publicCredential);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
		}
	}

	public static void main(String[] args) {
		new StplConfigReader().readFile();
		System.out.println("StplConfigReader.getInstance().getPropertyBean().getSamlPropertyBea-->>" + Arrays
				.asList(StplConfigReader.getInstance().getPropertyBean().getSamlPropertyBean().getSpecialCharArray()));
	}
}
