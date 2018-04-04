package com.stpl.app.bpm;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;

@Component(immediate = true, property = { "key=login.events.post" }, service = LifecycleAction.class)
public class GtnPostLoginHook implements LifecycleAction {

	public static final String BPMCOOKIENAME = "STPLBPMSESSIONID";

	private Logger gtnLogger = LoggerFactory.getLogger(GtnPostLoginHook.class);

	public void processLifecycleEvent(LifecycleEvent lifecycleEvent) throws ActionException {

		gtnLogger.info("Host -----------------" + lifecycleEvent.getRequest().getLocalName());
		gtnLogger.info("Address -----------------" + lifecycleEvent.getRequest().getLocalAddr());
		gtnLogger.info("Port -----------------" + lifecycleEvent.getRequest().getLocalPort());
		URL url;
		try {
			String bpmUrl = prepareBpmUrlString(lifecycleEvent);
			gtnLogger.info("Bpm url --  {}", bpmUrl);
			url = new URL(bpmUrl);
		} catch (MalformedURLException e) {
			gtnLogger.error("Exception in creating URL", e);
			return;

		}
		HttpURLConnection con;
		try {
			con = (HttpURLConnection) url.openConnection();
		} catch (IOException e1) {
			gtnLogger.error("Exception in Opening connection", e1);
			return;
		}
		try {
			con.setRequestMethod("GET");
		} catch (ProtocolException e) {
			gtnLogger.error("Exception in request method", e);
		}
		try {
			int statusCode = con.getResponseCode();
			gtnLogger.debug("connection with response code", statusCode);
			Map<String, List<String>> map = con.getHeaderFields();
			List<String> cookiesFromBpm = map.get("Set-Cookie");
			String jsessionId = cookiesFromBpm.get(0).split(";")[0];
			String[] sessionArray = jsessionId.split("=");
			Cookie bpmCookie = new Cookie(BPMCOOKIENAME, sessionArray[1]);
			lifecycleEvent.getResponse().addCookie(bpmCookie);

		} catch (IOException e) {
			gtnLogger.error("Exception in reading response method", e);
		} finally {
			con.disconnect();
		}

	}

	public static String prepareBpmUrlString(LifecycleEvent lifecycleEvent) {
		String port = lifecycleEvent.getRequest().getLocalPort() != 0 ? ":" + lifecycleEvent.getRequest().getLocalPort()
				: "";
		return "http://" + lifecycleEvent.getRequest().getLocalAddr() + port + getBpmUrlPath();
	}

	private static String getBpmUrlPath() {
		return System.getProperty("BPM.PATH", "/kie-drools-wb-6.5.0.Final-wildfly10");
	}

}
