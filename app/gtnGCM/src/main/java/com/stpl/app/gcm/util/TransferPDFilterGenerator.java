/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.gcm.util;


import com.stpl.app.gcm.tp.logic.CommmonLogic;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.Compare;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.Field;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TransferPDFilterGenerator implements ExtFilterGenerator {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TransferPDFilterGenerator.class);
    
    private static final BooleanConstant BOOLEAN_CONSTANT = new BooleanConstant();
    
   
    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
             
        // For other properties, use the default filter
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        if (originatingField instanceof ComboBox) {
            if (originatingField.getValue() != null) {

                if ("statusString".equals(propertyId)) {

                    ComboBox box = (ComboBox) originatingField;
                    HelperDTO dto = (HelperDTO) box.getValue();
                   
                    if (dto.getId() == 0) {
                         return new SimpleStringFilter(propertyId,"",true,true);
                    } else {
                        return new SimpleStringFilter(propertyId, String.valueOf(dto.getDescription()), false, false);
                    }
                } else {

                    return new Compare.Equal(propertyId, originatingField.getValue());
                }
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        try {
            ComboBox comboBox;
           
            if ("statusString".equals(propertyId)) {

                comboBox = new ComboBox();
                CommmonLogic.loaDDLBForListLoading(comboBox,"STATUS",BOOLEAN_CONSTANT.getTrueFlag());
                comboBox.setNullSelectionAllowed(true);
                comboBox.setNullSelectionItemId("Show All");
                comboBox.select(ConstantsUtils.ZERO_INT);
                return comboBox;

            }         
        } catch (Exception ex) {
            LOGGER.error("",ex);
        } 
        return null;
    }

    @Override
    public void filterRemoved(Object propertyId) {
        return;
    }

    @Override
    public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
        return;
    }

    @Override
    public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {        
   return null;
    }

    

}
