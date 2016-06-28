package com.stpl.app.security.busineessRoleMgmt.ui.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stpl.app.security.busineessRoleMgmt.dto.BusinessroleMasterDTO;
import com.stpl.app.security.busineessRoleMgmt.logic.BusinessRoleMgmtLogic;
import com.stpl.app.security.busineessRoleMgmt.ui.form.SearchResults;
import com.stpl.app.security.businessRoleModuleMaster.ui.layout.ActionButtonLayout;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.UserError;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class BusinessRoleMgmtView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "";
	
	final ErrorLabel errorMsg = new ErrorLabel();
	
	BusinessRoleMgmtLogic businessRoleMgmtLogic = new BusinessRoleMgmtLogic();
	BeanItemContainer<BusinessroleMasterDTO> searchResultbeans = new BeanItemContainer<BusinessroleMasterDTO>(
			BusinessroleMasterDTO.class);
	Table table=new Table();
	
	private static final Logger LOGGER =
			 LogManager.getLogger(ActionButtonLayout.class
			 .getName());
	
	
	public BusinessRoleMgmtView()
	{		
		setSpacing(true);
		loadAllBusinessRoles();
		addComponent(new SearchResults(getBinder(),searchResultbeans,table));
//		addComponent(new AddUpdateForm(getBinder(),searchResultbeans,table));
		setComponentError(new UserError(""));
	}

	private void loadAllBusinessRoles() {
		searchResultbeans.removeAllItems();
		List<BusinessroleMasterDTO> searchResults = new ArrayList<BusinessroleMasterDTO>();
		try {
			searchResults = businessRoleMgmtLogic.getAllBusinessroles();
			
		} catch (SystemException e) {
			LOGGER.error(e);
			
		} catch (PortalException e) {
			LOGGER.error(e);
		}
		searchResultbeans.addAll(searchResults);
		
	}

	private ErrorfulFieldGroup getBinder() {
		BusinessroleMasterDTO bean = new BusinessroleMasterDTO();
		ErrorfulFieldGroup binder = new ErrorfulFieldGroup(
				new BeanItem<BusinessroleMasterDTO>(bean));
		binder.setBuffered(true);
		binder.bindMemberFields(this);
		binder.setErrorDisplay(errorMsg);
		return binder;
	}
	
	public void enter(ViewChangeEvent event) {
//		searchResultbeans.removeAllItems();		
	}

}
