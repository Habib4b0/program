package com.stpl.app.ui;

import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.vaadin.v7.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractActionButtonLayout extends HorizontalLayout{

	private static final long serialVersionUID = 1L;
        
        private static final Logger LOGGER = LoggerFactory.getLogger(AbstractActionButtonLayout.class);

	protected ErrorfulFieldGroup binder;
	protected boolean enableDelete=false;
	protected boolean readOnly=true;
	protected boolean enableBack=false;

	public AbstractActionButtonLayout(ErrorfulFieldGroup binder) {
		super();
		this.binder = binder;
		init();
	}

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

	private void saveButton() {
		// Commit button
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
                                     LOGGER.error(e.getMessage());
					
				}
			}
		});
		this.addComponent(btnUpdate);
	}

	private void deleteButton() {
		// Commit button
		Button btnSearch = new Button("Delete");
		btnSearch.setWidth("75");
		btnSearch.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				deleteLogic();
				
				Notification notif = new Notification(
						"Deletion Completed",
						Notification.Type.HUMANIZED_MESSAGE);
				notif.setPosition(Position.MIDDLE_CENTER);
				notif.setStyleName("mystyle");
				notif.show(Page.getCurrent());
			}
		});
		this.addComponent(btnSearch);
	}

	private void backButton() {
		Button btnSearch = new Button("Back");
		btnSearch.setWidth("75");
		btnSearch.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				binder.discard();
				getUI().getNavigator().navigateTo("");
			}
		});
		this.addComponent(btnSearch);
	}
	protected abstract void saveLogic(); 
	protected abstract void deleteLogic(); 

}
