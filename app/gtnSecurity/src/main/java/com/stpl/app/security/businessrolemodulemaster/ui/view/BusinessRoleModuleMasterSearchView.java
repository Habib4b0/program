package com.stpl.app.security.businessrolemodulemaster.ui.view;


import com.stpl.app.security.businessrolemodulemaster.dto.SearchBusinessRoleModuleForm;
import com.stpl.app.security.businessrolemodulemaster.ui.form.SearchForm;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinSession;
import com.vaadin.v7.ui.VerticalLayout;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BusinessRoleModuleMasterSearchView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "";
	private static  String userId = "";
        private static final Logger LOGGER = LoggerFactory.getLogger(BusinessRoleModuleMasterSearchView.class
			.getName());
        
	public BusinessRoleModuleMasterSearchView(){		
       super();
         userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
		setSpacing(true);		
		addComponent(new SearchForm(new BeanItemContainer<>(SearchBusinessRoleModuleForm.class),new BeanItemContainer<>(SearchBusinessRoleModuleForm.class),new ExtFilterTable(),new ExtFilterTable(),userId));
		setComponentError(new UserError(""));
	}
        @Override
	public void enter(ViewChangeEvent event) {
            LOGGER.debug("Inside overriden method of enter: Class BusinessRoleModuleMasterSearchView");
	}
	

}
