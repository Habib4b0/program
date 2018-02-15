package com.stpl.app.security.businessRoleModuleMaster.ui.layout;

import com.stpl.app.security.businessRoleModuleMaster.dto.SearchBusinessRoleModuleForm;
import com.stpl.app.security.businessRoleModuleMaster.logic.BusinessRoleModuleSearchLogic;
import com.stpl.app.security.businessRoleModuleMaster.util.CommonUtils;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.v7.ui.CheckBox;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.ui.Notification;

public class ActionButtonLayout extends HorizontalLayout {

	private static final long serialVersionUID = 1L;

	
	private final BusinessRoleModuleSearchLogic businessRoleModuleLogic = new BusinessRoleModuleSearchLogic();
	
	private final BeanItemContainer<SearchBusinessRoleModuleForm> searchResultbeans;
	private final BeanItemContainer<SearchBusinessRoleModuleForm> searchFieldResult;
	private final ComboBox subModuleName;
        private final ComboBox moduleName;
        private final CheckBox add;
        private final CheckBox edit;
        private final CheckBox view;
        private final String userId;
        private final ComboBox businessRoleName;

	public ActionButtonLayout(BeanItemContainer<SearchBusinessRoleModuleForm> searchResultbeans,BeanItemContainer<SearchBusinessRoleModuleForm> searchFieldResult,
                ComboBox subModuleName,ComboBox moduleName,ComboBox businessRoleName, String userId,CheckBox add,CheckBox edit,CheckBox view) {
		super();
		this.searchResultbeans =searchResultbeans;
		this.searchFieldResult =searchFieldResult;
		this.subModuleName=subModuleName;
		this.moduleName=moduleName;
		this.add=add;
		this.edit=edit;
		this.userId=userId;
		this.view=view;
		this.businessRoleName=businessRoleName;
		init();
	}

	private void init(){
		this.setSpacing(true);
		saveButton();
	}

	private void saveButton() {
		// Commit button
		Button btnSearch = new Button("Save");
		btnSearch.setWidth("75");
		btnSearch.setErrorHandler(new ErrorHandler() {

            private static final long serialVersionUID = 1L;

            @Override
            public void error(com.vaadin.server.ErrorEvent event) {
                    return;
            

            }
    });
		          btnSearch.addClickListener(new ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                              public void buttonClick(ClickEvent event) {
                                  businessRoleModuleLogic.saveBusinessRoleModuleMaster(searchResultbeans.getItemIds(), searchFieldResult.getItemIds(), userId);
                                  searchResultbeans.removeAllItems();
                                  searchFieldResult.removeAllItems();
                                  subModuleName.removeAllItems();
                                  moduleName.removeAllItems();
                                  subModuleName.addItem(CommonUtils.SELECT_ONE);
                                  moduleName.addItem(CommonUtils.SELECT_ONE);
                                  subModuleName.select(CommonUtils.SELECT_ONE);
                                  moduleName.select(CommonUtils.SELECT_ONE);
                                  businessRoleName.select(CommonUtils.SELECT_ONE);
                                  add.setValue(Boolean.FALSE);
                                  edit.setValue(Boolean.FALSE);
                                  view.setValue(Boolean.FALSE);
                                  Notification notif = new Notification("Saved Successfully",
                                  Notification.Type.HUMANIZED_MESSAGE);
                        notif.setPosition(Position.MIDDLE_CENTER);
                        notif.setStyleName("mystyle");
                        notif.show(Page.getCurrent());
		
			}
		});
		this.addComponent(btnSearch);
	}
	}
