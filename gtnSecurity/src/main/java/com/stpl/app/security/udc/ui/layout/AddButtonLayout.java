package com.stpl.app.security.udc.ui.layout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stpl.app.model.HelperTable;
import com.stpl.app.security.udc.dto.HelperForm;
import com.stpl.app.security.udc.logic.UdcLogic;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;

public class AddButtonLayout extends HorizontalLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8933552027033590861L;
	   private ErrorfulFieldGroup binder;
    UdcLogic udcLogic = new UdcLogic();
    private static final Logger LOGGER = LogManager
            .getLogger(AddButtonLayout.class.getName());
    BeanItemContainer<HelperForm> searchResultbeans;
	
	

	public AddButtonLayout(ErrorfulFieldGroup binder,
			BeanItemContainer<HelperForm> searchResultbeans) {
		super();
		this.binder = binder;
		this.searchResultbeans = searchResultbeans;
		init();
	}
	
 public void init(){
	 addButton();
	 
	 deleteButton();
 }


 private void addButton() {
		// Commit button
		Button btnUpdate = new Button("Save");
		btnUpdate.setWidth("75");
		btnUpdate.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				try {
					binder.getFields();
					binder.commit();
					try {
					
						String success = udcLogic.SaveHelperTable(binder);
					                                   if (success.equals("success")) {
                                            searchResultbeans.removeAllItems();
                                        }
                                        if (success.equals("success")) {
                                            Notification notif = new Notification("Category"
                                                    + binder.getField("category")
                                                    .getValue() + "Saved successfully",
                                                    Notification.Type.HUMANIZED_MESSAGE);

                                            notif.setPosition(Position.MIDDLE_CENTER);
                                            notif.setStyleName("mystyle");
                                            notif.show(Page.getCurrent());
                                        }

                                    } catch (Exception e) {
                                        LOGGER.error(e);

                                    }

				} catch (CommitException e) {
					LOGGER.error(e);

				}
			}
		});
		this.addComponent(btnUpdate);
	}
 private void deleteButton() {
		// Commit button
		Button btnDelete = new Button("Delete");
		btnDelete.setWidth("75");
		btnDelete.setErrorHandler(new ErrorHandler() {

			private static final long serialVersionUID = 1L;

			public void error(com.vaadin.server.ErrorEvent event) {
						
			}
		});
	    btnDelete.addClickListener(new ClickListener() {
         private static final long serialVersionUID = 1L;

         public void buttonClick(ClickEvent event) {

             binder.getFields();

             try {

                 int code = Integer.parseInt(VaadinSession.getCurrent().getAttribute("code").toString());
                 HelperTable helperTable = udcLogic.deleteHelperTableByCode(code);

                 Notification notif = new Notification(" Category " + helperTable.getListName() + " Deleted successfully", Notification.Type.HUMANIZED_MESSAGE);

                 notif.setPosition(Position.MIDDLE_CENTER);
                 notif.setStyleName("mystyle");
                 notif.show(Page.getCurrent());

						
					

					} catch (Exception e) {
						LOGGER.error(e);
						
					}

					
				
			}
		});
		this.addComponent(btnDelete);
	}
}
