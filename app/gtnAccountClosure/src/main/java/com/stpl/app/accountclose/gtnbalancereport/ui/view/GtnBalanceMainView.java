/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.ui.view;

import com.stpl.app.accountclose.dto.FixedDollarDTO;
import com.stpl.app.accountclose.gtnbalancereport.ui.form.DataSelectionIndex;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.jboss.logging.Logger;

/**
 *
 * @author santanukumar
 */
public class GtnBalanceMainView extends VerticalLayout implements View{
     /**
     * View name for navigation.
     */
    public static final String NAME="";
    
    private static final Logger LOGGER = Logger.getLogger(GtnBalanceMainView.class);
    /**
     * Default constructor
     */
    public GtnBalanceMainView(){
       try{
            addComponent(new DataSelectionIndex());
       }catch(Exception e){
           LOGGER.error(e);
       } 
        
       
        }
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
        
    }
    
}
