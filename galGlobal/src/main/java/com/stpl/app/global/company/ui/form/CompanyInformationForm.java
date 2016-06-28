/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.company.ui.form;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.company.dto.CompanyMasterDTO;
import com.stpl.app.global.company.logic.CompanySearchLogic;
import com.stpl.app.global.company.ui.view.CompanyAddView;
import com.stpl.app.global.company.util.UIUtils;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorLabel;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.app.util.ValidationUtils;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import org.jboss.logging.Logger; 
import org.apache.commons.lang.StringUtils;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author shyam.duraipandian
 */
public class CompanyInformationForm {

    @UiField("cssLayout")
    CssLayout cssLayout;
    @UiField("hLayout")
    HorizontalLayout hlayout;
    //Labels
    /**
     * The company id.
     */
    @UiField("labelCompanyId")
    private Label labelCompanyId;
    /**
     * The company no.
     */
    @UiField("labelCompanyNo")
    private Label labelCompanyNo;
    /**
     * The company name.
     */
    @UiField("labelCompanyName")
    private Label labelCompanyName;
    /**
     * The company status.
     */
    @UiField("labelCompanyStatus")
    private Label labelCompanyStatus;
    /**
     * The company start date.
     */
    @UiField("labelCompanyStartDate")
    private Label labelCompanyStartDate;
    /**
     * The company end date.
     */
    @UiField("labelCompanyEndDate")
    private Label labelCompanyEndDate;
    /**
     * The company type.
     */
    @UiField("labelCompanyType")
    private Label labelCompanyType;
    /**
     * The lives.
     */
    @UiField("labelLives")
    private Label labelLives;
    /**
     * The company group.
     */
    @UiField("labelCompanyGroup")
    private Label labelCompanyGroup;
    /**
     * The company category.
     */
    @UiField("labelCompanyCategory")
    private Label labelCompanyCategory;
    /**
     * The organization key.
     */
    @UiField("labelOrganizationKey")
    private Label labelOrganizationKey;
    /**
     * Thesource.
     */
    @UiField("labelSource")
    private Label labelSource;
    /**
     * The financial system.
     */
    @UiField("labelFinancialSystem")
    private Label labelFinancialSystem;
    /**
     * The udc1.
     */
    @UiField("labelUdc1")
    private Label labelUdc1;
    /**
     * The udc2.
     */
    @UiField("labelUdc2")
    private Label labelUdc2;
    /**
     * The udc3.
     */
    @UiField("labelUdc3")
    private Label labelUdc3;
    /**
     * The udc4.
     */
    @UiField("labelUdc4")
    private Label labelUdc4;
    /**
     * The udc5.
     */
    @UiField("labelUdc5")
    private Label labelUdc5;
    /**
     * The udc6.
     */
    @UiField("labelUdc6")
    private Label labelUdc6;
    /**
     * The System Id label.
     */
    @UiField("labelSystemId")
    private Label labelSystemId;
    //Text box and Comb box
    /**
     * The error msg.
     */
    @UiField("errorMsg")
    ErrorLabel errorMsg = new ErrorLabel();
    /**
     * The company id.
     */
    @UiField("companyId")
    TextField companyId;
    /**
     * The company no.
     */
    @UiField("companyNo")
    TextField companyNo;
    /**
     * The company name.
     */
    @UiField("companyName")
    TextField companyName;
    /**
     * The company status.
     */
    @UiField("companyStatus")
    ComboBox companyStatus;
    /**
     * The company start date.
     */
    @UiField("companyStartDate")
    PopupDateField companyStartDate;
    /**
     * The company end date.
     */
    @UiField("companyEndDate")
    PopupDateField companyEndDate;
    /**
     * The company type.
     */
    @UiField("companyType")
    ComboBox companyType;
    /**
     * The lives.
     */
    @UiField("lives")
    TextField lives;
    /**
     * The company group.
     */
    @UiField("companyGroup")
    ComboBox companyGroup;
    /**
     * The company category.
     */
    @UiField("companyCategory")
    ComboBox companyCategory;
    /**
     * The organization key.
     */
    @UiField("organizationKey")
    ComboBox organizationKey;
    /**
     * Thesource.
     */
    @UiField("source")
    TextField source;
    /**
     * The financial system.
     */
    @UiField("financialSystem")
    TextField financialSystem;
    /**
     * The System Id.
     */
    @UiField("companySystemId")
    TextField companySystemId;
    /**
     * The udc1.
     */
    @UiField("udc1")
    ComboBox udc1;
    /**
     * The udc2.
     */
    @UiField("udc2")
    ComboBox udc2;
    /**
     * The udc3.
     */
    @UiField("udc3")
    ComboBox udc3;
    /**
     * The udc4.
     */
    @UiField("udc4")
    ComboBox udc4;
    /**
     * The udc5.
     */
    @UiField("udc5")
    ComboBox udc5;
    /**
     * The udc6.
     */
    @UiField("udc6")
    ComboBox udc6;
    @UiField("systemId")
    TextField systemId;
    /**
     * The created by.
     */
    @UiField("createdBy")
    TextField createdBy;
    /**
     * The created date.
     */
    @UiField("createdDate")
    PopupDateField createdDate;
    /**
     * The modified by.
     */
    @UiField("modifiedBy")
    TextField modifiedBy;
    /**
     * The modified date.
     */
    @UiField("modifiedDate")
    PopupDateField modifiedDate;   
    /**
     * The created by.
     */
    @UiField("createdByLB")
    Label createdByLB;
    /**
     * The created date.
     */
    @UiField("createdDateLB")
    Label createdDateLB;
    /**
     * The modified by.
     */
    @UiField("modifiedByLB")
    Label modifiedByLB;
    /**
     * The modified date.
     */
    @UiField("modifiedDateLB")
    Label modifiedDateLB;
    /** The common util. */
    private CommonUtil commonUtil = CommonUtil.getInstance();
    /** The Company Master DTO */
    CompanyMasterDTO companyMasterDTO = new CompanyMasterDTO();
    /** The binder */
    private ErrorfulFieldGroup binder;
    private CompanySearchLogic companyLogic = new CompanySearchLogic();
    SessionDTO sessionDTO;

    CommonUIUtils commonUiUtil = new CommonUIUtils();
    IFPLogic ifpLogic = new IFPLogic();
    private static final Logger LOGGER = Logger.getLogger(CompanyAddView.class);
  CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
  
    public VerticalLayout getContent(ErrorfulFieldGroup binder,final SessionDTO sessionDTO ) throws PortalException, SystemException, Exception {
        VerticalLayout vLayout = new VerticalLayout();
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
        final Map<String, AppPermission> fieldCompanyHM = stplSecurity
                .getFieldOrColumnPermission(userId, UISecurityUtil.COMPANY_MASTER+ConstantsUtils.COMMA+"Company Information",false);
        this.binder = binder;
        this.sessionDTO = sessionDTO;
        vLayout.addComponent(Clara.create(getClass().getResourceAsStream("/clara/companyMaster/CompanyInformationForm.xml"), this));
        getFirstTab1(fieldCompanyHM);
        if ((ConstantsUtils.ADD).equals(sessionDTO.getMode())) {
        getBinder();
        configureFields();
        }else{
         configureFields();  
          getBinder();
        
        }
        return vLayout;
    }

    private ErrorfulFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setBuffered(true);
        binder.setErrorDisplay(errorMsg);
        errorMsg.setId("ErrorMessage");
        return binder;
    }

    public void hideColumns(String mode) {
        if ((ConstantsUtils.EDIT).equals(mode)) {
            binder.getField("source").setEnabled(false);
            binder.getField("systemId").setEnabled(false);
            modifiedBy.setEnabled(false);
            createdBy.setEnabled(false);
            createdDate.setEnabled(false);
            modifiedDate.setEnabled(false);
        } else if ((ConstantsUtils.VIEW).equals(mode)) {
            modifiedBy.setEnabled(false);
            createdBy.setEnabled(false);
            createdDate.setEnabled(false);
            modifiedDate.setEnabled(false);
        } else if ((ConstantsUtils.ADD).equals(mode)) {
            modifiedBy.setEnabled(false);
            createdBy.setEnabled(false);
            createdDate.setEnabled(false);
            modifiedDate.setEnabled(false);
        }
        lives.setVisible(false);
        labelLives.setVisible(false);
    }

    public void configureFields() {
        try {
            LOGGER.info("configureFields");
            companyStartDate.addStyleName("startdateMandatory");

            createdBy.setEnabled(false);
            createdDate.setEnabled(false);
            createdDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            createdByLB.setEnabled(false);
            createdDateLB.setEnabled(false);

            modifiedBy.setEnabled(false);
            modifiedDate.setEnabled(false);
            modifiedDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            modifiedByLB.setEnabled(false);
            modifiedDateLB.setEnabled(false);

            LOGGER.info("companyName add" + companyName.getValue().toString());

            companyName.setDescription(companyName.getValue());
            companyName.markAsDirty();
            companyName.setImmediate(true);
            companyName.setValidationVisible(true);
            companyName.setData("maxlengthvalidationhundred,maxlengthvalidationcompanyname,null,null");
            companyNo.setImmediate(true);
            companyNo.setDescription(companyNo.getValue());
            companyNo.setValidationVisible(true);
            companyNo.setData("maxlengthvalidationfifty,maxlengthvalidationcompanyno,alphaNumericCharsWithoutStar,specialcharvalidationcompanyno");
            companyId.setData("maxlengthvalidation,maxlengthvalidationcompanyid,alphaNumericCharsWithoutStar,specialcharvalidationcompanyid");
            companyId.setImmediate(true);
            companyId.setValidationVisible(true);
            companyId.setDescription(companyId.getValue());
            companyStartDate.setValidationVisible(true);
            companyStartDate.setImmediate(true);
            companyStartDate.setId("CompanyStartDate");
            companyStartDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            companyStartDate.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for Value change event.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    try {
                        companyStartDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(companyStartDate.getValue()));
                    } catch (Exception ex) {
                        LOGGER.error(ex);
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
                    }
                }
            });
            companyEndDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            companyEndDate.setImmediate(true);
            companyEndDate.setValidationVisible(true);
            companyEndDate.setId("CompanyEndDate");
            companyEndDate.addValidator(new DateValidator("Company start date should be before Company end date"));
            companyEndDate.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for Value change event.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    try {
                        companyEndDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(companyEndDate.getValue()));
                    } catch (Exception ex) {
                        LOGGER.error(ex);
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
                        
                    }
                }
            });
           
            companyStartDate.addStyleName(UIUtils.MANDATORY_DATE_PICKER);

            commonUtil.loadComboBox(companyGroup, UIUtils.COMPANY_GROUP, false);
            commonUtil.loadComboBox(companyCategory, UIUtils.COMPANY_CATEGORY, false);
            commonUtil.loadComboBox(organizationKey, UIUtils.ORGANIZATION_KEY, false);
            commonUtil.loadComboBox(companyStatus, UIUtils.STATUS, false);
            commonUtil.loadComboBox(companyType, UIUtils.COMPANY_TYPE, false);

            commonUtil.loadComboBox(udc1, UIUtils.UDC1, false);
            commonUtil.loadComboBox(udc2, UIUtils.UDC2, false);
            commonUtil.loadComboBox(udc3, UIUtils.UDC3, false);
            commonUtil.loadComboBox(udc4, UIUtils.UDC4, false);
            commonUtil.loadComboBox(udc5, UIUtils.UDC5, false);
            commonUtil.loadComboBox(udc6, UIUtils.UDC6, false);

            financialSystem.addValidator(new RegexpValidator(ValidationUtils.SPECIAL_CHAR, ValidationUtils.SPECIAL_CHAR_MSG));
            financialSystem.setImmediate(true);
            financialSystem.setValidationVisible(true);
            financialSystem.setData("maxlengthvalidationhundred,maxlengthvalidationfinancialSystem,alphaNumericChars,alphaNumericCharsMessage");
            financialSystem.setDescription(financialSystem.getValue());
            financialSystem.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for Value change event.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    financialSystem.setDescription(financialSystem.getValue());
                }
            });

            lives.addValidator(new RegexpValidator(ValidationUtils.NUM_VALIDATION_WITH_COMMA, ValidationUtils.NUM_NEG_MSG));
            lives.setImmediate(true);
            lives.setValidationVisible(true);
            lives.setData("maxlengthvalidationhundred,maxlengthvalidationlives,numericonly,numberValidationMessage");
            lives.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for Value change event.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    try {
                        DecimalFormat formatLives = new DecimalFormat("###,###,###");
                        String value = lives.getValue();
                        value = value.replace(ConstantsUtils.COMMA, StringUtils.EMPTY);
                        if (!Double.isNaN(Double.valueOf(value))) {
                            if (value.contains(ConstantsUtils.COMMA) && StringUtils.isNotBlank(value)) {
                                value = value.replace(ConstantsUtils.COMMA, StringUtils.EMPTY);
                                lives.setDescription(formatLives.format(Double.valueOf(value)));
                                lives.setValue(formatLives.format(Double.valueOf(value)));
                            } else if (StringUtils.isNotBlank(value)) {
                                value = value.replace(ConstantsUtils.COMMA, StringUtils.EMPTY);
                                lives.setDescription(formatLives.format(Double.valueOf(value)));
                                lives.setValue(formatLives.format(Double.valueOf(value)));
                            }
                        }
                    } catch (Exception e) {
                        LOGGER.error(e);
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

                    }
                }
            });
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }

    }

    private class DateValidator extends AbstractValidator {

        /**
         * The Constant serialVersionUID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * The Default Constructor.
         */
        public DateValidator() {
            super(StringUtils.EMPTY);
        }

        /**
         * The Constructor with error message.
         *
         * @param errorMessage the error message
         */
        public DateValidator(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * To validate the companyStartDate.
         *
         * @param value the value
         * @throws InvalidValueException the invalid value exception
         */
        @Override
        public void validate(final Object value) throws Validator.InvalidValueException {
            if (companyStartDate.getValue() != null && companyEndDate.getValue() != null) {
                if (companyStartDate.getValue().after(companyEndDate.getValue())) {
                    throw new Validator.InvalidValueException("Company End date should be greater than Company Start date");
                } else if (companyStartDate.getValue().equals(companyEndDate.getValue())) {
                    throw new Validator.InvalidValueException("Company Start date and Company End date should not be  equal");
                }
            }

        }

        /**
         * To check the value is valid.
         *
         * @param value the value
         * @return true, if checks if is valid value
         */
        @Override
        protected boolean isValidValue(final Object value) {
            if (companyStartDate.getValue() != null && companyEndDate.getValue() != null) {
                return companyStartDate.getValue().compareTo(companyEndDate.getValue()) <= 0;

            }
            return true;
        }

        /**
         * To get the Type.
         *
         * @return the type
         */
        @Override
        public Class getType() {
            return null;
        }
    }

    private void getFirstTab1(final Map<String, AppPermission> fieldCompanyHM) {
        LOGGER.info("Entering getFirstTab1");
        try {
        String mode = sessionDTO.getMode();
        
        List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.COMPANY_MASTER,"Company Information");
        commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldCompanyHM, mode);
        }catch(Exception ex){
            LOGGER.error(ex);
        }
        LOGGER.info("Ending getFirstTab1");

    }
   
}
