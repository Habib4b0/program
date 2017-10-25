package org.r2.etl.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
/**
 * This Class Configures and initializes the application
 * @author stpl
 *
 */
public class AppConfig implements WebApplicationInitializer {
	/**
	 * The variable used for logger
	 */
	private static final String DISP_SERV_NAME = "dispatcher";
	/**
	 * The variable used for logger
	 */
	private static final String DISPAT_SERV_MAPP = "/";

	@Override
	/**
	 * the is the initial method for initialization
	 */
	public void onStartup(final ServletContext servletContext)
			throws ServletException {
		final AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(AppContext.class);

		// XmlWebApplicationContext rootContext = new
		// XmlWebApplicationContext();
		// rootContext.setConfigLocation("classpath:applicationContext.xml");

		final ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
				DISP_SERV_NAME, new DispatcherServlet(rootContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping(DISPAT_SERV_MAPP);

		// FilterRegistration.Dynamic security =
		// servletContext.addFilter("springSecurityFilterChain", new
		// DelegatingFilterProxy());
		// EnumSet<DispatcherType> securityDispatcherTypes =
		// EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
		// security.addMappingForUrlPatterns(securityDispatcherTypes, true,
		// "/*");

		servletContext.addListener(new ContextLoaderListener(rootContext));
	}
}