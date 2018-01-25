/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.filemanagement.ui;

import org.jboss.logging.Logger;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.filemanagement.ui.view.FileManagementIndexView;
import com.stpl.app.adminconsole.util.HelperListUtil;
import com.stpl.ifs.ui.DateToStringConverterFactory;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

// TODO: Auto-generated Javadoc
/**
 * The Class FileManagementUI.
 *
 * @author Elangovan
 */
public class FileManagementUI extends UI {

	public FileManagementUI(){
		 super();
	}
	/** The navigator. */
	private Navigator navigator;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(FileManagementUI.class);
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
			navigator = new Navigator(this, this);
			HelperListUtil helperListUtil = HelperListUtil.getInstance();
			helperListUtil.loadValuesWithListName("filemanagement");
			navigator.addView(FileManagementIndexView.NAME, new FileManagementIndexView(sessionDTO));
			navigator.setErrorView(new FileManagementIndexView(sessionDTO));
			setData(sessionDTO);
			VaadinSession.getCurrent().setConverterFactory(new DateToStringConverterFactory());
			LOGGER.info("init method Ended");
		} catch (Exception ex) {
			LOGGER.error(ex);
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
