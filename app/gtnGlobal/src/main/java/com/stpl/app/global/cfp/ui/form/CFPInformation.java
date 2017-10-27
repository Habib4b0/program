/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.cfp.ui.form;

import com.stpl.app.global.cfp.logic.CFPSearchLogic;
import com.stpl.app.global.cfp.util.CommonUtils;
import com.stpl.app.global.cfp.util.UIUtils;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.StplR2Exception;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.TabNameUtil;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.AbstractValidator;
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
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author sooriya.lakshmanan
 */
public class CFPInformation extends CustomComponent {

    /**
     * LOGGER
     */
    private static final Logger LOGGER = Logger.getLogger(CFPInformation.class);
    final ErrorfulFieldGroup binder;
    private final IFPLogic ifpLogic = new IFPLogic();
    @UiField("cssLayout")
    CssLayout cssLayout;
    @UiField("companyFamilyPlanIdLB")
    private Label companyFamilyPlanIdLB;
    @UiField("companyFamilyPlanId")
    TextField companyFamilyPlanId;
    @UiField("companyFamilyPlanNoLB")
    private Label companyFamilyPlanNoLB;
    @UiField("companyFamilyPlanNo")
    private TextField companyFamilyPlanNo;
    @UiField("companyFamilyPlanNameLB")
    private Label companyFamilyPlanNameLB;
    @UiField("companyFamilyPlanName")
    private TextField companyFamilyPlanName;
    @UiField("companyFamilyPlanStatusLB")
    private Label companyFamilyPlanStatusLB;
    @UiField("companyFamilyPlanStatus")
    private ComboBox companyFamilyPlanStatus;
    @UiField("companyFamilyPlanStartDateLB")
    private Label companyFamilyPlanStartDateLB;
    @UiField("companyFamilyPlanStartDate")
    private PopupDateField companyFamilyPlanStartDate;
    @UiField("companyFamilyPlanEndDateLB")
    private Label companyFamilyPlanEndDateLB;
    @UiField("companyFamilyPlanEndDate")
    private PopupDateField companyFamilyPlanEndDate;
    @UiField("companyFamilyPlanTypeLB")
    private Label companyFamilyPlanTypeLB;
    @UiField("companyFamilyPlanType")
    private ComboBox companyFamilyPlanType;
    @UiField("companyFamilyPlanCategoryLB")
    private Label companyFamilyPlanCategoryLB;
    @UiField("companyFamilyPlanCategory")
    private ComboBox companyFamilyPlanCategory;
    @UiField("companyFamilyPlanTradeClassLB")
    private Label companyFamilyPlanTradeClassLB;
    @UiField("companyFamilyPlanTradeClass")
    private ComboBox companyFamilyPlanTradeClass;
    @UiField("companyFamilyPlanDesignationLB")
    private Label companyFamilyPlanDesignationLB;
    @UiField("companyFamilyPlanDesignation")
    private ComboBox companyFamilyPlanDesignation;
    @UiField("parentCompanyFamilyPlanIdLB")
    private Label parentCompanyFamilyPlanIdLB;
    @UiField("parentCompanyFamilyPlanId")
    private CustomTextField parentCompanyFamilyPlanId;
    @UiField("parentCompanyFamilyPlanNameLB")
    private Label parentCompanyFamilyPlanNameLB;
    @UiField("parentCompanyFamilyPlanName")
    private CustomTextField parentCompanyFamilyPlanName;
    @UiField("createdByLB")
    private Label createdByLB;
    @UiField("createdBy")
    private TextField createdBy;
    @UiField("createdDateLB")
    private Label createdDateLB;
    @UiField("createdDate")
    private PopupDateField createdDate;
    @UiField("modifiedByLB")
    private Label modifiedByLB;
    @UiField("modifiedBy")
    private TextField modifiedBy;
    @UiField("modifiedDateLB")
    private Label modifiedDateLB;
    @UiField("modifiedDate")
    private PopupDateField modifiedDate;
    @UiField("salesInclusion")
    private ComboBox salesInclusion;
    private TextField parentFlagSID = new TextField();
    ParentCFPIdLookup lookUp = null;
    SessionDTO sessionDTO;
    private String mode;
    /**
     * The company Family Plan SystemId.
     */
    @SuppressWarnings("PMD")
    private TextField companyFamilyPlanSystemId = new TextField();// Dont delete
    /**
     * the used id
     */
    private String userId;
    final CommonUtils commonUtils = new CommonUtils();
    private CommonUtil commonUtil = CommonUtil.getInstance();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
 
    public CFPInformation(final ErrorfulFieldGroup binder, final SessionDTO sessionDTO)  {

        this.binder = binder;
        this.sessionDTO = sessionDTO;
        this.mode = sessionDTO.getMode();

        try {
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarativeui/company_family_plan/cfpinformation.xml"), this));
            if (ConstantsUtils.ADD.equals(mode)) {
                binder.bindMemberFields(this);
                 configSalesInclusion();
            }
            
            final StplSecurity stplSecurity = new StplSecurity();
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldCfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.COMPANY_FAMILY_PLAN+ConstantsUtils.COMMA+"CFP Information",false);
            configurePermission(fieldCfpHM);
            configureFields();
            if (ConstantsUtils.EDIT.equals(mode) || ConstantsUtils.VIEW_BTN.equals(mode)) {
                binder.bindMemberFields(this);
            }
            validateFields();
            setDefaultFocus();

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Gets the first tab.
     *
     * @param fieldCfpHM the field cfp hm
     * @return the first tab
     * @throws StplR2Exception the stpl r2 exception
     */
   private void configurePermission(final Map<String, AppPermission> fieldCfpHM) {
        LOGGER.debug("Entering configurePermission");
        try {
        List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.COMPANY_FAMILY_PLAN, TabNameUtil.CFP_INFO);
          commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldCfpHM, mode);
        }catch(Exception ex){
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending configurePermission");
    }

    private void configureFields() {

        commonUtil.loadComboBox(salesInclusion,"LOCKED_STATUS",false);
        commonUtil.loadComboBox(companyFamilyPlanStatus, UIUtils.CFP_STATUS, false);
        commonUtil.loadComboBox(companyFamilyPlanCategory, UIUtils.CFP_CATAGORY, false);
        commonUtil.loadComboBox(companyFamilyPlanType, UIUtils.CFP_TYPE, false);
        commonUtil.loadComboBox(companyFamilyPlanDesignation, UIUtils.CFP_DESIGNATION, false);
        commonUtil.loadComboBox(companyFamilyPlanTradeClass, UIUtils.TRADE_CLASS, false);
        
        companyFamilyPlanEndDate.setDescription(ConstantsUtils.DATE_DES);
        companyFamilyPlanStartDate.setDescription(ConstantsUtils.DATE_DES);
        companyFamilyPlanName.setData("maxlengthvalidationhundred,maxlengthvalidationcompanyfamilyplanname,null,null");
        companyFamilyPlanName.setDescription(companyFamilyPlanName.getValue());
        companyFamilyPlanName.setImmediate(true);
        companyFamilyPlanName.setValidationVisible(true);
        companyFamilyPlanName.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * This method listens to data source value changes and passes the
             * changes forwards.
             */
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {

                companyFamilyPlanName.setDescription(companyFamilyPlanName.getValue());

            }
        });

        companyFamilyPlanNo.setImmediate(true);
        companyFamilyPlanNo.setValidationVisible(true);
        companyFamilyPlanNo.setData("maxlengthvalidationfifty,maxlengthvalidationcompanyfamilyplanno,null,null");
        companyFamilyPlanNo.setDescription(companyFamilyPlanNo.getValue());
        companyFamilyPlanNo.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * This method listens to data source value changes and passes the
             * changes forwards.
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                companyFamilyPlanNo.setDescription(companyFamilyPlanNo.getValue());

            }
        });
        companyFamilyPlanId.setData("maxlengthvalidationfifty,maxlengthvalidationcompanyfamilyplanId,null,null");
        companyFamilyPlanId.setImmediate(true);
        companyFamilyPlanId.setValidationVisible(true);
        
        if (companyFamilyPlanId.getValue() == null) {
            companyFamilyPlanId.setValue(StringUtils.EMPTY);
        }
        companyFamilyPlanId.setDescription(companyFamilyPlanId.getValue());
        companyFamilyPlanId.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * This method listens to data source value changes and passes the
             * changes forwards.
             */
            public void valueChange(final Property.ValueChangeEvent event) {

                companyFamilyPlanId.setDescription(companyFamilyPlanId.getValue());
                if (companyFamilyPlanStatus.getValue() != null && companyFamilyPlanStatus.getValue().equals(StringUtils.EMPTY)) {
                    companyFamilyPlanStatus.select(null);
                }

            }
        });


        companyFamilyPlanStartDate.setImmediate(true);
        companyFamilyPlanStartDate.setValidationVisible(true);
        companyFamilyPlanStartDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        companyFamilyPlanStartDate.addStyleName(ConstantsUtils.DATE_FIEILD_CENTER);
        companyFamilyPlanStartDate.setId("CFPStartDate");
        companyFamilyPlanStartDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * This method listens to data source value changes and passes the
             * changes forwards.
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                companyFamilyPlanStartDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(companyFamilyPlanStartDate.getValue()));
            }
        });

        companyFamilyPlanEndDate.setImmediate(true);
        companyFamilyPlanEndDate.setValidationVisible(true);
        companyFamilyPlanEndDate.addValidator(new DateValidator("Company start date should be before Company end date"));
        companyFamilyPlanEndDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        companyFamilyPlanEndDate.addStyleName(ConstantsUtils.DATE_FIEILD_CENTER);
        companyFamilyPlanEndDate.setId("CFPEndDate");
        companyFamilyPlanEndDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * This method listens to data source value changes and passes the
             * changes forwards.
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                companyFamilyPlanEndDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(companyFamilyPlanEndDate.getValue()));
            }
        });

        parentCompanyFamilyPlanId.addStyleName("searchicon");
        parentCompanyFamilyPlanName.addStyleName("searchicon");
        parentCompanyFamilyPlanId.setReadOnly(true);
        parentCompanyFamilyPlanName.setReadOnly(true);
        parentCompanyFamilyPlanId.setImmediate(true);
        parentCompanyFamilyPlanName.setImmediate(true);
        parentCompanyFamilyPlanId.addClickListener(new CustomTextField.ClickListener() {
            @Override
            public void click(CustomTextField.ClickEvent event) {
                try {
                    int cfpSystemId = (companyFamilyPlanSystemId.getValue() != null) ? Integer.parseInt(companyFamilyPlanSystemId.getValue().replace(",", "")) : 0;
                    if(lookUp==null){
                    lookUp = new ParentCFPIdLookup(parentCompanyFamilyPlanId, parentCompanyFamilyPlanName, cfpSystemId, parentFlagSID, sessionDTO);
                    UI.getCurrent().addWindow(lookUp);
                    }
                    lookUp.addCloseListener(new Window.CloseListener() {
                        @SuppressWarnings("PMD")
                        public void windowClose(final Window.CloseEvent e) {
                            parentCompanyFamilyPlanId.setReadOnly(true);
                            parentCompanyFamilyPlanName.setReadOnly(true);
                            companyFamilyPlanId.focus();
                            lookUp=null;
                        }
                    });
                }catch (Exception e) {
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
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                // Do Nothing
                
            }
        });
        parentCompanyFamilyPlanName.addClickListener(new CustomTextField.ClickListener() {
            @Override
            public void click(CustomTextField.ClickEvent event) {
                try {
                    int cfpSystemId = (companyFamilyPlanSystemId.getValue() != null) ? Integer.parseInt(companyFamilyPlanSystemId.getValue().replace(",", "")) : 0;
                   if(lookUp==null){
                    lookUp = new ParentCFPIdLookup(parentCompanyFamilyPlanId, parentCompanyFamilyPlanName, cfpSystemId, parentFlagSID, sessionDTO);
                    UI.getCurrent().addWindow(lookUp);
                   }
                    lookUp.addCloseListener(new Window.CloseListener() {
                        @SuppressWarnings("PMD")
                        public void windowClose(final Window.CloseEvent e) {
                            parentCompanyFamilyPlanId.setReadOnly(true);
                            parentCompanyFamilyPlanName.setReadOnly(true);
                            companyFamilyPlanId.focus();
                            lookUp=null;
                        }
                    });
                }catch (Exception e) {
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
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                // Do Nothing
                
            }
        });
        companyFamilyPlanDesignation.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method used for lodding-RebateScheduleDesignation Dblb ListType
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                String caption = companyFamilyPlanDesignation.getItemCaption(companyFamilyPlanDesignation.getValue());
                companyFamilyPlanDesignation.setDescription(caption);
                if ("Child".equalsIgnoreCase(caption)) {
                    parentCompanyFamilyPlanId.setEnabled(true);
                    parentCompanyFamilyPlanName.setEnabled(true);
                    parentCompanyFamilyPlanId.setReadOnly(true);
                    parentCompanyFamilyPlanName.setReadOnly(true);
                } else {
                    parentCompanyFamilyPlanId.setReadOnly(false);
                    parentCompanyFamilyPlanId.setValue(StringUtils.EMPTY);
                    parentCompanyFamilyPlanId.setEnabled(false);
                    parentCompanyFamilyPlanName.setReadOnly(false);
                    parentCompanyFamilyPlanName.setValue(StringUtils.EMPTY);
                    parentCompanyFamilyPlanName.setEnabled(false);

                }
            }
        });

    }

    /**
     * The Class DateValidator.
     */
    @SuppressWarnings("rawtypes")
    private class DateValidator extends AbstractValidator {

        /**
         * The Constructor.
         */
        public DateValidator() {
            super(StringUtils.EMPTY);
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
         * Compares and validates the date.
         *
         * @Param value - Object.
         */
        public void validate(final Object value) {

            if (companyFamilyPlanStartDate.getValue() != null && companyFamilyPlanEndDate.getValue() != null) {
                if (companyFamilyPlanStartDate.getValue().after(companyFamilyPlanEndDate.getValue())) {
                    throw new Validator.InvalidValueException("Company FamilyPlan Start date should be less than Company FamilyPlan End date");
                } else if (companyFamilyPlanStartDate.getValue().equals(companyFamilyPlanEndDate.getValue())) {
                    throw new Validator.InvalidValueException("Company FamilyPlan Start date and Company FamilyPlan End date are equal");
                }
            }

        }

        /**
         * Checks the for a valid date.
         */
        protected boolean isValidValue(final Object value) {

            if (companyFamilyPlanStartDate.getValue() != null && companyFamilyPlanEndDate.getValue() != null) {
                return companyFamilyPlanStartDate.getValue().compareTo(companyFamilyPlanEndDate.getValue()) <= 0;
            }

            return true;
        }

        /**
         * Gets the type of the class.
         *
         * @return the type
         */
        @Override
        public Class getType() {
            return null;
        }
    }

    private void setDefaultFocus() {
        companyFamilyPlanId.focus();
        parentCompanyFamilyPlanId.setReadOnly(true);
        parentCompanyFamilyPlanId.setEnabled(false);
        parentCompanyFamilyPlanName.setReadOnly(true);
        parentCompanyFamilyPlanName.setEnabled(false);
        if (!String.valueOf(mode).equals(ConstantsUtils.ADD)) {
            modifiedDate.setValue(new Date());
        }
 if (String.valueOf(mode).equals(ConstantsUtils.ADD)){
  createdBy.setValue(CFPSearchLogic.getUseName());
 }
       
        createdDate.setValue(new Date());



        createdBy.setImmediate(true);
        createdBy.setReadOnly(true);
        createdBy.setEnabled(false);
        
        createdDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        createdDate.setImmediate(true);
        createdDate.setReadOnly(true);
        createdDate.setEnabled(false);
       

        modifiedBy.setImmediate(true);
        modifiedBy.setReadOnly(true);
        modifiedBy.setEnabled(false);
        
        modifiedDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        modifiedDate.setImmediate(true);
        modifiedDate.setReadOnly(true);
        modifiedDate.setEnabled(false);
    }
/*
 Company Famlily plan Length and Special Character Validation
 */
    public void validateFields() {
        Collection<Field<?>> collection = binder.getFields();
        CommonUtil commmonUtil = CommonUtil.getInstance();
        for (Field field : collection) {
            if (field instanceof TextField) {
                TextField textField = (TextField) field;
                commmonUtil.textValidation(textField, textField.getData());
               
            }
        }
    }
    
    /**
     * To make sales inclusion combobox disable with "YES" selected
     */
    private void configSalesInclusion() {
        HelperListUtil helperListUtil = HelperListUtil.getInstance();
        List<HelperDTO> helperList;
        helperList = helperListUtil.getListNameMap().get("LOCKED_STATUS");
        salesInclusion.setReadOnly(false);
        for (HelperDTO dto : helperList) {
            if (dto.getDescription().equalsIgnoreCase("YES")) {
                salesInclusion.setValue(dto);
                break;
            }
        }
        salesInclusion.setReadOnly(false);
    }
}
