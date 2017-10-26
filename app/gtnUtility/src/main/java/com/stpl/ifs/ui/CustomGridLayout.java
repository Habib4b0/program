package com.stpl.ifs.ui;


import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;

/**
 * Creates the Grid layout
 * @author shrihariharan
 */
@SuppressWarnings("serial")
public class CustomGridLayout extends GridLayout {
    
        /**
         * creates grid layout
         * @param columns
         * @param rows 
         */
	public CustomGridLayout(int columns,int rows){
		super(columns,rows);		
	}
        /**
         * Adds component sequentially
         * @param labelComponent
         * @param fieldComponent
         * @param appPermission 
         */
	 public void addComponentInGrid(Component labelComponent,Component fieldComponent,boolean appPermission){
		 if(appPermission){
		 this.addComponent(labelComponent);
		 this.addComponent(fieldComponent);
		 }		 
	 }	 
	 
	 /**
          * Adds component in the particular position
          * @param labelComponent
          * @param columnLabel
          * @param rowLabel
          * @param fieldComponent
          * @param columnField
          * @param rowField
          * @param appPermission 
          */
	 public void addComponentInGridPosition(Component labelComponent,int columnLabel,int rowLabel,Component fieldComponent,int columnField, int rowField,boolean appPermission) {
		 if(appPermission){
		 this.addComponent(labelComponent,columnLabel,rowLabel);
		 this.addComponent(fieldComponent,columnField,rowField);
		 }		 
	 }
	

}
