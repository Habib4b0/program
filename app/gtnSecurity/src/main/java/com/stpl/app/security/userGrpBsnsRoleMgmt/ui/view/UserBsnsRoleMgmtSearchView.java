package com.stpl.app.security.userGrpBsnsRoleMgmt.ui.view;



import com.stpl.app.security.userGrpBsnsRoleMgmt.ui.form.SearchForm;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.UserError;
import com.vaadin.v7.ui.VerticalLayout;

public class UserBsnsRoleMgmtSearchView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "";
	public UserBsnsRoleMgmtSearchView(){		
		
		setSpacing(true);
		addComponent(new SearchForm());
		setComponentError(new UserError(""));
	}
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	

}
