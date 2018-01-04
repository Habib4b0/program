/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.util.numberfilter;

import com.vaadin.v7.data.Container.Filter;
import com.vaadin.v7.data.util.filter.Compare;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.Field;
import java.io.Serializable;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;

public class DemoFilterGenerator implements ExtFilterGenerator , Serializable{
 
    @Override
    public Filter generateFilter(Object propertyId, Object value) {
        
        Filter filter = null;
       if (("history4ActualSales".equals(propertyId)) && (value != null && value instanceof String)) {
            /* Create an 'equals' filter for the ID field */
                try {
                    filter =  new Compare.Equal(propertyId,
                            Double.parseDouble((String) value));
                   
                  
                } catch (NumberFormatException ignored) {
                    // If no integer was entered, just generate default filter
                }
        // For other properties, use the default filter
       
    }
       
       if (("history3ActualSales".equals(propertyId)) && (value != null && value instanceof String)) {
            /* Create an 'equals' filter for the ID field */
                try {
                    filter =  new Compare.Equal(propertyId,
                            Double.parseDouble((String) value));
                  
                } catch (NumberFormatException ignored) {
                    // If no integer was entered, just generate default filter
                }
        // For other properties, use the default filter
       
    } 
        if ("graphComponent".equals(propertyId)) {
            /* Create an 'equals' filter for the ID field */
            
           return null;
            }
        // For other properties, use the default filter
       
    
     return filter;
    }

    @Override
    public Filter generateFilter(Object propertyId, Field<?> originatingField) {
        // Use the default filter
        return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        // removed custom filter component for quantity
        
        return null;
    }

    @Override
    public void filterRemoved(Object propertyId) {
        return;
    }

    @Override
    public void filterAdded(Object propertyId,
            Class<? extends Filter> filterType, Object value) {
       return;
    }

    @Override
    public Filter filterGeneratorFailed(Exception reason, Object propertyId,
            Object value) {
        /* Return null -> Does not add any filter on failure */
        return null;
    }

}
