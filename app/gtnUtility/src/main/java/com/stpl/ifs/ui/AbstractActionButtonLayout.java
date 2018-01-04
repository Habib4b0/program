package com.stpl.ifs.ui;

import com.vaadin.v7.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import org.jboss.logging.Logger;



/**
 * Class contains the abstract methods for save, delete and back buttons, by overriding the methods we can change the logic.
 * 
 * @author shrihariharan
 */
public abstract class AbstractActionButtonLayout extends HorizontalLayout{

	private static final long serialVersionUID = 1L;

	
         protected CustomFieldGroup binder;
        /**
         * Decides the visibilities of Delete Button
         */
	protected boolean enableDelete=false;
        /**
         * Decides the visibilities of Save Button
         */
	protected boolean readOnly=true;
        /**
         * Decides the visibilities of Back Button
         */
	protected boolean enableBack=false;

        /**
         * 
         * @param binder 
         */
	public AbstractActionButtonLayout(CustomFieldGroup binder) {
		super();
		this.binder = binder;
		init();
	}

        /**
         * Loads the Save, Delete and back buttons
         */
	private void init() {

		if(!readOnly){
			saveButton();
		}
		if(enableDelete){
			deleteButton();
		}
		if(enableBack){
			backButton();
		}		
	}

        /**
         * Save button configuration
         */
	private void saveButton() {
		Button btnUpdate = new Button("Save");
		btnUpdate.setWidth("75");
		btnUpdate.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				try {
					binder.getFields();
					binder.commit();

					saveLogic();

					Notification notif = new Notification(
							"Save Completed",
							Notification.Type.HUMANIZED_MESSAGE);

					notif.setPosition(Position.MIDDLE_CENTER);
					notif.setStyleName("mystyle");
					notif.show(Page.getCurrent());
				} catch (CommitException e) {
                                    Logger.getLogger(AbstractActionButtonLayout.class).error(e);
                            }
			}
		});
		this.addComponent(btnUpdate);
	}

        /**
         * Delete button configuration
         */
	private void deleteButton() {
                Button btnSearch = new Button("Delete");
		btnSearch.setWidth("75");
		btnSearch.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
			try {	
                            deleteLogic();
				
				Notification notif = new Notification(
						"Deletion Completed",
						Notification.Type.HUMANIZED_MESSAGE);

				notif.setPosition(Position.MIDDLE_CENTER);
				notif.setStyleName("mystyle");
				notif.show(Page.getCurrent());
                                } catch (Exception e) {
				Logger.getLogger(AbstractActionButtonLayout.class).error(e);
                            }
			}
		});
		this.addComponent(btnSearch);
	}

        /**
         * Back button configuration
         */
	private void backButton() {
		Button btnSearch = new Button("Back");
		btnSearch.setWidth("75");
		btnSearch.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
                            try {
				binder.discard();
				getUI().getNavigator().navigateTo("");
                                } catch (Exception e) {
				Logger.getLogger(AbstractActionButtonLayout.class).error(e);
                            }
			}
		});
		this.addComponent(btnSearch);
	}


	protected abstract void saveLogic(); 
	protected abstract void deleteLogic(); 

}
