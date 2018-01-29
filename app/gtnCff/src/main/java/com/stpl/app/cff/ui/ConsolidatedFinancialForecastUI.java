
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui;


import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.security.StplSecurity;
import com.stpl.app.cff.ui.view.ConsolidatedFinancialForecastView;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.ConstantsUtil;
import com.stpl.app.cff.util.HelperListUtil;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import org.slf4j.Logger;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
import org.slf4j.LoggerFactory;
import org.vaadin.alump.beforeunload.BeforeUnload;

/**
 * CFF Main UI
 *
 * @author shrihariharan
 */
@Theme("stpl")
@Widgetset("com.stpl.widgetset.vaadin.widgetset.AppWidgetSet")
@Component(service = UI.class, property = {
        "com.liferay.portlet.display-category=Consolidated Financial Forecasting",
        "javax.portlet.name=Consolidated Financial Forecasting",
        "javax.portlet.display-name=Consolidated Financial Forecasting",
        "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
@JavaScript("js/DuplicateWindowManager.js")
public class ConsolidatedFinancialForecastUI extends UI {

	private static final long serialVersionUID = -1698071209409581013L;
	@AutoGenerated
	/**
	 * navigator
	 */
	private Navigator navigator;
	private static boolean EXCEL_CLOSE = false;
	/**
	 * Logger constant for the class ConsolidatedFinancialForecastUI
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsolidatedFinancialForecastUI.class);

	/**
	 * Override methods called from UI class
	 *
	 * @param request
	 *            - Vaadin request parameter
	 */
	public ConsolidatedFinancialForecastUI() throws SystemException {
		StplSecurity.getUserName();
	}

        @Override
	protected void init(VaadinRequest request) {
		try {
			LOGGER.debug("init method started");
			beforeUnloadCloseUi();
			setId(CommonUtils.MODULE_NAME);
			navigator = new Navigator(this, this);
			SessionDTO sessionDto = new SessionDTO();
			addStyleName("bootstrap bootstrap-ui bootstrap-forecast bootstrap-nm");
			final String userId = request.getRemoteUser();
			final String sessionId = request.getWrappedSession().getId();
			sessionDto.setUserId(userId);
			sessionDto.setSessionId(sessionId);
			setData(sessionDto);
			navigator = new Navigator(this, this);
			HelperListUtil helperListUtil = HelperListUtil.getInstance();
			helperListUtil.loadValuesWithListName(CommonUtils.MODULE_NAME);
			VaadinSession.getCurrent().setAttribute(ConstantsUtil.SESSION_ID, sessionId);
			VaadinSession.getCurrent().setAttribute(ConstantsUtil.USER_ID, userId);
			StplSecurity.getUserName();
			LOGGER.info("USER_ID: " + userId);
			LOGGER.info("SESSION_ID: " + sessionId);
			navigator.addView(ConsolidatedFinancialForecastView.NAME, new ConsolidatedFinancialForecastView());

			navigator.setErrorView(new ConsolidatedFinancialForecastView());
			LOGGER.debug("init method ends");
		} catch (Exception ex) {
			LOGGER.error("",ex);
		}

		// Configure the error handler for the UI
		UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
			@Override
			public void error(com.vaadin.server.ErrorEvent event) {
				// Find the final cause
				String cause = "The Exception occured because of: ";
				for (Throwable t = event.getThrowable(); t != null; t = t.getCause()) {
					if (t.getCause() == null) // We're at final cause
					{

						cause += t.getClass().getName();
					}

				}

				LOGGER.error(cause);
				// Do the default error handling (optional)
			}
		});
	}

	/**
	 * Used to invalidate the session
	 *
	 */
	public static void makeSessionInValidate() {
		if (isEXCEL_CLOSE()) { // Fix to avoid blank page issue while excel export
			setEXCEL_CLOSE(false);
		} else {
			UI.getCurrent().close();
		}
	}

	/**
	 * Used to close the session in browser closer listener
	 *
	 */
	private void beforeUnloadCloseUi() {
		BeforeUnload ob = BeforeUnload.closeBeforeUnload(this);
		ob.addUnloadListener(new BeforeUnload.UnloadListener() {
			@Override
			public void unload(BeforeUnload.UnloadEvent event) {
				makeSessionInValidate();
			}
		});
	}

	public static boolean isEXCEL_CLOSE() {
		return EXCEL_CLOSE;
	}

	public static void setEXCEL_CLOSE(boolean eXCEL_CLOSE) {
		EXCEL_CLOSE = eXCEL_CLOSE;
	}
}
