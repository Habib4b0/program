package com.stpl.sso.saml;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.util.UUID;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

import com.stpl.sso.autologin.config.StplConfigReader;
import com.stpl.sso.saml.bean.StplSamlPropertyBean;

import org.joda.time.DateTime;
import org.opensaml.DefaultBootstrap;
import org.opensaml.common.SAMLVersion;
import org.opensaml.saml2.core.AuthnContextClassRef;
import org.opensaml.saml2.core.AuthnContextComparisonTypeEnumeration;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.LogoutRequest;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.core.NameIDPolicy;
import org.opensaml.saml2.core.RequestAbstractType;
import org.opensaml.saml2.core.RequestedAuthnContext;
import org.opensaml.saml2.core.impl.AuthnContextClassRefBuilder;
import org.opensaml.saml2.core.impl.AuthnRequestBuilder;
import org.opensaml.saml2.core.impl.IssuerBuilder;
import org.opensaml.saml2.core.impl.LogoutRequestBuilder;
import org.opensaml.saml2.core.impl.NameIDBuilder;
import org.opensaml.saml2.core.impl.NameIDPolicyBuilder;
import org.opensaml.saml2.core.impl.RequestedAuthnContextBuilder;
import org.opensaml.xml.io.Marshaller;
import org.opensaml.xml.util.Base64;
import org.opensaml.xml.util.XMLHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StplSAMLRequestGenerator {

  private static Logger LOGGER = LoggerFactory.getLogger(StplSAMLRequestGenerator.class.getName());
  private static final String SAML2_NAME_ID_POLICY = "urn:oasis:names:tc:SAML:1.1:nameid-format:emailAddress";
  private static final String SAML2_PROTOCOL = "urn:oasis:names:tc:SAML:2.0:protocol";
  private static final String SAML2_POST_BINDING = "urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST";
  private static final String SAML2_REDIRECT_BINDING = "urn:oasis:names:tc:SAML:2.0:bindings:HTTP-Redirect";
  private static final String SAML2_PASSWORD_PROTECTED_TRANSPORT = "urn:oasis:names:tc:SAML:2.0:ac:classes:PasswordProtectedTransport";
  private static final String SAML2_ASSERTION = "urn:oasis:names:tc:SAML:2.0:assertion";
  {
    try {
      DefaultBootstrap.bootstrap();
    } catch (Exception ex) {
      LOGGER.error("Unable to initialize SAML", ex);
      throw new RuntimeException("Unable to initialize SAML");
    }
  }

  public String getAuthNRedirectUrl() {
    StplSamlPropertyBean propertyBean = StplConfigReader.getInstance().getPropertyBean().getSamlPropertyBean();

    String url = null;

    try {

      AuthnRequest authRequest = buildAuthenticationRequest(propertyBean.getAssertionConsumerServiceUrl(),
          "BPI Technologies");
      String samlRequest = generateSAMLRequest(authRequest);

      url = propertyBean.getIdpURL() + "&SAMLRequest=" + samlRequest + "&RelayState="
          + URLEncoder.encode(propertyBean.getRelayState(), "UTF-8");

    } catch (Exception ex) {
      LOGGER.error("Exception while creating AuthN request - " + ex.getMessage(), ex);
    }

    LOGGER.debug("redirect url is = " + url);
    return url;

  }

  public String getLognRequest(String emailAddress) {
    StplSamlPropertyBean propertyBean = StplConfigReader.getInstance().getPropertyBean().getSamlPropertyBean();
    String url = null;
    LogoutRequest logOutRequest = buildLogOutRequest(propertyBean.getAssertionConsumerServiceUrl(),
        "BPI Technologies", emailAddress);
    String samlRequest;
    try {
      samlRequest = generateSAMLRequest(logOutRequest);
      url = propertyBean.getLogoutUrl() + "?SAMLRequest=" + samlRequest + "&RelayState="
          + URLEncoder.encode(propertyBean.getRelayState(), "UTF-8");
    } catch (Exception e) {
      LOGGER.error("Exception while creating Logut request - " + e.getMessage(), e);
    }

    LOGGER.debug("redirect url is = " + url);
    return url;
  }

  /*
   * Converts AuthN object to xml, compresses it, base64 encode it and url encode
   * it
   */
  private String generateSAMLRequest(RequestAbstractType authRequest) throws Exception {

    Marshaller marshaller = org.opensaml.Configuration.getMarshallerFactory().getMarshaller(authRequest);
    org.w3c.dom.Element authDOM = marshaller.marshall(authRequest);
    StringWriter rspWrt = new StringWriter();
    XMLHelper.writeNode(authDOM, rspWrt);
    String messageXML = rspWrt.toString();
    LOGGER.info(messageXML);
    Deflater deflater = new Deflater(Deflater.DEFLATED, true);
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, deflater);
    deflaterOutputStream.write(messageXML.getBytes());
    deflaterOutputStream.close();
    String samlRequest = Base64.encodeBytes(byteArrayOutputStream.toByteArray(), Base64.DONT_BREAK_LINES);
    return URLEncoder.encode(samlRequest, "UTF-8");

  }

  /**
   * Generate an authentication request.
   *
   * @return AuthnRequest Object
   */
  public AuthnRequest buildAuthenticationRequest(String assertionConsumerServiceUrl, String issuerId) {

    // Generate ID
    DateTime issueInstant = new DateTime();
    AuthnRequestBuilder authRequestBuilder = new AuthnRequestBuilder();
    AuthnRequest authRequest = authRequestBuilder.buildObject(SAML2_PROTOCOL, "AuthnRequest", "samlp");
    authRequest.setForceAuthn(Boolean.FALSE);
    authRequest.setIsPassive(Boolean.FALSE);
    authRequest.setIssueInstant(issueInstant);
    authRequest.setProtocolBinding(SAML2_REDIRECT_BINDING);
    authRequest.setAssertionConsumerServiceURL(assertionConsumerServiceUrl);
    authRequest.setIssuer(buildIssuer(issuerId));
    authRequest.setNameIDPolicy(buildNameIDPolicy());
    authRequest.setRequestedAuthnContext(buildRequestedAuthnContext());
    authRequest.setID(UUID.randomUUID().toString());
    authRequest.setVersion(SAMLVersion.VERSION_20);
    authRequest.setProviderName(issuerId);

    return authRequest;
  }

  /**
   * Build the issuer object
   *
   * @return Issuer object
   */
  private static Issuer buildIssuer(String issuerId) {
    IssuerBuilder issuerBuilder = new IssuerBuilder();
    Issuer issuer = issuerBuilder.buildObject();
    issuer.setValue(issuerId);
    return issuer;
  }

  /**
   * Build the NameIDPolicy object
   *
   * @return NameIDPolicy object
   */
  private static NameIDPolicy buildNameIDPolicy() {
    NameIDPolicy nameIDPolicy = new NameIDPolicyBuilder().buildObject();
    nameIDPolicy.setFormat(SAML2_NAME_ID_POLICY);
    nameIDPolicy.setAllowCreate(Boolean.TRUE);
    return nameIDPolicy;
  }

  /**
   * Build the RequestedAuthnContext object
   *
   * @return RequestedAuthnContext object
   */
  private static RequestedAuthnContext buildRequestedAuthnContext() {

    // Create AuthnContextClassRef
    AuthnContextClassRefBuilder authnContextClassRefBuilder = new AuthnContextClassRefBuilder();
    AuthnContextClassRef authnContextClassRef = authnContextClassRefBuilder.buildObject(SAML2_ASSERTION,
        "AuthnContextClassRef", "saml");
    authnContextClassRef.setAuthnContextClassRef(SAML2_PASSWORD_PROTECTED_TRANSPORT);

    // Create RequestedAuthnContext
    RequestedAuthnContextBuilder requestedAuthnContextBuilder = new RequestedAuthnContextBuilder();
    RequestedAuthnContext requestedAuthnContext = requestedAuthnContextBuilder.buildObject();
    requestedAuthnContext.setComparison(AuthnContextComparisonTypeEnumeration.EXACT);
    requestedAuthnContext.getAuthnContextClassRefs().add(authnContextClassRef);

    return requestedAuthnContext;
  }

  public LogoutRequest buildLogOutRequest(String destinationUrl, String issuerId, String emailAddress) {
    LogoutRequestBuilder logoutRequestBuilder = new LogoutRequestBuilder();
    LogoutRequest request = logoutRequestBuilder.buildObject(SAML2_PROTOCOL, "LogoutRequest", "samlp");
    request.setIssueInstant(new DateTime());
    request.setDestination(destinationUrl);
    request.setIssuer(buildIssuer(issuerId));
    request.setNameID(buildNameId(emailAddress));
    return request;

  }

  private NameID buildNameId(String emailAddress) {
    NameID nameID = new NameIDBuilder().buildObject();
    nameID.setFormat(SAML2_NAME_ID_POLICY);
    nameID.setValue(emailAddress);
    return nameID;
  }

}