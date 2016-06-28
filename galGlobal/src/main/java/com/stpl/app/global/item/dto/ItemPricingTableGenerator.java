package com.stpl.app.global.item.dto;

import com.stpl.app.global.common.util.CommonUtil;
import org.jboss.logging.Logger;

import com.stpl.app.global.item.logic.ItemSearchLogic;
import com.stpl.app.global.item.ui.form.ParentCompanyNo;
import com.stpl.app.global.item.util.UIUtils;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.text.DecimalFormat;
import org.asi.ui.customtextfield.CustomTextField;

// TODO: Auto-generated Javadoc
/*
 *@Created by Shri Hari Haran
 */
/**
 * The Class ItemPricingTableGenerator.
 */
public class ItemPricingTableGenerator extends DefaultFieldFactory {

    /**
     * The item logic.
     */
    private static final ItemSearchLogic ITEMLOGIC = new ItemSearchLogic();

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ItemPricingTableGenerator.class);

    /**
     * The common util.
     */
    private CommonUtil commonUtil = CommonUtil.getInstance();

    /**
     * (non-Javadoc).
     *
     * @param container the container
     * @param itemId the item id
     * @param propertyId the property id
     * @param uiContext the ui context
     * @return the field<?>
     */
    public Field<?> createField(final Container container, final Object itemId, final Object propertyId, final Component uiContext) {
        try {
            if ("checkbox".equals(propertyId)) {
                final CheckBox checkbox = new CheckBox();
                checkbox.setReadOnly(false);
                return checkbox;
            }
            if ("itemPrice".equals(propertyId)) {
                final TextField itemPrice = new TextField();
                itemPrice.setImmediate(true);
                itemPrice.setStyleName("align-right");
                itemPrice.setRequired(true);
                itemPrice.setRequiredError("Item Price should be selected on Pricing tab");
                itemPrice.addValueChangeListener(new Property.ValueChangeListener() {
                    /**
                     * After changing the value in pricingCodeStatus, function
                     * will be executed.
                     *
                     * @param event
                     */
                    public void valueChange(final ValueChangeEvent event) {
                        String value = String.valueOf(event.getProperty().getValue());

                        final DecimalFormat decimalFormat = new DecimalFormat("###,###,###.00##");
                        if (value.contains(ConstantsUtils.DOLLAR)) {
                            value = value.replace(ConstantsUtils.DOLLAR, "");
                            value = value.replace(",", "");
                            value = decimalFormat.format(Double.valueOf(value));
                            value = ConstantsUtils.DOLLAR + value;
                            itemPrice.setValue(value);
                        } else {
                            value = decimalFormat.format(Double.valueOf(value));
                            value = ConstantsUtils.DOLLAR + value;
                            itemPrice.setValue(value);
                        }

                    }
                });

                return itemPrice;
            }
            if (ConstantsUtils.ENTITY_CODE.equals(propertyId)) {
                final CustomTextField priceEntityCode = new CustomTextField();
                final CustomTextField priceEntityCodeName = new CustomTextField();
                final CustomTextField priceEntityCodeSid = new CustomTextField();
                priceEntityCode.setImmediate(true);
                priceEntityCode.setReadOnly(true);
                priceEntityCode.setStyleName("searchicon");
                priceEntityCode.addClickListener(new CustomTextField.ClickListener() {

                    /**
                     * Logic for focus event.
                     *
                     * @param event
                     */
                    public void click(final CustomTextField.ClickEvent event) {
                        try {
                            final ParentCompanyNo lookUp = new ParentCompanyNo(priceEntityCodeName, priceEntityCode, priceEntityCodeSid);
                            UI.getCurrent().addWindow(lookUp);
                            lookUp.addCloseListener(new Window.CloseListener() {
                                /**
                                 * window listener
                                 */
                                public void windowClose(final Window.CloseEvent e) {
                                    if (itemId != null) {
                                        ((ItemPricingDTO) itemId).setEntityCodeSid(priceEntityCodeSid.getValue());
                                    }
                                }
                            });
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }
                });
                return priceEntityCode;
            }
            if (propertyId.equals(ConstantsUtils.START_DATE) || "pricingStartDate".equals(propertyId) || "pricingEndDate".equals(propertyId)) {
                final PopupDateField startDate = new PopupDateField();
                startDate.setDescription(ConstantsUtils.DATE_DES);
                startDate.setImmediate(true);
                startDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
                startDate.addValueChangeListener(new Property.ValueChangeListener() {
                    /**
                     * Changes the value of start date, function will be
                     * executed.
                     */
                    @SuppressWarnings("PMD")
                    public void valueChange(final ValueChangeEvent event) {
                            startDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(startDate.getValue()));                        
                    }
                });
                return startDate;
            }
            if (propertyId.equals(ConstantsUtils.END_DATE)) {
                final PopupDateField endDate = new PopupDateField();
                endDate.setDescription(ConstantsUtils.DATE_DES);
                endDate.setImmediate(true);
                endDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
                endDate.addValueChangeListener(new Property.ValueChangeListener() {
                    /**
                     * Changes the value of end date, function will be executed.
                     */
                    public void valueChange(final ValueChangeEvent event) {
                            endDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(endDate.getValue()));                        
                    }
                });
                return endDate;
            }
            if ("itemUom".equals(propertyId)) {
                final ComboBox itemUom = new ComboBox();
                commonUtil.loadComboBox(itemUom, UIUtils.UOM, true);
                return itemUom;
            }
            if ("pricingCodeStatus".equals(propertyId)) {
                final ComboBox pricingCodeStatus = new ComboBox();
                commonUtil.loadComboBox(pricingCodeStatus, UIUtils.STATUS, true);
                return pricingCodeStatus;
            }
            if ("createdDate".equals(propertyId)) {
                        final DateField createdDate = new DateField();
                        createdDate.setDescription(ConstantsUtils.DATE_DES);
                        createdDate.setImmediate(true);
                        createdDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
                        createdDate.setReadOnly(true);
                        return createdDate;
                    }
            if ("modifiedDate".equals(propertyId)) {
                final DateField modifiedDate = new DateField();
                modifiedDate.setDescription(ConstantsUtils.DATE_DES);
                modifiedDate.setImmediate(true);
                modifiedDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
                modifiedDate.setReadOnly(true);
                return modifiedDate;
            }
        } catch (SystemException ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            LOGGER.error(errorMsg);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {  
                /**            
                 * The method is triggered when a button of the message box is 
                 * pressed .          
                 *               
                 * @param buttonId The buttonId of the pressed button.     
                 */             
                @SuppressWarnings("PMD")          
                public void buttonClicked(final ButtonId buttonId) {      
                    // Do Nothing              
                }         
            }, ButtonId.OK);      
            msg.getButton(ButtonId.OK).focus();
        } catch (PortalException portException) {
            LOGGER.error(portException);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {    
                /**              
                 * The method is triggered when a button of the message box is   
                 * pressed .   
                 *            
                 * @param buttonId The buttonId of the pressed button.     
                 */           
                @SuppressWarnings("PMD")    
                public void buttonClicked(final ButtonId buttonId) {   
                    // Do Nothing     
                }          
            }, ButtonId.OK);    
            msg.getButton(ButtonId.OK).focus();
        } catch (Exception exception) {
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {   
                /**         
                 * The method is triggered when a button of the message box is     
                 * pressed .        
                 *             
                 * @param buttonId The buttonId of the pressed button.  
                 */           
                @SuppressWarnings("PMD")  
                public void buttonClicked(final ButtonId buttonId) {   
                    // Do Nothing   
                }        
            }, ButtonId.OK);    
            msg.getButton(ButtonId.OK).focus();
            LOGGER.error(exception);
        }
        final Field field = super.createField(container, itemId, propertyId, uiContext);
        field.setReadOnly(true);
        return field;
    }
}
