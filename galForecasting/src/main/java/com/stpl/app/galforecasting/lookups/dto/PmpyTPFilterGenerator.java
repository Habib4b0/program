/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.lookups.dto;

import com.vaadin.data.Container;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Field;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;

/**
 * The class PmpyTPFilterGenerator.
 * @author vinodhini
 */
public class PmpyTPFilterGenerator implements ExtFilterGenerator {

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        // For other properties, use the default filter
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
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
