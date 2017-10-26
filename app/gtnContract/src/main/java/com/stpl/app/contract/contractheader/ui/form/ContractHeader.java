/**
 *
 */
package com.stpl.app.contract.contractheader.ui.form;

import com.stpl.app.contract.contractheader.dto.ContractMasterDTO;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.asi.ui.customtextfield.CustomTextField;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyContainer;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

import com.stpl.app.contract.contractheader.logic.ContractHeaderLogic;
import com.stpl.app.contract.contractheader.util.CommonUtils;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.app.contract.global.lazyload.ManufacturerCriteria;
import com.stpl.app.contract.global.lazyload.ManufacturerDAO;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.CommonUIUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.UISecurityUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import java.util.Collection;

/**
 * @author sibi
 *
 */
public class ContractHeader extends CustomComponent {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static final Logger LOGGER = Logger.getLogger(ContractHeader.class);
    @UiField("cssLayout")
    private CssLayout cssLayout;
    @UiField("contractIdLabel")
    private Label contractIdLabel;
    @UiField("contractNoLabel")
    private Label contractNoLabel;
    @UiField("contractNameLabel")
    private Label contractNameLabel;
    @UiField("contractTypeLabel")
    private Label contractTypeLabel;
    @UiField("contractStatusLabel")
    private Label contractStatusLabel;
    @UiField("documentTypeLabel")
    private Label documentTypeLabel;
    @UiField("startDateLabel")
    private Label startDateLabel;
    @UiField("endDateLabel")
    private Label endDateLabel;
    @UiField("documentClassLabel")
    private Label documentClassLabel;
    @UiField("companyNameLabel")
    private Label companyNameLabel;
    @UiField("tradeClassLabel")
    private Label tradeClassLabel;
    @UiField("termLabel")
    private Label termLabel;
    @UiField("tradingPartnerLabel")
    private Label tradingPartnerLabel;
    @UiField("reNegotiationStartDateLabel")
    private Label reNegotiationStartDateLabel;
    @UiField("reNegotiationEndDateLabel")
    private Label reNegotiationEndDateLabel;
    @UiField("priceProtectionStartDateLabel")
    private Label priceProtectionStartDateLabel;
    @UiField("priceProtectionEndDateLabel")
    private Label priceProtectionEndDateLabel;
    @UiField("manufacturerNoLabel")
    private Label manufacturerNoLabel;
    @UiField("contractId")
    private TextField contractId;
    @UiField("contractNo")
    private TextField contractNo;
    @UiField("contractName")
    private TextField contractName;
    @UiField("term")
    private TextField term;
    @UiField("startDate")
    private PopupDateField startDate;
    @UiField("endDate")
    private PopupDateField endDate;
    @UiField("renegotiationStartDate")
    private PopupDateField renegotiationStartDate;
    @UiField("renegotiationEndDate")
    private PopupDateField renegotiationEndDate;
    @UiField("priceprotectionStartDate")
    private PopupDateField priceprotectionStartDate;
    @UiField("priceprotectionEndDate")
    private PopupDateField priceprotectionEndDate;
    @UiField("contractType")
    private ComboBox contractType;
    @UiField("contractStatus")
    private ComboBox contractStatus;
    @UiField("documentType")
    private ComboBox docType;
    @UiField("documentClass")
    private ComboBox docClass;
    @UiField("tradeClass")
    private ComboBox tradeClass;
    @UiField("companySystemId")
    private ComboBox companySystemId;
    @UiField("companyLabel")
    private CustomTextField companyLabel;
    @UiField("tradingPartnerName")
    private CustomTextField tradingPartnerName;
    private final TextField companyName = new TextField();
    public final TextField contractSystemId = new TextField();
    /**
     * TextField for trading partner system id.
     */
    private final TextField tradingPartnerSystemId = new TextField();
    private HelperDTO dto = new HelperDTO(Constants.SELECT_ONE);
    private CustomFieldGroup binder;
    private String mode;
    CompanyNameLookUp companyNameLookUp =null;
    TradingPartnerLookUp tpLookUp =null;
    /**
     * Object for contract header logic.
     */
    private ContractMasterDTO contractMasterDTO;
    CommonUIUtils commonUIUtils= new CommonUIUtils();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    /** 
     * The common util. 
     */
    private com.stpl.app.contract.abstractsearch.util.CommonUtils commonUtil = com.stpl.app.contract.abstractsearch.util.CommonUtils.getInstance();

    /**
     * @throws Exception
     * @throws SystemException
     * @throws PortalException
     *
     */
    public ContractHeader(final CustomFieldGroup binder,final ContractMasterDTO contractMasterDTO,final String mode) throws PortalException, SystemException {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarative-ui/contract-header/contract-header.xml"), this));
        this.binder = binder;
        this.mode=mode;
        this.contractMasterDTO = contractMasterDTO;
        addResponsivenessToComponents();
        if (Constants.EDIT.equals(mode) || Constants.VIEW.equals(mode)) {            
            configureFields();
            configureBinder();
        } else {            
            configureBinder();
            configureFields();
        }
    }

    private void configureBinder() {
        binder.bindMemberFields(this);
    }

    private void configureFields() throws PortalException, SystemException {

        contractId.setData("maxlengthvalidation,maxlengthvalidationcontractid,null,null");
        contractId.setImmediate(true);
        contractId.setValidationVisible(true);
        contractId.setDescription(contractId.getValue());
        contractId.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * sets the value for contractId Textfield on Value change.
             *
             * @param event - ValueChangeEvent
             */
            @SuppressWarnings("PMD")
            public void valueChange(final ValueChangeEvent event) {
                contractId.setDescription(contractId.getValue());
            }
        });
        contractId.focus();

      contractName.setData("maxlengthvalidationhundred,maxlengthvalidationcontractname,null,null");
        contractName.setImmediate(true);
        contractName.setValidationVisible(true);
        contractName.setDescription(contractName.getValue());
        contractName.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * sets the value for contractName Textfield on Value change.
             *
             * @param event - ValueChangeEvent
             */
            @SuppressWarnings("PMD")
            public void valueChange(final ValueChangeEvent event) {
                contractName.setDescription(contractName.getValue());
            }
        });

        startDate.setValidationVisible(true);
        startDate.setImmediate(true);
        startDate.setDateFormat(Constants.MM_DD_YYYY);
        startDate.setId("ContractStartDate");
        startDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the difference between dates in the term TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                termSetValue();
                startDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(startDate.getValue()));
            }
        });

        endDate.setImmediate(true);
        endDate.setValidationVisible(true);
        endDate.addValidator(new DateValidatorContract("Contract End date should be greater than Contract Start Date"));
        endDate.setDateFormat(Constants.MM_DD_YYYY);
        endDate.setId("ContractEndDate");

        endDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the difference between dates in the term TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                termSetValue();
                endDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(endDate.getValue()));
            }
        });

        renegotiationStartDate.setValidationVisible(true);
        renegotiationStartDate.setImmediate(true);
        renegotiationStartDate.setDateFormat(Constants.MM_DD_YYYY);
        renegotiationStartDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the difference between dates in the term TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                termSetValue();
                renegotiationStartDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(renegotiationStartDate.getValue()));
            }
        });

        renegotiationEndDate.setImmediate(true);
        renegotiationEndDate.setValidationVisible(true);
        renegotiationEndDate.addValidator(new DateValidatorReNegotiation("Re-Negotiation End Date should be greater than Re-Negotiation Start date"));
        renegotiationEndDate.setDateFormat(Constants.MM_DD_YYYY);
        renegotiationEndDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the difference between dates in the term TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                termSetValue();
                renegotiationEndDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(renegotiationEndDate.getValue()));
            }
        });

        priceprotectionStartDate.setImmediate(true);
        priceprotectionStartDate.setValidationVisible(true);
        priceprotectionStartDate.setDateFormat(Constants.MM_DD_YYYY);
        priceprotectionStartDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the difference between dates in the term TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                termSetValue();
                priceprotectionStartDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(priceprotectionStartDate.getValue()));
            }
        });

        priceprotectionEndDate.setImmediate(true);
        priceprotectionEndDate.setValidationVisible(true);
        priceprotectionEndDate.addValidator(new DateValidatorPriceProtection("Price Protection End Date should be greater than Price Protection Start Date"));
        priceprotectionEndDate.setDateFormat(Constants.MM_DD_YYYY);
        priceprotectionEndDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the difference between dates in the term TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                termSetValue();
                priceprotectionEndDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(priceprotectionEndDate.getValue()));
            }
        });

        companySystemId.setImmediate(true);
        companySystemId.setNullSelectionAllowed(true);
        companySystemId.setNullSelectionItemId(dto);
        companySystemId.setItemCaptionPropertyId(Constants.DESCRIPTION);
        companySystemId.setInputPrompt(Constants.SELECT_ONE);
        companySystemId.markAsDirty();
        LazyContainer manufacturerContainer;
        if (Constants.EDIT.equals(mode) || Constants.VIEW.equals(mode)) {
            manufacturerContainer = new LazyContainer(HelperDTO.class, new ManufacturerDAO(contractMasterDTO.getCompanySystemId()), new ManufacturerCriteria());
        } else {
            manufacturerContainer = new LazyContainer(HelperDTO.class, new ManufacturerDAO(), new ManufacturerCriteria());
        }        
        manufacturerContainer.setMinFilterLength(Constants.ZERO);
        companySystemId.setContainerDataSource(manufacturerContainer);
        companySystemId.select(dto);
        companySystemId.setPageLength(NumericConstants.SEVEN);
        companySystemId.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                CommonUtils.setNullValue(tradeClass);
                  ComboBox property = (ComboBox) event.getProperty();
                if (property.getValue() != null && !"0".equals(property.getValue().toString())) {
                    property.setDescription(property.getItemCaption(property.getValue()));
                }
            }
        });
        
        commonUtil.loadComboBox(contractType, UIUtils.CONTRACT_TYPE, false);
        commonUtil.loadComboBox(tradeClass, UIUtils.TRADE_CLASS,false);
        commonUtil.loadComboBox(contractStatus, UIUtils.STATUS,false);
        commonUtil.loadComboBox(docType, UIUtils.DOCUMENT_TYPE,false);
        commonUtil.loadComboBox(docClass, UIUtils.DOCUMENT_CLASS,false);
        
        tradingPartnerName.addValidator(new StringLengthValidator("Trading Partner Name should be less than 100 characters", Constants.ZERO, NumericConstants.HUNDRED, true));
        tradingPartnerName.setValidationVisible(true);
        tradingPartnerName.setImmediate(true);
        tradingPartnerName.addStyleName(Constants.SEARCH_ICON_STYLE);
        tradingPartnerName.setDescription(tradingPartnerName.getValue());
        tradingPartnerName.setReadOnly(true);
        tradingPartnerName.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the Description for TradingPartnerName.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                tradingPartnerName.setDescription(tradingPartnerName.getValue());
            }
        });

        contractNo.setData("maxlengthvalidationfifty,maxlengthvalidationcontractno,null,null");
        contractNo.setImmediate(true);
        contractNo.setValidationVisible(true);        
        contractNo.setDescription(contractNo.getValue());
        contractNo.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for Contract Number.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                contractNo.setDescription(contractNo.getValue());
            }
        });

        term.setData("maxlengthvalidationfifty,maxlengthvalidationterm,numericvalidation,numericvalidationterm");
        term.setValidationVisible(true);
        term.setDescription(term.getValue());
        term.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for term TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                term.setDescription(term.getValue());
            }
        });


        companyLabel.setImmediate(true);
        companyLabel.addStyleName(Constants.SEARCH_ICON_STYLE);
        companyLabel.setReadOnly(true);
        companyLabel.addClickListener(new CustomTextField.ClickListener() {
            /**
             * Opens CompanyNameLookUp window.
             *
             * @param event - FocusEvent.
             */
            public void click(final CustomTextField.ClickEvent event) {
                if(companyNameLookUp ==null){
               companyNameLookUp = new CompanyNameLookUp(companyName, companyLabel);
                UI.getCurrent().addWindow(companyNameLookUp);
                }
                companyNameLookUp.addCloseListener(new Window.CloseListener() {
                    /**
                     * To catch window close event
                     *
                     * @param event - WindowCloseEvent
                     */
                    @SuppressWarnings("PMD")
                    @Override
                    public void windowClose(final Window.CloseEvent event) {
                        tradeClass.focus();
                        companyNameLookUp=null;
                    }
                });
            }
        });

        tradingPartnerName.addClickListener(new CustomTextField.ClickListener() {
            /**
             * Opens the TradingPartnerLookUp window.
             *
             * @param event - FocusEvent
             */
            public void click(final CustomTextField.ClickEvent event) {
                try {
                    if(tpLookUp==null){
                   tpLookUp = new TradingPartnerLookUp(tradingPartnerSystemId, tradingPartnerName);
                    UI.getCurrent().addWindow(tpLookUp);
                    }
                    tpLookUp.addCloseListener(new Window.CloseListener() {
                        /**
                         * To catch window close event
                         *
                         * @param event - WindowCloseEvent
                         */
                        @SuppressWarnings("PMD")
                        public void windowClose(final Window.CloseEvent event) {

                            renegotiationStartDate.focus();
                            tpLookUp=null;
                        }
                    });
                } catch (SystemException ex) {
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
                    LOGGER.error(errorMsg);
                    AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);

                }
            }
        });

        startDate.setDescription(Constants.DATE);
        endDate.setDescription(Constants.DATE);
        renegotiationStartDate.setDescription(Constants.DATE);
        renegotiationEndDate.setDescription(Constants.DATE);
        priceprotectionStartDate.setDescription(Constants.DATE);
        priceprotectionEndDate.setDescription(Constants.DATE);

    }

    /**
     * Sets the value for the term TexField based on the StartDate and EndDate.
     */
    public void termSetValue() {
        LOGGER.debug("Entering termSetValue method ");
        if (startDate.getValue() == null || endDate.getValue() == null) {
            term.setReadOnly(false);
            term.setValue("0");
            term.setReadOnly(true);

        } else {
            term.setReadOnly(false);
            final Date dateObj1 = startDate.getValue();
            final Date dateObj2 = endDate.getValue();
            final long diff = dateObj2.getTime() - dateObj1.getTime();
            final int diffDays = (int) (diff / (24 * 1000 * 60 * 60));
            if (diffDays >= Constants.ZERO) {
                term.setValue(String.valueOf(diffDays));
            }
            term.setReadOnly(true);
        }
        LOGGER.debug("termSetValue method ends ");
    }

    private void loadComboBox(ComboBox comboBox, List<HelperDTO> dropDownList) {
        for (int i = Constants.ZERO; dropDownList.size() > i; i++) {
            comboBox.addItem(String.valueOf(dropDownList.get(i)
                    .getId()));
            comboBox.setItemCaption(String.valueOf(dropDownList.get(i)
                    .getId()), dropDownList.get(i).getDescription());

        }
    }

    private void addResponsivenessToComponents() throws PortalException, SystemException {

        final ContractHeaderLogic contractLogic = new ContractHeaderLogic();
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final Map<String, AppPermission> fieldContract = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_HEADER+","+"Contract Header",false);

        LOGGER.debug("Entering configurePermission");
        try {

            List<Object> resultList = contractLogic.getFieldsForSecurity(UISecurityUtil.CONTRACT_HEADER, "Contract Header");
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldContract, mode);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending configurePermission");

    }

    /**
     * Inner Class DateValidatorContract that extends AbstractValidator to
     * validate the DateFields.
     */
    public class DateValidatorContract extends AbstractValidator {

        /**
         * Default Constructor.
         */
        public DateValidatorContract() {
            super("");
        }

        /**
         * Parameterized Constructor with parameters error message.
         *
         * @param errorMessage the error message
         */
        public DateValidatorContract(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * Validates the startDate and EndDate field for equals dates or
         * mismatching startDate and EndDate.
         *
         * @param value - Object
         * @throws InvalidValueException the invalid value exception
         */
        public void validate(final Object value) throws Validator.InvalidValueException {
            LOGGER.debug("Entering validate method for start date and end date");
            if (startDate.getValue() != null && endDate.getValue() != null) {
                if (startDate.getValue().after(endDate.getValue())) {
                    throw new Validator.InvalidValueException("Contract End date should be after Contract Start Date");
                } else if (startDate.getValue().getTime() == endDate.getValue().getTime()) {
                    throw new Validator.InvalidValueException("Contract Start date and Contract End date are equal");
                }
            }
            LOGGER.debug("validate method ends ");
        }

        /**
         * compares the date field and returns the boolean value.
         *
         * @param value - Object
         * @return true, if checks if is valid value
         */
        @Override
        protected boolean isValidValue(final Object value) {
            LOGGER.debug("Entering validValue method ");
            if (startDate.getValue() != null && endDate.getValue() != null) {
                final boolean isValid = startDate.getValue().compareTo(endDate.getValue()) <= Constants.ZERO;
                LOGGER.debug("isValidValue method ends after returning isValid= " + isValid);
                return isValid;
            }
            LOGGER.debug("isValidValue method ends after returning isValid = true");
            return true;
        }

        /**
         * Abstract method Overridded.
         *
         * @return Class object.
         */
        @Override
        public Class getType() {
            return null;
        }
    }

    /**
     * Inner Class DateValidatorReNegotiation that extends AbstractValidator to
     * validate the DateFields.
     */
    public class DateValidatorReNegotiation extends AbstractValidator {

        /**
         * Default Constructor.
         */
        public DateValidatorReNegotiation() {
            super("");
        }

        /**
         * Parameterized Constructor with parameter errorMessage.
         *
         * @param errorMessage the error message
         */
        public DateValidatorReNegotiation(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * Validates the renegotiationStartDate and renegotiationEndDate field
         * for equals dates or mismatching startDate and EndDate.
         *
         * @param value - Object
         * @throws InvalidValueException the invalid value exception
         */
        @Override
        public void validate(final Object value) throws InvalidValueException {
            LOGGER.debug("Entering validate method for renegotiationStartDate and renegotiationEndDate");
            if (renegotiationStartDate.getValue() != null && renegotiationEndDate.getValue() != null) {
                if (renegotiationStartDate.getValue().after(renegotiationEndDate.getValue())) {
                    throw new InvalidValueException("Re-Negotiation End Date should be after Re-Negotiation Start date");
                } else if (renegotiationStartDate.getValue().equals(renegotiationEndDate.getValue())) {
                    throw new InvalidValueException("Re-Negotiation Start Date and Re-Negotiation End Date are equal");
                }
            } else if (renegotiationStartDate.getValue() == null && renegotiationEndDate.getValue() != null) {
                throw new InvalidValueException("Re-Negotiation End Date should be selected on Contract Header tab ");
            }
            LOGGER.debug("validate method ended");
        }

        /**
         * compares the date field and returns the boolean value.
         *
         * @param value - Object
         * @return true, if checks if is valid value
         */
        @Override
        protected boolean isValidValue(final Object value) {
            LOGGER.debug("Entering validValue method for renegotiationDate ");
            if (renegotiationStartDate.getValue() != null && renegotiationEndDate.getValue() != null) {
                final boolean valid = renegotiationStartDate.getValue().compareTo(renegotiationEndDate.getValue()) <= Constants.ZERO;
                LOGGER.debug("isValidValue method ended and returns valid=" + valid);
                return valid;
            }
            LOGGER.debug("isValidValue method ended and returns true");
            return true;
        }

        /**
         * Abstract method Overridded.
         *
         * @return Class object.
         */
        @Override
        public Class getType() {
            return null;
        }
    }

    /**
     * Inner Class DateValidatorPriceProtection that extends AbstractValidator
     * to validate the DateFields.
     */
    public class DateValidatorPriceProtection extends AbstractValidator {

        /**
         * Default Constructor.
         */
        public DateValidatorPriceProtection() {
            super("");
        }

        /**
         * Parameterized Constructor with parameter errorMessage.
         *
         * @param errorMessage the error message
         */
        public DateValidatorPriceProtection(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * Validates the priceprotectionStartDate and priceprotectionEndDate
         * field for equals dates or mismatching startDate and EndDate.
         *
         * @param value - Object
         * @throws InvalidValueException the invalid value exception
         */
        @Override
        public void validate(final Object value) throws InvalidValueException {
            LOGGER.debug("Entering validate method ");
            if (priceprotectionStartDate.getValue() != null && priceprotectionEndDate.getValue() != null) {
                if (priceprotectionStartDate.getValue().after(priceprotectionEndDate.getValue())) {
                    throw new InvalidValueException("Price Protection End Date should be after Price Protection Start Date");
                } else if (priceprotectionStartDate.getValue().equals(priceprotectionEndDate.getValue())) {
                    throw new InvalidValueException("Price Protection Start Date and Price Protection End Date are equal");
                }
            } else if (priceprotectionStartDate.getValue() == null && priceprotectionEndDate.getValue() != null) {
                throw new InvalidValueException("Price Protection Start Date should be selected on Contract Header tab");
            }
            LOGGER.debug("validate method ends ");
        }

        /**
         * compares the date field and returns the boolean value.
         *
         * @param value - Object
         * @return true, if checks if is valid value
         */
        @Override
        protected boolean isValidValue(final Object value) {
            LOGGER.debug("Entering isValidValue method ");
            if (priceprotectionStartDate.getValue() != null && priceprotectionEndDate.getValue() != null) {
                final boolean isValid = priceprotectionStartDate.getValue().compareTo(priceprotectionEndDate.getValue()) <= Constants.ZERO;
                LOGGER.debug("isValidValue method ends with isValid = " + isValid);
                return isValid;
            }
            LOGGER.debug("isValidValue method ends with value = true");
            return true;
        }

        /**
         * Abstract method is Over ridded.
         *
         * @return Class object.
         */
        @Override
        public Class getType() {
            return null;
        }
    }
          public void validateFields(){
        Collection<Field<?>> collection = binder.getFields();
            CommonUtils commmonUtil = CommonUtils.getInstance();
            for (Field field : collection) {
                if(field instanceof TextField){
                    TextField textField = (TextField)field;                    
                    commmonUtil.textValidation(textField, textField.getData());                    
                    LOGGER.debug("---->"+textField.getValue());
                }
            }
    }
}
