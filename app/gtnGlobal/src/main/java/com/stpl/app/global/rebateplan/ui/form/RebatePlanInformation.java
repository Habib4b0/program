package com.stpl.app.global.rebateplan.ui.form;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.ifp.logic.IFPLogic;
import java.util.Map;

import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

import com.stpl.app.global.rebateplan.dto.RebatePlanMasterDTO;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.GeneralCommonUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import java.util.List;


/**
 * 
 * @author sibi
 *
 */
public class RebatePlanInformation extends CustomComponent {

	/**
	 * Serial Version Id
	 */
	private static final long serialVersionUID = 1L;
	/**
         * The Constant Logger
         */
	private static final Logger LOGGER = Logger.getLogger(RebatePlanInformation.class);
        /**
         * The Css Layout
         */
	@UiField("cssLayout")
	private CssLayout cssLayout;
		
	/**
         * The Text Field
         */	
	@UiField("rebatePlanId")
	private TextField rebatePlanId;
        
	/**
         * The Text Field
         */	
	@UiField("rebatePlanNo")
	private TextField rebatePlanNo;
        
	/**
         * The Text Field
         */	
	@UiField("rebatePlanName")
	private TextField rebatePlanName;
		
	/**
         * The Combo Box.
         */	
	@UiField("rebatePlanStatus")
	private ComboBox rebatePlanStatus;
	/**
         * The Combo Box.
         */	
	@UiField("rebatePlanType")
	private ComboBox rebatePlanType;
	
	/**
         * The Option Group.
         */	
	@UiField("formulaType")
	private OptionGroup formulaType;	
        /**
         * The Binder.
         */
	ErrorfulFieldGroup binder;
	/**
         * The Rebate Plan Master Dto.
         */
	final com.stpl.app.global.item.util.CommonUtils commonUtils = new com.stpl.app.global.item.util.CommonUtils();
    
        CommonUIUtils commonUiUtil = new CommonUIUtils();
        private final IFPLogic ifpLogic = new IFPLogic();
    
        private TextField rebatePlanSystemId = new TextField();
	
	RebatePlanMasterDTO rebatePlanMasterDTO;
        SessionDTO sessionDTO;
        String mode;
        
        private CommonUtil commonUtil = CommonUtil.getInstance();
        
        CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    /**
     * 
     * @param binder
     * @param rebatePlanMasterDTO
     * @param sessionDTO
     * @throws PortalException
     * @throws SystemException
     * @throws Exception 
     */
    public RebatePlanInformation(final ErrorfulFieldGroup binder, final RebatePlanMasterDTO rebatePlanMasterDTO, final SessionDTO sessionDTO) throws PortalException, SystemException {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/rebateplan/rebate-plan-information.xml"), this));
        this.binder = binder;
        this.rebatePlanMasterDTO = rebatePlanMasterDTO;
        this.sessionDTO = sessionDTO;
        this.mode = this.sessionDTO.getMode();
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute("userId"));
        Map<String, AppPermission> fieldCfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.REBATE_PLAN + ConstantsUtils.COMMA + "Rebate Plan Information", false);
        if (ConstantsUtils.EDIT.equals(this.sessionDTO.getMode())
                || ConstantsUtils.VIEW.equals(this.sessionDTO.getMode())||ConstantsUtils.COPY.equals(this.sessionDTO.getMode())) {
            configureFields();
            getBinder();
            configurePermission(fieldCfpHM);
        } else {
            getBinder();
            configureFields();
            configurePermission(fieldCfpHM);
        }
    }
    
    private void configurePermission(final Map<String, AppPermission> fieldIfpHM) {
        LOGGER.debug("Entering configurePermission");
        try {
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.REBATE_PLAN, "Rebate Plan Information");
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldIfpHM, mode.equals("Copy") ? "Edit":mode);
        } catch (Exception ex) {
            LOGGER.error(ex);

        }
        LOGGER.debug("Ending configurePermission");
    }
    
    /**
     * Gets the binder.
     *
     * @return the binder
     */
    private void getBinder() {
        binder.bindMemberFields(this);
    }
    
    
    	
    /**
     * Configure fields.
     *
     * @throws SystemException the system exception
     * @throws Exception the exception
     */
    private void configureFields() {

        rebatePlanSystemId.setImmediate(Boolean.TRUE);
        formulaType.addItem(ConstantsUtils.SIMPLE);
        formulaType.addItem("Complex");
        formulaType.setValue(ConstantsUtils.SIMPLE);
        formulaType.setDescription((String) formulaType.getValue());
        formulaType.setStyleName("horizontaladd");
        formulaType.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            /**
             * Notifies this listener that the Property's value has changed.
             */
            public void valueChange(final ValueChangeEvent event) {

                formulaType.setDescription((String) formulaType.getValue());

            }
        });

        // Rebate Plan ID Validation
        rebatePlanId.focus();
        rebatePlanId.setTabIndex(0);
        rebatePlanId.setData("maxlengthvalidation,maxlengthvalidationrebateplanid,null,null");
        rebatePlanId.setImmediate(true);
        rebatePlanId.setValidationVisible(true);
        rebatePlanId.setDescription((String) rebatePlanId.getValue());
        rebatePlanId.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             */
            @SuppressWarnings("PMD")
            public void valueChange(final ValueChangeEvent event) {

                rebatePlanId.setDescription((String) rebatePlanId.getValue());

                // To maintain ordering for Native select
                if (rebatePlanStatus.getValue() != null && rebatePlanStatus.getValue().equals("")) {
                    rebatePlanStatus.setValue(null);
                }
                if (rebatePlanType.getValue() != null && rebatePlanType.getValue().equals("")) {
                    rebatePlanType.setValue(null);
                }

            }
        });

        rebatePlanNo.setData("maxlengthvalidationfifty,maxlengthvalidationrebateplanno,null,null");
        rebatePlanNo.setImmediate(true);
        rebatePlanNo.setValidationVisible(true);
        rebatePlanNo.setDescription((String) rebatePlanNo.getValue());
        rebatePlanNo.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             */
            public void valueChange(final ValueChangeEvent event) {

                rebatePlanNo.setDescription((String) rebatePlanNo.getValue());

            }
        });

        rebatePlanName.setData("maxlengthvalidationhundred,maxlengthvalidationrebateplanname,null,null");
        rebatePlanName.setImmediate(true);
        rebatePlanName.setValidationVisible(true);

        rebatePlanName.setDescription((String) rebatePlanName.getValue());
        rebatePlanName.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             */
            public void valueChange(final ValueChangeEvent event) {

                rebatePlanName.setDescription((String) rebatePlanName.getValue());

            }
        });

        commonUtil.loadComboBox(rebatePlanStatus, GeneralCommonUtils.STATUS, false);
        commonUtil.loadComboBox(rebatePlanType, GeneralCommonUtils.REBATE_PLAN_TYPE, false);

        formulaType.addItem(ConstantsUtils.SIMPLE);
        formulaType.addItem("Complex");
        formulaType.setValue(ConstantsUtils.SIMPLE);
        formulaType.setDescription((String) formulaType.getValue());
        formulaType.setStyleName("horizontaladd");
        formulaType.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Notifies this listener that the Property's value has changed.
             */
            public void valueChange(final ValueChangeEvent event) {

                formulaType.setDescription((String) formulaType.getValue());

            }
        });

    }
    
    /**
     * Focusing Rebate Plan Id
     */
    public void focusRebatePlanId(){
        rebatePlanId.focus();
    }  
    
}
