package com.stpl.app.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.TabSheet;

public interface AbstractDetailForm  extends  View{
//	public static final String NAME = "view";
//	final ErrorLabel errorMsg = new ErrorLabel();
//	final Label space = new Label("&nbsp;", ContentMode.HTML);
//	
//	protected String spaceHeight=StringUtils.EMPTY;

//	 ErrorfulFieldGroup binder;
	
	/*public AbstractDetailForm(ErrorfulFieldGroup binder) {
		this.binder = binder;	
		init();

	}*/
	
	/*public AbstractDetailForm(ErrorfulFieldGroup binder,String spaceHeight) {
		this.binder = binder;	
		this.spaceHeight = spaceHeight;
		init();
		getBinder();
		configureFields();
	}*/
	

	public void init();
//		if(StringUtils.isNotBlank(spaceHeight)){
//			space.setHeight(spaceHeight);
//		}else{
//			space.setHeight("30");
//		}
//		addToContent();
	

	/*private void addToContent() {
		final FormLayout content = new FormLayout();
		content.addComponentAsFirst(space);
		content.addComponent(errorMsg);
		content.addComponent(space);
		content.addComponent(addTabSheet());
		content.addComponent(space);
		content.addComponent(addActionBtn());
	}*/
	
	/*private ErrorfulFieldGroup getBinder() {	
		binder.bindMemberFields(this);
		 binder.setBuffered(true);		
		 binder.setErrorDisplay(errorMsg);
		 return binder;
		 }*/
	
	public abstract TabSheet addTabSheet();
	public abstract AbstractActionButtonLayout addActionBtn();
	public abstract void configureFields();
	public void enter(ViewChangeEvent event);
}
