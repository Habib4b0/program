/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.ifs.util;

import com.vaadin.data.Property;

/**
 *
 * @author Abhiram
 */
public interface ExportableCustomColumnGenerator extends com.vaadin.ui.ExtCustomTable.ColumnGenerator {

    Property getGeneratedProperty(Object itemId, Object columnId);
    // the type of the generated property
    Class<?> getType();

}
