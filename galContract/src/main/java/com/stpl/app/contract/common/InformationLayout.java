/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.contract.common;

import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.ResourceBundle;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 * Common Class to add the information layout to all the modules.
 * 
 * @author sibi
 */
public class InformationLayout extends VerticalLayout {
   
    @UiField("label1")
    private Label idLabel;
    
    @UiField("label2")
    private Label noLabel;
    
    @UiField("label3")
    private Label nameLabel;
    
    @UiField("textField1")
    private TextField idTextField;
    
    @UiField("textField2")
    private TextField noTextField;
    
    @UiField("textField3")
    private TextField nameTextField;
    
    @UiField("panel")
    private Panel panel;
      
    public static final ResourceBundle labelBundle = ResourceBundle.getBundle("properties.labelname");
    
   /**
    * Constructor for the information layout. 
    * 
    * @param moduleName - Module name should be in lower case and separated by '_'.
    * @param id
    * @param no
    * @param name 
    */
    public InformationLayout(final String moduleName,final String id,final String no,final String name){            
       init(); 
       configureLabels(moduleName);
       configureFields(id,no,name);       
    }
    
    /**
     * Init method to load the layout.
     */
    private void init(){
        this.addComponent(Clara.create(getClass().getResourceAsStream("/clara/common/information-layout.xml"), this));
    }
    
   /**
    * Configure the label based on the module name.
    */
    private void configureLabels(final String moduleName) {        
        String infomationValues = labelBundle.getString(moduleName+"_information_label");
        String[] values = infomationValues.split(",");
        idLabel.setValue(values[0].trim());
        noLabel.setValue(values[1].trim());
        nameLabel.setValue(values[2].trim());
        
        String panelValues = labelBundle.getString(moduleName+"_panel_caption");
        panel.setCaption(panelValues);
    }
    
    /**
     * Configure and populate the values to the fields.
     */
    private void configureFields(final String id,final String no,final String name){                        
        if(!id.equals("null"))
           idTextField.setValue(id);
        else
            idTextField.setValue("");
        if(!no.equals("null"))
            noTextField.setValue(no);
        else
            noTextField.setValue("");
        
        if(!name.equals("null"))
            nameTextField.setValue(name);          
        else
            nameTextField.setValue("");          
    }
    
}
