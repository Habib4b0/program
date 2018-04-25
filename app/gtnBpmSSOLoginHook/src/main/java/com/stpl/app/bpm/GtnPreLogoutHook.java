package com.stpl.app.bpm;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;

@Component(immediate = true, property = { "key=logout.events.pre" }, service = LifecycleAction.class)
public class GtnPreLogoutHook implements LifecycleAction {

	private Logger gtnLogger = LoggerFactory.getLogger(GtnPostLoginHook.class);

	public void processLifecycleEvent(LifecycleEvent lifecycleEvent) throws ActionException {
		gtnLogger.info("Before logout");
		Cookie[] liferayCookie = lifecycleEvent.getRequest().getCookies();
		Optional<Cookie> bpmCookieOptional = Arrays.stream(liferayCookie)
				.filter(cookie -> GtnPostLoginHook.BPMCOOKIENAME.equals(cookie.getName())).findFirst();
		Cookie bpmCookie = bpmCookieOptional.orElseThrow(() -> new IllegalArgumentException("BpmCookie Not Found"));
		URL url;
		try {
			url = new URL(GtnPostLoginHook.prepareBpmUrlString(lifecycleEvent) + "/logout.jsp");
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
		con.setRequestProperty("Cookie", bpmCookie.getName() + "=" + bpmCookie.getValue() + "; domain="
				+ bpmCookie.getDomain() + "; path=" + bpmCookie.getPath());
		try {
			int statusCode = con.getResponseCode();
			gtnLogger.debug("returned with status code {}" , statusCode);
		} catch (IOException e) {
			return;
		}
	}

}
