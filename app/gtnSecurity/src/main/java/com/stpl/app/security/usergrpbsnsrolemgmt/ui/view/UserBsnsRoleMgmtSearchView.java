package com.stpl.app.security.usergrpbsnsrolemgmt.ui.view;

import com.stpl.app.security.usergrpbsnsrolemgmt.ui.form.SearchForm;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.UserError;
import com.vaadin.v7.ui.VerticalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserBsnsRoleMgmtSearchView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "";
        private static final Logger LOGGER = LoggerFactory.getLogger(UserBsnsRoleMgmtSearchView.class
			.getName());
	public UserBsnsRoleMgmtSearchView(){		
		
		setSpacing(true);
		addComponent(new SearchForm());
		setComponentError(new UserError(""));
	}
        @Override
	public void enter(ViewChangeEvent event) {
               LOGGER.debug("Inside overriden method of enter: Class UserBsnsRoleMgmtSearchView");
		
	}
	

}
