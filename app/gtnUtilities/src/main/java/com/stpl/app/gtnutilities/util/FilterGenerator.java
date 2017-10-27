
package com.stpl.app.gtnutilities.util;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;


/**
 *
 * @author Karthik.Raja
 */

public class FilterGenerator implements ExtFilterGenerator {

    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(FilterGenerator.class);
    
    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        if (originatingField instanceof ComboBox || originatingField instanceof TextField) {

            if (originatingField.getValue() != null) {
                String value = originatingField.getValue().toString();
                return new SimpleStringFilter(propertyId, value, false, false);
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
       ComboBox ddlb;
        if (Constants.STATUS.equals(propertyId)) {
            try {
                ddlb = new ComboBox();
                ddlb.addItem(Constants.SHOW_ALL);
                ddlb.addItem(Constants.SUCCESS);
                ddlb.addItem(Constants.FAIL);
                ddlb.setNullSelectionAllowed(true);
                ddlb.setNullSelectionItemId(Constants.SHOW_ALL);
                ddlb.select(Constants.SHOW_ALL);
                return ddlb;
            } catch (Exception ex) {
                LOGGER.debug(ex);
            }
         }
         if (Constants.ENABLED.equalsIgnoreCase(String.valueOf(propertyId))) {
            try {
                ddlb = new ComboBox();
                ddlb.addItem(Constants.SHOW_ALL);
                ddlb.addItem(Constants.YES);
                ddlb.addItem(Constants.NO);
                ddlb.setNullSelectionAllowed(true);
                ddlb.setNullSelectionItemId(Constants.SHOW_ALL);
                ddlb.select(Constants.SHOW_ALL);
                return ddlb;
            } catch (Exception ex) {
                LOGGER.debug(ex);
            }
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
