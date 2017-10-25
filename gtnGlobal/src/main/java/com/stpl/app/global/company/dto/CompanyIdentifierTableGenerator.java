package com.stpl.app.global.company.dto;

import com.stpl.app.global.common.util.CommonUtil;
import org.jboss.logging.Logger;

import com.stpl.app.global.company.logic.CompanySearchLogic;
import com.stpl.app.global.company.ui.form.ParentCompanyNo;
import com.stpl.app.global.company.util.CommonUtils;
import com.stpl.app.global.company.util.UIUtils;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.ValidationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
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
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import org.asi.ui.customtextfield.CustomTextField;

/**
 * The Class CompanyIdentifierTableGenerator to Generate Field Factory.
 */
public class CompanyIdentifierTableGenerator extends DefaultFieldFactory {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CompanyIdentifierTableGenerator.class);
    /** The common util. */
    private CommonUtil commonUtil = CommonUtil.getInstance();

    /**
     * To create the fields for the particular Table cell.
     *
     * @param container
     * @param itemId
     * @param propertyId
     * @param uiContext
     * @return
     */
    public Field<?> createField(final Container container, final Object itemId, final Object propertyId, final Component uiContext) {
        Field<?> field = null;
        try {
            if (propertyId.equals(ConstantsUtils.CHECK_BOX)) {
                final CheckBox checkbox = new CheckBox();
                checkbox.setReadOnly(false);
                checkbox.setImmediate(true);
                field = checkbox;
			}

			else if ("companyIdentifier".equals(propertyId)) {
                final TextField companyIdentifier = new TextField();
                companyIdentifier.setImmediate(true);
                companyIdentifier.setValidationVisible(true);
                companyIdentifier.setId("companyIdentifier");
                companyIdentifier.addValidator(new RegexpValidator(ValidationUtils.SEARCH_SP_CHAR, "Identifier " + " " + ValidationUtils.SEARCH_SPCHAR_MSG));
                companyIdentifier.addValidator(new StringLengthValidator("Identifier Should be less than 100 characters", 0, NumericConstants.HUNDRED, true));
                field = companyIdentifier;
            } else if (ConstantsUtils.START_DATE.equals(propertyId)) {
                final PopupDateField startDate = new PopupDateField();
                startDate.setDescription(ConstantsUtils.DATE_DES);
                startDate.setImmediate(true);
                startDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
                startDate.setId("startDate");
                startDate.addValueChangeListener(new Property.ValueChangeListener() {
                    /**
                     * To perform value change event.
                     *
                     * @param event
                     */
                    @SuppressWarnings("PMD")
                    public void valueChange(final ValueChangeEvent event) { 
                        startDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(startDate.getValue()));
                    }
                });
                field = startDate;

            } else if (ConstantsUtils.END_DATE.equals(propertyId)) {
                final PopupDateField endDate = new PopupDateField();
                endDate.setDescription(ConstantsUtils.DATE_DES);
                endDate.setImmediate(true);
                endDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
                endDate.addValueChangeListener(new Property.ValueChangeListener() {
                    /**
                     * To perform value change event.
                     *
                     * @param event
                     */
                    public void valueChange(final ValueChangeEvent event) {
                        endDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(endDate.getValue()));                        
                    }
                });
                field = endDate;
            } else if (ConstantsUtils.ENTITY_CODE.equals(propertyId)) {
                final CustomTextField entityCode = new CustomTextField();
                entityCode.setImmediate(true);
                entityCode.setValidationVisible(true);
                entityCode.setReadOnly(true);
                field = entityCode;
                entityCode.addClickListener(new CustomTextField.ClickListener() {

                /**
                 * Logic for focus event.
                 *
                 * @param event
                 */
                public void click(final CustomTextField.ClickEvent event) {
                    try {
                        final ParentCompanyNo lookUp = new ParentCompanyNo(entityCode);
                        UI.getCurrent().addWindow(lookUp);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            }); 
            } else if ("identifierStatus".equals(propertyId)) {
                final CompanySearchLogic companyLogic = new CompanySearchLogic();
                final ComboBox identifierStatus = new ComboBox();
                new CommonUtils().getNativeSelect(identifierStatus, companyLogic.getDropDownList(UIUtils.STATUS));
                identifierStatus.setNullSelectionAllowed(true);
                identifierStatus.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
                commonUtil.loadComboBox(identifierStatus, UIUtils.STATUS, false);
                field = identifierStatus;
            } else if ("createdDate".equals(propertyId)) {
                final PopupDateField date = new PopupDateField();
                date.setDateFormat(ConstantsUtils.DATE_FORMAT);
                 field = date;
                 field.setReadOnly(true);
            }else if ("modifiedDate".equals(propertyId)) {
                final PopupDateField date = new PopupDateField();
                date.setDateFormat(ConstantsUtils.DATE_FORMAT);
                 field = date;
                 field.setReadOnly(true);
            }
            else {
                field = super.createField(container, itemId, propertyId, uiContext);
                field.setReadOnly(true);
                field.setSizeFull();
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
        } catch (PortalException ex) {
            LOGGER.error(ex);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004), new MessageBoxListener() {
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
        } catch (Exception ex) {
            LOGGER.error(ex);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004), new MessageBoxListener() {
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
        }
        // Otherwise use the default field factory
        return field;
    }
}
