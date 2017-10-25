/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.global.dto;

import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.dashboard.dto.TempPricingDTO;
import com.stpl.app.contract.global.logic.IfpLogic;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.CommonUIUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.PopupDateField;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author karthikraja.k
 */
/*
 * The Class IFPItemsTableGenerator.
 */
public class IFPItemsTableGenerator extends DefaultFieldFactory {

    /**
     */
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(com.stpl.app.contract.global.dto.IFPItemsTableGenerator.class);
    private BeanItemContainer<TempPricingDTO> saveContainer;
    SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
    private CommonUtil commonUtil = CommonUtil.getInstance();
    Object[] dates;
    Map<String, List> tempDate;
    List<Date> tempDateList = new ArrayList<Date>();
    boolean isEdit = false;

    public IFPItemsTableGenerator(BeanItemContainer<TempPricingDTO> saveContainer, final Object[] dates, final Map<String, List> tempDate, boolean isEdit) {
        this.saveContainer = saveContainer;
        this.dates = dates;
        this.tempDate = tempDate;
        this.isEdit = isEdit;
    }

    /**
     * Creates the field based on propertyId.
     *
     * @param container the container
     * @param itemId the item id
     * @param propertyId the property id
     * @param uiContext the ui context
     * @return the field
     */
    public Field<?> createField(final Container container, final Object itemId, final Object propertyId, final Component uiContext) {
        final TempPricingDTO temp = (TempPricingDTO) itemId;
        tempDateList.add(temp.getStartDate());
        tempDateList.add(temp.getEndDate());
        tempDate.put(temp.getItemId(), tempDateList);


        if (Constants.CHECK_BOX.equals(propertyId)) {
            final CheckBox checkbox = new CheckBox();
            checkbox.setValue(temp.getCheckbox());
            checkbox.setReadOnly(false);
            checkbox.setId("contractdashboardcheckbox");
            checkbox.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    try {
                        saveContainer.addItem(itemId);
                        IfpLogic.saveToTempIFP(saveContainer.getItemIds(), isEdit);
                    } catch (PortalException ex) {
                        LOGGER.error(ex);
                    } catch (SystemException ex) {
                         LOGGER.error(ex);
                    }

                }
            });
            return checkbox;
        }
        if ("startDate".equals(propertyId)) {
            try {
                final PopupDateField startDate = new PopupDateField();
                startDate.setImmediate(true);
                startDate.setDateFormat(Constants.MM_DD_YYYY);
                startDate.setDescription(Constants.DATE);
                startDate.setValue(temp.getStartDate());
                attachListeners(startDate, Constants.START_DATE, itemId, temp);
                return startDate;
            } catch (Exception ex) {
               LOGGER.error(ex);
            }
        }
        if ("endDate".equals(propertyId)) {
            try {
                final PopupDateField endDate = new PopupDateField();
                endDate.setImmediate(true);
                endDate.setDateFormat(Constants.MM_DD_YYYY);
                endDate.setDescription(Constants.DATE);
                endDate.setValue(temp.getEndDate());
                attachListeners(endDate, Constants.END_DATE, itemId, temp);
                return endDate;
            } catch (Exception ex) {
                 LOGGER.error(ex);
            }
        }

        if ("ifpStatus".equals(propertyId)) {
            try {
                final ComboBox itemFamilyPlanStatus = new ComboBox();
                commonUtil.loadComboBox(itemFamilyPlanStatus, "STATUS", false);
                itemFamilyPlanStatus.select(temp.getIfpStatus());
                itemFamilyPlanStatus.addValueChangeListener(new Property.ValueChangeListener() {
                    @Override
                    public void valueChange(Property.ValueChangeEvent event) {
                         if(StringUtils.EMPTY.equals(temp.getIfpStatus())){
                        itemFamilyPlanStatus.setValue(Constants.SELECT_ONE);
                    }else{
                        itemFamilyPlanStatus.setValue(temp.getIfpStatus());
                        saveContainer.addItem(itemId);
                    }
                    }
                });
                return itemFamilyPlanStatus;
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }

        return null;
    }

    public void attachListeners(final AbstractField field, final String component, final Object itemId, final TempPricingDTO temp){
        field.setImmediate(true);
        field.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                try {
                    if (Constants.START_DATE.equals(component)) {
                        tempDateList = tempDate.get(temp.getItemId());
                        if (((PopupDateField) field).getValue()!= null && ((PopupDateField) field).getValue().before((Date) dates[0])) {
                            AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Start date cannot be before " + format.format(dates[0]));
                            detachListeners(field);
                            ((PopupDateField) field).setValue(tempDateList.get(0));
                            attachListeners(field, component, itemId, temp);
                            return;
                        } else if (((PopupDateField) field).getValue()!= null &&(Date) dates[1] != null && ((PopupDateField) field).getValue().after((Date) dates[1])) {
                            AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "Start date cannot be after " + format.format(dates[1]));
                            detachListeners(field);
                            ((PopupDateField) field).setValue(tempDateList.get(0));
                            attachListeners(field, component, itemId, temp);
                            return;
                        } else {
                            temp.setStartDate(((PopupDateField) field).getValue());
                            saveContainer.addItem(itemId);
                            ((PopupDateField) field).setDescription(CommonUIUtils.convert2DigitTo4DigitYear(((PopupDateField) field).getValue()));
                        }
                    } else if (Constants.END_DATE.equals(component)) {
                        tempDateList = tempDate.get(temp.getItemId());
                        if (((PopupDateField) field).getValue()!= null &&(Date) dates[1] != null && ((PopupDateField) field).getValue().after((Date) dates[1])) {
                            detachListeners(field);
                            ((PopupDateField) field).setValue(tempDateList.get(1) != null ? tempDateList.get(1) : null);
                            AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "End date cannot be after " + format.format(dates[1]));
                            attachListeners(field, component, itemId, temp);
                            return;
                        } else if (((PopupDateField) field).getValue()!= null && ((PopupDateField) field).getValue().before((Date) dates[0])) {
                            detachListeners(field);
                            ((PopupDateField) field).setValue(tempDateList.get(1) != null ? tempDateList.get(1) : null);
                            AbstractNotificationUtils.getErrorNotification(Constants.POPULATE_ERROR, "End date cannot be before " + format.format(dates[0]));
                            attachListeners(field, component, itemId, temp);
                            return;
                        } else {
                            temp.setEndDate(((PopupDateField) field).getValue());
                            saveContainer.addItem(itemId);
                            ((PopupDateField) field).setDescription(CommonUIUtils.convert2DigitTo4DigitYear(((PopupDateField) field).getValue()));
                        }
                    }

                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });

    }

    public void detachListeners(final AbstractField field) {

        List<Property.ValueChangeListener> listeners;

        listeners = (List<Property.ValueChangeListener>) field.getListeners(Property.ValueChangeEvent.class);
        for (final Property.ValueChangeListener object : listeners) {
            field.removeValueChangeListener(object);
        }

    }
}
