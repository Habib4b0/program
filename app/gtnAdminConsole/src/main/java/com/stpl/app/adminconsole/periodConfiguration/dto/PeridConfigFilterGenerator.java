/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.adminconsole.periodConfiguration.dto;

import com.stpl.app.adminconsole.util.CommonUtils;
import com.stpl.ifs.util.numberfilter.NumberFilterPopup;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.jboss.logging.Logger;

/**
 *
 * @author Mahesh.James
 */
public class PeridConfigFilterGenerator implements ExtFilterGenerator {

    CommonUtils commonUtil=CommonUtils.getInstance();
     /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(PeridConfigFilterGenerator.class);
    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        // For other properties, use the default filter
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        if(propertyId.equals("activeFlag")){
        if (originatingField instanceof ComboBox) {
            
            return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
        }
        }else if (originatingField instanceof ComboBox) {
            return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
        }
        
        
        if (originatingField instanceof NumberFilterPopup) {
            NumberFilterPopup popup = (NumberFilterPopup) originatingField;
            if (StringUtils.isNotBlank(popup.getValue().getEqualsValue())) {
                return new Compare.Equal(propertyId, popup.getValue().getEqualsValue());
            } else if (StringUtils.isNotBlank(popup.getValue().getLessThanValue()) && StringUtils.isNotBlank(popup.getValue().getGreaterThanValue())) {
                return new And(new Compare.Less(propertyId, popup.getValue().getLessThanValue()), new Compare.Greater(propertyId, popup.getValue().getGreaterThanValue()));
            } else if (StringUtils.isNotBlank(popup.getValue().getLessThanValue())) {
                return new Compare.Less(propertyId, popup.getValue().getLessThanValue());
            } else if (StringUtils.isNotBlank(popup.getValue().getGreaterThanValue())) {
                return new Compare.Greater(propertyId, popup.getValue().getGreaterThanValue());
            }

        }
        
        return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        if ("activeFlag".equals(propertyId)) {
            try {
                ComboBox interval = new ComboBox();//
                interval.addItem("Show All");
                interval.addItem("Active");
                interval.addItem("Inactive");
                interval.setImmediate(true);
                interval.setNullSelectionAllowed(true);
                interval.setNullSelectionItemId("Show All");
                interval.select("Active");
                
                interval.setDebugId("testing");
                return interval;
            } catch (Exception ex) {
                LOGGER.error(ex);
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