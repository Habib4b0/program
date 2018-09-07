/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.sso.saml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;
import org.joda.time.DateTime;
import org.opensaml.DefaultBootstrap;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.core.Response;
import org.opensaml.security.SAMLSignatureProfileValidator;
import org.opensaml.xml.Configuration;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.io.Unmarshaller;
import org.opensaml.xml.io.UnmarshallerFactory;
import org.opensaml.xml.io.UnmarshallingException;
import org.opensaml.xml.signature.Signature;
import org.opensaml.xml.signature.SignatureValidator;
import org.opensaml.xml.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.stpl.sso.autologin.config.StplConfigReader;

/**
 *
 * @author Abishek.Ram
 */
public class StplSAMLResponseParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(StplSAMLResponseParser.class);

	public StplSAMLResponseParser() {

		try {
			DefaultBootstrap.bootstrap();
		} catch (ConfigurationException exception) {
			LOGGER.error("Exception in Bootstraping SAML", exception);
		}
	}

	public Response parseAndGetSamlResponse(String response) {
		try {
			Base64 base64 = new Base64();
			byte[] decodedByteArray = base64.decode(response.trim());
			Element element = buildXMLDocument(new String(decodedByteArray));
			Response samlResponse = unMarshallSamlResponse(element);
			return samlResponse;
		} catch (ParserConfigurationException | SAXException | IOException | UnmarshallingException exception) {
			LOGGER.error("Exception in  SAML parsing", exception);
		}
		LOGGER.info("- SAML  Parse error - ");
		return null;

	}

	private Element buildXMLDocument(String base64DecodedResponse)
			throws ParserConfigurationException, SAXException, IOException {
		if (StplConfigReader.getInstance().getPropertyBean().getSamlPropertyBean().isLogSamlResponse()) {
			LOGGER.info("Response - \n\n " + base64DecodedResponse + "\n\n");
			System.out.println("Response - \n\n " + base64DecodedResponse + "\n\n");
		}
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setNamespaceAware(true);
		DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(base64DecodedResponse));
		Document document = db.parse(is);
		return document.getDocumentElement();
	}

	private Response unMarshallSamlResponse(Element element) throws UnmarshallingException {
		UnmarshallerFactory unmarshallerFactory = Configuration.getUnmarshallerFactory();
		Unmarshaller unmarshaller = unmarshallerFactory.getUnmarshaller(element);
		org.opensaml.xml.XMLObject responseXmlObj = unmarshaller.unmarshall(element);
		return (Response) responseXmlObj;
	}

	public String validateAndGetUser(Response samlResponse) {
		NameID name = samlResponse.getAssertions().get(0).getSubject().getNameID();
		if (!validateResponse(samlResponse) && !validateSignature(samlResponse)) {
			LOGGER.info("Validation failed in SAML parse :: - returning null ");
			return null;
		}
		String value = name.getValue();
		LOGGER.debug("Name Value in SAML Response -- " + value);
		return value;
	}

	private boolean validateResponse(Response samlResponse) {
		String issuerInResponse = samlResponse.getIssuer().getValue();
		LOGGER.info("Resposne Issuer--" + issuerInResponse);
		validateAllowedTime(samlResponse);
		boolean isSameIssuer = issuerInResponse
				.equals(StplConfigReader.getInstance().getPropertyBean().getSamlPropertyBean().getIssuer());
		boolean isInAllowedTime = validateAllowedTime(samlResponse);
		return isSameIssuer && isInAllowedTime;

	}

	private boolean validateAllowedTime(Response samlResponse) {
		DateTime notBefore = samlResponse.getAssertions().get(0).getConditions().getNotBefore();
		DateTime notOnOrAfter = samlResponse.getAssertions().get(0).getConditions().getNotOnOrAfter();
		DateTime today = DateTime.now();
		return today.isAfter(notBefore) && today.isBefore(notOnOrAfter);
	}

	private boolean validateSignature(Response response) {
		SignatureValidator signatureValidator = new SignatureValidator(
				StplConfigReader.getInstance().getPropertyBean().getSamlPropertyBean().getIssuerPublicCertificate());
		SAMLSignatureProfileValidator profileValidator = new SAMLSignatureProfileValidator();
		Signature signature = response.getSignature();
		if (signature == null) {
			signature = response.getAssertions().get(0).getSignature();
		}
		try {
			profileValidator.validate(signature);
			signatureValidator.validate(signature);
		} catch (ValidationException ve) {
			LOGGER.error("Signature is Not Valid");
			return false;
		} catch (Exception E) {
			LOGGER.error("Signature is Not Valid", E);
			return false;
		}
		LOGGER.info("Signature is  Valid");
		return true;
	}

	public byte[] inflate(byte[] inflate) throws IOException {
		Inflater inflater = new Inflater(true);
		ByteArrayOutputStream byteArray1 = new ByteArrayOutputStream();
		InflaterOutputStream def1 = new InflaterOutputStream(byteArray1, inflater);
		def1.write(inflate);
		def1.close();
		byteArray1.close();
		byte[] output = byteArray1.toByteArray();
		inflater.end();
		return output;
	}

	public static void main(String[] args) throws EncoderException {
		// sas.parse("nVVdc6pIEH3fqv0PFHm0EAYRkIpJYYwRvxU1kZetCTMKCTA6Myj66xc%2Fkqvevbl388j0mdOnT%2Fc0t%2FdZHAlrTFlIkqoIiooo4MQnKEwWVXEybkimeH93y2AcqUurQxYk5SO8SjHjQn4zYdYxVBVTmlgEspBZCYwxs7hvuXa3Y6lFxVpSwolPIlGo5xfDBPJDtoDzJbNkGShFrWiUi6BkWhWlBOQQLWXGIeWu2y8ucyn3g%2FwjwdRdOqjKtuw13ImCU6%2BK%2F6i6AQ2E5lIZ%2BEDSoA4kE%2BuqpFcMpYJ0%2FxXppRzKWIqdJOdMeFVUFaBLAEgqGCumpemWphUrRtkThR7h%2FaRP7TnH9BpXBj9wIwzZvgI31xZh4eiLKEw%2FfMyLFu%2F%2B%2FksQhKN11kEAPbfsa8cgY5juXRLvjuXeyudEJ27ELDdc5HamFJ%2B4ETv6mtu62WyKm1KR0IWsKooiKxU5xyAWLm5O4s44MHKSOfk8PkUeYEKS0IdRuDv0rIt5QJBgRwtCQx7Ev8gF8pbuc0k48yUfaMmNKF9Tfwr%2FQ84L%2FZRBiQUQ%2FEw7wnNM8wnGwmTkVMWbPxuQc5YT0ZjChM0JjdlV8Dr%2B%2F5TjZI0jssRIYh8GXBbxnQy%2F83tPKH9V0z5dPVzkj%2FM73fi5ExeUUxil%2BI69uMhGu07Hfgw7LRCbIQtfZ2ywwrPqQdw5%2BLyp8nlXf8yt%2FN%2BDezFaR7KOz5uKwQall8mgPa7U6lxZdd6H42RrsydUU%2BPnUsXwzAcuo9VTeWLu4k0X1ApZuc5WcPse0XXTT7cg6A8e0Wxbfxh4UJ%2FMNl4FmnLypplRXevG3Wz7VKdey5l1W66X%2BbMmCwugrAZv20WkTd4dR%2B6Pxu4se4uXgea2Ua8JenzLRp2236JZK32JZa7q%2BrDvoPduM1RwYfiYmg%2F9xvSxY%2Fff59RHJXPNl9PSbt1WnoIaBOsaXL3sGr7pySUYZJuoMchsz9E77tighaGRzrJnbtNu9tokPBj5L1DV64t2wTV1Aw9tRIc91SMV%2BOaX13PcTsmsFsQVDEifGNl0S1e6MYieE6Q5rYJWfuplHvY9ZVtgvWEwrFY%2FW3Dm9XEvXQQu9mAv33RO%2FTt7UGjkcwv51%2FD9SYik%2BQFq4RiGkY0QxYyJdxTGH0v0qCIXdjq4%2BrPlgX8B");
		System.out.println(new StplSAMLResponseParser().parseAndGetSamlResponse(
				"PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz48c2FtbDJwOlJlc3BvbnNlIHhtbG5zOnNhbWwycD0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOnByb3RvY29sIiBEZXN0aW5hdGlvbj0iaHR0cHM6Ly9hbGxlcmdhbi1kZXYuYnBpdGVjaG5vbG9naWVzLmNvbSIgSUQ9ImlkMTYwMzI5Nzg4MzU2MDk5MjAxMjc2OTQ0OTMiIElzc3VlSW5zdGFudD0iMjAxNy0wMy0yMVQxMDo1ODoyOS4xNTNaIiBWZXJzaW9uPSIyLjAiPjxzYW1sMjpJc3N1ZXIgeG1sbnM6c2FtbDI9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDphc3NlcnRpb24iIEZvcm1hdD0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOm5hbWVpZC1mb3JtYXQ6ZW50aXR5Ij5odHRwOi8vd3d3Lm9rdGEuY29tL2V4azFhcmFiNDN4NVFVS1VFMWQ4PC9zYW1sMjpJc3N1ZXI+PGRzOlNpZ25hdHVyZSB4bWxuczpkcz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC8wOS94bWxkc2lnIyI+PGRzOlNpZ25lZEluZm8+PGRzOkNhbm9uaWNhbGl6YXRpb25NZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzEwL3htbC1leGMtYzE0biMiLz48ZHM6U2lnbmF0dXJlTWV0aG9kIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMC8wOS94bWxkc2lnI3JzYS1zaGExIi8+PGRzOlJlZmVyZW5jZSBVUkk9IiNpZDE2MDMyOTc4ODM1NjA5OTIwMTI3Njk0NDkzIj48ZHM6VHJhbnNmb3Jtcz48ZHM6VHJhbnNmb3JtIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMC8wOS94bWxkc2lnI2VudmVsb3BlZC1zaWduYXR1cmUiLz48ZHM6VHJhbnNmb3JtIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMS8xMC94bWwtZXhjLWMxNG4jIi8+PC9kczpUcmFuc2Zvcm1zPjxkczpEaWdlc3RNZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwLzA5L3htbGRzaWcjc2hhMSIvPjxkczpEaWdlc3RWYWx1ZT5WY0I1Y0t1Q3pwVlJKaE85WVg4QS9EMnEwLzA9PC9kczpEaWdlc3RWYWx1ZT48L2RzOlJlZmVyZW5jZT48L2RzOlNpZ25lZEluZm8+PGRzOlNpZ25hdHVyZVZhbHVlPlpGTFZZa2ZoVE15SG1RL2Q4MXFBVkE4enRnbmZjd25MMm40Qi9tRTBkRGNCdElwdk5JdUxNdXU4d2RzSWFvVVFsWmpYN1hsb2xRYzlCUWttbE9VMUNMR3pndVIwdUhvWFlNQkRseXU2WTltbmV1SWkxUzhMa1E3bWNjVWQ3MGxydFYvbTJ3cHpKTFdWOElRRnMxTG54UHFUWHg3akg1N3g0R2NpMHhPNTl2MDE4bkJZbU1CbUo2NzlHcS9wMEVUdFBPemQ5YXhGaHErZnNxRUJmUEpSYjBJRVN1MktnU05OR2ZsM1lHYnduZXNiMU1zRk96ZlNtTk1DRENFa29lYlo3ajl1QTdmS1dmc0hBSVZrNU1SODMwb2F5dnpFbVZIU0VVTFhtVTRubjlHTWhzblI3QlZrY1BUQ1pFV3BoT2tMaGF5OHBya2VycDhuQXcxV2ZESFZkUT09PC9kczpTaWduYXR1cmVWYWx1ZT48ZHM6S2V5SW5mbz48ZHM6WDUwOURhdGE+PGRzOlg1MDlDZXJ0aWZpY2F0ZT5NSUlEcGpDQ0FvNmdBd0lCQWdJR0FVdEIxZGw4TUEwR0NTcUdTSWIzRFFFQkJRVUFNSUdUTVFzd0NRWURWUVFHRXdKVlV6RVRNQkVHCkExVUVDQXdLUTJGc2FXWnZjbTVwWVRFV01CUUdBMVVFQnd3TlUyRnVJRVp5WVc1amFYTmpiekVOTUFzR0ExVUVDZ3dFVDJ0MFlURVUKTUJJR0ExVUVDd3dMVTFOUFVISnZkbWxrWlhJeEZEQVNCZ05WQkFNTUMyRnNiR1Z5WjJGdVpHVjJNUnd3R2dZSktvWklodmNOQVFrQgpGZzFwYm1adlFHOXJkR0V1WTI5dE1CNFhEVEUxTURFek1USXhNVEEwT0ZvWERUUTFNREV6TVRJeE1URTBPRm93Z1pNeEN6QUpCZ05WCkJBWVRBbFZUTVJNd0VRWURWUVFJREFwRFlXeHBabTl5Ym1saE1SWXdGQVlEVlFRSERBMVRZVzRnUm5KaGJtTnBjMk52TVEwd0N3WUQKVlFRS0RBUlBhM1JoTVJRd0VnWURWUVFMREF0VFUwOVFjbTkyYVdSbGNqRVVNQklHQTFVRUF3d0xZV3hzWlhKbllXNWtaWFl4SERBYQpCZ2txaGtpRzl3MEJDUUVXRFdsdVptOUFiMnQwWVM1amIyMHdnZ0VpTUEwR0NTcUdTSWIzRFFFQkFRVUFBNElCRHdBd2dnRUtBb0lCCkFRQ213SlNwaEZYNDc2RkM3KzR4ZDY4Mlg2bU5KRG9kWWN4d2dhVnU3ZmFOSVE5cldMdWlIakhwc2t4eENTS3FWSlB3RXVKcDVMSi8KOEt6MllaS3JvaHJuMFZ0ditMN0htTHQ0Qmt4UnppZFR3dVJkcmhNbkNpQjBtY2l2MHdvU0had3BodlFDZXVCejJsaWZmUy92dkZnTQovdDA3RDloUkN3NDBDbnVDLzlGWXZacytaaGwxMFRLOHR3emtUakxhUTh2d2hZRDYzSjkzbEh0UWVHK2Y0bUZxbVlweHR2Sis1bkdTClJQcERkTzBxZVlSUjk3eEZoZ1NxSERzQmlIYnh6bkNHNjFydGV6NG5UazdhOWZYa3F0azcra2JjN2E3SUFVMkZvTjF4aENDNWxpUkcKMGN2WkRVbDR5eG11b0xmOVFrSGtwUGFFa3oycitiTVM2V21McEJDL0FnTUJBQUV3RFFZSktvWklodmNOQVFFRkJRQURnZ0VCQUM5QQozZFllNnBsRXlraEthb1htTlhab1o0YUN4eFFuSjErYVZFYkYwRWFvTXhQUFpyVFhrRDRhdE1tTjdNYzZIL25SbDg4NkhENGhiZU1UCnVpbGRQN3ZKVkhESVA0TU4yT0dzVUFxRlJXbzlwRWc0bGsyUkdZUmI3UUcraFp1dlFZdmZoYTdreWIyQlh3cHl6eEhvTEsxbUV6a0gKWEZEcnVjN0ZDWjd2QjcvN0VmdkoydzFNblpPVkdDbnVZVjluYWFyNG1CMTdjcjlTcGxHampGRjV4SmZuNWhDejJNTjNoVGpuUWZybgpGcmsyR1VnVnBNUXMyRDNhV3Y5b0FDS1AxclZwNDlxM0gyRWVOY2ZvQ2hvNTdGSmJ5RUhXVEErV0JIOGhSeUpVZzJBOEJBZCt2QkpyCnpGMytkZ2lGZEtkNXNQTWxGZFZuOHl6TFJ2UnFURlpmQVBVPTwvZHM6WDUwOUNlcnRpZmljYXRlPjwvZHM6WDUwOURhdGE+PC9kczpLZXlJbmZvPjwvZHM6U2lnbmF0dXJlPjxzYW1sMnA6U3RhdHVzIHhtbG5zOnNhbWwycD0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOnByb3RvY29sIj48c2FtbDJwOlN0YXR1c0NvZGUgVmFsdWU9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDpzdGF0dXM6U3VjY2VzcyIvPjwvc2FtbDJwOlN0YXR1cz48c2FtbDI6QXNzZXJ0aW9uIHhtbG5zOnNhbWwyPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6YXNzZXJ0aW9uIiBJRD0iaWQxNjAzMjk3ODgzNTYzODU5OTEwNzI2OTg5MzIiIElzc3VlSW5zdGFudD0iMjAxNy0wMy0yMVQxMDo1ODoyOS4xNTNaIiBWZXJzaW9uPSIyLjAiPjxzYW1sMjpJc3N1ZXIgRm9ybWF0PSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6bmFtZWlkLWZvcm1hdDplbnRpdHkiPmh0dHA6Ly93d3cub2t0YS5jb20vZXhrMWFyYWI0M3g1UVVLVUUxZDg8L3NhbWwyOklzc3Vlcj48c2FtbDI6U3ViamVjdD48c2FtbDI6TmFtZUlEIEZvcm1hdD0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6MS4xOm5hbWVpZC1mb3JtYXQ6dW5zcGVjaWZpZWQiPnZpc3dhbmF0aC5hQHN5c2Jpei5jb208L3NhbWwyOk5hbWVJRD48c2FtbDI6U3ViamVjdENvbmZpcm1hdGlvbiBNZXRob2Q9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDpjbTpiZWFyZXIiPjxzYW1sMjpTdWJqZWN0Q29uZmlybWF0aW9uRGF0YSBOb3RPbk9yQWZ0ZXI9IjIwMTctMDMtMjFUMTE6MDM6MjkuMTUzWiIgUmVjaXBpZW50PSJodHRwczovL2FsbGVyZ2FuLWRldi5icGl0ZWNobm9sb2dpZXMuY29tIi8+PC9zYW1sMjpTdWJqZWN0Q29uZmlybWF0aW9uPjwvc2FtbDI6U3ViamVjdD48c2FtbDI6Q29uZGl0aW9ucyBOb3RCZWZvcmU9IjIwMTctMDMtMjFUMTA6NTM6MjkuMTUzWiIgTm90T25PckFmdGVyPSIyMDE3LTAzLTIxVDExOjAzOjI5LjE1M1oiPjxzYW1sMjpBdWRpZW5jZVJlc3RyaWN0aW9uPjxzYW1sMjpBdWRpZW5jZT5odHRwczovL2FsbGVyZ2FuLWRldi5icGl0ZWNobm9sb2dpZXMuY29tPC9zYW1sMjpBdWRpZW5jZT48L3NhbWwyOkF1ZGllbmNlUmVzdHJpY3Rpb24+PC9zYW1sMjpDb25kaXRpb25zPjxzYW1sMjpBdXRoblN0YXRlbWVudCBBdXRobkluc3RhbnQ9IjIwMTctMDMtMjFUMTA6NTg6MjkuMTUzWiIgU2Vzc2lvbkluZGV4PSJpZDE0OTAwOTM5MDkxNTMuMTkxNjMwMTc1NCI+PHNhbWwyOkF1dGhuQ29udGV4dD48c2FtbDI6QXV0aG5Db250ZXh0Q2xhc3NSZWY+dXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmFjOmNsYXNzZXM6UGFzc3dvcmRQcm90ZWN0ZWRUcmFuc3BvcnQ8L3NhbWwyOkF1dGhuQ29udGV4dENsYXNzUmVmPjwvc2FtbDI6QXV0aG5Db250ZXh0Pjwvc2FtbDI6QXV0aG5TdGF0ZW1lbnQ+PC9zYW1sMjpBc3NlcnRpb24+PC9zYW1sMnA6UmVzcG9uc2U+"));

		String SAML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><saml2p:Response xmlns:saml2p=\"urn:oasis:names:tc:SAML:2.0:protocol\" Destination=\"https://allergan-dev.bpitechnologies.com\" ID=\"id16032978835609920127694493\" IssueInstant=\"2017-03-21T10:58:29.153Z\" Version=\"2.0\">\n"
				+ "    <saml2:Issuer xmlns:saml2=\"urn:oasis:names:tc:SAML:2.0:assertion\" Format=\"urn:oasis:names:tc:SAML:2.0:nameid-format:entity\">http://www.okta.com/exk1arab43x5QUKUE1d8</saml2:Issuer>\n"
				+ "    <ds:Signature xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\">\n" + "        <ds:SignedInfo>\n"
				+ "            <ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/>\n"
				+ "            <ds:SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/>\n"
				+ "            <ds:Reference URI=\"#id16032978835609920127694493\">\n"
				+ "                <ds:Transforms>\n"
				+ "                    <ds:Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/>\n"
				+ "                    <ds:Transform Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"/>\n"
				+ "                </ds:Transforms>\n"
				+ "                <ds:DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/>\n"
				+ "                <ds:DigestValue>VcB5cKuCzpVRJhO9YX8A/D2q0/0=</ds:DigestValue>\n"
				+ "            </ds:Reference>\n" + "        </ds:SignedInfo>\n"
				+ "        <ds:SignatureValue>ZFLVYkfhTMyHmQ/d81qAVA8ztgnfcwnL2n4B/mE0dDcBtIpvNIuLMuu8wdsIaoUQlZjX7XlolQc9BQkmlOU1CLGzguR0uHoXYMBDlyu6Y9mneuIi1S8LkQ7mccUd70lrtV/m2wpzJLWV8IQFs1LnxPqTXx7jH57x4Gci0xO59v018nBYmMBmJ679Gq/p0ETtPOzd9axFhq+fsqEBfPJRb0IESu2KgSNNGfl3YGbwnesb1MsFOzfSmNMCDCEkoebZ7j9uA7fKWfsHAIVk5MR830oayvzEmVHSEULXmU4nn9GMhsnR7BVkcPTCZEWphOkLhay8prkerp8nAw1WfDHVdQ==</ds:SignatureValue>\n"
				+ "        <ds:KeyInfo>\n" + "            <ds:X509Data>\n"
				+ "                <ds:X509Certificate>MIIDpjCCAo6gAwIBAgIGAUtB1dl8MA0GCSqGSIb3DQEBBQUAMIGTMQswCQYDVQQGEwJVUzETMBEG\n"
				+ "                    A1UECAwKQ2FsaWZvcm5pYTEWMBQGA1UEBwwNU2FuIEZyYW5jaXNjbzENMAsGA1UECgwET2t0YTEU\n"
				+ "                    MBIGA1UECwwLU1NPUHJvdmlkZXIxFDASBgNVBAMMC2FsbGVyZ2FuZGV2MRwwGgYJKoZIhvcNAQkB\n"
				+ "                    Fg1pbmZvQG9rdGEuY29tMB4XDTE1MDEzMTIxMTA0OFoXDTQ1MDEzMTIxMTE0OFowgZMxCzAJBgNV\n"
				+ "                    BAYTAlVTMRMwEQYDVQQIDApDYWxpZm9ybmlhMRYwFAYDVQQHDA1TYW4gRnJhbmNpc2NvMQ0wCwYD\n"
				+ "                    VQQKDARPa3RhMRQwEgYDVQQLDAtTU09Qcm92aWRlcjEUMBIGA1UEAwwLYWxsZXJnYW5kZXYxHDAa\n"
				+ "                    BgkqhkiG9w0BCQEWDWluZm9Ab2t0YS5jb20wggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIB\n"
				+ "                    AQCmwJSphFX476FC7+4xd682X6mNJDodYcxwgaVu7faNIQ9rWLuiHjHpskxxCSKqVJPwEuJp5LJ/\n"
				+ "                    8Kz2YZKrohrn0Vtv+L7HmLt4BkxRzidTwuRdrhMnCiB0mciv0woSHZwphvQCeuBz2liffS/vvFgM\n"
				+ "                    /t07D9hRCw40CnuC/9FYvZs+Zhl10TK8twzkTjLaQ8vwhYD63J93lHtQeG+f4mFqmYpxtvJ+5nGS\n"
				+ "                    RPpDdO0qeYRR97xFhgSqHDsBiHbxznCG61rtez4nTk7a9fXkqtk7+kbc7a7IAU2FoN1xhCC5liRG\n"
				+ "                    0cvZDUl4yxmuoLf9QkHkpPaEkz2r+bMS6WmLpBC/AgMBAAEwDQYJKoZIhvcNAQEFBQADggEBAC9A\n"
				+ "                    3dYe6plEykhKaoXmNXZoZ4aCxxQnJ1+aVEbF0EaoMxPPZrTXkD4atMmN7Mc6H/nRl886HD4hbeMT\n"
				+ "                    uildP7vJVHDIP4MN2OGsUAqFRWo9pEg4lk2RGYRb7QG+hZuvQYvfha7kyb2BXwpyzxHoLK1mEzkH\n"
				+ "                    XFDruc7FCZ7vB7/7EfvJ2w1MnZOVGCnuYV9naar4mB17cr9SplGjjFF5xJfn5hCz2MN3hTjnQfrn\n"
				+ "                    Frk2GUgVpMQs2D3aWv9oACKP1rVp49q3H2EeNcfoCho57FJbyEHWTA+WBH8hRyJUg2A8BAd+vBJr\n"
				+ "                    zF3+dgiFdKd5sPMlFdVn8yzLRvRqTFZfAPU=</ds:X509Certificate>\n"
				+ "            </ds:X509Data>\n" + "        </ds:KeyInfo>\n" + "    </ds:Signature>\n"
				+ "    <saml2p:Status xmlns:saml2p=\"urn:oasis:names:tc:SAML:2.0:protocol\">\n"
				+ "        <saml2p:StatusCode Value=\"urn:oasis:names:tc:SAML:2.0:status:Success\"/>\n"
				+ "    </saml2p:Status>\n"
				+ "    <saml2:Assertion xmlns:saml2=\"urn:oasis:names:tc:SAML:2.0:assertion\" ID=\"id160329788356385991072698932\" IssueInstant=\"2017-03-21T10:58:29.153Z\" Version=\"2.0\">\n"
				+ "        <saml2:Issuer Format=\"urn:oasis:names:tc:SAML:2.0:nameid-format:entity\">http://www.okta.com/exk1arab43x5QUKUE1d8</saml2:Issuer>\n"
				+ "        <saml2:Subject>\n"
				+ "            <saml2:NameID Format=\"urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified\">viswanath.a@sysbiz.com</saml2:NameID>\n"
				+ "            <saml2:SubjectConfirmation Method=\"urn:oasis:names:tc:SAML:2.0:cm:bearer\">\n"
				+ "                <saml2:SubjectConfirmationData NotOnOrAfter=\"2017-03-21T11:03:29.153Z\" Recipient=\"https://allergan-dev.bpitechnologies.com\"/>\n"
				+ "            </saml2:SubjectConfirmation>\n" + "        </saml2:Subject>\n"
				+ "        <saml2:Conditions NotBefore=\"2017-03-21T10:53:29.153Z\" NotOnOrAfter=\"2017-03-21T11:03:29.153Z\">\n"
				+ "            <saml2:AudienceRestriction>\n"
				+ "                <saml2:Audience>https://allergan-dev.bpitechnologies.com</saml2:Audience>\n"
				+ "            </saml2:AudienceRestriction>\n" + "        </saml2:Conditions>\n"
				+ "        <saml2:AuthnStatement AuthnInstant=\"2017-03-21T10:58:29.153Z\" SessionIndex=\"id1490093909153.1916301754\">\n"
				+ "            <saml2:AuthnContext>\n"
				+ "                <saml2:AuthnContextClassRef>urn:oasis:names:tc:SAML:2.0:ac:classes:PasswordProtectedTransport</saml2:AuthnContextClassRef>\n"
				+ "            </saml2:AuthnContext>\n" + "        </saml2:AuthnStatement>\n"
				+ "    </saml2:Assertion>\n" + "</saml2p:Response>";
		Base64 base64 = new Base64();

		System.out.println("" + base64.encodeToString(SAML.getBytes()));
		System.out
				.println(new StplSAMLResponseParser().parseAndGetSamlResponse(base64.encodeToString(SAML.getBytes())));

	}
}
