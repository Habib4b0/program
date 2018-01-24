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

public class BusinessRoleModuleMasterSearchView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "";
	private static  String userId = "";
        
	private BeanItemContainer<SearchBusinessRoleModuleForm> searchResultbeans = new BeanItemContainer<SearchBusinessRoleModuleForm>(
			SearchBusinessRoleModuleForm.class);
	private BeanItemContainer<SearchBusinessRoleModuleForm> searchFieldResult = new BeanItemContainer<SearchBusinessRoleModuleForm>(
			SearchBusinessRoleModuleForm.class);
	private ExtFilterTable table=new ExtFilterTable();
	private ExtFilterTable tableResult=new ExtFilterTable();
	public BusinessRoleModuleMasterSearchView(){		
       super();
         userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
		setSpacing(true);		
		addComponent(new SearchForm(searchResultbeans,searchFieldResult,table,tableResult,userId));
		setComponentError(new UserError(""));
	}
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	

}
