package com.stpl.app.security.userGrpBsnsRoleMgmt.ui.layout;

import com.stpl.app.security.businessRoleModuleMaster.dto.SearchBusinessRoleModuleForm;
import com.stpl.app.security.businessRoleModuleMaster.logic.BusinessRoleModuleSearchLogic;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.ui.Notification;

public class ActionButtonLayout extends HorizontalLayout {

	private static final long serialVersionUID = 1L;
		BusinessRoleModuleSearchLogic businessRoleModuleLogic = new BusinessRoleModuleSearchLogic();
	
	BeanItemContainer<SearchBusinessRoleModuleForm> searchResultbeans;

	public ActionButtonLayout(BeanItemContainer<SearchBusinessRoleModuleForm> searchResultbeans) {
		super();
		this.searchResultbeans =searchResultbeans;
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
		btnSearch.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			
			public void buttonClick(ClickEvent event) {
					businessRoleModuleLogic.saveBusinessRoleModuleMaster(searchResultbeans.getItemIds());

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
