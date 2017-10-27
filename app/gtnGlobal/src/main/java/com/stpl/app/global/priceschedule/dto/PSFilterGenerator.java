/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.priceschedule.dto;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.priceschedule.logic.PSLogic;
import com.stpl.app.global.priceschedule.ui.lazyload.PriceTypeCriteria;
import com.stpl.app.global.priceschedule.ui.lazyload.PriceTypeLazyContainer;
import com.stpl.app.global.priceschedule.util.UIUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.numberfilter.NumberFilterPopup;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Compare.Equal;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import java.util.Map;
import java.util.logging.Level;
import org.jboss.logging.Logger; 
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.vaadin.addons.lazycontainer.LazyContainer;

public class PSFilterGenerator implements ExtFilterGenerator {
    private static final Logger LOGGER = Logger.getLogger(PSFilterGenerator.class);
    CommonUtil commonUtil = CommonUtil.getInstance();
    PSDTO priceScheduleDTO = new PSDTO();

    public PSFilterGenerator(final PSDTO psDTO) {
        this.priceScheduleDTO = psDTO;
    }

    public PSFilterGenerator() {

    }
    
    @Override
    public Filter generateFilter(Object propertyId, Object value) {

        if ("startDate".equals(propertyId) && value != null && value instanceof String) {
            /* Create an 'equals' filter for the ID field */
                try {
                    return new Compare.Equal(propertyId,
                            Integer.parseInt((String) value));
                } catch (NumberFormatException ignored) {
                }
        }
        // For other properties, use the default filter
        return null;
    }

    @Override
    public Filter generateFilter(Object propertyId, Field<?> originatingField) {
        
        if (originatingField instanceof ComboBox) {
               if (propertyId.toString().equals("createdBy") || propertyId.toString().equals("modifiedBy") || propertyId.toString().equals("cfpcreatedBy") || propertyId.toString().equals("cfpmodifiedBy")|| propertyId.toString().equals("createdUserName")  ) {
                if (originatingField.getValue() != null) {
                    return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
                }
                 else {
                    return null;
                }
            }
            if(("netBasePrice".equals(propertyId) || "netSubsequentPeriodPrice".equals(propertyId) 
                    || "netResetPriceType".equals(propertyId) || "basePriceType".equals(propertyId)) && originatingField.getValue() != null){
                return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
            }else            
            if (originatingField.getValue() != null) {
                return new Equal(propertyId, ((HelperDTO)originatingField.getValue()).getId());
            } else {
                return null;
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
        final PSIFPDTO psDTO = new PSIFPDTO();
        Map<Integer, String> userMap= null;
      
        try {
            userMap = StplSecurity.getUserName();
        } catch (SystemException ex) {
            java.util.logging.Logger.getLogger(PSFilterGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
            ComboBox comboBox1;
        if ("resetEligible".equals(propertyId) || "netPriceType".equals(propertyId)) {
            try {
                ComboBox comboBox = new ComboBox();
                commonUtil.loadYesNoDDLB(comboBox, true);
                comboBox.setDebugId(ConstantsUtils.TESTING);
                return comboBox;
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        if ("priceScheduleStatus".equals(propertyId) || "itemStatus".equals(propertyId) || "priceProtectionStatus".equals(propertyId)) {
            try {
                ComboBox priceScheduleStatus = new ComboBox();
                commonUtil.loadComboBox(priceScheduleStatus, UIUtils.STATUS, true);
                priceScheduleStatus.setDebugId(ConstantsUtils.TESTING);
                return priceScheduleStatus;
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        if ("resetType".equals(propertyId) || "resetInterval".equals(propertyId) || "resetFrequency".equals(propertyId)) {
            try {
                ComboBox resetType = new ComboBox();
                commonUtil.loadComboBox(resetType, "resetType".equals(propertyId) ? UIUtils.RESET_TYPE : 
                                    "resetInterval".equals(propertyId) ? UIUtils.PRICE_TOLERANCE_INTERVAL : UIUtils.PRICE_TOLERANCE_FRERQUENCY, true);
                resetType.setDebugId(ConstantsUtils.TESTING);
                return resetType;
            } catch (Exception ex) {
               LOGGER.error(ex);
            }
        }
        if ("priceScheduleType".equals(propertyId)) {
            try {
                ComboBox priceScheduleType = new ComboBox();
                commonUtil.loadComboBox(priceScheduleType, UIUtils.PS_TYPE, true);
                priceScheduleType.setDebugId(ConstantsUtils.TESTING);
                return priceScheduleType;
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        if ("priceScheduleCategory".equals(propertyId)) {
            try {
                ComboBox priceScheduleCategory = new ComboBox();
                commonUtil.loadComboBox(priceScheduleCategory, UIUtils.PS_CATEGORY, true);
                priceScheduleCategory.setDebugId(ConstantsUtils.TESTING);
                return priceScheduleCategory;
            } catch (Exception ex) {
               LOGGER.error(ex);
            }
        }

        if ("priceScheduleDesignation".equals(propertyId)) {
            try {
                ComboBox priceScheduleDesignation = new ComboBox();
                commonUtil.loadComboBox(priceScheduleDesignation, UIUtils.PS_DESIGNATION, true);
                priceScheduleDesignation.setDebugId(ConstantsUtils.TESTING);
                return priceScheduleDesignation;
            } catch (Exception ex) {
               LOGGER.error(ex);
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
                tradeClass.setDebugId(ConstantsUtils.TESTING);
                return tradeClass;
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        if ("priceType".equals(propertyId) || "priceProtectionPriceType".equals(propertyId)) {
            final ComboBox priceType = new ComboBox();
            priceType.setPageLength(NumericConstants.SEVEN);
            priceType.setImmediate(true);
            priceType.setNullSelectionAllowed(true);
            priceType.setInputPrompt(ConstantsUtils.SELECT_ONE);
            priceType.setNullSelectionItemId(new HelperDTO(0, Constants.SELECT_ONE));
            priceType.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
            priceType.markAsDirty();
            final LazyContainer priceTypeContainer = new LazyContainer(HelperDTO.class, new PriceTypeLazyContainer(psDTO.getPriceType()), new PriceTypeCriteria());
            priceTypeContainer.setMinFilterLength(0);
            priceType.setContainerDataSource(priceTypeContainer);

            return priceType;
        }
        if ("priceToleranceType".equals(propertyId)) {
            try {
                final ComboBox tolerance = new ComboBox();
                commonUtil.loadComboBox(tolerance, UIUtils.PRICE_TOLERANCE_TYPE, true);
                tolerance.select(psDTO.getPriceTolerance());

                return tolerance;
            } catch (Exception ex) {
               LOGGER.error(ex);
            }
        }
        if ("priceToleranceInterval".equals(propertyId)) {
            try {
                final ComboBox toleranceInterval = new ComboBox();
                commonUtil.loadComboBox(toleranceInterval, UIUtils.PRICE_TOLERANCE_INTERVAL, true);
                toleranceInterval.select(psDTO.getPriceToleranceInterval());

                return toleranceInterval;
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        if ("priceToleranceFrequency".equals(propertyId)) {
            try {
                final ComboBox frequency = new ComboBox();
                commonUtil.loadComboBox(frequency, UIUtils.PRICE_TOLERANCE_FRERQUENCY, true);
                frequency.setValue(psDTO.getPriceToleranceFrequency());
                return frequency;
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        } 
        if ("basePriceType".equals(propertyId)) {
            final ComboBox comboBox = new ComboBox();
            comboBox.addItem(Constants.SHOW_ALL);
            comboBox.addItem(Constants.MANUAL);
            comboBox.addItem(Constants.DATE);
            comboBox.addItem(Constants.PRICE_TYPE);
            comboBox.setImmediate(true);
            comboBox.setNullSelectionAllowed(true);
            comboBox.setNullSelectionItemId(Constants.SHOW_ALL);
            comboBox.select(Constants.SHOW_ALL);
            return comboBox;
        } else if ("netBasePrice".equals(propertyId) || "netSubsequentPeriodPrice".equals(propertyId) || "netResetPriceType".equals(propertyId)) {
            final ComboBox comboBox = new ComboBox();
            comboBox.addItem(Constants.SHOW_ALL);
            comboBox.addItem(Constants.YES);
            comboBox.addItem(Constants.NO);
            comboBox.setImmediate(true);
            comboBox.setNullSelectionAllowed(true);
            comboBox.setNullSelectionItemId(Constants.SHOW_ALL);
            comboBox.select(Constants.SHOW_ALL);
            return comboBox;
        } else if ("resetPriceType".equals(propertyId) || "subsequentPeriodPriceType".equals(propertyId)) {
            final ComboBox comboBox = new ComboBox();
            comboBox.setImmediate(true);
            PSLogic psLogic = new PSLogic();
            Container priceTypeContainer = new BeanItemContainer(HelperDTO.class, psLogic.getConfiguredPriceTypes(priceScheduleDTO.getItemPricingQualifierMap(), true));
            comboBox.setContainerDataSource(priceTypeContainer);
            comboBox.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
            comboBox.setNullSelectionAllowed(true);
            comboBox.setNullSelectionItemId(new HelperDTO(0, Constants.SHOW_ALL));
            comboBox.select(new HelperDTO(0, Constants.SHOW_ALL));
            return comboBox;
           
         } else if ("createdUserName".equals(propertyId)) {       
                 comboBox1 = new ComboBox();
                    comboBox1.addItem(0);
                    comboBox1.setItemCaption(0, ConstantsUtils.SHOW_ALL);
                    for (Map.Entry<Integer, String> entry : userMap.entrySet()) {
                        comboBox1.addItem(entry.getKey());
                        comboBox1.setItemCaption(entry.getKey(), entry.getValue());
                    }
                    comboBox1.setNullSelectionAllowed(true);
                    comboBox1.setNullSelectionItemId(0);
                    return comboBox1;
         
            } else if ("modifiedBy".equals(propertyId)) {
                comboBox1 = new ComboBox();
                    comboBox1.addItem(0);
                    comboBox1.setItemCaption(0, ConstantsUtils.SHOW_ALL);
                    for (Map.Entry<Integer, String> entry : userMap.entrySet()) {
                        comboBox1.addItem(entry.getKey());
                        comboBox1.setItemCaption(entry.getKey(), entry.getValue());
                    }
                    comboBox1.setNullSelectionAllowed(true);
                    comboBox1.setNullSelectionItemId(0);
                    return comboBox1;
                    }
       
        return null;
    }

    @Override
    public void filterRemoved(Object propertyId) {
        return;
    }

    @Override
    public void filterAdded(Object propertyId, Class<? extends Filter> filterType, Object value) {
        return;
    }

    @Override
    public Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {

        return null;
    }
}
