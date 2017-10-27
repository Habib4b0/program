/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.filemanagement.dto;

import com.stpl.app.adminconsole.filemanagement.logic.FileManagementLogic;
import com.stpl.app.adminconsole.util.CommonUtils;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import java.util.List;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.jboss.logging.Logger;

/**
 *
 * @author vinodhini
 */
public class FileManagementFilterGenerator implements ExtFilterGenerator {

    private static final Logger LOGGER = Logger.getLogger(FileManagementFilterGenerator.class);
    FileManagementLogic logic=new FileManagementLogic();

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        if (originatingField instanceof ComboBox) {
            if (propertyId.equals("brand") || "identifierType".equals(propertyId)||"businessUnit".equals(propertyId) ||"companyName".equals(propertyId)) {
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

            CommonUtils commonUtil = CommonUtils.getInstance();

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
            if ("businessUnit".equals(propertyId)) {
                ComboBox businessUnit = new ComboBox();
                logic.loadBusinessUnitDdlb(businessUnit,true);
                return businessUnit;
            }
            
            if ("companyName".equals(propertyId)) {
                ComboBox companyDDLB = new ComboBox();
                companyDDLB.setImmediate(true);
                companyDDLB.addItem(0);
                companyDDLB.setItemCaption(0, ConstantsUtils.SHOW_ALL);
                companyDDLB.setNullSelectionAllowed(true);
                companyDDLB.setNullSelectionItemId(0);
                companyDDLB.setInputPrompt(ConstantsUtils.SHOW_ALL);
                companyDDLB.markAsDirty();

                List<Object[]> companyList = logic.getCompanies(0);
                if (companyList != null && !companyList.isEmpty()) {
                    for (Object[] object : companyList) {
                        companyDDLB.addItem(object[0]);
                        companyDDLB.setItemCaption(object[0], object[NumericConstants.TWO] + " - " + object[NumericConstants.THREE]);
                    }
                }
                companyDDLB.setValue(null);
                return companyDDLB;
            }
            

        } catch (Exception e) {
            LOGGER.error(e);
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
