/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.item.ui.form;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.global.item.logic.ItemSearchLogic;
import com.stpl.app.global.item.util.CommonUtils;
import com.stpl.app.global.item.util.UIUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 * The class Additional Information for tab in Item Master
 * @author sooriya.lakshmanan
 */
public class AdditionalInformation extends CustomComponent implements View {

    @UiField("cssLayout")
    CssLayout cssLayout;
    @UiField("hLayout")
    HorizontalLayout hlayout;
    @UiField("dosesPerUnitLB")
    private Label dosesPerUnitLB;
    /**
     * The doses per unit.
     */
    @UiField("dosesPerUnit")
    private TextField dosesPerUnit;

    @UiField("shelfLifeLB")
    private Label shelfLifeLB;
    /**
     * The shelf life.
     */
    @UiField("shelfLife")
    private TextField shelfLife;

    @UiField("shelfLifeTypeLB")
    private Label shelfLifeTypeLB;
    /**
     * The shelf life type.
     */
    @UiField("shelfLifeType")
    private ComboBox shelfLifeType;

    @UiField("lastLotExpirationDateLB")
    private Label lastLotExpirationDateLB;

    /**
     * The last lot expiration date.
     */
    @UiField("lastLotExpirationDate")
    private PopupDateField lastLotExpirationDate;

    @UiField("authorizedGenericLB")
    private Label authorizedGenericLB;
    /**
     * The authorized generic.
     */
    @UiField("authorizedGenericIndicator")
    private OptionGroup authorizedGeneric;

    @UiField("pediatricExclusiveIndicatorLB")
    private Label pediatricExclusiveIndicatorLB;
    /**
     * The authorized generic.
     */
    @UiField("pediatricIndicator")
    private OptionGroup pediatricExclusiveIndicator;

    @UiField("clottingFactorIndicatorLB")
    private Label clottingFactorIndicatorLB;
    /**
     * The authorized generic.
     */
    @UiField("clottingFactorIndicator")
    private OptionGroup clottingFactorIndicator;

    @UiField("dualPricingIndicatorLB")
    private Label dualPricingIndicatorLB;
    /**
     * The authorized generic.
     */
    @UiField("dualPricingIndicator")
    private OptionGroup dualPricingIndicator;

    @UiField("authorizedGenericStartDateLB")
    private Label authorizedGenericStartDateLB;

    @UiField("genericStartDate")
    private PopupDateField authorizedGenericStartDate;

    @UiField("pediatricExclusiveStartDateLB")
    private Label pediatricExclusiveStartDateLB;

    @UiField("pediatricStartDate")
    private PopupDateField pediatricExclusiveStartDate;

    @UiField("clottingFactorStartDateLB")
    private Label clottingFactorStartDateLB;

    @UiField("clottingFactorStartDate")
    private PopupDateField clottingFactorStartDate;

    @UiField("discontinuationDateLB")
    private Label discontinuationDateLB;

    @UiField("discontinuationDate")
    private PopupDateField discontinuationDate;

    @UiField("authorizedGenericEndDateLB")
    private Label authorizedGenericEndDateLB;

    @UiField("authorizedGenericEndDate")
    private PopupDateField authorizedGenericEndDate;

    @UiField("pediatricExclusiveEndDateLB")
    private Label pediatricExclusiveEndDateLB;

    @UiField("pediatricEndDate")
    private PopupDateField pediatricExclusiveEndDate;

    @UiField("clottingFactorEndDateLB")
    private Label clottingFactorEndDateLB;

    @UiField("clottingFactorEndDate")
    private PopupDateField clottingFactorEndDate;

    @UiField("divestitureDateLB")
    private Label divestitureDateLB;

    @UiField("divestitureDate")
    private PopupDateField divestitureDate;

    @UiField("newFormulationIndicatorLB")
    private Label newFormulationIndicatorLB;

    @UiField("newFormulationIndicator")
    private OptionGroup newFormulationIndicator;

    @UiField("baselineAmpLB")
    private Label baselineAmpLB;
    /**
     * The shelf life.
     */
    @UiField("baselineAmp")
    private TextField baselineAmp;

    @UiField("acquisitionDateLB")
    private Label acquisitionDateLB;

    @UiField("acquisitionDate")
    private PopupDateField acquisitionDate;

    @UiField("baseCpiLB")
    private Label baseCpiLB;
    /**
     * The shelf life.
     */
    @UiField("baseCpi")
    private TextField baseCpi;

    @UiField("acquiredAmpLB")
    private Label acquiredAmpLB;
    /**
     * The shelf life.
     */
    @UiField("acquiredAmp")
    private TextField acquiredAmp;

    @UiField("marketTerminationDateLB")
    private Label marketTerminationDateLB;

    @UiField("marketTerminationDate")
    private PopupDateField marketTerminationDate;

    @UiField("newFormulationStartDateLB")
    private Label newFormulationStartDateLB;

    @UiField("newFormStartDate")
    private PopupDateField newFormulationStartDate;

    @UiField("baseCpiPeriodLB")
    private Label baseCpiPeriodLB;

    @UiField("baseCpiPeriod")
    private PopupDateField baseCpiPeriod;

    @UiField("acquiredBampLB")
    private Label acquiredBampLB;

    @UiField("acquiredBamp")
    private TextField acquiredBamp;

    @UiField("newFormulationEndDateLB")
    private Label newFormulationEndDateLB;

    @UiField("newFormEndDate")
    private PopupDateField newFormulationEndDate;

    @UiField("draLB")
    private Label draLB;

    @UiField("dra")
    private TextField dra;

    @UiField("obraBampLB")
    private Label obraBampLB;
    
    @UiField("ObraBamp")
    private TextField obraBamp;

    @UiField("nonFederalExpirationDateLB")
    private Label nonFederalExpirationDateLB;

    @UiField("nonFederalExpirationDate")
    private PopupDateField nonFederalExpirationDate;

    IFPLogic ifpLogic = new IFPLogic();
    CommonUIUtils commonUiUtil = new CommonUIUtils();

    ErrorfulFieldGroup binder;
    /**
     * The logger.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(AdditionalInformation.class);
    /**
     * The item logic.
     */
    private final ItemSearchLogic itemLogic = new ItemSearchLogic();
    /**
     * The common util.
     */
    private CommonUtil commonUtil = CommonUtil.getInstance();
    CommonUtil util = CommonUtil.getInstance();
    String mode;

CommonSecurityLogic commonSecurityLogic = new CommonSecurityLogic();
    /**
     * The Constructor
     * 
     * @param binder
     * @throws Exception 
     */
    public AdditionalInformation(final ErrorfulFieldGroup binder, final String mode) {
        this.binder = binder;
        this.mode=mode;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/declarativeui/itemmaster/additionalinformation.xml"), this));
           

        init();
       
    }

    private void init() {

        try {
            final StplSecurity stplSecurity = new StplSecurity();

            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
              if (ConstantsUtils.EDIT.equals(mode) 
                || ConstantsUtils.VIEW.equals(mode) ) {
            configureFields();
            binder.bindMemberFields(this);


             } else {
               binder.bindMemberFields(this);
            configureFields();
     
        }

            final Map<String, AppPermission> fieldItemHM = stplSecurity
                    .getFieldOrColumnPermission(userId, UISecurityUtil.ITEM_MASTER+ConstantsUtils.COMMA+ConstantsUtils.ADDITIONAL_INFO,false);
            getResponsiveTab(fieldItemHM);
          
            addValidationForTextField(binder);
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        //EMPTY
    }

    private void getResponsiveTab(Map<String, AppPermission> fieldItemHM) {
        LOGGER.debug("Entering getFirstTab1");
        try {

            List<Object> resultList = ifpLogic.getFieldsForSecurity(UISecurityUtil.ITEM_MASTER, ConstantsUtils.ADDITIONAL_INFO);
     commonSecurityLogic.removeComponentOnPermission(resultList, cssLayout, fieldItemHM, mode);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending getFirstTab1");

    }

    private void configureFields() {
        try {
            lastLotExpirationDate.setDescription(ConstantsUtils.DATE_DES);
            acquisitionDate.setDescription(ConstantsUtils.DATE_DES);

            authorizedGenericStartDate.setDescription(ConstantsUtils.DATE_DES);
            authorizedGenericEndDate.setDescription(ConstantsUtils.DATE_DES);
            marketTerminationDate.setDescription(ConstantsUtils.DATE_DES);
            newFormulationEndDate.setDescription(ConstantsUtils.DATE_DES);
            newFormulationStartDate.setDescription(ConstantsUtils.DATE_DES);
            discontinuationDate.setDescription(ConstantsUtils.DATE_DES);
            divestitureDate.setDescription(ConstantsUtils.DATE_DES);

            divestitureDate.setDescription(ConstantsUtils.DATE_DES);
            nonFederalExpirationDate.setDescription(ConstantsUtils.DATE_DES);
            baseCpiPeriod.setDescription(ConstantsUtils.DATE_DES);

            clottingFactorEndDate.setDescription(ConstantsUtils.DATE_DES);
            clottingFactorStartDate.setDescription(ConstantsUtils.DATE_DES);
            pediatricExclusiveEndDate.setDescription(ConstantsUtils.DATE_DES);
            pediatricExclusiveStartDate.setDescription(ConstantsUtils.DATE_DES);

            authorizedGeneric.addItem(ConstantsUtils.YES_VARIABLE);
            authorizedGeneric.addItem(ConstantsUtils.NO_VARIABLE);
            authorizedGeneric.select(ConstantsUtils.NO_VARIABLE);
            authorizedGeneric.setImmediate(true);

            pediatricExclusiveIndicator.addItem(ConstantsUtils.YES_VARIABLE);
            pediatricExclusiveIndicator.addItem(ConstantsUtils.NO_VARIABLE);
            pediatricExclusiveIndicator.select(ConstantsUtils.NO_VARIABLE);
            pediatricExclusiveIndicator.setImmediate(true);

            clottingFactorIndicator.setImmediate(true);
            clottingFactorIndicator.addItem(ConstantsUtils.YES_VARIABLE);
            clottingFactorIndicator.addItem(ConstantsUtils.NO_VARIABLE);
            clottingFactorIndicator.select(ConstantsUtils.NO_VARIABLE);
            dualPricingIndicator.addItem(ConstantsUtils.YES_VARIABLE);
            dualPricingIndicator.addItem(ConstantsUtils.NO_VARIABLE);
            dualPricingIndicator.select(ConstantsUtils.NO_VARIABLE);
            dualPricingIndicator.setImmediate(true);
            newFormulationIndicator.addItem(ConstantsUtils.YES_VARIABLE);
            newFormulationIndicator.addItem(ConstantsUtils.NO_VARIABLE);
            newFormulationIndicator.select(ConstantsUtils.NO_VARIABLE);
            newFormulationIndicator.setImmediate(true);

            authorizedGeneric.setStyleName(ConstantsUtils.HORIZONTAL);
            newFormulationIndicator.setStyleName(ConstantsUtils.HORIZONTAL);
            dualPricingIndicator.setStyleName(ConstantsUtils.HORIZONTAL);
            clottingFactorIndicator.setStyleName(ConstantsUtils.HORIZONTAL);
            pediatricExclusiveIndicator.setStyleName(ConstantsUtils.HORIZONTAL);

            dosesPerUnit.setImmediate(true);
            dosesPerUnit.setValidationVisible(true);
            dosesPerUnit.setData("maxlengthvalidationtwentyfive,maxlengthvalidationdosesperunit,alphaNumericCharsWithoutStar,specialcharvalidationdosesperunit");

            shelfLife.setImmediate(true);
            shelfLife.setValidationVisible(true);

            shelfLife.setData("maxlengthvalidationthirty,maxlengthvalidationshelflife,alphaNumericCharsWithoutStar,specialcharvalidationshelflife");
            shelfLifeType.setNullSelectionAllowed(true);
            shelfLifeType.setNullSelectionItemId(ConstantsUtils.ZERO_INT);
            new CommonUtils().getNativeSelect(shelfLifeType,
                    itemLogic.getItemType(UIUtils.SHELF_LIFE_TYPE));
            shelfLifeType.setImmediate(true);
            shelfLifeType.setDescription(shelfLifeType.getItemCaption(shelfLifeType.getValue()));
            commonUtil.loadComboBox(shelfLifeType, UIUtils.SHELF_LIFE_TYPE, false);

            lastLotExpirationDate.setImmediate(true);
            lastLotExpirationDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            lastLotExpirationDate
                    .addValueChangeListener(new Property.ValueChangeListener() {
                        /**
                         * After changing the value in lastLotExpirationDate,
                         * function will be executed.
                         *
                         * @param event
                         */
                        public void valueChange(final Property.ValueChangeEvent event) {
                                lastLotExpirationDate.setDescription(CommonUIUtils
                                        .convert2DigitTo4DigitYear(lastLotExpirationDate
                                                .getValue()));
                           
                        }
                    });

            authorizedGeneric.addItem(ConstantsUtils.YES_VARIABLE);
            authorizedGeneric.addItem(ConstantsUtils.NO_VARIABLE);
            authorizedGeneric.select(ConstantsUtils.NO_VARIABLE);
            authorizedGeneric.setImmediate(true);
            authorizedGeneric.setDescription((String) authorizedGeneric.getValue());
            authorizedGeneric
                    .addValueChangeListener(new Property.ValueChangeListener() {
                        /**
                         * After changing the value in authorizedGeneric,
                         * function will be executed.
                         *
                         * @param event
                         */
                        public void valueChange(final Property.ValueChangeEvent event) {
                            authorizedGeneric
                            .setDescription((String) authorizedGeneric
                                    .getValue());
                            if (ConstantsUtils.YES_VARIABLE
                            .equals(authorizedGeneric.getValue())) {
                                authorizedGenericStartDate.setImmediate(true);
                                authorizedGenericStartDate.setEnabled(true);
                                authorizedGenericEndDate.setImmediate(true);
                                authorizedGenericEndDate.setEnabled(true);
                            } else {
                                authorizedGenericStartDate.setImmediate(true);
                                authorizedGenericStartDate.setEnabled(false);
                                authorizedGenericEndDate.setImmediate(true);
                                authorizedGenericEndDate.setEnabled(false);
                            }
                        }
                    });

            pediatricExclusiveIndicator.addItem(ConstantsUtils.YES_VARIABLE);
            pediatricExclusiveIndicator.addItem(ConstantsUtils.NO_VARIABLE);
            pediatricExclusiveIndicator.select(ConstantsUtils.NO_VARIABLE);
            pediatricExclusiveIndicator.setImmediate(true);
            pediatricExclusiveIndicator
                    .setDescription((String) pediatricExclusiveIndicator.getValue());
            pediatricExclusiveIndicator
                    .addValueChangeListener(new Property.ValueChangeListener() {
                        /**
                         * After changing the value in itemDesc, function will
                         * be executed.
                         *
                         * @param event
                         */
                        public void valueChange(final Property.ValueChangeEvent event) {
                            pediatricExclusiveIndicator
                            .setDescription((String) pediatricExclusiveIndicator
                                    .getValue());
                            if (ConstantsUtils.YES_VARIABLE
                            .equals(pediatricExclusiveIndicator.getValue())) {
                                pediatricExclusiveStartDate.setImmediate(true);
                                pediatricExclusiveStartDate.setEnabled(true);
                                pediatricExclusiveEndDate.setImmediate(true);
                                pediatricExclusiveEndDate.setEnabled(true);
                            } else {
                                pediatricExclusiveStartDate.setImmediate(true);
                                pediatricExclusiveStartDate.setEnabled(false);
                                pediatricExclusiveEndDate.setImmediate(true);
                                pediatricExclusiveEndDate.setEnabled(false);
                            }
                        }
                    });

            clottingFactorIndicator.setImmediate(true);
            clottingFactorIndicator.addItem(ConstantsUtils.YES_VARIABLE);
            clottingFactorIndicator.addItem(ConstantsUtils.NO_VARIABLE);
            clottingFactorIndicator.select(ConstantsUtils.NO_VARIABLE);
            clottingFactorIndicator.setDescription((String) clottingFactorIndicator
                    .getValue());
            clottingFactorIndicator
                    .addValueChangeListener(new Property.ValueChangeListener() {
                        /**
                         * After changing the value in clottingFactorIndicator,
                         * function will be executed.
                         *
                         * @param event
                         */
                        public void valueChange(final Property.ValueChangeEvent event) {
                            clottingFactorIndicator
                            .setDescription((String) clottingFactorIndicator
                                    .getValue());
                            if (ConstantsUtils.YES_VARIABLE
                            .equals(clottingFactorIndicator.getValue())) {
                                clottingFactorStartDate.setImmediate(true);
                                clottingFactorStartDate.setEnabled(true);
                                clottingFactorEndDate.setImmediate(true);
                                clottingFactorEndDate.setEnabled(true);
                            } else {
                                clottingFactorStartDate.setImmediate(true);
                                clottingFactorStartDate.setEnabled(false);
                                clottingFactorEndDate.setImmediate(true);
                                clottingFactorEndDate.setEnabled(false);
                            }
                        }
                    });

            dualPricingIndicator.addItem(ConstantsUtils.YES_VARIABLE);
            dualPricingIndicator.addItem(ConstantsUtils.NO_VARIABLE);
            dualPricingIndicator.select(ConstantsUtils.NO_VARIABLE);
            dualPricingIndicator.setImmediate(true);
            dualPricingIndicator.setDescription((String) dualPricingIndicator
                    .getValue());
            dualPricingIndicator
                    .addValueChangeListener(new Property.ValueChangeListener() {
                        /**
                         * After changing the value in dualPricingIndicator,
                         * function will be executed.
                         *
                         * @param event
                         */
                        @SuppressWarnings("PMD")
                        public void valueChange(final Property.ValueChangeEvent event) {
                            dualPricingIndicator
                            .setDescription((String) dualPricingIndicator
                                    .getValue());
                        }
                    });

            authorizedGenericStartDate.setImmediate(true);
            authorizedGenericStartDate.setEnabled(false);
            authorizedGenericStartDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            authorizedGenericStartDate.setValidationVisible(true);
            authorizedGenericStartDate
                    .addValidator(new DateValidatorAuthorizedGeneric(
                                    ConstantsUtils.END_DATE_AFTER_START_DATE));
            authorizedGenericStartDate
                    .addValueChangeListener(new Property.ValueChangeListener() {
                        /**
                         * After changing the value in discontinuationDate,
                         * function will be executed.
                         *
                         * @param event
                         */
                        public void valueChange(final Property.ValueChangeEvent event) {
                                authorizedGenericStartDate.setDescription(CommonUIUtils
                                        .convert2DigitTo4DigitYear(authorizedGenericStartDate
                                                .getValue()));
                            
                        }
                    });

            authorizedGenericEndDate.setImmediate(true);
            authorizedGenericEndDate.setEnabled(false);
            authorizedGenericEndDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            authorizedGenericEndDate.setValidationVisible(true);
            authorizedGenericEndDate
                    .addValidator(new DateValidatorAuthorizedGeneric(
                                    ConstantsUtils.END_DATE_AFTER_START_DATE));
            authorizedGenericEndDate
                    .addValueChangeListener(new Property.ValueChangeListener() {
                        /**
                         * After changing the value in discontinuationDate,
                         * function will be executed.
                         *
                         * @param event
                         */
                        public void valueChange(final Property.ValueChangeEvent event) {
                                authorizedGenericEndDate.setDescription(CommonUIUtils
                                        .convert2DigitTo4DigitYear(authorizedGenericEndDate
                                                .getValue()));
                            
                        }
                    });

            pediatricExclusiveStartDate.setImmediate(true);
            pediatricExclusiveStartDate.setEnabled(false);
            pediatricExclusiveStartDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            pediatricExclusiveStartDate.setValidationVisible(true);
            pediatricExclusiveStartDate.addValidator(new DateValidatorPediatric(
                    ConstantsUtils.END_DATE_AFTER_START_DATE));
            pediatricExclusiveStartDate
                    .addValueChangeListener(new Property.ValueChangeListener() {
                        /**
                         * After changing the value in discontinuationDate,
                         * function will be executed.
                         *
                         * @param event
                         */
                        public void valueChange(final Property.ValueChangeEvent event) {
                                pediatricExclusiveStartDate.setDescription(CommonUIUtils
                                        .convert2DigitTo4DigitYear(pediatricExclusiveStartDate
                                                .getValue()));
                            
                        }
                    });

            pediatricExclusiveEndDate.setImmediate(true);
            pediatricExclusiveEndDate.setEnabled(false);
            pediatricExclusiveEndDate.setValidationVisible(true);
            pediatricExclusiveEndDate.addValidator(new DateValidatorPediatric(
                    ConstantsUtils.END_DATE_AFTER_START_DATE));
            pediatricExclusiveEndDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            pediatricExclusiveEndDate
                    .addValueChangeListener(new Property.ValueChangeListener() {
                        /**
                         * After changing the value in discontinuationDate,
                         * function will be executed.
                         *
                         * @param event
                         */
                        public void valueChange(final Property.ValueChangeEvent event) {
                                pediatricExclusiveEndDate.setDescription(CommonUIUtils
                                        .convert2DigitTo4DigitYear(pediatricExclusiveEndDate
                                                .getValue()));
                            
                        }
                    });

            clottingFactorStartDate.setImmediate(true);
            clottingFactorStartDate.setEnabled(false);
            clottingFactorStartDate.addValidator(new DateValidatorClotting(
                    ConstantsUtils.END_DATE_AFTER_START_DATE));
            clottingFactorStartDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            clottingFactorStartDate
                    .addValueChangeListener(new Property.ValueChangeListener() {
                        /**
                         * After changing the value in discontinuationDate,
                         * function will be executed.
                         *
                         * @param event
                         */
                        public void valueChange(final Property.ValueChangeEvent event) {
                                clottingFactorStartDate.setDescription(CommonUIUtils
                                        .convert2DigitTo4DigitYear(clottingFactorStartDate
                                                .getValue()));
                            
                        }
                    });

            clottingFactorEndDate.setImmediate(true);
            clottingFactorEndDate.setEnabled(false);
            clottingFactorEndDate.setValidationVisible(true);
            clottingFactorEndDate.addValidator(new DateValidatorClotting(
                    ConstantsUtils.END_DATE_AFTER_START_DATE));
            clottingFactorEndDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            clottingFactorEndDate
                    .addValueChangeListener(new Property.ValueChangeListener() {
                        /**
                         * After changing the value in discontinuationDate,
                         * function will be executed.
                         *
                         * @param event
                         */
                        public void valueChange(final Property.ValueChangeEvent event) {
                                clottingFactorEndDate.setDescription(CommonUIUtils
                                        .convert2DigitTo4DigitYear(clottingFactorEndDate
                                                .getValue()));
                            
                        }
                    });

            discontinuationDate.setImmediate(true);
            discontinuationDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            discontinuationDate
                    .addValueChangeListener(new Property.ValueChangeListener() {
                        /**
                         * After changing the value in discontinuationDate,
                         * function will be executed.
                         *
                         * @param event
                         */
                        public void valueChange(final Property.ValueChangeEvent event) {
                                discontinuationDate.setDescription(CommonUIUtils
                                        .convert2DigitTo4DigitYear(discontinuationDate
                                                .getValue()));
                            
                        }
                    });

            divestitureDate.setImmediate(true);
            divestitureDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            divestitureDate
                    .addValueChangeListener(new Property.ValueChangeListener() {
                        /**
                         * After changing the value in divestitureDate, function
                         * will be executed.
                         *
                         * @param event
                         */
                        public void valueChange(final Property.ValueChangeEvent event) {
                                divestitureDate.setDescription(CommonUIUtils
                                        .convert2DigitTo4DigitYear(divestitureDate
                                                .getValue()));
                           
                        }
                    });

            newFormulationIndicator.addItem(ConstantsUtils.YES_VARIABLE);
            newFormulationIndicator.addItem(ConstantsUtils.NO_VARIABLE);
            newFormulationIndicator.select(ConstantsUtils.NO_VARIABLE);
            newFormulationIndicator.setImmediate(true);
            newFormulationIndicator.setDescription((String) newFormulationIndicator
                    .getValue());
            newFormulationIndicator
                    .addValueChangeListener(new Property.ValueChangeListener() {
                        /**
                         * After changing the value in newFormulationIndicator,
                         * function will be executed.
                         *
                         * @param event
                         */
                        public void valueChange(final Property.ValueChangeEvent event) {
                            newFormulationIndicator
                            .setDescription((String) newFormulationIndicator
                                    .getValue());
                            if (ConstantsUtils.YES_VARIABLE
                            .equals(newFormulationIndicator.getValue())) {
                                newFormulationStartDate.setImmediate(true);
                                newFormulationStartDate.setEnabled(true);
                                newFormulationEndDate.setImmediate(true);
                                newFormulationEndDate.setEnabled(true);
                            } else {
                                newFormulationStartDate.setImmediate(true);
                                newFormulationStartDate.setEnabled(false);
                                newFormulationEndDate.setImmediate(true);
                                newFormulationEndDate.setEnabled(false);
                            }
                        }
                    });

            baselineAmp.setData("maxlengthvalidationfifty,maxlengthvalidationbaselineamp,pricevalidation,specialcharvalidationbaselineamp");
            baselineAmp.setValidationVisible(true);
            baselineAmp.setImmediate(true);
            baselineAmp.setDescription(baselineAmp.getValue());
            baselineAmp.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in baselineAmp, function will be
                 * executed.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    baselineAmp.setDescription(baselineAmp.getValue());
                }
            });

            acquisitionDate.setImmediate(true);
            acquisitionDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            acquisitionDate
                    .addValueChangeListener(new Property.ValueChangeListener() {
                        /**
                         * After changing the value in acquisitionDate, function
                         * will be executed.
                         *
                         * @param event
                         */
                        public void valueChange(final Property.ValueChangeEvent event) {
                                acquisitionDate.setDescription(CommonUIUtils
                                        .convert2DigitTo4DigitYear(acquisitionDate
                                                .getValue()));
                            
                        }
                    });

            baseCpi.setData("maxlengthvalidationfifty,maxlengthvalidationbasecpi,pricevalidation,specialcharvalidationbasecpi");
            baseCpi.setValidationVisible(true);
            baseCpi.setImmediate(true);
            baseCpi.setDescription(baseCpi.getValue());
            baseCpi.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in baseCpi, function will be
                 * executed.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    baseCpi.setDescription(baseCpi.getValue());
                }
            });

            acquiredAmp.setData("maxlengthvalidationfifty,maxlengthvalidationacquiredamp,pricevalidation,specialcharvalidationacquiredamp");
            acquiredAmp.setValidationVisible(true);
            acquiredAmp.setImmediate(true);
            acquiredAmp.setDescription(acquiredAmp.getValue());
            acquiredAmp.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in acquiredAmp text box, function
                 * will be executed.
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    acquiredAmp.setDescription(acquiredAmp.getValue());
                }
            });

            marketTerminationDate.setImmediate(true);
            marketTerminationDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            marketTerminationDate
                    .addValueChangeListener(new Property.ValueChangeListener() {
                        /**
                         * After changing the value in marketTerminationDate,
                         * function will be executed.
                         *
                         * @param event
                         */
                        public void valueChange(final Property.ValueChangeEvent event) {
                                marketTerminationDate.setDescription(CommonUIUtils
                                        .convert2DigitTo4DigitYear(marketTerminationDate
                                                .getValue()));
                            
                        }
                    });
            newFormulationStartDate.setImmediate(true);
            newFormulationStartDate.setEnabled(false);
            newFormulationStartDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            newFormulationStartDate.addValidator(new DateValidatorFormulation(
                    ConstantsUtils.END_DATE_AFTER_START_DATE));
            newFormulationStartDate.setValidationVisible(true);
            newFormulationStartDate
                    .addValueChangeListener(new Property.ValueChangeListener() {
                        /**
                         * After changing the value in marketTerminationDate,
                         * function will be executed.
                         *
                         * @param event
                         */
                        public void valueChange(final Property.ValueChangeEvent event) {
                                newFormulationStartDate.setDescription(CommonUIUtils
                                        .convert2DigitTo4DigitYear(newFormulationStartDate
                                                .getValue()));
                            
                        }
                    });

            newFormulationEndDate.setImmediate(true);
            newFormulationEndDate.setEnabled(false);
            newFormulationEndDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            newFormulationEndDate.addValidator(new DateValidatorFormulation(
                    ConstantsUtils.END_DATE_AFTER_START_DATE));
            newFormulationEndDate.setValidationVisible(true);
            newFormulationEndDate
                    .addValueChangeListener(new Property.ValueChangeListener() {
                        /**
                         * After changing the value in marketTerminationDate,
                         * function will be executed.
                         *
                         * @param event
                         */
                        public void valueChange(final Property.ValueChangeEvent event) {
                                newFormulationEndDate.setDescription(CommonUIUtils
                                        .convert2DigitTo4DigitYear(newFormulationEndDate
                                                .getValue()));
                            
                        }
                    });

            baseCpiPeriod.setImmediate(true);
            baseCpiPeriod.setDateFormat(ConstantsUtils.DATE_FORMAT);
            baseCpiPeriod
                    .addValueChangeListener(new Property.ValueChangeListener() {
                        /**
                         * After changing the value in baseCpiPeriod, function
                         * will be executed.
                         *
                         * @param event
                         */
                        public void valueChange(final Property.ValueChangeEvent event) {
                                baseCpiPeriod.setDescription(CommonUIUtils
                                        .convert2DigitTo4DigitYear(baseCpiPeriod
                                                .getValue()));
                            
                        }
                    });

            acquiredBamp.setData("maxlengthvalidationfifty,maxlengthvalidationacquiredbamp,pricevalidation,numvalidationacquiredbamp");
            acquiredBamp.setValidationVisible(true);
            acquiredBamp.setImmediate(true);
            acquiredBamp.setDescription(acquiredBamp.getValue());
            acquiredBamp.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in acquiredBamp, function will be
                 * executed.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    acquiredBamp.setDescription(acquiredBamp.getValue());
                }
            });

            dra.setData("maxlengthvalidationfifty,maxlengthvalidationdra,pricevalidation,numvalidationdra");
            dra.setValidationVisible(true);
            dra.setImmediate(true);
            dra.setDescription(dra.getValue());
            dra.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in dra, function will be executed.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    dra.setDescription(dra.getValue());
                }
            });

            obraBamp.setData("maxlengthvalidationfifty,maxlengthvalidationobrabamp,pricevalidation,numvalidationobrabamp");
            obraBamp.setValidationVisible(true);
            obraBamp.setImmediate(true);
            obraBamp.setDescription(obraBamp.getValue());
            obraBamp.addValueChangeListener(new Property.ValueChangeListener() {
                /**
                 * After changing the value in obraBamp, function will be
                 * executed.
                 *
                 * @param event
                 */
                public void valueChange(final Property.ValueChangeEvent event) {
                    obraBamp.setDescription(obraBamp.getValue());
                }
            });

            nonFederalExpirationDate.setImmediate(true);
            nonFederalExpirationDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
            nonFederalExpirationDate
                    .addValueChangeListener(new Property.ValueChangeListener() {
                        /**
                         * After changing the value, function will be executed.
                         */
                        public void valueChange(final Property.ValueChangeEvent event) {
                                nonFederalExpirationDate.setDescription(CommonUIUtils
                                        .convert2DigitTo4DigitYear(nonFederalExpirationDate
                                                .getValue()));
                            
                        }
                    });

        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    /**
     * The Class DateValidatorAuthorizedGeneric.
     */
    public class DateValidatorAuthorizedGeneric extends AbstractValidator {

        /**
         * The Constant serialVersionUID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * The Constructor.
         *
         * @param errorMessage the error message
         */
        public DateValidatorAuthorizedGeneric(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * Validating the values.
         *
         * @param value the value
         * @throws InvalidValueException the invalid value exception
         * @see
         * com.vaadin.data.validator.AbstractValidator#validate(java.lang.Object)
         */
        @Override
        public void validate(final Object value) throws Validator.InvalidValueException {
            if (authorizedGenericStartDate.getValue() != null
                    && authorizedGenericEndDate.getValue() != null) {
                if (authorizedGenericStartDate.getValue().after(
                        authorizedGenericEndDate.getValue())) {
                    throw new Validator.InvalidValueException(
                            "Authorized Generic End Date should be greater than Authorized Generic Start Date");
                } else if (authorizedGenericStartDate.getValue().getTime() == authorizedGenericEndDate
                        .getValue().getTime()) {
                    throw new Validator.InvalidValueException(
                            "Authorized Generic End Date and Authorized Generic Start Date should not be  equal");
                }
            }

        }

        /**
         * Checking the value is valid or not.
         *
         * @param value the value
         * @return true, if is valid value
         * @see
         * com.vaadin.data.validator.AbstractValidator#isValidValue(java.lang.Object)
         */
        @Override
        protected boolean isValidValue(final Object value) {

            if (authorizedGenericStartDate.getValue() != null
                    && authorizedGenericEndDate.getValue() != null) {
                return authorizedGenericStartDate.getValue().compareTo(
                        authorizedGenericEndDate.getValue()) <= 0;
            }

            return true;
        }

        /**
         * (non-Javadoc).
         *
         * @return the type
         * @see com.vaadin.data.validator.AbstractValidator#getType()
         */
        @Override
        public Class getType() {
            return null;
        }
    }

    /**
     * The Class DateValidatorPediatric.
     */
    public class DateValidatorPediatric extends AbstractValidator {

        /**
         * The Constant serialVersionUID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * The Constructor.
         *
         * @param errorMessage the error message
         */
        public DateValidatorPediatric(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * Validation.
         *
         * @param value the value
         * @throws InvalidValueException the invalid value exception
         * @see
         * com.vaadin.data.validator.AbstractValidator#validate(java.lang.Object)
         */
        @Override
        public void validate(final Object value) throws Validator.InvalidValueException {
            if (pediatricExclusiveStartDate.getValue() != null
                    && pediatricExclusiveEndDate.getValue() != null) {
                if (pediatricExclusiveStartDate.getValue().after(
                        pediatricExclusiveEndDate.getValue())) {
                    throw new Validator.InvalidValueException(
                            "Pediatric Exclusive End Date should be greater than Pediatric Exclusive Start Date");
                } else if (pediatricExclusiveStartDate.getValue().getTime() == pediatricExclusiveEndDate
                        .getValue().getTime()) {
                    throw new Validator.InvalidValueException(
                            "Pediatric Exclusive Start Date and Pediatric Exclusive End Date should not be  equal");
                }
            }

        }

        /**
         * Checking the value is valid or not.
         *
         * @param value the value
         * @return true, if is valid value
         * @see
         * com.vaadin.data.validator.AbstractValidator#isValidValue(java.lang.Object)
         */
        @Override
        protected boolean isValidValue(final Object value) {

            if (pediatricExclusiveStartDate.getValue() != null
                    && pediatricExclusiveEndDate.getValue() != null) {
                return pediatricExclusiveStartDate.getValue().compareTo(
                        pediatricExclusiveEndDate.getValue()) <= 0;
            }

            return true;
        }

        /**
         * (non-Javadoc).
         *
         * @return the type
         * @see com.vaadin.data.validator.AbstractValidator#getType()
         */
        @Override
        public Class getType() {
            return null;
        }
    }

    /**
     * The Class DateValidatorClotting.
     */
    public class DateValidatorClotting extends AbstractValidator {

        /**
         * The Constant serialVersionUID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * The Constructor.
         *
         * @param errorMessage the error message
         */
        public DateValidatorClotting(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * Validating Values.
         *
         * @param value the value
         * @throws InvalidValueException the invalid value exception
         * @see
         * com.vaadin.data.validator.AbstractValidator#validate(java.lang.Object)
         */
        @Override
        public void validate(final Object value) throws Validator.InvalidValueException {
            if (clottingFactorStartDate.getValue() != null
                    && clottingFactorEndDate.getValue() != null) {
                if (clottingFactorStartDate.getValue().after(
                        clottingFactorEndDate.getValue())) {
                    throw new Validator.InvalidValueException(
                            "ClottingFactor EndDate should be greater than ClottingFactor StartDate");
                } else if (clottingFactorStartDate.getValue().getTime() == clottingFactorEndDate
                        .getValue().getTime()) {
                    throw new Validator.InvalidValueException(
                            "ClottingFactorStartDate and ClottingFactorEndDate should not be  equal");
                }
            }

        }

        /**
         * Checking the value is valid or not.
         *
         * @param value the value
         * @return true, if is valid value
         * @see
         * com.vaadin.data.validator.AbstractValidator#isValidValue(java.lang.Object)
         */
        @Override
        protected boolean isValidValue(final Object value) {

            if (clottingFactorStartDate.getValue() != null
                    && clottingFactorEndDate.getValue() != null) {
                return clottingFactorStartDate.getValue().compareTo(
                        clottingFactorEndDate.getValue()) <= 0;
            }

            return true;
        }

        /**
         * (non-Javadoc).
         *
         * @return the type
         * @see com.vaadin.data.validator.AbstractValidator#getType()
         */
        @Override
        public Class getType() {
            return null;
        }
    }

    /**
     * The Class DateValidatorFormulation.
     */
    public class DateValidatorFormulation extends AbstractValidator {

        /**
         * The Constant serialVersionUID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * The Constructor.
         *
         * @param errorMessage the error message
         */
        public DateValidatorFormulation(final String errorMessage) {
            super(errorMessage);
        }

        /**
         * Validation.
         *
         * @param value the value
         * @throws InvalidValueException the invalid value exception
         * @see
         * com.vaadin.data.validator.AbstractValidator#validate(java.lang.Object)
         */
        @Override
        public void validate(final Object value) throws InvalidValueException {
            if (newFormulationStartDate.getValue() != null
                    && newFormulationEndDate.getValue() != null) {
                if (newFormulationStartDate.getValue().after(
                        newFormulationEndDate.getValue())) {
                    throw new InvalidValueException(
                            "New Formulation End Date should be greater than New Formulation Start Date");
                } else if (newFormulationStartDate.getValue().getTime() == newFormulationEndDate
                        .getValue().getTime()) {
                    throw new InvalidValueException(
                            "New Formulation Start Date and NewFormulation End Date should not be  equal");
                }
            }

        }

        /**
         * Checking the value is valid or not.
         *
         * @param value the value
         * @return true, if is valid value
         * @see
         * com.vaadin.data.validator.AbstractValidator#isValidValue(java.lang.Object)
         */
        @Override
        protected boolean isValidValue(final Object value) {

            if (newFormulationStartDate.getValue() != null
                    && newFormulationEndDate.getValue() != null) {
                return newFormulationStartDate.getValue().compareTo(
                        newFormulationEndDate.getValue()) <= 0;
            }

            return true;
        }

        /**
         * (non-Javadoc).
         *
         * @return the type
         * @see com.vaadin.data.validator.AbstractValidator#getType()
         */
        @Override
        public Class getType() {
            return null;
        }
    }

    private void addValidationForTextField(ErrorfulFieldGroup binder) {
        Collection collection = binder.getFields();
        for (Object object : collection) {
            if (object instanceof TextField) {
                TextField filed = (TextField) object;
                util.textValidation(filed, filed.getData());
            }
        }
    }
}
