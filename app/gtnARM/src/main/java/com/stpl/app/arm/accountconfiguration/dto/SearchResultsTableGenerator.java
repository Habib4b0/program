/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.accountconfiguration.dto;

import com.stpl.app.arm.accountconfiguration.logic.AccountConfigLogic;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.Field;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.vaadin.v7.ui.TextField;

import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Mohamed.Shahul
 */
public class SearchResultsTableGenerator implements ExtFilterGenerator {

    private AccountConfigLogic logic = AccountConfigLogic.getInstance();
    /**
     * The Constant LOGGER.
     */
    protected static final Logger LOGGER = LoggerFactory.getLogger(SearchResultsTableGenerator.class);

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        String value = null;
        if (originatingField.getValue() != null) {
            return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
        }
        return generateFilter(propertyId, value);
    }

    @Override
    public void filterRemoved(Object propertyId) {
        LOGGER.debug("Inside filterRemoved");
    }

    @Override
    public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
        LOGGER.debug("Inside filterAdded Override Method");
    }

    @Override
    public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
        return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        if ("brandDdlb".equals(propertyId.toString())) {
            TextField text = new TextField();
            text.setImmediate(true);
            return text;
        }
        return null;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
