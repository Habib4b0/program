/**
 *
 */
package com.stpl.app.contract.contractheader.ui.form;

import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

import com.stpl.app.contract.contractheader.logic.ContractHeaderLogic;
import com.stpl.app.contract.contractheader.util.CommonUtils;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.app.contract.util.CommonUIUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.UISecurityUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
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
import java.util.Collection;
import org.apache.commons.lang.StringUtils;

/**
 * @author sibi
 *
 */
public class ContractInformation extends CustomComponent {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final static Logger LOGGER = Logger.getLogger(ContractInformation.class);

    @UiField("cssLayout")
    private CssLayout cssLayout;

    @UiField("insideOwnerLabel")
    private Label insideOwnerLabel;

    @UiField("insidePhoneLabel")
    private Label insidePhoneLabel;

    @UiField("insideAuthorLabel")
    private Label insideAuthorLabel;

    @UiField("insideAdditionalLabel")
    private Label insideAdditionalLabel;

    @UiField("insideAdditionalNameLabel")
    private Label insideAdditionalNameLabel;

    @UiField("insideAdditionalPhoneLabel")
    private Label insideAdditionalPhoneLabel;

    @UiField("outsideOwnerLabel")
	private Label outsideOwnerLabel;

	@UiField("outsidePhoneLabel")
	private Label outsidePhoneLabel;

	@UiField("outsideAuthorLabel")
	private Label outsideAuthorLabel;

	@UiField("outsideAdditionalLabel")
	private Label outsideAdditionalLabel;

	@UiField("outsideAdditionalNameLabel")
	private Label outsideAdditionalNameLabel;

	@UiField("outsideAdditionalPhoneLabel")
	private Label outsideAdditionalPhoneLabel;

	@UiField("advanceNoticeDaysLabel")
	private Label advanceNoticeDaysLabel;

	@UiField("affiliatedContractInfoLabel")
	private Label affiliatedContractInfoLabel;

	@UiField("shippingTermsLabel")
	private Label shippingTermsLabel;

	@UiField("proposalStartDateLabel")
	private Label proposalStartDateLabel;

	@UiField("proposalEndDateLabel")
	private Label proposalEndDateLabel;

	@UiField("originalStartDateLabel")
	private Label originalStartDateLabel;

	@UiField("originalEndDateLabel")
	private Label originalEndDateLabel;

	@UiField("awardStatusLabel")
	private Label awardStatusLabel;

	@UiField("lastUpdatedDateLabel")
	private Label lastUpdatedDateLabel;

	@UiField("priceEscalationClauseLabel")
	private Label priceEscalationClauseLabel;

	@UiField("exemptFromLowPriceLabel")
	private Label exemptFromLowPriceLabel;

	@UiField("priceResetIndicatorLabel")
	private Label priceResetIndicatorLabel;

	@UiField("cancellationClauseLabel")
	private Label cancellationClauseLabel;

	@UiField("mostFavoredNationLabel")
	private Label mostFavoredNationLabel;

	@UiField("categoryLabel")
	private Label categoryLabel;

	@UiField("currencyLabel")
	private Label currencyLabel;

	@UiField("minimumOrderLabel")
	private Label minimumOrderLabel;

	@UiField("paymentTermsLabel")
	private Label paymentTermsLabel;

	@UiField("insideOwner")
	private TextField insideOwner;
	
	@UiField("insidePhone")
	private TextField insidePhone;
	
	@UiField("insideAuthor")
	private TextField insideAuthor;
	
	@UiField("insideAdditional")
	private TextField insideAdditional;
	
	@UiField("insideAdditionalName")
	private TextField insideAdditionalName;
	
	@UiField("insideAdditionalPhone")
	private TextField insideAdditionalPhone;
	
	@UiField("outsideOwner")
	private TextField outsideOwner;
	
	@UiField("outsidePhone")
	private TextField outsidePhone;
	
	@UiField("outsideAuthor")
	private TextField outsideAuthor;
	
	@UiField("outsideAdditional")
	private TextField outsideAdditional;
	
	@UiField("outsideAdditionalName")
	private TextField outsideAdditionalName;
	
	@UiField("outsideAdditionalPhone")
	private TextField outsideAdditionalPhone;
	
	@UiField("advanceNoticeDays")
	private TextField advanceNoticeDays;
	
	@UiField("affiliatedContractInfo")
	private TextField affiliatedContractInfo;
	
	@UiField("shippingTerms")
	private TextField shippingTerms;
	
	@UiField("priceEscalationClause")
	private TextField priceEscalationClause;
	
	@UiField("exemptFromLowPrice")
	private ComboBox exemptFromLowPrice;
	
	@UiField("cancellationClause")
	private TextField cancellationClause;
	
	@UiField("mostFavoredNation")
	private TextField mostFavoredNation;
	
	@UiField("category")
	private TextField category;
	
	@UiField("currency")
	private TextField currency;
	
	@UiField("minimumOrder")
	private TextField minimumOrder;
	
	@UiField("proposalStartDate")
	private PopupDateField proposedStartDate;
	
	@UiField("proposalEndDate")
	private PopupDateField proposedEndDate;
	
	@UiField("originalStartDate")
	private PopupDateField originalStartDate;
	
	@UiField("originalEndDate")
	private PopupDateField originalEndDate;
	
	@UiField("lastUpdatedDate")
	private PopupDateField lastUpdatedDate;
	
	@UiField("awardStatus")
	private ComboBox awardStatus;
	
	@UiField("priceResetIndicator")
	private ComboBox priceResetIndicator;
	
	@UiField("paymentTerms")
	private ComboBox paymentTerms;

    final CustomFieldGroup binder;
    final String mode;
    CommonUIUtils commonUIUtils = new CommonUIUtils();
      CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
     /** The common util. */
    private com.stpl.app.contract.abstractsearch.util.CommonUtils commonUtil = com.stpl.app.contract.abstractsearch.util.CommonUtils.getInstance();
    

    public ContractInformation(final CustomFieldGroup binder , final String mode) throws PortalException, SystemException, Exception {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarative-ui/contract-header/contract-information.xml"), this));
        this.binder = binder;
        this.mode = mode;
        addResponsivenessToComponents();
        configureFields();
        configureBinder();
        validateFields();
    }

    private void configureBinder() {
        binder.bindMemberFields(this);
    }

    private void configureFields() throws SystemException {
        LOGGER.info("Entering configureFields method ");
	
	try{
	final CustomFieldGroup binder;
	
     

        proposedStartDate.setImmediate(true);
        proposedStartDate.setValidationVisible(true);
        proposedStartDate.setDateFormat(Constants.MM_DD_YYYY);
        proposedStartDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Changes the Date format for PopupdateField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                    proposedStartDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(proposedStartDate.getValue()));
            }
        });
        proposedEndDate.setImmediate(true);
        proposedEndDate.setValidationVisible(true);
        proposedEndDate.addValidator(new DateValidatorProposed("Proposed End Date should be greater than Proposed Start Date"));
        proposedEndDate.setDateFormat(Constants.MM_DD_YYYY);
        proposedEndDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Changes the Date format for PopupdateField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                    proposedEndDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(proposedEndDate.getValue()));
            }
        });
        originalStartDate.setImmediate(true);
        originalStartDate.setValidationVisible(true);
        originalStartDate.setDateFormat(Constants.MM_DD_YYYY);
        originalStartDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Changes the Date format for PopupdateField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                    originalStartDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(originalStartDate.getValue()));
            }
        });
        originalEndDate.setImmediate(true);
        originalEndDate.setValidationVisible(true);
        originalEndDate.addValidator(new DateValidatorOriginal("Original End Date should be greater than Original Start Date"));
        originalEndDate.setDateFormat(Constants.MM_DD_YYYY);
        originalEndDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Changes the Date format for PopupdateField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                    originalEndDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(originalEndDate.getValue()));
            }
        });

        exemptFromLowPrice.setNullSelectionAllowed(true);
        exemptFromLowPrice.setNullSelectionItemId(Constants.SELECT_ONE);
        exemptFromLowPrice.addItem(Constants.SELECT_ONE);
        exemptFromLowPrice.addItem("Yes");
        exemptFromLowPrice.addItem("No");
        exemptFromLowPrice.select(Constants.SELECT_ONE);     
        currency.setData("maxlengthvalidationforcurrency,maxlengthvalidationcurrency,null,null");
        currency.setImmediate(true);
        currency.setValidationVisible(true);
        currency.setDescription(currency.getValue());
        currency.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for currency TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                currency.setDescription(currency.getValue());
            }
        });

        advanceNoticeDays.setImmediate(true);
        advanceNoticeDays.setValidationVisible(true);
        advanceNoticeDays.setDescription(advanceNoticeDays.getValue());
        advanceNoticeDays.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for advanceNoticeDays TextField.
             *
             * @param event - ValueChangeEvent
             */
            @SuppressWarnings("PMD")
            public void valueChange(final ValueChangeEvent event) {
                String advanceNoticeDay = advanceNoticeDays.getValue();
                if(advanceNoticeDay!=null && !StringUtils.isBlank(advanceNoticeDay) && advanceNoticeDay != "null"){
                    advanceNoticeDays.setDescription(advanceNoticeDays.getValue());
                }else{
                    advanceNoticeDays.setValue("0");
                }

            }
        });

        insideOwner.setData("maxlengthvalidationfifty,maxlengthvalidationinsideowner,null,null");
        insideOwner.setImmediate(true);
        insideOwner.setValidationVisible(true);
        insideOwner.setDescription(insideOwner.getValue());
        insideOwner.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for insideOwner TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                insideOwner.setDescription(insideOwner.getValue());
            }
        });

        insidePhone.setData("maxlengthvalidationfifty,maxlengthvalidationinsidephone,numericvalidation,specialcharvalidationinsidephone");
        insidePhone.setImmediate(true);
        insidePhone.setValidationVisible(true);
        insidePhone.setDescription(insidePhone.getValue());
        insidePhone.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for insidePhone TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                insidePhone.setDescription(insidePhone.getValue());
            }
        });

        insideAuthor.setData("maxlengthvalidationfifty,maxlengthvalidationinsideauthor,null,null");
        insideAuthor.setImmediate(true);
        insideAuthor.setValidationVisible(true);
        insideAuthor.setDescription(insideAuthor.getValue());
        insideAuthor.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for insideAuthor TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                insideAuthor.setDescription(insideAuthor.getValue());
            }
        });

        insideAdditional.setData("maxlengthvalidationfifty,maxlengthvalidationinsideadditional,null,null");
        insideAdditional.setImmediate(true);
        insideAdditional.setValidationVisible(true);
        insideAdditional.setDescription(insideAdditional.getValue());
        insideAdditional.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for insideAdditional TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                insideAdditional.setDescription(insideAdditional.getValue());
            }
        });

        insideAdditionalName.setData("maxlengthvalidationfifty,maxlengthvalidationinsideadditionalname,null,null");
        insideAdditionalName.setImmediate(true);
        insideAdditionalName.setValidationVisible(true);
        insideAdditionalName.setDescription(insideAdditionalName.getValue());
        insideAdditionalName.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for insideAdditionalName TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                insideAdditionalName.setDescription(insideAdditionalName.getValue());
            }
        });

        insideAdditionalPhone.setData("maxlengthvalidationfifty,maxlengthvalidationinsideadditionalphone,numericvalidation,specialcharvalidationinsideadditionalphone");
        insideAdditionalPhone.setImmediate(true);
        insideAdditionalPhone.setValidationVisible(true);
        insideAdditionalPhone.setDescription(insideAdditionalPhone.getValue());
        insideAdditionalPhone.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for insideAdditionalPhone TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                insideAdditionalPhone.setDescription(insideAdditionalPhone.getValue());
            }
        });

        outsideOwner.setData("maxlengthvalidationfifty,maxlengthvalidationoutsideowner,null,null");
        outsideOwner.setImmediate(true);
        outsideOwner.setValidationVisible(true);
        outsideOwner.setDescription(outsideOwner.getValue());
        outsideOwner.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for outsideOwner TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                outsideOwner.setDescription(outsideOwner.getValue());
            }
        });

        outsidePhone.setData("maxlengthvalidationfifty,maxlengthvalidationoutsidephone,numericvalidation,specialcharvalidationoutsidephone");
        outsidePhone.setImmediate(true);
        outsidePhone.setValidationVisible(true);
        outsidePhone.setDescription(outsidePhone.getValue());
        outsidePhone.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for outsidePhone TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                outsidePhone.setDescription(outsidePhone.getValue());
            }
        });

        outsideAuthor.setData("maxlengthvalidationfifty,maxlengthvalidationoutsideauthor,null,null");
        outsideAuthor.setImmediate(true);
        outsideAuthor.setValidationVisible(true);
        outsideAuthor.setDescription(outsideAuthor.getValue());
        outsideAuthor.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for outsideAuthor TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                outsideAuthor.setDescription(outsideAuthor.getValue());
            }
        });

        outsideAdditional.setData("maxlengthvalidationfifty,maxlengthvalidationoutsideadditional,null,null");
        outsideAdditional.setImmediate(true);
        outsideAdditional.setValidationVisible(true);
        outsideAdditional.setDescription(outsideAdditional.getValue());
        outsideAdditional.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for outsideAdditional TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                outsideAdditional.setDescription(outsideAdditional.getValue());
            }
        });

        outsideAdditionalName.setData("maxlengthvalidationfifty,maxlengthvalidationoutsideadditionalname,null,null");
        outsideAdditionalName.setImmediate(true);
        outsideAdditionalName.setValidationVisible(true);
        outsideAdditionalName.setDescription(outsideAdditionalName.getValue());
        outsideAdditionalName.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for outsideAdditionalName TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                outsideAdditionalName.setDescription(outsideAdditionalName.getValue());
            }
        });

        outsideAdditionalPhone.setData("maxlengthvalidationfifty,maxlengthvalidationoutsideadditionalphone,numericvalidation,specialcharvalidationoutsideadditionalphone");
        outsideAdditionalPhone.setImmediate(true);
        outsideAdditionalPhone.setValidationVisible(true);
        outsideAdditionalPhone.setDescription(outsideAdditionalPhone.getValue());
        outsideAdditionalPhone.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for outsideAdditionalPhone TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                outsideAdditionalPhone.setDescription(outsideAdditionalPhone.getValue());
            }
        });

        affiliatedContractInfo.setData("maxlengthvalidationfifty,maxlengthvalidationaffiliatedcontractinfo,null,null");
        affiliatedContractInfo.setImmediate(true);
        affiliatedContractInfo.setValidationVisible(true);
        affiliatedContractInfo.setDescription(affiliatedContractInfo.getValue());
        affiliatedContractInfo.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for affiliatedContractInfo TextField.
             *
             * @param event - ValueChangeEvent
             */
            @SuppressWarnings("PMD")
            public void valueChange(final ValueChangeEvent event) {
                affiliatedContractInfo.setDescription(affiliatedContractInfo.getValue());
            }
        });

        shippingTerms.setData("maxlengthvalidationfifty,maxlengthvalidationshippingterms,null,null");
        shippingTerms.setImmediate(true);
        shippingTerms.setValidationVisible(true);
        shippingTerms.setDescription(shippingTerms.getValue());
        shippingTerms.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for shippingTerms TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                shippingTerms.setDescription(shippingTerms.getValue());
            }
        });
        
         commonUtil.loadComboBox(awardStatus, UIUtils.STATUS, false);
         commonUtil.loadComboBox(priceResetIndicator, UIUtils.PRICE_RESET_INDICATOR, false);
         commonUtil.loadComboBox(paymentTerms, UIUtils.PAYMENT_TERMS_DDLB, false);
        
        priceEscalationClause.addValidator(new StringLengthValidator("Price Escalation Clause should be less than 50 characters", 0, 50, true));
        priceEscalationClause.setData("maxlengthvalidationfifty,maxlengthvalidationpriceescalationclause,null,null");
        priceEscalationClause.setImmediate(true);
        priceEscalationClause.setValidationVisible(true);
        priceEscalationClause.setDescription(priceEscalationClause.getValue());
        priceEscalationClause.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for priceEscalationClause TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                priceEscalationClause.setDescription(priceEscalationClause.getValue());
            }
        });

        cancellationClause.setData("maxlengthvalidationfifty,maxlengthvalidationcancellationclause,null,null");
        cancellationClause.setImmediate(true);
        cancellationClause.setValidationVisible(true);
        cancellationClause.setDescription(cancellationClause.getValue());
        cancellationClause.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for cancellationClause TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                cancellationClause.setDescription(cancellationClause.getValue());
            }
        });

        mostFavoredNation.setData("maxlengthvalidationfifty,maxlengthvalidationmostfavorednation,null,null");
        mostFavoredNation.setImmediate(true);
        mostFavoredNation.setValidationVisible(true);
        mostFavoredNation.setDescription(mostFavoredNation.getValue());
        mostFavoredNation.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for mostFavoredNation TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                mostFavoredNation.setDescription(mostFavoredNation.getValue());
            }
        });

        category.setData("maxlengthvalidationfifty,maxlengthvalidationcategory,null,null");
        category.setImmediate(true);
        category.setValidationVisible(true);
        category.setDescription(category.getValue());
        category.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for category TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                category.setDescription(category.getValue());
            }
        });

        minimumOrder.setData("maxlengthvalidationfifty,maxlengthvalidationminimumorder,null,null");
        minimumOrder.setImmediate(true);
        minimumOrder.setValidationVisible(true);
        minimumOrder.setDescription(minimumOrder.getValue());
        minimumOrder.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for minimumOrder TextField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                minimumOrder.setDescription(minimumOrder.getValue());
            }
        });

        lastUpdatedDate.setDateFormat(Constants.MM_DD_YYYY);
        lastUpdatedDate.setImmediate(true);
        lastUpdatedDate.setValidationVisible(true);
        lastUpdatedDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Sets the description for lastUpdatedDate DateField.
             *
             * @param event - ValueChangeEvent
             */
            public void valueChange(final ValueChangeEvent event) {
                    lastUpdatedDate.setValue(lastUpdatedDate.getValue());
            }
        });

        proposedStartDate.setDescription(Constants.DATE);
        proposedEndDate.setDescription(Constants.DATE);
        originalStartDate.setDescription(Constants.DATE);
        originalEndDate.setDescription(Constants.DATE);
        lastUpdatedDate.setDescription(Constants.DATE);
        }catch(Exception e){
            LOGGER.error(e);
        }
        LOGGER.info(" configureFields method ends ");
    }

    private void addResponsivenessToComponents() throws PortalException, SystemException, Exception {

        final ContractHeaderLogic contractLogic = new ContractHeaderLogic();
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        final Map<String, AppPermission> fieldContract = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.CONTRACT_HEADER+Constants.COMMA+"Contract Information",false);
        LOGGER.info("Entering configurePermission");
        try {

            List<Object> resultList = contractLogic.getFieldsForSecurity(UISecurityUtil.CONTRACT_HEADER, "Contract Information");

            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldContract, mode);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("Ending configurePermission");
		
    }

    private void loadComboBox(ComboBox comboBox, List<HelperDTO> dropDownList) {
        for (int i = 0; dropDownList.size() > i; i++) {
            comboBox.addItem(String.valueOf(dropDownList.get(i)
                    .getId()));
            comboBox.setItemCaption(String.valueOf(dropDownList.get(i)
                    .getId()), dropDownList.get(i).getDescription());

        }
    }

    /**
     * Inner Class DateValidatorProposed that extends AbstractValidator to
     * validate the DateFields.
     */
    public class DateValidatorProposed extends AbstractValidator {

        /**
         * Default Constructor.
         */
        public DateValidatorProposed() {
            super("");
        }

        /**
         * Parameterized Constructor with parameter errorMessage.
         *
         * @param errorMessage the error message
         */
        public DateValidatorProposed(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * Validates the proposedStartDate and proposedEndDate field for equals
         * dates or mismatching startDate and EndDate.
         *
         * @param value - Object
         */
        @Override
        public void validate(final Object value) {
            LOGGER.info("Entering validate method ");
            if (proposedStartDate.getValue() != null && proposedEndDate.getValue() != null) {
                if (proposedStartDate.getValue().after(proposedEndDate.getValue())) {
                    throw new InvalidValueException("Proposed End Date should be after Proposed Start Date");
                } else if (proposedStartDate.getValue().equals(proposedEndDate.getValue())) {
                    throw new InvalidValueException("Proposed Start Date and Proposed End Date are equal");
                }
            } else if (proposedStartDate.getValue() == null && proposedEndDate.getValue() != null) {
                throw new InvalidValueException("Proposed End Date should be after Proposed Start Date");
            }
            LOGGER.info("validate method ends");
        }

        /**
         * compares the date field and returns the boolean value.
         *
         * @param value - Object
         * @return true, if checks if is valid value
         */
        @Override
        protected boolean isValidValue(final Object value) {
            LOGGER.info("Entering isValidValue method ");
            if (proposedStartDate.getValue() != null && proposedEndDate.getValue() != null) {
                final boolean validValue = proposedStartDate.getValue().compareTo(proposedEndDate.getValue()) <= 0;
                LOGGER.info("isValidValue method ends with validValue=" + validValue);
                return validValue;
            }
            LOGGER.info("isValidValue method ends with validValue == true");
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

    /**
     * Inner Class DateValidatorOriginal that extends AbstractValidator to
     * validate the DateFields.
     */
    public class DateValidatorOriginal extends AbstractValidator {

        /**
         * Default Constructor.
         */
        public DateValidatorOriginal() {
            super("");
        }

        /**
         * Parameterized Constructor with parameter errorMessage.
         *
         * @param errorMessage the error message
         */
        public DateValidatorOriginal(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * Validates the originalStartDate and originalEndDate field for equals
         * dates or mismatching startDate and EndDate.
         *
         * @param value - Object
         * @throws InvalidValueException the invalid value exception
         */
        @Override
        public void validate(final Object value) throws InvalidValueException {
            LOGGER.info("Entering validate method ");
            if (originalStartDate.getValue() != null && originalEndDate.getValue() != null) {
                if (originalStartDate.getValue().after(originalEndDate.getValue())) {
                    throw new InvalidValueException("Original End Date should be after Original Start Date");
                } else if (originalStartDate.getValue().equals(originalEndDate.getValue())) {
                    throw new InvalidValueException("Original Start Date and Original End Date are equal");
                }
            } else if (originalStartDate.getValue() == null && originalEndDate.getValue() != null) {
                throw new InvalidValueException("Original End Date should be after Original Start Date");
            }
            LOGGER.info("validate method ends ");
        }

        /**
         * compares the date field and returns the boolean value.
         *
         * @param value - Object
         * @return true, if checks if is valid value
         */
        @Override
        protected boolean isValidValue(final Object value) {
            LOGGER.info("Entering isValidValue method ");
            if (originalStartDate.getValue() != null && originalEndDate.getValue() != null) {
                final boolean isvalueValid = originalStartDate.getValue().compareTo(originalEndDate.getValue()) <= 0;
                LOGGER.info("isValidValue method ends with isvalueValid= " + isvalueValid);
                return isvalueValid;
            }
            LOGGER.info("isValidValue method ends with value = true");
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
                    LOGGER.info("---->"+textField.getValue());
                }
            }
    }

}
