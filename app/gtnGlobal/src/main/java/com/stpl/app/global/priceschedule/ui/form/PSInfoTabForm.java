package com.stpl.app.global.priceschedule.ui.form;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.global.item.util.CommonUtils;
import com.stpl.app.global.priceschedule.dto.PSDTO;
import com.stpl.app.global.priceschedule.logic.PSLogic;
import com.stpl.app.global.priceschedule.util.UIUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customcombobox.CustomComboBox;
import org.asi.ui.customtextfield.CustomTextField;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 * The Class PSInfoTabForm.
 */
public class PSInfoTabForm extends CustomComponent implements View {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The Constant logger.
     */
    private static final Logger logger = Logger.getLogger(PSInfoTabForm.class.getName());

    /**
     * The css layout.
     */
    @UiField("cssLayout")
    private CssLayout cssLayout;
    /**
     * The price schedule id.
     */
    @UiField("priceScheduleId")
    private TextField priceScheduleId;
    /**
     * The price schedule name.
     */
    @UiField("priceScheduleName")
    private TextField priceScheduleName;
    /**
     * The price schedule no.
     */
    @UiField("priceScheduleNo")
    private TextField priceScheduleNo;

    /**
     * The price schedule status.
     */
    @UiField("priceScheduleStatus")
    private CustomComboBox priceScheduleStatus;

    /**
     * The price schedule start date.
     */
    @UiField("priceScheduleStartDate")
    private PopupDateField priceScheduleStartDate;
    /**
     * The price schedule end date.
     */
    @UiField("priceScheduleEndDate")
    private PopupDateField priceScheduleEndDate;

    /**
     * The price schedule designation.
     */
    @UiField("priceScheduleDesignation")
    private CustomComboBox priceScheduleDesignation;

    /**
     * The parent price schedule id.
     */
    @UiField("parentPriceScheduleId")
    private CustomTextField parentPriceScheduleId;

    /**
     * The parent price schedule name.
     */
    @UiField("parentPriceScheduleName")
    private CustomTextField parentPriceScheduleName;

    /**
     * The price schedule type.
     */
    @UiField("priceScheduleType")
    private CustomComboBox priceScheduleType;

    /**
     * The created by.
     */
    @UiField("createdBy")
    private TextField createdBy;

    /**
     * The created date.
     */
    @UiField("createdDate")
    private PopupDateField createdDate;
    /**
     * The price schedule category.
     */
    @UiField("priceScheduleCategory")
    private CustomComboBox priceScheduleCategory;

    /**
     * The modified by.
     */
    @UiField("modifiedBy")
    private TextField modifiedBy;

    /**
     * The modified date.
     */
    @UiField("modifiedDate")
    private PopupDateField modifiedDate;

    /**
     * The trade class.
     */
    @UiField("tradeClass")
    private CustomComboBox tradeClass;

    /**
     * The price schedule id.
     */
    @UiField("priceScheduleIdLb")
    private Label priceScheduleIdLb;
    /**
     * The price schedule name.
     */
    @UiField("priceScheduleNameLb")
    private Label priceScheduleNameLb;
    /**
     * The price schedule no.
     */
    @UiField("priceScheduleNoLb")
    private Label priceScheduleNoLb;

    /**
     * The price schedule status.
     */
    @UiField("priceScheduleStatusLb")
    private Label priceScheduleStatusLb;

    /**
     * The price schedule start date.
     */
    @UiField("priceScheduleStartDateLb")
    private Label priceScheduleStartDateLb;
    /**
     * The price schedule end date.
     */
    @UiField("priceScheduleEndDateLb")
    private Label priceScheduleEndDateLb;

    /**
     * The price schedule designation.
     */
    @UiField("priceScheduleDesignationLb")
    private Label priceScheduleDesignationLb;

    /**
     * The parent price schedule id.
     */
    @UiField("parentPriceScheduleIdLb")
    private Label parentPriceScheduleIdLb;

    /**
     * The parent price schedule name.
     */
    @UiField("parentPriceScheduleNameLb")
    private Label parentPriceScheduleNameLb;

    /**
     * The price schedule type.
     */
    @UiField("priceScheduleTypeLb")
    private Label priceScheduleTypeLb;

    /**
     * The created by lb.
     */
    @UiField("createdByLb")
    private Label createdByLb;

    /**
     * The created date lb.
     */
    @UiField("createdDateLb")
    private Label createdDateLb;
    /**
     * The price schedule category.
     */
    @UiField("priceScheduleCategoryLb")
    private Label priceScheduleCategoryLb;

    /**
     * The modified by lb.
     */
    @UiField("modifiedByLb")
    private Label modifiedByLb;

    /**
     * The modified date lb.
     */
    @UiField("modifiedDateLb")
    private Label modifiedDateLb;

    /**
     * The trade class.
     */
    @UiField("tradeClassLb")
    private Label tradeClassLb;
    /**
     * The binder.
     */
    private final ErrorfulFieldGroup binder;
    /**
     * The error message.
     */
    final private ErrorLabel errorMsg;
    /**
     * The ps master.
     */
    final private PSDTO psMaster;

    /**
     * The ps logic.
     */
    final private PSLogic psLogic;

    /**
     * The price schedule system id.
     */
    final private TextField priceScheduleSystemId = new TextField();

    /**
     * The field ps hm.
     */
    final private Map<String, AppPermission> fieldPsHM;

    /**
     * The ifp logic.
     */
    private final IFPLogic ifpLogic = new IFPLogic();

    /**
     * The common utils.
     */
    final CommonUtils commonUtils = new CommonUtils();

    /**
     * The common ui util.
     */
    CommonUIUtils commonUiUtil = new CommonUIUtils();

    /**
     * The common util.
     */
    private CommonUtil commonUtil = CommonUtil.getInstance();

    /**
     * The look up.
     */
    ParentLookup lookUp = null;
    String mode;

    /**
     * Instantiates a new PS info tab form.
     *
     * @param binder the binder
     * @param psLogic the ps logic
     * @param errorMsg the error msg
     * @param psMaster the ps master
     * @param fieldPsHM the field ps hm
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    public PSInfoTabForm(final ErrorfulFieldGroup binder, final PSLogic psLogic, final ErrorLabel errorMsg, final PSDTO psMaster, Map<String, AppPermission> fieldPsHM, final String mode) {
        this.psLogic = psLogic;
        this.binder = binder;
        this.errorMsg = errorMsg;
        this.psMaster = psMaster;
        this.fieldPsHM = fieldPsHM;
        this.mode = mode;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarativeui/priceschedule/psinfotabform.xml"), this));
        configurePermission(this.fieldPsHM);
        getBinder();
        configureFields();

    }

    /**
     * Configure permission.
     *
     * @param fieldPsHM the field ps hm
     */
    private void configurePermission(final Map<String, AppPermission> fieldPsHM) {
        logger.debug("Entering configurePermission");
        try {
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.PRICE_SCHEDULE, "Price Schedule Information");
            commonUiUtil.removeComponentOnPermission(resultList, cssLayout, fieldPsHM, mode, binder);
        } catch (Exception ex) {
            logger.error(ex);
        }
        logger.debug("Ending configurePermission");
    }

    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private ErrorfulFieldGroup getBinder() {

        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<>(psMaster));
        binder.setBuffered(true);
        binder.setErrorDisplay(errorMsg);
        errorMsg.setId("ErrorMessage");
        return binder;
    }

    /**
     * Configure fields.
     *
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    private void configureFields() {
        priceScheduleSystemId.setImmediate(Boolean.TRUE);
        priceScheduleName.setImmediate(true);
        priceScheduleName.setValidationVisible(true);
        priceScheduleName.addValidator(new StringLengthValidator(" Price Schedule Name Should be less than 100 characters", 0, NumericConstants.HUNDRED, true));
        priceScheduleName.setDescription(priceScheduleName.getValue());
        priceScheduleName.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            /**
             * Value change for PS Name.
             *
             * @param event the event
             */
            public void valueChange(final ValueChangeEvent event) {

                priceScheduleName.setDescription(priceScheduleName.getValue());

            }
        });
        priceScheduleNo.setImmediate(true);
        priceScheduleNo.setValidationVisible(true);
        priceScheduleNo.addValidator(new StringLengthValidator(" Price Schedule No should be less than 50 characters", 0, NumericConstants.FIFTY, true));
        priceScheduleNo.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Value Change listener
             */
            public void valueChange(final ValueChangeEvent event) {
                return;

            }
        });

        priceScheduleId.setImmediate(true);
        priceScheduleId.setValidationVisible(true);
        priceScheduleId.focus();
        priceScheduleId.addValidator(new StringLengthValidator("Price Schedule Id should be less than 38 characters", 0, NumericConstants.THIRTY_EIGHT, true));
        priceScheduleId.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             */
            public void valueChange(final ValueChangeEvent event) {
                return;

            }
        });

        commonUtil.loadComboBox(priceScheduleStatus, UIUtils.STATUS, false);
        commonUtil.loadComboBox(priceScheduleDesignation, UIUtils.PS_DESIGNATION, false);
        priceScheduleDesignation.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Value change for PS Designation
             */
            public void valueChange(final ValueChangeEvent event) {

                String value = StringUtils.EMPTY;
                if (!(priceScheduleDesignation.getValue() == null || ConstantsUtils.SELECT_ONE.equals(((HelperDTO) priceScheduleDesignation.getValue()).getDescription()))) {
                    value = ((HelperDTO) priceScheduleDesignation.getValue()).getDescription();
                }
                if ("Child".equalsIgnoreCase(value)) {
                    parentPriceScheduleName.setEnabled(true);
                    parentPriceScheduleName.setReadOnly(true);
                    parentPriceScheduleId.setEnabled(true);
                    parentPriceScheduleId.setReadOnly(true);
                } else {
                    parentPriceScheduleName.setEnabled(true);
                    parentPriceScheduleName.setReadOnly(false);
                    parentPriceScheduleName.setValue(StringUtils.EMPTY);
                    parentPriceScheduleName.setReadOnly(true);
                    parentPriceScheduleName.setEnabled(false);

                    parentPriceScheduleId.setEnabled(true);
                    parentPriceScheduleId.setReadOnly(false);
                    parentPriceScheduleId.setValue(StringUtils.EMPTY);
                    parentPriceScheduleId.setReadOnly(true);
                    parentPriceScheduleId.setEnabled(false);
                }
            }
        });

        priceScheduleStartDate.setValidationVisible(true);
        priceScheduleStartDate.setDescription(ConstantsUtils.DATE_DES);
        priceScheduleStartDate.setImmediate(true);
        priceScheduleStartDate.setId("PriceScheduleStartDate");
        priceScheduleStartDate.setDateFormat(ConstantsUtils.DATE_FORMAT);

        priceScheduleStartDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Value Change for PS Start Date
             */
            public void valueChange(final ValueChangeEvent event) {
                try {
                    priceScheduleStartDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(priceScheduleStartDate.getValue()));
                } catch (Exception ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    logger.error(errorMsg);
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message
                         * box is pressed .
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

            }
        });

        priceScheduleEndDate.setDescription(ConstantsUtils.DATE_DES);
        priceScheduleEndDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        priceScheduleEndDate.setValidationVisible(true);
        priceScheduleEndDate.addValidator(new DateValidator("Start date should be before item end date"));
        priceScheduleEndDate.setImmediate(true);
        priceScheduleEndDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             */
            public void valueChange(final ValueChangeEvent event) {

                priceScheduleEndDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(priceScheduleEndDate.getValue()));

            }
        });

        parentPriceScheduleId.addStyleName("searchicon");
        parentPriceScheduleId.setReadOnly(true);
        parentPriceScheduleName.setReadOnly(true);
        parentPriceScheduleId.setImmediate(true);
        parentPriceScheduleName.setImmediate(true);

        parentPriceScheduleId.addClickListener(new CustomTextField.ClickListener() {
            /**
             * Focus listnerer for Parent PS Name
             */
            public void click(CustomTextField.ClickEvent event) {
                try {

                    if (lookUp == null) {
                        lookUp = new ParentLookup();
                        UI.getCurrent().addWindow(lookUp);

                        lookUp.addCloseListener(new Window.CloseListener() {
                            /**
                             * Method used to Called when the user closes a
                             * window .
                             */
                            @SuppressWarnings("PMD")
                            public void windowClose(final Window.CloseEvent event) {
                                parentPriceScheduleName.setReadOnly(false);
                                parentPriceScheduleId.setReadOnly(false);

                                if (lookUp.getParentPriceScheduleNameValue() != null) {
                                    parentPriceScheduleName.setValue(lookUp.getParentPriceScheduleNameValue());
                                }

                                if (lookUp.getParentPriceScheduleIdValue() != null) {
                                    parentPriceScheduleId.setValue(lookUp.getParentPriceScheduleIdValue());
                                }

                                parentPriceScheduleName.setReadOnly(true);
                                parentPriceScheduleId.setReadOnly(true);
                                priceScheduleId.focus();
                                lookUp.refresh();
                                lookUp = null;
                            }
                        });
                    }
                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    logger.error(errorMsg);
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message
                         * box is pressed .
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
                    logger.error(portException);
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message
                         * box is pressed .
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
                         * The method is triggered when a button of the message
                         * box is pressed .
                         *
                         * @param buttonId The buttonId of the pressed button.
                         */
                        @SuppressWarnings("PMD")
                        public void buttonClicked(final ButtonId buttonId) {
                            // Do Nothing     
                        }
                    }, ButtonId.OK);
                    msg.getButton(ButtonId.OK).focus();
                    logger.error(exception);
                }
            }
        });

        commonUtil.loadComboBox(priceScheduleType, UIUtils.PS_TYPE, false);
        commonUtil.loadComboBox(priceScheduleCategory, UIUtils.PS_CATEGORY, false);
        commonUtil.loadComboBox(tradeClass, UIUtils.TRADE_CLASS, false);

        createdDate.setDateFormat(Constants.DATE_FORMAT);
        modifiedDate.setDateFormat(Constants.DATE_FORMAT);

    }

    /**
     * The Class DateValidator.
     */
    @SuppressWarnings("rawtypes")
    public class DateValidator extends AbstractValidator {

        /**
         * The Constructor.
         */
        public DateValidator() {
            super("");
        }

        /**
         * The Constructor.
         *
         * @param errorMessage the error message
         */
        public DateValidator(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * Validate for Date range.
         *
         * @param value the value
         */
        @Override
        public void validate(final Object value) {

            if (priceScheduleStartDate.getValue() != null && priceScheduleEndDate.getValue() != null) {
                priceScheduleStartDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
                priceScheduleEndDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
                if (priceScheduleStartDate.getValue().after(priceScheduleEndDate.getValue())) {

                    throw new InvalidValueException("Price Schedule Start date is less than Price Schedule End date");
                } else if (priceScheduleStartDate.getValue().equals(priceScheduleEndDate.getValue())) {
                    throw new InvalidValueException("Price Schedule Start date and Price Schedule End date are equal");
                }
            }

        }

        /**
         * This method can be used to perform validation in subclasses if
         * customization of the error message is not needed.
         *
         * @param value the value
         * @return true, if is valid value
         */
        @Override
        protected boolean isValidValue(final Object value) {

            if (priceScheduleStartDate.getValue() != null && priceScheduleEndDate.getValue() != null) {
                return priceScheduleStartDate.getValue().compareTo(priceScheduleEndDate.getValue()) <= 0;
            }

            return true;
        }

        /**
         * Method used to get type.
         *
         * @return the type
         */
        @Override
        public Class getType() {
            return null;
        }
    }

    /**
     * Sets the default focus.
     *
     * @param mode the new default focus
     */
    public void setDefaultFocus(String mode) {
        if (ConstantsUtils.ADD.equals(mode)) {
            priceScheduleId.focus();
            parentPriceScheduleName.setReadOnly(true);
            parentPriceScheduleName.setEnabled(false);
            parentPriceScheduleId.setReadOnly(true);
            parentPriceScheduleId.setEnabled(false);
            
            createdBy.setValue(IFPLogic.getUseName());
            createdDate.setValue(new Date());

            createdBy.setReadOnly(false);
            createdBy.setEnabled(true);
            createdBy.setImmediate(true);
            createdBy.setReadOnly(true);
            createdBy.setEnabled(false);

            createdDate.setReadOnly(false);
            createdDate.setEnabled(true);
            createdDate.setImmediate(true);
            createdDate.setReadOnly(true);
            createdDate.setEnabled(false);

            modifiedBy.setReadOnly(false);
            modifiedBy.setEnabled(true);
            modifiedBy.setImmediate(true);
            modifiedBy.setReadOnly(true);
            modifiedBy.setEnabled(false);

            modifiedDate.setReadOnly(false);
            modifiedDate.setEnabled(true);
            modifiedDate.setImmediate(true);
            modifiedDate.setReadOnly(true);
            modifiedDate.setEnabled(false);
        } else if (ConstantsUtils.EDIT.equals(mode) || ConstantsUtils.COPY.equals(mode)) {
            priceScheduleId.focus();
            String caption = priceScheduleDesignation.getItemCaption(priceScheduleDesignation.getValue());
            priceScheduleDesignation.setDescription(caption);
            parentPriceScheduleName.setReadOnly(true);
            parentPriceScheduleId.setReadOnly(true);
            if ("child".equalsIgnoreCase(caption)) {
                parentPriceScheduleName.setEnabled(true);
                parentPriceScheduleId.setEnabled(true);
            } else {
                parentPriceScheduleName.setEnabled(false);
                parentPriceScheduleId.setEnabled(false);
            }

            createdBy.setEnabled(false);
            createdBy.setReadOnly(true);
            createdDate.setReadOnly(true);
            createdDate.setEnabled(false);
            modifiedBy.setEnabled(false);
            modifiedBy.setReadOnly(true);
            modifiedDate.setReadOnly(true);
            modifiedDate.setEnabled(false);

        }

    }

    /**
     * Gets the css layout.
     *
     * @return the css layout
     */
    public CssLayout getCssLayout() {
        return cssLayout;
    }

    /**
     * Gets the price schedule id.
     *
     * @return the price schedule id
     */
    public TextField getPriceScheduleId() {
        return priceScheduleId;
    }

    /**
     * Focus price schedule id.
     */
    public void focusPriceScheduleId() {
        priceScheduleId.focus();
    }

    /**
     * Gets the price schedule name.
     *
     * @return the price schedule name
     */
    public TextField getPriceScheduleName() {
        return priceScheduleName;
    }

    /**
     * Gets the price schedule no.
     *
     * @return the price schedule no
     */
    public TextField getPriceScheduleNo() {
        return priceScheduleNo;
    }

    /**
     * Gets the price schedule status.
     *
     * @return the price schedule status
     */
    public CustomComboBox getPriceScheduleStatus() {
        return priceScheduleStatus;
    }

    /**
     * Gets the price schedule start date.
     *
     * @return the price schedule start date
     */
    public PopupDateField getPriceScheduleStartDate() {
        return priceScheduleStartDate;
    }

    /**
     * Gets the price schedule end date.
     *
     * @return the price schedule end date
     */
    public PopupDateField getPriceScheduleEndDate() {
        return priceScheduleEndDate;
    }

    /**
     * Gets the price schedule designation.
     *
     * @return the price schedule designation
     */
    public CustomComboBox getPriceScheduleDesignation() {
        return priceScheduleDesignation;
    }

    /**
     * Gets the parent price schedule id.
     *
     * @return the parent price schedule id
     */
    public CustomTextField getParentPriceScheduleId() {
        return parentPriceScheduleId;
    }

    /**
     * Gets the parent price schedule name.
     *
     * @return the parent price schedule name
     */
    public CustomTextField getParentPriceScheduleName() {
        return parentPriceScheduleName;
    }

    /**
     * Gets the price schedule type.
     *
     * @return the price schedule type
     */
    public CustomComboBox getPriceScheduleType() {
        return priceScheduleType;
    }

    /**
     * Gets the created by.
     *
     * @return the created by
     */
    public TextField getCreatedBy() {
        return createdBy;
    }

    /**
     * Gets the created date.
     *
     * @return the created date
     */
    public PopupDateField getCreatedDate() {
        return createdDate;
    }

    /**
     * Gets the price schedule category.
     *
     * @return the price schedule category
     */
    public CustomComboBox getPriceScheduleCategory() {
        return priceScheduleCategory;
    }

    /**
     * Gets the modified by.
     *
     * @return the modified by
     */
    public TextField getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Gets the modified date.
     *
     * @return the modified date
     */
    public PopupDateField getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Gets the trade class.
     *
     * @return the trade class
     */
    public CustomComboBox getTradeClass() {
        return tradeClass;
    }

    /**
     * Gets the ps logic.
     *
     * @return the ps logic
     */
    public PSLogic getPsLogic() {
        return psLogic;
    }

    /**
     * Sets the css layout.
     *
     * @param cssLayout the new css layout
     */
    public void setCssLayout(CssLayout cssLayout) {
        this.cssLayout = cssLayout;
    }

    /**
     * Sets the modified by.
     *
     * @param modifiedBy the new modified by
     */
    public void setModifiedBy(TextField modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * Enter.
     *
     * @param event the event
     */
    @Override
    public void enter(ViewChangeEvent event) {
        return;

    }

}
