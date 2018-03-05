/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.filemanagement.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import com.stpl.app.adminconsole.filemanagement.ui.view.FileManagementIndexView;
import com.stpl.app.adminconsole.util.HelperListUtil;
import com.stpl.ifs.ui.DateToStringConverterFactory;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

// TODO: Auto-generated Javadoc
/**
 * The Class FileManagementUI.
 *
 * @author Elangovan
 */
@Theme("stpl")
@Widgetset("com.stpl.widgetset.vaadin.widgetset.AppWidgetSet") 
@Component(service = UI.class, property = {
        "com.liferay.portlet.display-category=Admin Console",
        "javax.portlet.name=FileManagement",
        "javax.portlet.display-name=File Management",
        "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
public class FileManagementUI extends UI {

	public FileManagementUI(){
		 super();
	}
	/** The navigator. */
	private Navigator navigator;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FileManagementUI.class);
	private SessionDTO sessionDTO = new SessionDTO();

	/**
	 * Initializes this UI.
	 */
	@Override
	protected void init(final VaadinRequest request) {

		try {
			LOGGER.info("init method Started");
			addStyleName("bootstrap-bb");
			addStyleName("bootstrap");
			addStyleName("filemanagement-index");
			final String userId = request.getRemoteUser();
			sessionDTO.setUserId(userId);
			final String sessionId = request.getWrappedSession().getId();
			sessionDTO.setSessionId(sessionId);
			LOGGER.info("USER_ID: " + userId);
			LOGGER.info("SESSION_ID: " + sessionId);
                        CommonUIUtil.beforeUnloadCloseUi(this, sessionDTO);
			navigator = new Navigator(this, this);
			HelperListUtil helperListUtil = HelperListUtil.getInstance();
			helperListUtil.loadValuesWithListName("filemanagement");
			navigator.addView(FileManagementIndexView.NAME, new FileManagementIndexView(sessionDTO));
			navigator.setErrorView(new FileManagementIndexView(sessionDTO));
			setData(sessionDTO);
			VaadinSession.getCurrent().setConverterFactory(new DateToStringConverterFactory());
			LOGGER.info("init method Ended");
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	/**
	 * Getter for navigator
	 * 
	 * @return
	 */
	public Navigator getNavigator() {
		return navigator;
	}

	/**
	 * Setter for navigator
	 */
	public void setNavigator(final Navigator navigator) {
		this.navigator = navigator;
	}

}
