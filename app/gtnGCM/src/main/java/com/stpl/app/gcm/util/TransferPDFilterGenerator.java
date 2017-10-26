/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.gcm.util;


import com.stpl.app.gcm.tp.logic.CommmonLogic;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.jboss.logging.Logger;


public class TransferPDFilterGenerator implements ExtFilterGenerator {
    
    private static Logger LOGGER = Logger.getLogger(TransferPDFilterGenerator.class);
    
   
  CommmonLogic commonLogic = new CommmonLogic();
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
                comboBox.setImmediate(true);
                 commonLogic.loaDDLBForListLoading(comboBox,"STATUS",true);
                comboBox.setNullSelectionAllowed(true);
                comboBox.setNullSelectionItemId("Show All");
                comboBox.select(ConstantsUtils.ZERO_INT);
                return comboBox;

            }         
        } catch (Exception ex) {
            LOGGER.error(ex);
        } 
        return null;
    }

    @Override
    public void filterRemoved(Object propertyId) {
    }

    @Override
    public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
    }

    @Override
    public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {        
   return null;
    }

    

}
