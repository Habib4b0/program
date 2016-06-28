package com.stpl.app.security.udc.ui.view;

import com.stpl.app.security.udc.dto.HelperForm;
import com.stpl.app.security.udc.ui.form.UdcHelperForm;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.UserError;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class UdcView extends VerticalLayout implements View {

	public static final String NAME = "";
	BeanItemContainer<HelperForm> searchResultbeans = new BeanItemContainer<HelperForm>(
			HelperForm.class);
	
	Table table=new Table();
	
	public UdcView()
	{		
		setSpacing(true);
		addComponent(new UdcHelperForm(searchResultbeans,table));
		//addComponent(new UdcResults(searchResultbeans,table));		
		// layout.setImmediate(true);
		setComponentError(new UserError(""));
	}
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
