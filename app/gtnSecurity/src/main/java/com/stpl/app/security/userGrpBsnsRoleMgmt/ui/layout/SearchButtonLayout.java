package com.stpl.app.security.userGrpBsnsRoleMgmt.ui.layout;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stpl.app.security.businessRoleModuleMaster.dto.SearchBusinessRoleModuleForm;
import com.stpl.app.security.businessRoleModuleMaster.dto.SearchDTO;
import com.stpl.app.security.businessRoleModuleMaster.logic.BusinessRoleModuleSearchLogic;
import com.stpl.app.security.businessRoleModuleMaster.util.UIUtils;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Table;

public class SearchButtonLayout extends HorizontalLayout {

	private static final long serialVersionUID = 1L;

	
	private ErrorfulFieldGroup binder;
	BusinessRoleModuleSearchLogic businessRoleModuleLogic=new BusinessRoleModuleSearchLogic();
	
	BeanItemContainer<SearchBusinessRoleModuleForm> searchResultbeans;
	Table table;
	NativeSelect subModuleName;
	private static final Logger LOGGER = LogManager.getLogger(SearchButtonLayout.class
			.getName());

	public SearchButtonLayout() {
		super();
		init();
	}

	private void init(){
		this.setSpacing(true);
		SearchButton();
		ResetButton();
	}

	private void SearchButton() {
		// Commit button
		Button btnSearch = new Button("Search");
		btnSearch.setWidth("75");
		btnSearch.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			
			public void buttonClick(ClickEvent event) {
				try {
					binder.getFields();
					binder.commit();
					
					try {
						
						List<SearchBusinessRoleModuleForm> searchResults=businessRoleModuleLogic.searchmoduleAccessDetails(binder);
						searchResultbeans.removeAllItems();
						searchResultbeans.addAll(searchResults);
						table.setVisibleColumns(UIUtils.getInstance().businessRoleModuleMasterCol);
					} catch (SystemException e) {
						LOGGER.error(e);
						
					} catch (PortalException e) {
						LOGGER.error(e);
					}
					
				} catch (CommitException e) {
					LOGGER.error(e);
				}
			}
		});
		this.addComponent(btnSearch);
	}

	private void ResetButton() {
		Button btnReset = new Button("Reset");
		btnReset.setWidth("75");
		btnReset.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				binder.discard();
				binder.setItemDataSource(new BeanItem<SearchDTO>(new SearchDTO()));
				searchResultbeans.removeAllItems();
				subModuleName.removeAllItems();
			}
		});
		this.addComponent(btnReset);
	}

}
