package com.stpl.app.global.item.dto;

import com.stpl.app.global.common.util.CommonUtil;
import org.jboss.logging.Logger;

import com.stpl.app.global.item.ui.form.ParentCompanyNo;
import com.stpl.app.global.item.util.UIUtils;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.ValidationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import org.asi.ui.customtextfield.CustomTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemIdentifierTableGenerator.
 */
public class ItemIdentifierTableGenerator extends DefaultFieldFactory {


	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(ItemIdentifierTableGenerator.class);
         /** The common util. */
        private CommonUtil commonUtil = CommonUtil.getInstance();
        
	/**
	 * (non-Javadoc).
	 *
	 * @param container
	 *            the container
	 * @param itemId
	 *            the item id
	 * @param propertyId
	 *            the property id
	 * @param uiContext
	 *            the ui context
	 * @return the field<?>
	 */
	public Field<?> createField(final Container container, final Object itemId, final Object propertyId, final Component uiContext) {
		try {
			if ("checkbox".equals(propertyId)) {
				final CheckBox checkbox = new CheckBox();
				checkbox.setReadOnly(false);
				return checkbox;
			}
			if (ConstantsUtils.ITEM_IDENTIFIER.equals(propertyId)) {
				final TextField itemIdentifier = new TextField();
				itemIdentifier.setImmediate(true);
				itemIdentifier.setValidationVisible(true);
				itemIdentifier.setRequired(true);
				itemIdentifier.addValidator(new RegexpValidator(ValidationUtils.SEARCH_SP_CHAR, "Identifier " + " " + ValidationUtils.SEARCH_SPCHAR_MSG));
				itemIdentifier.addValidator(new StringLengthValidator("Identifier Should be less than 100 characters", 0, NumericConstants.HUNDRED, true));
				return itemIdentifier;
			}
			if (propertyId.equals(ConstantsUtils.START_DATE)) {
				final PopupDateField startDate = new PopupDateField();
				startDate.setDescription(ConstantsUtils.DATE_DES);
				startDate.setImmediate(true);
				startDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
				startDate.addValueChangeListener(new Property.ValueChangeListener() {
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
			if (ConstantsUtils.ENTITY_CODE.equals(propertyId)) {
                            final TextField entityCodeSid = new TextField();
				final CustomTextField entityCode = new CustomTextField();
                                final CustomTextField entityCodeName = new CustomTextField();
				entityCode.setImmediate(true);
                                entityCode.setReadOnly(true);
                                entityCode.setStyleName("searchicon");
                            entityCode.addClickListener(new CustomTextField.ClickListener() {
                                /**
                                 * Logic for focus event.
                                 *
                                 * @param event
                                 */
                                public void click(final CustomTextField.ClickEvent event) {
                                    try {
                                        final ParentCompanyNo lookUp = new ParentCompanyNo(entityCodeName,entityCode, entityCodeSid);
                                        UI.getCurrent().addWindow(lookUp);
                                        lookUp.addCloseListener(new Window.CloseListener() {
                                            /**
                                             * window listener
                                             */
                                            public void windowClose(final Window.CloseEvent e) {
                                                if (itemId != null) {
                                                    ((ItemIrtIdentifierDTO) itemId).setEntityCodeSid(entityCodeSid.getValue());
                                                    if (!"null".equalsIgnoreCase(String.valueOf(entityCode.getValue()))) {
                                                        container.getContainerProperty(itemId, "entityCode").setValue(entityCode.getValue());
                                                        container.getContainerProperty(itemId, "entityCodeName").setValue(entityCodeName.getValue());
                                                    }
                                                }
                                            }
                                        });

                                    } catch (Exception ex) {
                                        LOGGER.error(ex);
                                    }
                                }
                            });   
                                 return entityCode;
			}
                        if (ConstantsUtils.ENTITY_CODE_NAME.equals(propertyId)) {
                            final TextField entityCodeSid = new TextField();
				final CustomTextField entityCodeName = new CustomTextField();
                                final CustomTextField entityCode = new CustomTextField();
				entityCodeName.setImmediate(true);
                                entityCodeName.setReadOnly(true);
                                entityCodeName.addClickListener(new CustomTextField.ClickListener() {
                                /**
                                 * Logic for focus event.
                                 *
                                 * @param event
                                 */
                                public void click(final CustomTextField.ClickEvent event) {
                                    try {
                                        final ParentCompanyNo lookUp = new ParentCompanyNo(entityCodeName,entityCode, entityCodeSid);
                                        UI.getCurrent().addWindow(lookUp);
                                        lookUp.addCloseListener(new Window.CloseListener() {
                                            /**
                                             * window listener
                                             */
                                            public void windowClose(final Window.CloseEvent e) {
                                                if (itemId != null) {
                                                    ((ItemIrtIdentifierDTO) itemId).setEntityCodeSid(entityCodeSid.getValue());
                                                    if(!"null".equalsIgnoreCase(String.valueOf(entityCode.getValue()))){
                                                        container.getContainerProperty(itemId, "entityCode").setValue(entityCode.getValue());
                                                        container.getContainerProperty(itemId, "entityCodeName").setValue(entityCode.getValue());
                                                    }
                                                }
                                            }
                                        });

                                    } catch (Exception ex) {
                                        LOGGER.error(ex);
                                    }
                                }
                            });
                            
                                
				return entityCodeName;
			}
                    if ("identifierStatus".equals(propertyId)) {
                        final ComboBox identifierStatus = new ComboBox();
                          commonUtil.loadComboBox(identifierStatus, UIUtils.STATUS, true);
                        return identifierStatus;
                    }
                    if ("createdDate".equals(propertyId)) {
                        final PopupDateField createdDate = new PopupDateField();
                        createdDate.setDescription(ConstantsUtils.DATE_DES);
                        createdDate.setImmediate(true);
                        createdDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
                        createdDate.setReadOnly(true);
                        return createdDate;
                    }
                    if ("modifiedDate".equals(propertyId)) {
                        final PopupDateField modifiedDate = new PopupDateField();
                        modifiedDate.setDescription(ConstantsUtils.DATE_DES);
                        modifiedDate.setImmediate(true);
                        modifiedDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
                        modifiedDate.setReadOnly(true);
                        return modifiedDate;
                    }
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