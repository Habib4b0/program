package com.stpl.app.global.company.dto;

import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.PopupDateField;

/**
 * The Class TradeClassTableGenerator.
 */
public class TradeClassTableGenerator extends DefaultFieldFactory {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * To create the fields for the particular Table cell.
     *
     * @param container
     * @param itemId
     * @param propertyId
     * @param uiContext
     * @return
     */
    public Field<?> createField(final Container container, final Object itemId,
            final Object propertyId, final Component uiContext) {
        Field<?> field;
        if ("tradeClassSDate".equals(propertyId)) {

            final PopupDateField startDate = new PopupDateField();
            startDate.setDescription(ConstantsUtils.DATE_DES);
            startDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            startDate.setImmediate(true);
            startDate.setRequiredError("Start Date should  be present");
            startDate.addValueChangeListener(new Property.ValueChangeListener() {
                public void valueChange(Property.ValueChangeEvent event) {
                    startDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(startDate.getValue()));   
                }
            });
            field = startDate;
        } else if ("tradeClassEDate".equals(propertyId)) {

            final PopupDateField endDate = new PopupDateField();
            endDate.setDescription(ConstantsUtils.DATE_DES);
            endDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            endDate.setImmediate(true);
            endDate.addValueChangeListener(new Property.ValueChangeListener() {
                public void valueChange(Property.ValueChangeEvent event) {
                    endDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(endDate.getValue()));
                }
            });
            field = endDate;
        }
      
        else if ("createdDate".equals(propertyId)) {

            final DateField createdDate = new DateField();
            createdDate.setDescription(ConstantsUtils.DATE_DES);
            createdDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            createdDate.setImmediate(true); 
            createdDate.setEnabled(false);
            field = createdDate;
        } else if ("modifiedDate".equals(propertyId)) {

            final DateField modifiedDate = new DateField();
            modifiedDate.setDescription(ConstantsUtils.DATE_DES);
            modifiedDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            modifiedDate.setImmediate(true); 
            modifiedDate.setEnabled(false);
            field = modifiedDate;
        } 
        else {
            field = super.createField(container, itemId, propertyId,
                    uiContext);
            field.setReadOnly(true);
            field.setSizeFull();
        }
        // Otherwise use the default field factory
        return field;
    }

}
