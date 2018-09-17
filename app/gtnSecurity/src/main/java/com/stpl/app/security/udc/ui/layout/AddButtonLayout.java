package com.stpl.app.security.udc.ui.layout;

import com.stpl.app.model.HelperTable;
import com.stpl.app.security.udc.dto.HelperForm;
import com.stpl.app.security.udc.logic.UdcLogic;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.vaadin.v7.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddButtonLayout extends HorizontalLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8933552027033590861L;
	   private final ErrorfulFieldGroup binder;
	   private final UdcLogic udcLogic = new UdcLogic();
    private static final Logger LOGGER = LoggerFactory
            .getLogger(AddButtonLayout.class.getName());
    private final BeanItemContainer<HelperForm> searchResultbeans;
	
	

	public AddButtonLayout(ErrorfulFieldGroup binder,
			BeanItemContainer<HelperForm> searchResultbeans) {
		super();
		this.binder = binder;
		this.searchResultbeans = searchResultbeans;
		init();
	}
	
 public final void init(){
	 addButton();
	 
	 deleteButton();
 }


 private void addButton() {
		// Commit button
		Button btnUpdate = new Button("Save");
		btnUpdate.setWidth("75");
		btnUpdate.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

                        @Override
			public void buttonClick(ClickEvent event) {
				try {
					binder.getFields();
					binder.commit();
					
						String success = udcLogic.saveHelperTable(binder);
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

				} catch (CommitException e) {
					LOGGER.error(e.getMessage());

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

                        @Override
			public void error(com.vaadin.server.ErrorEvent event) {
                            return;
			}
		});
	    btnDelete.addClickListener(new ClickListener() {
         private static final long serialVersionUID = 1L;

         @Override
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
						LOGGER.error(e.getMessage());
						
					}

					
				
			}
		});
		this.addComponent(btnDelete);
	}
}
