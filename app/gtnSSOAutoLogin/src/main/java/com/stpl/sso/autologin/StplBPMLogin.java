package com.stpl.sso.autologin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.model.User;

public class StplBPMLogin {
	private static final Logger LOGGER = LoggerFactory.getLogger(StplBPMLogin.class);
	public static final String BPMCOOKIENAME = "STPLBPMSESSIONID";

	public void login(User user, HttpServletResponse response) {

		String request = prepareBpmUrlString();
		try {

			BasicCookieStore cookieStore = new BasicCookieStore();
			HttpGet getRequest = new HttpGet(request);
			CloseableHttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore)
					.disableRedirectHandling().build();
			client.execute(getRequest);
			LOGGER.info("Response cookies before auth " + cookieStore.getCookies());
			HttpPost httpPost = new HttpPost(request);
			List<BasicNameValuePair> params = new ArrayList<>();
			params.add(new BasicNameValuePair("j_username", user.getScreenName()));
			params.add(new BasicNameValuePair("j_password", user.getScreenName()));
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			client.execute(httpPost);
			LOGGER.info("Response cookies after auth " + cookieStore.getCookies());
			Cookie bpmCookie = new Cookie(BPMCOOKIENAME, cookieStore.getCookies().get(0).getValue());
			response.addCookie(bpmCookie);
			client.close();
		} catch (Exception e) {
			LOGGER.info("Request url is " + e);
		}
	}

	public static String prepareBpmUrlString() {
		String bpmEndPointUrl = getBpmEndPoint();
		return bpmEndPointUrl + getBpmUrlPath();

	}

	private static String getBpmUrlPath() {
		return System.getProperty("BPM.PATH", "/kie-drools-wb-6.5.0.Final-wildfly10");
	}

	private static String getBpmEndPoint() {
		return System.getProperty("BPM.ENDPOINT", "");
	}
}
