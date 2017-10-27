/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.ifp.ui.form;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.ifp.dto.ItemFamilyplanMasterDTO;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.global.ifp.util.UIUtils;
import com.stpl.app.global.item.util.CommonUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanItem;
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
public class IFPInformation extends CustomComponent {

    private static final Logger LOGGER = Logger.getLogger(IFPInformation.class);

    /**
     * CSS layout for fields
     */
    @UiField("cssLayout")
    CssLayout cssLayout;

    /**
     * ItemFamilyplanId Label
     */
    @UiField("itemFamilyplanIdLB")
    private Label itemFamilyplanIdLB;

    @UiField("itemFamilyplanId")
    private TextField itemFamilyplanId;

    @UiField("itemFamilyplanNoLB")
    private Label itemFamilyplanNoLB;

    @UiField("itemFamilyplanNo")
    private TextField itemFamilyplanNo;

    @UiField("itemFamilyplanNameLB")
    private Label itemFamilyplanNameLB;

    @UiField("itemFamilyplanName")
    private TextField itemFamilyplanName;

    @UiField("itemFamilyplanStatusLB")
    private Label itemFamilyplanStatusLB;

    @UiField("itemFamilyplanStatus")
    private ComboBox itemFamilyplanStatus;

    @UiField("itemFamilyplanStartDateLB")
    private Label itemFamilyplanStartDateLB;

    @UiField("itemFamilyplanStartDate")
    private PopupDateField itemFamilyplanStartDate;

    @UiField("itemFamilyplanEndDateLB")
    private Label itemFamilyplanEndDateLB;

    @UiField("ifpEndDate")
    private PopupDateField itemFamilyplanEndDate;

    @UiField("itemFamilyplanDesignationLB")
    private Label itemFamilyplanDesignationLB;

    @UiField("ifpDesignation")
    private ComboBox itemFamilyplanDesignation;

    @UiField("parentItemFamilyplanIdLB")
    private Label parentItemFamilyplanIdLB;

    @UiField("parentIfpId")
    private CustomTextField parentItemFamilyplanId;

    @UiField("parentItemFamilyplanNameLB")
    private Label parentItemFamilyplanNameLB;

    @UiField("parentIfpName")
    private CustomTextField parentItemFamilyplanName;

    @UiField("itemFamilyplanTypeLB")
    private Label itemFamilyplanTypeLB;

    @UiField("ifpType")
    private ComboBox itemFamilyplanType;

    @UiField("createdByLB")
    private Label createdByLB;

    @UiField("createdBy")
    private TextField createdBy;
    @UiField("createdDateLB")
    private Label createdDateLB;

    @UiField("createdDate")
    private PopupDateField createdDate;

    @UiField("itemFamilyplanCategoryLB")
    private Label itemFamilyplanCategoryLB;

    @UiField("ifpCategory")
    private ComboBox itemFamilyplanCategory;

    @UiField("modifiedByLB")
    private Label modifiedByLB;

    @UiField("modifiedBy")
    private TextField modifiedBy;

    @UiField("modifiedDateLB")
    private Label modifiedDateLB;

    @UiField("modifiedDate")
    private PopupDateField modifiedDate;

    /**
     * The item Family plan SystemId.
     */
    private TextField itemFamilyplanSystemId = new TextField();

    /**
     * the used id
     */
    private String userId;

    final CommonUtils commonUtils = new CommonUtils();

    CommonUIUtils commonUiUtil = new CommonUIUtils();
    CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();

    private CommonUtil commonUtil = CommonUtil.getInstance();
    ParentIFPIdLookup lookUp =null;
    private ItemFamilyplanMasterDTO ifpDTO = new ItemFamilyplanMasterDTO();
    ErrorfulFieldGroup binder = new ErrorfulFieldGroup(new BeanItem<>(ifpDTO));
    private String mode;

    /**
     * The ifp logic.
     */
    private final IFPLogic ifpLogic = new IFPLogic();

    public IFPInformation(final ItemFamilyplanMasterDTO ifpMaster, final String mode) {
        this.ifpDTO = ifpMaster;
        this.mode = mode;
        try {
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarativeui/item_family_plan/ifpinformation.xml"), this));

            if ("Add".equals(mode)) {
                getBinder();
                
            }
            final StplSecurity stplSecurity = new StplSecurity();
            userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, UISecurityUtil.ITEM_FAMILY_PLAN+","+"IFP Information",false);
            configurePermission(fieldIfpHM);
            configureFields();
            if (ConstantsUtils.EDIT.equals(mode) || ConstantsUtils.VIEW_BTN.equals(mode) || ConstantsUtils.COPY.equals(mode)) {
                getBinder();
                if(ConstantsUtils.COPY.equals(mode))
                {
                    System.out.println("ifpDTO"+ifpDTO.getIfpId());
                     ifpDTO.setIfpId(StringUtils.EMPTY);
                    ifpDTO.setIfpName(StringUtils.EMPTY);
                    ifpDTO.setIfpNo(StringUtils.EMPTY);
                }
            
            }
            validateFields();
        } catch (Exception e) {
            LOGGER.error(e);
            LOGGER.error(e);
        }
    }

    /**
     * Gets the first tab.
     *
     * @param fieldIfpHM the field ifp hm
     * @return the first tab
     * @throws Exception the exception
     */
    private void configurePermission(final Map<String, AppPermission> fieldIfpHM) {
        LOGGER.debug("Entering configurePermission");
        try {
            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.ITEM_FAMILY_PLAN, "IFP Information");
            commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldIfpHM, mode);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending configurePermission");
    }

    private void configureFields() {
        itemFamilyplanSystemId.setImmediate(Boolean.TRUE);
        itemFamilyplanEndDate.setDescription(ConstantsUtils.DATE_DES);
        itemFamilyplanStartDate.setDescription(ConstantsUtils.DATE_DES);
        itemFamilyplanId.setImmediate(true);
        itemFamilyplanId.focus();
        itemFamilyplanId.setData("maxlengthvalidationfifty,maxlengthvalidationitemfamilyplanid,null,null");
        itemFamilyplanId.setValidationVisible(true);
        itemFamilyplanId.setDescription(itemFamilyplanId.getValue());
        itemFamilyplanId.addValueChangeListener(new Property.ValueChangeListener() {

            /**
             * This method listens to data source value changes and passes the
             * changes forwards.
             */
            @SuppressWarnings("PMD")
            public void valueChange(final Property.ValueChangeEvent event) {
                itemFamilyplanId.setDescription(itemFamilyplanId.getValue());
                if (itemFamilyplanStatus.getValue() != null && itemFamilyplanStatus.getValue() == "") {
                    itemFamilyplanStatus.select(null);
                }
            }
        });

        itemFamilyplanNo.setData("maxlengthvalidationfifty,maxlengthvalidationitemfamilyplanno,null,null");
        itemFamilyplanNo.setImmediate(true);
        itemFamilyplanNo.setValidationVisible(true);
        itemFamilyplanNo.setDescription(itemFamilyplanNo.getValue());
        itemFamilyplanNo.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * This method listens to data source value changes and passes the
             * changes forwards.
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                itemFamilyplanNo.setDescription(itemFamilyplanNo.getValue());
            }
        });
        itemFamilyplanName.setData("maxlengthvalidationhundred,maxlengthvalidationitemfamilyplanname,null,null");
        itemFamilyplanName.setImmediate(true);
        itemFamilyplanName.setValidationVisible(true);
        itemFamilyplanName.setDescription(itemFamilyplanName.getValue());
        itemFamilyplanName.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * This method listens to data source value changes and passes the
             * changes forwards.
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                itemFamilyplanName.setDescription(itemFamilyplanName.getValue());
            }
        });
        commonUtil.loadComboBox(itemFamilyplanStatus, "STATUS", false);

        itemFamilyplanStartDate.setImmediate(true);
        itemFamilyplanStartDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        itemFamilyplanStartDate.addValidator(new DateValidator("IFP start date should be before IFP end date"));
        itemFamilyplanStartDate.setId("IFPStartDate");
        itemFamilyplanStartDate.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for Value change event.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    try {
                        itemFamilyplanStartDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(itemFamilyplanStartDate.getValue()));
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

        itemFamilyplanEndDate.setImmediate(true);
        itemFamilyplanEndDate.setValidationVisible(true);
        itemFamilyplanEndDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        itemFamilyplanEndDate.setId("IFPEndDate");
        itemFamilyplanEndDate.addValidator(new DateValidator("IFP start date should be before IFP end date"));
        itemFamilyplanEndDate.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * Logic for Value change event.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    try {
                        itemFamilyplanEndDate.setDescription(CommonUIUtils.convert2DigitTo4DigitYear(itemFamilyplanEndDate.getValue()));
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

        if ("Child".equalsIgnoreCase(itemFamilyplanDesignation.getItemCaption(itemFamilyplanDesignation.getValue()))) {
                    parentItemFamilyplanId.setEnabled(true);
                    parentItemFamilyplanName.setEnabled(true);
                    parentItemFamilyplanId.setReadOnly(true);
                    parentItemFamilyplanName.setReadOnly(true);
                } else {
                    parentItemFamilyplanId.setReadOnly(true);
                    parentItemFamilyplanId.setValue(StringUtils.EMPTY);
                    parentItemFamilyplanId.setEnabled(false);

                    parentItemFamilyplanName.setReadOnly(true);
                    parentItemFamilyplanName.setValue(StringUtils.EMPTY);
                    parentItemFamilyplanName.setEnabled(false);

                }
        
        
        commonUtil.loadComboBox(itemFamilyplanDesignation, UIUtils.IFP_DESIGNATION, false);
        itemFamilyplanDesignation.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * This method listens to data source value changes and passes the
             * changes forwards.
             */
            public void valueChange(final Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && StringUtils.EMPTY.equals(event.getProperty().getValue().toString())) {
                    itemFamilyplanDesignation.setValue(ConstantsUtils.SELECT_ONE);
                }
                String caption = itemFamilyplanDesignation.getItemCaption(itemFamilyplanDesignation.getValue());
                itemFamilyplanDesignation.setDescription(caption);
                if ("Child".equalsIgnoreCase(caption)) {
                    parentItemFamilyplanId.setEnabled(true);
                    parentItemFamilyplanName.setEnabled(true);
                    parentItemFamilyplanId.setReadOnly(true);
                    parentItemFamilyplanName.setReadOnly(true);
                } else {
                    parentItemFamilyplanId.setReadOnly(false);
                    parentItemFamilyplanId.setEnabled(true);
                    parentItemFamilyplanId.setValue(StringUtils.EMPTY);
                    parentItemFamilyplanId.setReadOnly(true);
                    parentItemFamilyplanId.setEnabled(false);
                    parentItemFamilyplanName.setReadOnly(false);
                    parentItemFamilyplanName.setEnabled(true);
                    parentItemFamilyplanName.setValue(StringUtils.EMPTY);
                    parentItemFamilyplanName.setReadOnly(true);
                    parentItemFamilyplanName.setEnabled(false);

                }
            }
        });

        parentItemFamilyplanName.setStyleName(ConstantsUtils.SEARCH_SYLENAME);
        parentItemFamilyplanName.setImmediate(true);
        parentItemFamilyplanName.setReadOnly(true);
        parentItemFamilyplanId.setEnabled(false);

        parentItemFamilyplanId.setStyleName(ConstantsUtils.SEARCH_SYLENAME);
        parentItemFamilyplanId.setImmediate(true);
        parentItemFamilyplanId.setReadOnly(true);
        parentItemFamilyplanName.setEnabled(false);
        parentItemFamilyplanId.addClickListener(new CustomTextField.ClickListener() {
            /**
             * Adding click listener to parentItemFamilyplanId text box.
             *
             * @param event
             */
            public void click(final CustomTextField.ClickEvent event) {
                try {
                    if(lookUp==null){
                    LOGGER.debug("Entering parent Item Family plan Id look up");
                    lookUp = new ParentIFPIdLookup(parentItemFamilyplanId, parentItemFamilyplanName);
                    UI.getCurrent().addWindow(lookUp);
                    }
                    lookUp.addCloseListener(new Window.CloseListener() {

                        /**
                         * Called when the user closes a window.
                         *
                         * @param e Event containing
                         */
                        @SuppressWarnings("PMD")
                        public void windowClose(final Window.CloseEvent e) {
                            parentItemFamilyplanId.setReadOnly(true);
                            parentItemFamilyplanName.setReadOnly(true);
                            lookUp=null;
                            LOGGER.debug("Ending parent Item Family plan Id look up");
                        }
                    });
                }
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                // Do Nothing
                 catch (Exception exception) {
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
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                // Do Nothing
                

            }
        });
        parentItemFamilyplanName.addClickListener(new CustomTextField.ClickListener() {
            /**
             * Adding click listener to parentItemFamilyplanId text box.
             *
             * @param event
             */
            public void click(final CustomTextField.ClickEvent event) {
                try {
                    if(lookUp==null){
                    LOGGER.debug("Entering parent Item Family plan Id look up");
                    lookUp = new ParentIFPIdLookup(parentItemFamilyplanId, parentItemFamilyplanName);
                    UI.getCurrent().addWindow(lookUp);
                    }    
                    lookUp.addCloseListener(new Window.CloseListener() {

                        /**
                         * Called when the user closes a window.
                         *
                         * @param e Event containing
                         */
                        @SuppressWarnings("PMD")
                        public void windowClose(final Window.CloseEvent e) {
                            parentItemFamilyplanId.setReadOnly(true);
                            parentItemFamilyplanName.setReadOnly(true);
                            lookUp=null;
                            LOGGER.debug("Ending parent Item Family plan Id look up");
                        }
                    });

                }
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                // Do Nothing
                 catch (Exception exception) {
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
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                // Do Nothing
                

            }
        });

        commonUtil.loadComboBox(itemFamilyplanType, UIUtils.IFP_TYPE, false);

        commonUtil.loadComboBox(itemFamilyplanCategory, UIUtils.IFP_CATEGORY, false);

    }

    /**
     * The Class DateValidator.
     */
    @SuppressWarnings("rawtypes")
    public class DateValidator extends AbstractValidator {

        /**
         * Instantiates a new date validator.
         */
        public DateValidator() {
            super(StringUtils.EMPTY);
        }

        /**
         * Instantiates a new date validator.
         *
         * @param errorMessage the error message
         */
        public DateValidator(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * Validate the ITEMFAMILYPLANSTARTDATE Values and throws error if it is
         * fails.
         *
         * @param value the value
         * @throws InvalidValueException the invalid value exception
         */
        @Override
        public void validate(final Object value) {

            if (itemFamilyplanStartDate.getValue() != null && itemFamilyplanEndDate.getValue() != null) {
                if (itemFamilyplanStartDate.getValue().after(itemFamilyplanEndDate.getValue())) {
                    throw new Validator.InvalidValueException("Item FamilyPlan Start date should be less than Item FamilyPlan End date");
                } else if (itemFamilyplanStartDate.getValue().getTime() == itemFamilyplanEndDate.getValue().getTime()) {
                    throw new Validator.InvalidValueException("Item FamilyPlan Start date and Item FamilyPlan End date are equal");
                }
            }

        }

        /**
         * Validate whether the date is valid by comparing with 0.
         *
         * @param value the value
         * @return Boolean - true / false
         */
        @Override
        protected boolean isValidValue(final Object value) {

            if (itemFamilyplanStartDate.getValue() != null && itemFamilyplanEndDate.getValue() != null) {
                return itemFamilyplanStartDate.getValue().compareTo(itemFamilyplanEndDate.getValue()) <= 0;
            }

            return true;
        }

        /**
         * get Null type.
         *
         * @return null
         */
        @Override
        public Class getType() {
            return null;
        }
    }

    public void setDefaultFocus() {
        itemFamilyplanId.focus();
        String caption = itemFamilyplanDesignation.getItemCaption(itemFamilyplanDesignation.getValue());
        itemFamilyplanDesignation.setDescription(caption);
        if ("Add".equals(mode)) {
            parentItemFamilyplanId.setEnabled(false);
            parentItemFamilyplanName.setEnabled(false);

            parentItemFamilyplanId.setReadOnly(true);
            parentItemFamilyplanName.setReadOnly(true);
            createdBy.setReadOnly(false);
            createdBy.setEnabled(true);
            createdBy.setValue(IFPLogic.getUseName());
            createdBy.setImmediate(true);
            createdBy.setReadOnly(true);
            createdBy.setEnabled(false);

            createdDate.setReadOnly(false);
            createdDate.setEnabled(true);
            createdDate.setValue(new Date());
            createdDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            createdDate.setImmediate(true);
            createdDate.setReadOnly(true);
            createdDate.setEnabled(false);

            modifiedBy.setImmediate(true);
            modifiedBy.setReadOnly(true);
            modifiedDate.setImmediate(true);
            modifiedDate.setReadOnly(true);
            modifiedDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        } else if (ConstantsUtils.EDIT.equals(mode)||ConstantsUtils.COPY.equals(mode)) {
                parentItemFamilyplanId.setReadOnly(true);
                parentItemFamilyplanName.setReadOnly(true);
            if ("child".equalsIgnoreCase(caption)) {
                parentItemFamilyplanId.setReadOnly(false);
                parentItemFamilyplanName.setReadOnly(false);
            } else {
                parentItemFamilyplanId.setReadOnly(true);
                parentItemFamilyplanName.setReadOnly(true);
            }

            createdBy.setEnabled(false);
            createdBy.setReadOnly(true);
            createdDate.setReadOnly(true);
            createdDate.setEnabled(false);
            createdDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            modifiedBy.setEnabled(false);
            modifiedBy.setReadOnly(true);
            modifiedDate.setReadOnly(true);
            modifiedDate.setEnabled(false);
            modifiedDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        } else if (ConstantsUtils.VIEW_BTN.equals(mode)) {
            parentItemFamilyplanId.setEnabled(false);
            parentItemFamilyplanName.setEnabled(false);

            createdBy.setEnabled(false);
            createdBy.setReadOnly(true);
            createdDate.setReadOnly(true);
            createdDate.setEnabled(false);
            createdDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            modifiedBy.setEnabled(false);
            modifiedBy.setReadOnly(true);
            modifiedDate.setReadOnly(true);
            modifiedDate.setEnabled(false);
            modifiedDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
        }
    }

    private ErrorfulFieldGroup getBinder() {

        binder.bindMemberFields(this);

        binder.setItemDataSource(new BeanItem<>(ifpDTO));
        if (ConstantsUtils.VIEW_BTN.equals(mode)) {
       binder.setReadOnly(true);
        }
        binder.setBuffered(true);
        return binder;
    }

    public void resetBinder(ItemFamilyplanMasterDTO dto) {

        this.binder.bindMemberFields(this);
        this.binder.setItemDataSource(new BeanItem<>(dto));
        this.binder.setBuffered(true);
    }

    public ErrorfulFieldGroup getBindedFields() {
        return this.binder;
    }

    /**
     *
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
    
     public void enableParent(boolean value) {
        if (!value) {
            parentItemFamilyplanName.setEnabled(false);
            parentItemFamilyplanName.setReadOnly(true);
            parentItemFamilyplanId.setEnabled(false);
            parentItemFamilyplanId.setReadOnly(true);
        } else {
            parentItemFamilyplanName.setEnabled(true);
            parentItemFamilyplanName.setReadOnly(true);
            parentItemFamilyplanId.setEnabled(true);
            parentItemFamilyplanId.setReadOnly(true);
        }
    
    
    }
}
