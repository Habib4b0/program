package com.stpl.app.security.busineessRoleMgmt.ui.layout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stpl.app.security.busineessRoleMgmt.dto.BusinessroleMasterDTO;
import com.stpl.app.security.busineessRoleMgmt.logic.BusinessRoleMgmtLogic;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.stpl.app.security.businessRoleModuleMaster.util.CommonUtils;

public class DeleteButtonLayout extends HorizontalLayout {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager
			.getLogger(DeleteButtonLayout.class.getName());
	private ErrorfulFieldGroup binder;
	BeanItemContainer<BusinessroleMasterDTO> searchResultbeans;
	Table table;
	BusinessRoleMgmtLogic businessRoleMgmtLogic = new BusinessRoleMgmtLogic();

        public DeleteButtonLayout(ErrorfulFieldGroup binder,
			BeanItemContainer<BusinessroleMasterDTO> searchResultbeans,
			Table table) {
		super();
		this.searchResultbeans = searchResultbeans;
		this.binder = binder;
		this.table = table;
		init();
	}

	private void init() {
		this.setSpacing(true);
		DeleteButton();
	}

	private void DeleteButton() {
		Button btnSearch = new Button("Delete");
		btnSearch.setErrorHandler(new ErrorHandler() {

            private static final long serialVersionUID = 1L;

            public void error(com.vaadin.server.ErrorEvent event) {
                   
            

            }
    });		
		btnSearch.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				try {
					binder.getFields();
					binder.commit();
					
					if(binder.getField(CommonUtils.BUSINESS_ROLE_MASTERSID).getValue()!=null && !"".equals(binder.getField(CommonUtils.BUSINESS_ROLE_MASTERSID).getValue().toString())){
						int id = Integer.parseInt(binder.getField(CommonUtils.BUSINESS_ROLE_MASTERSID).getValue().toString());
						businessRoleMgmtLogic.deleteBusinessRole(id);
						searchResultbeans.removeAllItems();
						searchResultbeans.addAll(businessRoleMgmtLogic
								.getAllBusinessroles());
						binder.discard();
						binder.setItemDataSource(new BeanItem<BusinessroleMasterDTO>(
								new BusinessroleMasterDTO()));
						
					}else{
						Notification notif = new Notification("Select Record to delete", Notification.Type.ERROR_MESSAGE);
						notif.setPosition(Position.MIDDLE_CENTER);
						notif.setStyleName("mystyle");
						notif.show(Page.getCurrent());
					}
				}catch (SystemException e) {
					// TODO Auto-generated catch block
				LOGGER.error(e);
				} catch (PortalException e) {
					// TODO Auto-generated catch block
					LOGGER.error(e);
				} catch (CommitException e) {
                                     LOGGER.error(e);
				}
				binder.getField(CommonUtils.BUSINESS_ROLE_MASTERSID).setReadOnly(true);
			}
		});
		this.addComponent(btnSearch);
	}

}
