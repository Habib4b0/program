package com.stpl.app.security.usergrpbsnsrolemgmt.ui.layout;

import java.util.List;
import com.stpl.app.security.businessrolemodulemaster.dto.SearchBusinessRoleModuleForm;
import com.stpl.app.security.businessrolemodulemaster.dto.SearchDTO;
import com.stpl.app.security.businessrolemodulemaster.logic.BusinessRoleModuleSearchLogic;
import com.stpl.app.security.businessrolemodulemaster.util.UIUtils;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.v7.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.NativeSelect;
import com.vaadin.v7.ui.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchButtonLayout extends HorizontalLayout {

	private static final long serialVersionUID = 1L;

	
	private ErrorfulFieldGroup binder;
	private final BusinessRoleModuleSearchLogic businessRoleModuleLogic=new BusinessRoleModuleSearchLogic();
	
	private BeanItemContainer<SearchBusinessRoleModuleForm> searchResultbeans;
	private Table table;
	private NativeSelect subModuleName;
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchButtonLayout.class
			.getName());

	public SearchButtonLayout() {
		super();
		init();
	}

	private void init(){
		this.setSpacing(true);
		searchButton();
		resetButton();
	}

	private void searchButton() {
		// Commit button
		Button btnSearch = new Button("Search");
		btnSearch.setWidth("75");
		btnSearch.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			
                        @Override
			public void buttonClick(ClickEvent event) {
				try {
					binder.getFields();
					binder.commit();
						List<SearchBusinessRoleModuleForm> searchResults=businessRoleModuleLogic.searchmoduleAccessDetails(binder);
						searchResultbeans.removeAllItems();
						searchResultbeans.addAll(searchResults);
						table.setVisibleColumns(UIUtils.getInstance().businessRoleModuleMasterCol);
					
				} catch (SystemException | PortalException | CommitException e) {
					LOGGER.error(e.getMessage());
				}
			}
		});
		this.addComponent(btnSearch);
	}

	private void resetButton() {
		Button btnReset = new Button("Reset");
		btnReset.setWidth("75");
		btnReset.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

                        @Override
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
