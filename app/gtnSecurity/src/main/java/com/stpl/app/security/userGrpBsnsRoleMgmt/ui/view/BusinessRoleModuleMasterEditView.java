package com.stpl.app.security.userGrpBsnsRoleMgmt.ui.view;

import com.stpl.app.security.businessRoleModuleMaster.dto.BusinessRoleModuleMasterDTO;
import com.stpl.app.security.businessRoleModuleMaster.ui.form.ViewForm;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.UserError;
import com.vaadin.ui.VerticalLayout;

public class BusinessRoleModuleMasterEditView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "view";
	
	private BusinessRoleModuleMasterDTO businessRoleModuleMasterDTO = new BusinessRoleModuleMasterDTO();
	private ErrorfulFieldGroup binder = new ErrorfulFieldGroup(
			 new BeanItem<BusinessRoleModuleMasterDTO>(businessRoleModuleMasterDTO));
	 
	 
	public BusinessRoleModuleMasterEditView(){		
		setSpacing(true);
		addComponent(new ViewForm(businessRoleModuleMasterDTO,binder));
		setComponentError(new UserError(""));
	}
	
	public void enter(ViewChangeEvent event) {
            return;
	}
}
