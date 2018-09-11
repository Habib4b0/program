/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.security.busineessRoleMgmt.ui.layout;

import com.stpl.app.security.busineessRoleMgmt.dto.BusinessroleMasterDTO;
import com.stpl.app.security.busineessRoleMgmt.logic.BusinessRoleMgmtLogic;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.vaadin.v7.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.v7.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.v7.ui.Table;
import com.stpl.app.security.businessRoleModuleMaster.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @author suthan
 */
public class ButtonLayout extends HorizontalLayout {

	private static final long serialVersionUID = -8493167007955745933L;
	private final ErrorfulFieldGroup binder;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ButtonLayout.class.getName());
	private final BeanItemContainer<BusinessroleMasterDTO> searchResultbeans;
	private Table table;
	private final Label space = new Label("&nbsp;", ContentMode.HTML);
	private final BusinessRoleMgmtLogic businessRoleMgmtLogic = new BusinessRoleMgmtLogic();

	public ButtonLayout(ErrorfulFieldGroup binder,
			BeanItemContainer<BusinessroleMasterDTO> searchResultbeans,
			Table table) {
		super();
		this.searchResultbeans = searchResultbeans;
		this.binder = binder;
		this.setTable(table);
		init();
	}

	private void init() {
		this.setSpacing(true);
		this.addComponent(space);
		cancelButton();
		saveButton();
	}

	private void cancelButton() {
		Button btnReset = new Button("Cancel");
		btnReset.setWidth("75");
		btnReset.setErrorHandler(new ErrorHandler() {

            private static final long serialVersionUID = 1L;

            @Override
            public void error(com.vaadin.server.ErrorEvent event) {
                    return;
            

            }
    });
		btnReset.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

                        @Override
			public void buttonClick(ClickEvent event) {
				binder.discard();
				binder.setItemDataSource(new BeanItem<BusinessroleMasterDTO>(
						new BusinessroleMasterDTO()));
				binder.getField(CommonUtils.BUSINESS_ROLE_MASTERSID).setReadOnly(true);
			}
		});
		this.addComponent(btnReset);
	}

	private void saveButton() {
		Button btnSearch = new Button("Save");
		btnSearch.setWidth("75");
                LOGGER.debug("In Save");
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
				LOGGER.debug("Clicked on Save");
				try {
					binder.commit();
					String businessroleName = binder
							.getField("businessroleName").getValue().toString();
					String businessroleDesc = binder
							.getField("businessroleDesc").getValue().toString();
					String businessroleMasterSid = binder
							.getField(CommonUtils.BUSINESS_ROLE_MASTERSID).getValue()
							.toString();
					String hierarchyLevel = binder.getField("hierarchyLevel")
							.getValue().toString();
					if (!"".equals(businessroleName)
							|| !"".equals(businessroleDesc)
							|| !"".equals(businessroleMasterSid)
							|| !"".equals(hierarchyLevel)
							) {
						String flag = businessRoleMgmtLogic
								.saveBusinessRoleMgmt(binder);
						if (flag != null && "success".equals(flag)) {
							searchResultbeans.removeAllItems();
							searchResultbeans.addAll(businessRoleMgmtLogic
									.getAllBusinessroles());
						} else {
							Notification notif = new Notification(
									"Role Aleady Exists",
									Notification.Type.ERROR_MESSAGE);
							notif.setPosition(Position.MIDDLE_CENTER);
							notif.setStyleName("mystyle");
							notif.show(Page.getCurrent());
						}
					} else {
						Notification notif = new Notification(
								"Enter Values to Save",
								Notification.Type.ERROR_MESSAGE);
						notif.setPosition(Position.MIDDLE_CENTER);
						notif.setStyleName("mystyle");
						notif.show(Page.getCurrent());
					}
				}   catch (CommitException e) {
					LOGGER.error(e.getMessage());
                                      
				} 

				binder.discard();
				binder.setItemDataSource(new BeanItem<BusinessroleMasterDTO>(
						new BusinessroleMasterDTO()));
				binder.getField(CommonUtils.BUSINESS_ROLE_MASTERSID).setReadOnly(true);
			}
		});
		this.addComponent(btnSearch);
	}

	public Table getTable() {
		return table;
	}

	public final void setTable(Table table) {
		this.table = table;
	}
}
