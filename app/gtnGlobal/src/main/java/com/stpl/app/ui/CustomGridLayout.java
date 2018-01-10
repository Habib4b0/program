package com.stpl.app.ui;

import org.jboss.logging.Logger;

import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;

/**
 * Customized GridLayout Component.
 *
 * @author
 */
public class CustomGridLayout extends GridLayout {
     
     /** The Constant LOGGER. */
     private static final Logger LOGGER = Logger.getLogger(CustomGridLayout.class);

    /**
     * Parameterized Constructor.
     *
     * @param columns - int
     * @param rows - int
     */
    public CustomGridLayout(final int columns,final int rows) {
        
        super(columns, rows);
    }

    /**
     * Adds the component to the grid layout.
     * 
     * @param labelComponent - Component
     * @param fieldComponent - Component
     * @param appPermission - boolean
     */
    public void addComponentInGrid(final Component labelComponent,final Component fieldComponent,final boolean appPermission) {
        try{
        if (appPermission) {
            this.addComponent(labelComponent);
            this.addComponent(fieldComponent);
        }
        } catch(Exception e){
        LOGGER.error(e);
        }
    }

    /**
     * Adds the component to the particular fields in Grid Layout.
     * 
     * @param labelComponent - Component
     * @param columnLabel - int
     * @param rowLabel - int
     * @param fieldComponent - Component
     * @param columnField - int
     * @param rowField - int
     * @param appPermission - boolean
     */
    public void addComponentInGridPosition(final Component labelComponent,final int columnLabel,final int rowLabel,final Component fieldComponent,final int columnField,final int rowField,final boolean appPermission) {
       
        try{
            if (appPermission) {
            this.addComponent(labelComponent, columnLabel, rowLabel);
            this.addComponent(fieldComponent, columnField, rowField);
        }
            } catch (OutOfBoundsException | OverlapsException e) {
            LOGGER.error(e);
        }
    }

}
