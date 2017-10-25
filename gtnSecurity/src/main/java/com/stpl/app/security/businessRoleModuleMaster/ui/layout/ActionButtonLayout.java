package com.stpl.app.security.businessRoleModuleMaster.ui.layout;

import com.stpl.app.security.businessRoleModuleMaster.dto.SearchBusinessRoleModuleForm;
import com.stpl.app.security.businessRoleModuleMaster.logic.BusinessRoleModuleSearchLogic;
import com.stpl.app.security.businessRoleModuleMaster.util.CommonUtils;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;

public class ActionButtonLayout extends HorizontalLayout {

	private static final long serialVersionUID = 1L;

	
	BusinessRoleModuleSearchLogic businessRoleModuleLogic = new BusinessRoleModuleSearchLogic();
	
	BeanItemContainer<SearchBusinessRoleModuleForm> searchResultbeans;
	BeanItemContainer<SearchBusinessRoleModuleForm> searchFieldResult;
        ComboBox subModuleName;
	ComboBox moduleName;
	CheckBox add;
	CheckBox edit;
	CheckBox view;
        String userId;
	ComboBox businessRoleName;

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
		SaveButton();
	}

	private void SaveButton() {
		// Commit button
		Button btnSearch = new Button("Save");
		btnSearch.setWidth("75");
		btnSearch.setErrorHandler(new ErrorHandler() {

            private static final long serialVersionUID = 1L;

            public void error(com.vaadin.server.ErrorEvent event) {
                   
            

            }
    });
		          btnSearch.addClickListener(new ClickListener() {
                private static final long serialVersionUID = 1L;

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
