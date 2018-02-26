/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.fileSelection.dto;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import com.stpl.app.cff.ui.fileSelection.logic.FileManagementLogic;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.Field;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author vinodhini
 */
public class FileManagementFilterGenerator implements ExtFilterGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileManagementFilterGenerator.class);
    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        // For other properties, use the default filter
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        if (originatingField instanceof ComboBox) {
            if (propertyId.equals("brand") || "identifierType".equals(propertyId)) {
                return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
            } else if (originatingField.getValue() != null) {
                return new SimpleStringFilter(propertyId, String.valueOf(((HelperDTO) originatingField.getValue()).getId()), false, false);
            }
        }
        return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        try {
            FileManagementLogic fileLogic = new FileManagementLogic();

            CommonUtils commonUtil = new CommonUtils();

            if ("identifierType".equals(propertyId)) {
                final ComboBox identifierType = new ComboBox();
                fileLogic.getNativeSelect(identifierType, fileLogic.getItemQualifierNameResults());
                identifierType.setNullSelectionAllowed(true);
                identifierType.setInputPrompt(ConstantsUtils.SHOW_ALL);
                identifierType.setImmediate(true);
                identifierType.setDescription(String.valueOf((Integer) identifierType.getValue()));
                identifierType.addValueChangeListener(new Property.ValueChangeListener() {
                    /**
                     * After changing the value in itemType, function will be
                     * executed.
                     *
                     * @param event
                     */
                    @Override
                    public void valueChange(final Property.ValueChangeEvent event) {
                        identifierType.setDescription(String.valueOf((Integer) identifierType.getValue()));
                    }
                });
                return identifierType;
            }

            if ("brand".equals(propertyId)) {
                final ComboBox brand = new ComboBox();
                fileLogic.getNativeSelect(brand, fileLogic.getBrandResults());
                brand.setNullSelectionAllowed(true);
                brand.setInputPrompt(ConstantsUtils.SHOW_ALL);
                brand.setImmediate(true);
                brand.setDescription(String.valueOf((Integer) brand.getValue()));
                brand.addValueChangeListener(new Property.ValueChangeListener() {
                    /**
                     * After changing the value in itemType, function will be
                     * executed.
                     *
                     * @param event
                     */
                    @Override
                    public void valueChange(final Property.ValueChangeEvent event) {
                        brand.setDescription(String.valueOf((Integer) brand.getValue()));
                    }
                });
                return brand;
            }

            if ("itemType".equals(propertyId)) {
                ComboBox itemType = new ComboBox();
                commonUtil.loadComboBox(itemType, "ITEM_TYPE", true);
                return itemType;
            }
            if ("itemStatus".equals(propertyId)) {
                ComboBox itemStatus = new ComboBox();
                commonUtil.loadComboBox(itemStatus, "STATUS", true);
                return itemStatus;
            }
            if ("therapyClass".equals(propertyId)) {
                ComboBox therapyClass = new ComboBox();
                commonUtil.loadComboBox(therapyClass, "THERAPEUTIC_CLASS", true);
                return therapyClass;
            }

        } catch (PortalException | SystemException e) {
            LOGGER.error(e.getMessage());
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
