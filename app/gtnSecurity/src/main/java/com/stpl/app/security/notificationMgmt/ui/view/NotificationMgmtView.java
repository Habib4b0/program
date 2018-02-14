/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.security.notificationMgmt.ui.view;

import com.stpl.app.security.notificationMgmt.ui.form.NotificationMgmtIndex;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.v7.ui.VerticalLayout;

/**
 *
 * @author Manasa
 */
public class NotificationMgmtView extends VerticalLayout implements View{
    private static final long serialVersionUID = 1L;
	public static final String NAME = "";    
        
         public NotificationMgmtView(){
        setSpacing(true); 
        addComponent(new NotificationMgmtIndex());
    }
        
    @Override
    public void enter(ViewChangeEvent event) {
        return;
		
	}

}
