/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.util;

import com.stpl.addons.tableexport.ExportableFormattedProperty;
import com.vaadin.v7.data.Property;
import org.asi.ui.extfilteringtable.ExtCustomTable;

/**
 *
 * @author Jayaram.LeelaRam
 */
public class PropertyFormatCustomTable extends ExtCustomTable implements ExportableFormattedProperty{

    @Override
    public String getFormattedPropertyValue(Object rowId, Object colId, Property property) {
        return this.formatPropertyValue(rowId, colId, property);
    }
    
}
