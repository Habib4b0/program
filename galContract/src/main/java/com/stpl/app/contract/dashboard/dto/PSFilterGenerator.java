/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.dto;


import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.app.contract.dashboard.ui.lazyload.PriceTypeCriteria;
import com.stpl.app.contract.dashboard.ui.lazyload.PriceTypeLazyContainer;
import com.stpl.app.contract.dashboard.util.ContractUtils;
import com.stpl.app.contract.global.logic.IfpLogic;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.numberfilter.NumberFilterPopup;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.LazyContainer;

public class PSFilterGenerator implements ExtFilterGenerator {

 /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(PSFilterGenerator.class);   
    Map<Integer,HelperDTO> priceProtectionPriceType = null;
    CommonUtil commonUtil=CommonUtil.getInstance();

    public PSFilterGenerator(final Map<Integer,HelperDTO> priceProtectionPriceType) {
        this.priceProtectionPriceType = priceProtectionPriceType;  
    } 


    @Override
    public Filter generateFilter(Object propertyId, Object value) {

        if ("priceProtectionStartDate".equals(propertyId) || 
                "priceProtectionEndStartDate".equals(propertyId) ||
                "resetDate".equals(propertyId)) {
            /* Create an 'equals' filter for the ID field */
            if (value != null && value instanceof String) {
                try {
                    return new Compare.Equal(propertyId,
                            Integer.parseInt((String) value));
                } catch (NumberFormatException ignored) {
                    LOGGER.error(ignored.getMessage());
                }
            }
        }
        return null;
    }

    @Override
    public Filter generateFilter(Object propertyId, Field<?> originatingField) {
        
        if (originatingField instanceof ComboBox) {
            if("resetEligible".equals(propertyId)){
                return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
            }else {
            if (originatingField.getValue() != null) {
                return new SimpleStringFilter(propertyId, String.valueOf(((HelperDTO)originatingField.getValue()).getId()), false, false);
            } else {
                return null;
            }
            }
        }
        if (originatingField instanceof NumberFilterPopup) {

            NumberFilterPopup popup = (NumberFilterPopup) originatingField;
            
            if (StringUtils.isNotBlank(popup.getValue().getEqualsValue())) {
                return new Compare.Equal(propertyId, popup.getValue().getEqualsValue());
            } else if (StringUtils.isNotBlank(popup.getValue().getLessThanValue()) && StringUtils.isNotBlank(popup.getValue().getGreaterThanValue())) {
                return new And(new Compare.Less(propertyId, popup.getValue().getLessThanValue()), new Compare.Greater(propertyId, popup.getValue().getGreaterThanValue()));
            } else if (StringUtils.isNotBlank(popup.getValue().getLessThanValue())) {
                return new Compare.Less(propertyId, popup.getValue().getLessThanValue());
            } else if (StringUtils.isNotBlank(popup.getValue().getGreaterThanValue())) {
                return new Compare.Greater(propertyId, popup.getValue().getGreaterThanValue());
            }

        }

        return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        final TempPricingDTO psDTO = new TempPricingDTO();
        if ("resetEligible".equals(propertyId) || "netPriceType".equals(propertyId)) {
            try {
                ComboBox comboBox = new ComboBox();
                commonUtil.loadYesNoDDLB(comboBox, true);
                comboBox.setDebugId("testing");
                return comboBox;
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        if ("priceScheduleStatus".equals(propertyId) || "itemStatus".equals(propertyId) || "priceProtectionStatus".equals(propertyId)) {
            try {
                ComboBox priceScheduleStatus = new ComboBox();
                commonUtil.loadComboBox(priceScheduleStatus, UIUtils.STATUS, true);
                priceScheduleStatus.setDebugId("testing");
                return priceScheduleStatus;
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        if ("resetType".equals(propertyId) || "resetInterval".equals(propertyId) || "resetFrequency".equals(propertyId)) {
            try {
                ComboBox resetType = new ComboBox();
                commonUtil.loadComboBox(resetType, "resetType".equals(propertyId) ? UIUtils.RESET_TYPE : 
                                    "resetInterval".equals(propertyId) ? UIUtils.PRICE_TOLERANCE_INTERVAL : UIUtils.PRICE_TOLERANCE_FRERQUENCY, true);
                resetType.setDebugId("testing");
                return resetType;
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        if ("priceScheduleType".equals(propertyId)) {
            try {
                ComboBox priceScheduleType = new ComboBox();
                commonUtil.loadComboBox(priceScheduleType, UIUtils.PS_TYPE, true);
                priceScheduleType.setDebugId("testing");
                return priceScheduleType;
            } catch (Exception ex) {
                 LOGGER.error(ex.getMessage());
            }
        }
       
        if ("price".equals(propertyId) || "nep".equals(propertyId) || "contractPrice".equals(propertyId)
                || "basePrice".equals(propertyId) || "priceTolerance".equals(propertyId) || "maxIncrementalChange".equals(propertyId)) {
            NumberFilterPopup popup = new NumberFilterPopup();
            return popup;
        }

        if ("tradeClass".equals(propertyId)) {
            try {
                ComboBox tradeClass = new ComboBox();
                commonUtil.loadComboBox(tradeClass, UIUtils.TRADE_CLASS, true);
                tradeClass.setDebugId("testing");
                return tradeClass;
            } catch (Exception ex) {
               LOGGER.error(ex.getMessage());
            }
        }
        if ("priceType".equals(propertyId) || "priceProtectionPriceType".equals(propertyId)) {
            final ComboBox priceType = new ComboBox();
            priceType.setPageLength(7);
            priceType.setImmediate(true);
            priceType.setNullSelectionAllowed(true);
            priceType.setInputPrompt(ConstantsUtils.SHOW_ALL);
            priceType.setNullSelectionItemId(new HelperDTO(0, ConstantsUtils.SHOW_ALL));
            priceType.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
            priceType.markAsDirty();
            LazyContainer priceTypeContainer;
            if("priceType".equals(propertyId)){
                priceTypeContainer = new LazyContainer(HelperDTO.class, new PriceTypeLazyContainer(psDTO.getPriceType()), new PriceTypeCriteria());
            }else {
                priceTypeContainer = new LazyContainer(HelperDTO.class, new PriceTypeLazyContainer(psDTO.getPriceProtectionPriceType()), new PriceTypeCriteria());
            }
            priceTypeContainer.setMinFilterLength(0);
            priceType.setContainerDataSource(priceTypeContainer);
            return priceType;
        }
        if ("ppPriceToleranceType".equals(propertyId)) {
            try {
                final ComboBox tolerance = new ComboBox();
                commonUtil.loadComboBox(tolerance, UIUtils.PRICE_TOLERANCE_TYPE, true);
                tolerance.select(psDTO.getPriceTolerance());

                return tolerance;
            } catch (Exception ex) {
               LOGGER.error(ex.getMessage());
            }
        }
        if ("ppPriceToleranceInterval".equals(propertyId)) {
            try {
                final ComboBox toleranceInterval = new ComboBox();
                commonUtil.loadComboBox(toleranceInterval, UIUtils.PRICE_TOLERANCE_INTERVAL, true);
                toleranceInterval.select(psDTO.getPriceToleranceInterval());

                return toleranceInterval;
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        if ("ppPriceToleranceFrequency".equals(propertyId)) {
            try {
                final ComboBox frequency = new ComboBox();
                commonUtil.loadComboBox(frequency, UIUtils.PRICE_TOLERANCE_FRERQUENCY, true);
                frequency.setValue(psDTO.getPriceToleranceFrequency());
                return frequency;
            } catch (Exception ex) {
                 LOGGER.error(ex.getMessage());
            }
        } else if ("netBasePrice".equals(propertyId) || "netSubsequentPeriodPrice".equals(propertyId) || "netResetPriceType".equals(propertyId)){
            try {
                final ComboBox yesNoDdlb = new ComboBox();
                commonUtil.loadComboBox(yesNoDdlb, UIUtils.LOCKED_STATUS, true);
                return yesNoDdlb;
            } catch (Exception ex) {
               LOGGER.error(ex.getMessage());
            }
        }
        else if ("basePriceType".equals(propertyId)) {
            final ComboBox comboBox = new ComboBox();
            comboBox.addItem(ContractUtils.SHOW_ALL);
            comboBox.addItem(Constants.MANUAL);
            comboBox.addItem(Constants.DATE);
            comboBox.addItem(ContractUtils.FIELD_PRICE_TYPE);
            comboBox.setImmediate(true);
            comboBox.setNullSelectionAllowed(true);
            comboBox.setNullSelectionItemId(ContractUtils.SHOW_ALL);
            comboBox.select(ContractUtils.SHOW_ALL);
            return comboBox;
        } else if ("resetPriceType".equals(propertyId) || "subsequentPeriodPriceType".equals(propertyId)) {
            final ComboBox comboBox = new ComboBox();
            comboBox.setImmediate(true);
            IfpLogic psLogic = new IfpLogic();
            LazyContainer priceTypeContainer;
            if ("resetPriceType".equals(propertyId)){
             priceTypeContainer = new LazyContainer(HelperDTO.class, new PriceTypeLazyContainer(psDTO.getResetPriceType()), new PriceTypeCriteria());
            } else {
                 priceTypeContainer = new LazyContainer(HelperDTO.class, new PriceTypeLazyContainer(psDTO.getSubsequentPeriodPriceType()), new PriceTypeCriteria());
            }
            comboBox.setContainerDataSource(priceTypeContainer);
            comboBox.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
            comboBox.setNullSelectionAllowed(true);
            comboBox.setNullSelectionItemId(new HelperDTO(0, ContractUtils.SHOW_ALL));
            comboBox.select(new HelperDTO(0, ContractUtils.SHOW_ALL));
            return comboBox;
        }
        return null;
    }

    @Override
    public void filterRemoved(Object propertyId) {
    }

    @Override
    public void filterAdded(Object propertyId, Class<? extends Filter> filterType, Object value) {
    }

    @Override
    public Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {

        return null;
    }
}
