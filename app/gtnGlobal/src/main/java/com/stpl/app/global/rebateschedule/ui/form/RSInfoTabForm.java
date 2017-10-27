package com.stpl.app.global.rebateschedule.ui.form;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.global.netsalesformula.dto.NetSalesRuleLookupDto;
import com.stpl.app.global.netsalesformula.ui.form.NetSalesRuleLookUp;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import static com.stpl.app.util.CommonUIUtils.MMDDYYYY;
import static com.stpl.app.util.CommonUIUtils.getDateTime;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.GeneralCommonUtils;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.app.util.ValidationUtils;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.RegexpValidator;
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
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 * Rebate Schedule Information Tab
 *
 * @author Sibi
 */
public class RSInfoTabForm extends CustomComponent {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(RSInfoTabForm.class);
    /**
     * The rebate schedule Id.
     */
    @UiField("rebateScheduleId")
    private TextField rebateScheduleId = new TextField();

    /**
     * The rebate schedule no.
     */
    @UiField("rebateScheduleNo")
    private TextField rebateScheduleNo;
    /**
     * The rebate schedule name.
     */
    @UiField("rebateScheduleName")
    private TextField rebateScheduleName;
    /**
     * The rebate schedule status.
     */
    @UiField("rebateScheduleStatus")
    private ComboBox rebateScheduleStatus;

    @UiField("calculationType")
    private ComboBox calculationType;

    @UiField("calculationLevel")
    private ComboBox calculationLevel;

    @UiField("rebateFrequency")
    private ComboBox rebateFrequency;

    @UiField("paymentLevel")
    private ComboBox paymentLevel;

    @UiField("programFrequency")
    private ComboBox paymentFrequency;

    @UiField("interestBearingIndicator")
    private ComboBox interestBearingIndicator;

    @UiField("interestBearingBasisInfo")
    private ComboBox interestBearingBasisInfo;

    @UiField("rebateRuleType")
    private ComboBox rebateRuleType;

    /**
     * The rebate schedule type.
     */
    @UiField("rebateScheduleType")
    private ComboBox rebateScheduleType;

    /**
     * The rebate program type.
     */
    @UiField("rebateProgramType")
    private ComboBox rebateProgramType;

    /**
     * The rebate schedule category.
     */
    @UiField("rebateScheduleCategory")
    private ComboBox rebateScheduleCategory;

    /**
     * The rebate schedule designation.
     */
    @UiField("rsDesignation")
    private ComboBox rebateScheduleDesignation;
    /**
     * The parent rebate schedule id.
     */
    @UiField("parentRsId")
    private CustomTextField parentRebateScheduleId;
    /**
     * The parent rebate schedule name.
     */
    @UiField("parentRsName")
    private CustomTextField parentRebateScheduleName;

    /**
     * The rebate schedule trans ref no.
     */
    @UiField("rebateScheduleTransRefNo")
    private CustomTextField rebateScheduleTransRefNo;
    /**
     * The rebate schedule trans ref name.
     */
    @UiField("rebateScheduleTransRefName")
    private TextField rebateScheduleTransRefName;

    /**
     * The trade class.
     */
    @UiField("tradeClass")
    private ComboBox tradeClass;

    /**
     * The udc1.
     */
    @UiField("udc1")
    private ComboBox udc1;
    /**
     * The udc1.
     */
    @UiField("udc2")
    private ComboBox udc2;
    /**
     * The udc1.
     */
    @UiField("udc3")
    private ComboBox udc3;
    /**
     * The udc4.
     */
    @UiField("udc4")
    private ComboBox udc4;
    /**
     * The udc5.
     */
    @UiField("udc5")
    private ComboBox udc5;
    /**
     * The udc6.
     */
    @UiField("udc6")
    private ComboBox udc6;

    /**
     * The calendar.
     */
    @UiField("calendar")
    private ComboBox calendar;
    /**
     * The rebate schedule alias.
     */
    @UiField("rsAlias")
    private TextField rebateScheduleAlias;

    /**
     * The rebate plan level.
     */
    @UiField("rebatePlanLevel")
    private ComboBox rebatePlanLevel;

    /**
     * The payment terms.
     */
    @UiField("paymentTerms")
    private ComboBox paymentTerms;
    /**
     * The payment method.
     */
    @UiField("paymentMethod")
    private ComboBox paymentMethod;

    /**
     * The payment grace period.
     */
    @UiField("paymentGracePeriod")
    private TextField paymentGracePeriod;

    @UiField("rebateScheduleIdLb")
    private Label rebateScheduleIdLb;
    /**
     * The rebate schedule no.
     */
    @UiField("rebateScheduleNoLb")
    private Label rebateScheduleNoLb;
    /**
     * The rebate schedule name.
     */
    @UiField("rebateScheduleNameLb")
    private Label rebateScheduleNameLb;
    /**
     * The rebate schedule status.
     */
    @UiField("rebateScheduleStatusLb")
    private Label rebateScheduleStatusLb;

    /**
     * The rebate schedule type.
     */
    @UiField("rebateScheduleTypeLb")
    private Label rebateScheduleTypeLb;

    /**
     * The rebate program type.
     */
    @UiField("rebateProgramTypeLb")
    private Label rebateProgramTypeLb;

    /**
     * The rebate schedule category.
     */
    @UiField("rebateScheduleCategoryLb")
    private Label rebateScheduleCategoryLb;

    /**
     * The rebate schedule designation.
     */
    @UiField("rebateScheduleDesignationLb")
    private Label rebateScheduleDesignationLb;
    /**
     * The parent rebate schedule id.
     */
    @UiField("parentRebateScheduleIdLb")
    private Label parentRebateScheduleIdLb;
    /**
     * The parent rebate schedule name.
     */
    @UiField("parentRebateScheduleNameLb")
    private Label parentRebateScheduleNameLb;

    /**
     * The rebate schedule trans ref no.
     */
    @UiField("rebateScheduleTransRefNoLb")
    private Label rebateScheduleTransRefNoLb;
    /**
     * The rebate schedule trans ref name.
     */
    @UiField("rebateScheduleTransRefNameLb")
    private Label rebateScheduleTransRefNameLb;

    /**
     * The trade class.
     */
    @UiField("tradeClassLb")
    private Label tradeClassLb;

    /**
     * The udc1.
     */
    @UiField("udc1Lb")
    private Label udc1Lb;
    /**
     * The udc4.
     */
    @UiField("udc4Lb")
    private Label udc4Lb;
    /**
     * The udc5.
     */
    @UiField("udc5Lb")
    private Label udc5Lb;
    /**
     * The udc6.
     */
    @UiField("udc6Lb")
    private Label udc6Lb;

    /**
     * The calendar.
     */
    @UiField("calendarLb")
    private Label calendarLb;
    /**
     * The rebate schedule alias.
     */
    @UiField("rebateScheduleAliasLb")
    private Label rebateScheduleAliasLb;

    /**
     * The rebate plan level.
     */
    @UiField("rebatePlanLevelLb")
    private Label rebatePlanLevelLb;

    /**
     * The payment terms.
     */
    @UiField("paymentTermsLb")
    private Label paymentTermsLb;
    /**
     * The payment method.
     */
    @UiField("paymentMethodLb")
    private Label paymentMethodLb;

    /**
     * The payment grace period.
     */
    @UiField("paymentGracePeriodLb")
    private Label paymentGracePeriodLb;

    @UiField("cssLayout")
    CssLayout cssLayout;

    @UiField("cssLayout2")
    CssLayout cssLayout2;
    
    @UiField("deductionInclusionLb")
    private Label deductionInclusionLb;
    
    @UiField("deductionInclusion")
    private ComboBox deductionInclusion;
    
    @UiField("evaluationRuleLevelLb")
    private Label evaluationRuleLevelLb;
    
    @UiField("evaluationRuleTypeLb")
    private Label evaluationRuleTypeLb;
    
    @UiField("evaluationRuleAssociationLb")
    private Label evaluationRuleAssociationLb;
    
    @UiField("calculationRuleLevelLb")
    private Label calculationRuleLevelLb;
    
    @UiField("calculationRuleLb")
    private Label calculationRuleLb;
    
    @UiField("evaluationRuleLevel")
    private ComboBox evaluationRuleLevel;
    
    @UiField("evaluationRuleType")
    private ComboBox evaluationRuleType;
    
    @UiField("evaluationRuleAssociation")
    private CustomTextField evaluationRuleAssociation;
    
    @UiField("calculationRuleLevel")
    private ComboBox calculationRuleLevel;
    
    
    @UiField("calculationRule")
    private CustomTextField calculationRule;
    
    @UiField("startDateLabel")
    private Label startDateLabel;
	/**
         * The Layout
         */
	
    @UiField("endDateLabel")
    private Label endDateLabel;
    
    @UiField("startDate")
    private PopupDateField startDate;
	/**
         * The Popup Date Field.
         */	
    @UiField("endDate")
    private PopupDateField endDate;

    private CommonUtil commonUtils = CommonUtil.getInstance();
    final RebateScheduleAddForm tabForm;
    private final ErrorfulFieldGroup binder;
    ParentLookUp lookUp = null;
    CommonUIUtils commonUiUtil = new CommonUIUtils();
    private String userId;
    String mode;
    private final IFPLogic ifpLogic = new IFPLogic();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    HelperListUtil helperList=HelperListUtil.getInstance();
    private TextField calculationSystemId=new TextField();
   private TextField evaluationSystemId=new TextField();
        

    public RSInfoTabForm(final RebateScheduleAddForm tabForm, final ErrorfulFieldGroup binder, final String mode) {

        this.tabForm = tabForm;
        this.binder = binder;
        this.mode = mode;

        try {
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/rebateschedule/rsinfotabform.xml"), this));
            getBinder();
            final StplSecurity stplSecurity = new StplSecurity();
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldRsHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.REBATE_SCHEDULE+","+"Rebate Schedule Information",false);
            addResposivenessToInformationPanel(fieldRsHM);
            configureFields();
            validateFields();
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private ErrorfulFieldGroup getBinder() {

        binder.bindMemberFields(this);
        binder.setBuffered(true);
        return binder;
    }

    private void addResposivenessToInformationPanel(final Map<String, AppPermission> fieldRsHM) {

        LOGGER.debug("Entering configurePermission");
        try {
            String mode = (String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.GLOBAL_FILES_MODE);

            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.REBATE_SCHEDULE, "Rebate Schedule Information");
           commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldRsHM, ConstantsUtils.COPY.equals(mode)? "Edit" : mode);
           commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout2, fieldRsHM, ConstantsUtils.COPY.equals(mode)? "Edit" : mode);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending configurePermission");

    }

    private void configureFields() {

        try {
            
            evaluationRuleAssociation.addStyleName(ConstantsUtils.SEARCH_SYLENAME);
            calculationRule.addStyleName(ConstantsUtils.SEARCH_SYLENAME);
            
        startDate.setDescription(ConstantsUtils.DATE_DES);
            
        startDate.setImmediate(true);
        startDate.setValidationVisible(true);
        startDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        startDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             */
            public void valueChange(final ValueChangeEvent event) {

                startDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(endDate.getValue()));

            }
        });  
            
            
        endDate.setDescription(ConstantsUtils.DATE_DES);
            
        endDate.setImmediate(true);
        endDate.setValidationVisible(true);
        endDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        endDate.addValidator(new EndDateValidators("To Date should be after From Date"));
        endDate.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             */
            public void valueChange(final ValueChangeEvent event) {

                endDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(endDate.getValue()));

            }
        });
            
            rebateScheduleId.setImmediate(true);
            rebateScheduleId.setValidationVisible(true);
            rebateScheduleId.setData("maxlengthvalidationfifty,maxlengthvalidationrebatescheduleid,null,null");
            rebateScheduleId.setDescription(rebateScheduleId.getValue());
            /**
             * Value Change Listener for Rebate Schedule ID
             */
            rebateScheduleId.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used for Rebate Schedule Information tab
                 */
                @SuppressWarnings("PMD")
                public void valueChange(final ValueChangeEvent event) {
                    rebateScheduleId.setDescription(rebateScheduleId.getValue());
                }
            });

            rebateScheduleNo.setImmediate(true);
            rebateScheduleNo.setValidationVisible(true);
            rebateScheduleNo.setData("maxlengthvalidationfifty,maxlengthvalidationrebatescheduleno,null,null");
            rebateScheduleNo.setDescription(rebateScheduleNo.getValue());
            rebateScheduleNo.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used for Rebate Schedule Information
                 */
                public void valueChange(final ValueChangeEvent event) {
                    rebateScheduleNo.setDescription(rebateScheduleNo.getValue());
                }
            });
            rebateScheduleName.setImmediate(true);
            rebateScheduleName.setValidationVisible(true);
            rebateScheduleName.setData("maxlengthvalidationhundred,maxlengthvalidationrebateschedulename,null,null");
            rebateScheduleName.setDescription(rebateScheduleName.getValue());
            rebateScheduleName.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used for Rebate Schedule Name Value Change Listener
                 */
                public void valueChange(final ValueChangeEvent event) {
                    rebateScheduleName.setDescription(rebateScheduleName.getValue());
                }
            });
            LOGGER.debug("In configureFields lodding-RebateScheduleStatus Dblb ListType=" + GeneralCommonUtils.RS_STATUS);

            rebateScheduleName.setValidationVisible(true);
            rebateScheduleAlias.setData("maxlengthvalidationhundred,maxlengthvalidationrebateschedulename,splcharvalidation,splcharacters");

            commonUtils.loadComboBox(rebateScheduleStatus, GeneralCommonUtils.STATUS, false);
            commonUtils.loadComboBox(rebateScheduleType, GeneralCommonUtils.REBATE_SCHEDULE_TYPE, false);
            commonUtils.loadComboBox(rebateProgramType, GeneralCommonUtils.REBATE_PROGRAM_TYPE, false);
            commonUtils.loadComboBox(rebateScheduleCategory, GeneralCommonUtils.REBATE_SCHEDULE_CATEGORY, false);
            commonUtils.loadComboBox(tradeClass, GeneralCommonUtils.TRADE_CLASS, false);
            commonUtils.loadComboBox(rebateScheduleDesignation, GeneralCommonUtils.REBATE_SCHEDULE_DESIGNATION, false);
            commonUtils.loadComboBox(udc1, GeneralCommonUtils.UDC1, false);
            commonUtils.loadComboBox(udc2, GeneralCommonUtils.UDC2, false);
            commonUtils.loadComboBox(udc3, GeneralCommonUtils.UDC3, false);
            commonUtils.loadComboBox(udc4, GeneralCommonUtils.UDC4, false);
            commonUtils.loadComboBox(udc5, GeneralCommonUtils.UDC5, false);
            commonUtils.loadComboBox(udc6, GeneralCommonUtils.UDC6, false);

            commonUtils.loadComboBox(calendar, GeneralCommonUtils.CALENDAR, false);
            commonUtils.loadComboBox(rebateFrequency, "REBATE_FREQUENCY", false);
            commonUtils.loadComboBox(paymentLevel, "PAYMENT_LEVEL", false);
            commonUtils.loadComboBox(paymentFrequency, "PAYMENT_FREQUENCY", false);
            commonUtils.loadComboBox(calculationType, GeneralCommonUtils.CALCULATION_TYPE, false);
            commonUtils.loadComboBox(interestBearingIndicator, "INTEREST_BEARING_INDICATOR", false);
            commonUtils.loadComboBox(interestBearingBasisInfo, "INTEREST_BEARING_BASIS", false);
            commonUtils.loadComboBox(paymentTerms, GeneralCommonUtils.PAYMENT_TERMS, false);
            commonUtils.loadComboBox(calculationLevel, GeneralCommonUtils.CALCULATION_LEVEL, false);
            commonUtils.loadComboBox(rebateRuleType, "REBATE_RULE_TYPE", false);
            commonUtils.loadComboBox(paymentMethod, GeneralCommonUtils.PAYMENT_METHOD, false);
            commonUtils.loadComboBox(deductionInclusion, "LOCKED_STATUS", false);
           

            commonUtils.loadComboBox(evaluationRuleType, "EVALUATION_RULE_TYPE",false);
            commonUtils.loadComboBox(calculationRuleLevel, "RULE_LEVEL",false);
            commonUtils.loadComboBox(evaluationRuleLevel, "RULE_LEVEL",false);
            
            rebateScheduleDesignation.setDescription(rebateScheduleDesignation.getItemCaption(rebateScheduleDesignation.getValue()));
            rebateScheduleDesignation.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used for lodding-RebateScheduleDesignation Dblb
                 * ListType
                 */
                public void valueChange(final ValueChangeEvent event) {
                    String caption = rebateScheduleDesignation.getItemCaption(rebateScheduleDesignation.getValue());
                    rebateScheduleDesignation.setDescription(caption);
                    if ("child".equalsIgnoreCase(caption)) {
                        parentRebateScheduleId.setEnabled(true);
                        parentRebateScheduleId.setReadOnly(true);
                    } else {
                        parentRebateScheduleId.setEnabled(true);
                        parentRebateScheduleId.setReadOnly(false);
                        parentRebateScheduleId.setValue(StringUtils.EMPTY);
                        parentRebateScheduleId.setReadOnly(true);
                        parentRebateScheduleId.setEnabled(false);
                        parentRebateScheduleName.setReadOnly(false);
                        parentRebateScheduleName.setValue(StringUtils.EMPTY);
                        parentRebateScheduleName.setReadOnly(true);

                    }
                }
            });

            parentRebateScheduleId.addStyleName("searchicon");
            parentRebateScheduleId.setEnabled(false);
            parentRebateScheduleId.setImmediate(true);
            parentRebateScheduleId.setDescription(parentRebateScheduleId.getValue());
            parentRebateScheduleId.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used for ParentRebateScheduleId Listener
                 * parentRebateScheduleId
                 */
                public void valueChange(final ValueChangeEvent event) {
                    parentRebateScheduleId.setDescription(parentRebateScheduleId.getValue());
                }
            });

            parentRebateScheduleName.setImmediate(true);
            parentRebateScheduleName.setReadOnly(true);
            parentRebateScheduleName.setValidationVisible(true);
            parentRebateScheduleName.addValidator(new RegexpValidator(ValidationUtils.SPECIAL_CHAR, ValidationUtils.SPECIAL_CHAR_MSG));
            parentRebateScheduleName.addValidator(new StringLengthValidator("Parent Rebate Schedule Name should be less than 100 Characters", 0, NumericConstants.HUNDRED, true));
            parentRebateScheduleId.addClickListener(new CustomTextField.ClickListener() {
                /**
                 * Method used for parent Rebate Schedule Id
                 */
                public void click(final CustomTextField.ClickEvent event) {
                    if (lookUp == null) {
                        LOGGER.debug("Entering parent Rebate Schedule look up");
                        LOGGER.debug("In configureFields ParentRebateScheduleId Listener parentRebateScheduleId=" + parentRebateScheduleId.getValue() + " parentRebateScheduleName="
                                + parentRebateScheduleName.getValue());
                        lookUp = new ParentLookUp();
                        lookUp.setCaption("Parent Rebate Schedule Lookup");
                        lookUp.refeshLookup();
                        UI.getCurrent().addWindow(lookUp);
                        
                        lookUp.addCloseListener(new Window.CloseListener() {
                        /**
                         * Adding close listener to lookup window.
                         */
                        @SuppressWarnings("PMD")
                        public void windowClose(final Window.CloseEvent event) {
                            if (lookUp.isItemCliked()) {
                                parentRebateScheduleId.setReadOnly(false);
                                parentRebateScheduleId.setValue(lookUp.getRebateScheduleTransRefNo());
                                parentRebateScheduleName.setReadOnly(false);
                                parentRebateScheduleName.setValue(lookUp.getRebateScheduleTransRefName());
                                parentRebateScheduleName.setEnabled(false);
                                parentRebateScheduleId.setReadOnly(true);
                            }
                               lookUp = null;
                            LOGGER.debug("Ending parent Rebate Schedule look up");
                        }
                    });
                    }
                }
            });

            rebateScheduleTransRefNo.addStyleName(ConstantsUtils.SEARCH_SYLENAME);
            rebateScheduleTransRefNo.setImmediate(true);
            rebateScheduleTransRefNo.setReadOnly(true);
            rebateScheduleTransRefNo.setValidationVisible(true);
            rebateScheduleTransRefNo.addValidator(new RegexpValidator(ValidationUtils.SPECIAL_CHAR, ValidationUtils.SPECIAL_CHAR_MSG));
            rebateScheduleTransRefNo.addValidator(new StringLengthValidator("Rebate Schedule Reference No should be less than 100 Characters", 0, NumericConstants.HUNDRED, true));
            rebateScheduleTransRefNo.setDescription(rebateScheduleTransRefNo.getValue());
            rebateScheduleTransRefNo.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used to rebate Schedule Reference No for Value change
                 * listener
                 */
                public void valueChange(final ValueChangeEvent event) {
                    rebateScheduleTransRefNo.setDescription(rebateScheduleTransRefNo.getValue());
                }
            });

            rebateScheduleTransRefName.setImmediate(true);
            rebateScheduleTransRefName.setReadOnly(true);
            rebateScheduleTransRefName.setValidationVisible(true);
            rebateScheduleTransRefName.addValidator(new RegexpValidator(ValidationUtils.SPECIAL_CHAR, ValidationUtils.SPECIAL_CHAR_MSG));
            rebateScheduleTransRefName.addValidator(new StringLengthValidator("Rebate Schedule Reference Name should be less than 100 Characters", 0, NumericConstants.HUNDRED, true));

            rebateScheduleTransRefNo.addClickListener(new CustomTextField.ClickListener() {
                /**
                 * Adding click listener to rebateScheduleTransRefNo text box.
                 *
                 * @param event
                 */
                public void click(final CustomTextField.ClickEvent event) {
                    if (lookUp == null) {

                        lookUp = new ParentLookUp();
                        lookUp.setCaption("RS Transaction Ref ID lookup");
                        lookUp.refeshLookup();
                        UI.getCurrent().addWindow(lookUp);
                        lookUp.addCloseListener(new Window.CloseListener() {
                        /**
                         * window
                         */
                        public void windowClose(final Window.CloseEvent e) {
                            if (lookUp.isItemCliked()) {
                                rebateScheduleAlias.focus();
                                rebateScheduleTransRefName.setReadOnly(false);
                                rebateScheduleTransRefNo.setReadOnly(false);
                                rebateScheduleTransRefNo.setValue(lookUp.getRebateScheduleTransRefID());
                                rebateScheduleTransRefNo.setData(lookUp.getRebateScheduleTransRefNo());
                                rebateScheduleTransRefName.setValue(lookUp.getRebateScheduleTransRefName());
                                rebateScheduleTransRefName.setReadOnly(true);
                                rebateScheduleTransRefNo.setReadOnly(true);
                                
                            }
                            lookUp = null;
                            LOGGER.debug("Ending RS Transaction Ref ID lookup");
                        }
                    });
                    }
                    
                }
            });

            LOGGER.debug("In configureFields lodding-RebatePlanLevel Dblb ListType=" + GeneralCommonUtils.REBATE_PLAN_LEVEL);

            paymentGracePeriod.setImmediate(true);
            paymentGracePeriod.addValidator(new RegexpValidator(ValidationUtils.NUM_VALIDATION, ValidationUtils.NUM_VALID_MSG));
            paymentGracePeriod.setDescription(paymentGracePeriod.getValue());
            paymentGracePeriod.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Method used for load paymentGP value
                 */
                public void valueChange(final ValueChangeEvent event) {
                    paymentGracePeriod.setDescription(paymentGracePeriod.getValue());
                }
            });

            parentRebateScheduleId.setEnabled(!String.valueOf(mode).equals(ConstantsUtils.VIEW));
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(RSInfoTabForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        calculationRule.addClickListener(new CustomTextField.ClickListener() {
            @Override
            public void click(CustomTextField.ClickEvent event) {
                try {
                        HelperDTO  tempDto=new HelperDTO();
                           for(HelperDTO helperDto: helperList.getListNameMap().get("RULE_TYPE")){
                               if(helperDto.getDescription().equalsIgnoreCase("Calculation")){
                                  tempDto.setId(helperDto.getId());
                               tempDto.setDescription(helperDto.getDescription());
                               break;
                               }
                            }
                    
                    
                    final NetSalesRuleLookUp netSalesLookup = new NetSalesRuleLookUp(calculationRule, tempDto,"Calculation Rule Lookup","Rebate Schedule");
                    UI.getCurrent().addWindow(netSalesLookup);
                    netSalesLookup.addCloseListener(new Window.CloseListener() {
                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            if (netSalesLookup.isSelected()) {
                                NetSalesRuleLookupDto searchDTO = netSalesLookup.getSelectedItem();
                                calculationRule.setValue(searchDTO.getRuleName());
                               calculationSystemId.setValue(searchDTO.getRuleSystemId());
                            }
                        }
                    });
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });
        
          evaluationRuleAssociation.addClickListener(new CustomTextField.ClickListener() {
            @Override
            public void click(CustomTextField.ClickEvent event) {
                try {
                        HelperDTO  tempDto=new HelperDTO();
                           for(HelperDTO helperDto: helperList.getListNameMap().get("RULE_TYPE")){
                               if(helperDto.getDescription().equalsIgnoreCase("Evaluation")){
                                  tempDto.setId(helperDto.getId());
                               tempDto.setDescription(helperDto.getDescription());
                               break;
                               }
                            }
                    
                    
                    final NetSalesRuleLookUp netSalesLookup = new NetSalesRuleLookUp(evaluationRuleAssociation, tempDto,"Evaluation Rule Lookup","Rebate Schedule");
                    UI.getCurrent().addWindow(netSalesLookup);
                    netSalesLookup.addCloseListener(new Window.CloseListener() {
                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            if (netSalesLookup.isSelected()) {
                                NetSalesRuleLookupDto searchDTO = netSalesLookup.getSelectedItem();
                                evaluationRuleAssociation.setValue(searchDTO.getRuleName());
                               evaluationSystemId.setValue(searchDTO.getRuleSystemId());
                            }
                        }
                    });
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });
        
        
        
        
    }

    public ComboBox getDeductionInclusion() {
        return deductionInclusion;
    }

    public void setDeductionInclusion(ComboBox deductionInclusion) {
        this.deductionInclusion = deductionInclusion;
    }

    
    @SuppressWarnings("rawtypes")
     class EndDateValidators extends AbstractValidator {

        /**
         * The Constant serialVersionUID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * The Constructor.
         */
        public EndDateValidators() {
            super(StringUtils.EMPTY);
        }

        /**
         * The Constructor.
         *
         * @param errorMessage the error message
         */
        public EndDateValidators(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * Validates the value.
         *
         * @param value the value
         */
        @Override
        public void validate(final Object value) {

            if (startDate.getValue() != null && endDate.getValue() != null) {
                if (startDate.getValue().after(endDate.getValue())) {
                    throw new Validator.InvalidValueException("endDate should be greater than startDate");
                } else if (startDate.getValue().equals(endDate.getValue())) {
                    throw new Validator.InvalidValueException("startDate and endDate should not be  equal");
                }
            }

        }

        /**
         * Validates the value and returns result as boolean.
         *
         * @param value the value
         * @return true, if is valid value
         */
        @Override
        protected boolean isValidValue(final Object value) {

            if (startDate.getValue() != null && endDate.getValue() != null) {
                return startDate.getValue().compareTo(endDate.getValue()) <= 0;
            }

            return true;
        }

        /**
         * Default method.
         *
         * @return the type
         */
        @Override
        public Class getType() {
            return null;
        }
    }
    
    
    
    public void setDefaultFocus() {
        rebateScheduleId.focus();
        parentRebateScheduleName.setReadOnly(true);
        rebateScheduleTransRefNo.setReadOnly(true);
        rebateScheduleTransRefName.setReadOnly(true);
        String caption = rebateScheduleDesignation.getItemCaption(rebateScheduleDesignation.getValue());
        rebateScheduleDesignation.setDescription(caption);
        if ("child".equalsIgnoreCase(caption)) {
            parentRebateScheduleId.setEnabled(true);
            parentRebateScheduleId.setReadOnly(true);
        } else {
            parentRebateScheduleId.setEnabled(false);
            parentRebateScheduleId.setReadOnly(true);
            parentRebateScheduleName.setEnabled(false);
        }
    }

    public String getRebatePlanLevelCaption() {
        // TODO Auto-generated method stub
        return rebatePlanLevel.getItemCaption(rebatePlanLevel.getValue());

    }

    public void setRebatePlanLevelValue(int val) {
        // TODO Auto-generated method stub
        rebatePlanLevel.setValue(val);

    }

    public void focusRebateScheduleId() {
        rebateScheduleId.focus();
    }

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
        
        public static final String convert2DigitTo4DigitYear(final Date aDate) {
        if(aDate != null){
            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);
            calendar.setTime(aDate);
            int year = calendar.get(Calendar.YEAR);
            int length = 0;
            if(year != 0){
                length = (int) Math.log10(year) + 1;            
            }
            if (length < NumericConstants.FOUR) {
                    int century = currentYear / NumericConstants.HUNDRED;
                    aDate.setYear(((century * NumericConstants.HUNDRED) + year) - NumericConstants.ONE_NINE_ZERO_ZERO);
            }
        }
        return getDateTime(MMDDYYYY, aDate);
    }
    
}
