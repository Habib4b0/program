package com.stpl.app.security.businessRoleModuleMaster.ui.view;


import com.stpl.app.security.businessRoleModuleMaster.dto.SearchBusinessRoleModuleForm;
import com.stpl.app.security.businessRoleModuleMaster.ui.form.SearchForm;
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
        
	private final BeanItemContainer<SearchBusinessRoleModuleForm> searchResultbeans = new BeanItemContainer<SearchBusinessRoleModuleForm>(
			SearchBusinessRoleModuleForm.class);
	private final BeanItemContainer<SearchBusinessRoleModuleForm> searchFieldResult = new BeanItemContainer<SearchBusinessRoleModuleForm>(
			SearchBusinessRoleModuleForm.class);
	private final ExtFilterTable table=new ExtFilterTable();
	private final ExtFilterTable tableResult=new ExtFilterTable();
	public BusinessRoleModuleMasterSearchView(){		
       super();
         userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
		setSpacing(true);		
		addComponent(new SearchForm(searchResultbeans,searchFieldResult,table,tableResult,userId));
		setComponentError(new UserError(""));
	}
        @Override
	public void enter(ViewChangeEvent event) {
            LOGGER.debug("Inside overriden method of enter: Class BusinessRoleModuleMasterSearchView");
	}
	

}
