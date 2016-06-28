package com.stpl.app.ui;

import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;

public abstract class AbstractActionButtonLayout extends HorizontalLayout{

	private static final long serialVersionUID = 1L;

	protected ErrorfulFieldGroup binder;
//	protected boolean enableReset=false;
	protected boolean enableDelete=false;
	protected boolean readOnly=true;
	protected boolean enableBack=false;

	public AbstractActionButtonLayout(ErrorfulFieldGroup binder) {
		super();
		this.binder = binder;
		init();
	}

	private void init() {
		
	/*	if(!readOnly&&enableReset){
			resetButton();
		}*/
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
					// Customize it
//						notif.setDelayMsec(20000);
					notif.setPosition(Position.MIDDLE_CENTER);
					notif.setStyleName("mystyle");
					notif.show(Page.getCurrent());
				} catch (CommitException e) {
					
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
				// Customize it
//					notif.setDelayMsec(20000);
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

	/*private void resetButton() {
		Button btnSearch = new Button("Reset");
		btnSearch.setWidth("75");
		btnSearch.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				binder.discard();
				Notification notif = new Notification("Reset Completed",
						Notification.Type.HUMANIZED_MESSAGE);
				// Customize it
//				notif.setDelayMsec(20000);
				notif.setPosition(Position.MIDDLE_CENTER);
				notif.setStyleName("mystyle");
				notif.show(Page.getCurrent());
			}
		});
		this.addComponent(btnSearch);
	}*/
	
	
	protected abstract void saveLogic(); 
	protected abstract void deleteLogic(); 

}
