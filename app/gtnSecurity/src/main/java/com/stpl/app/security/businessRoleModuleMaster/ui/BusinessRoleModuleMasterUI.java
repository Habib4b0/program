package com.stpl.app.security.businessRoleModuleMaster.ui;


import com.stpl.app.security.businessRoleModuleMaster.ui.view.BusinessRoleModuleMasterSearchView;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")

public class BusinessRoleModuleMasterUI extends UI {
	
@AutoGenerated
private Navigator navigator;
	@Override
	protected void init(VaadinRequest request) {
                addStyleName("bootstrap");
                addStyleName("bootstrap-bb");
                final String userId = request.getRemoteUser();
                VaadinSession.getCurrent().setAttribute("userId", userId);
		navigator=new Navigator(this, this);
		
		navigator.addView(BusinessRoleModuleMasterSearchView.NAME, new BusinessRoleModuleMasterSearchView());
		setComponentError(new UserError(""));
	}
}
