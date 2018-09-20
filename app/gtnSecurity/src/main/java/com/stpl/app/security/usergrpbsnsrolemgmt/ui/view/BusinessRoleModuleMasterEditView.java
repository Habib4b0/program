package com.stpl.app.security.usergrpbsnsrolemgmt.ui.view;

import com.stpl.app.security.businessrolemodulemaster.dto.BusinessRoleModuleMasterDTO;
import com.stpl.app.security.businessrolemodulemaster.ui.form.ViewForm;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.UserError;
import com.vaadin.v7.ui.VerticalLayout;

public class BusinessRoleModuleMasterEditView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "view";
	 
	public BusinessRoleModuleMasterEditView(){		
		setSpacing(true);
		addComponent(new ViewForm(new BusinessRoleModuleMasterDTO(),new ErrorfulFieldGroup(new BeanItem<>(new BusinessRoleModuleMasterDTO()))));
		setComponentError(new UserError(""));
	}
	
        @Override
	public void enter(ViewChangeEvent event) {
            return;
	}
}
