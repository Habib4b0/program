/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.rebateschedule.dto;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.item.util.UIUtils;
import com.stpl.app.global.rebateschedule.logic.RebateScheduleLogic;
import com.stpl.app.global.rebateschedule.util.RsUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.GeneralCommonUtils;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.jboss.logging.Logger;

/**
 *
 * @author sooriya.lakshmanan
 */
public class RSFilterGenerate implements ExtFilterGenerator {

    private final CommonUtil commonUtil = CommonUtil.getInstance();    
    
    private static final Logger LOGGER = Logger.getLogger(RSFilterGenerate.class);
    
    RebateScheduleLogic rebateLogic = new RebateScheduleLogic();
    
    public RSFilterGenerate() {
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {        
        return null;
    }
    
    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        try
        {
            if (propertyId.toString().equals("createdBy") || propertyId.toString().equals("modifiedBy")) {
                if (originatingField.getValue() != null) {
                    return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
                }
                 else {
                    return null;
                }
            } else if (originatingField instanceof ComboBox) {
                if (originatingField.getValue() != null) {
                    return new SimpleStringFilter(propertyId, String.valueOf(((HelperDTO) originatingField.getValue()).getId()), false, false);
                } else {
                    return null;
                }
            }
   
        }
        catch(Exception e){
            LOGGER.error(e);
        }
        return null;
    }
    
    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {

        try {
            final ComboBox comboBox = new ComboBox();
           Map<Integer, String> userMap = StplSecurity.getUserName();
            switch (propertyId.toString()) {
                case "rebateScheduleStatus":
                    commonUtil.loadComboBox(comboBox, UIUtils.STATUS, true);
                    return comboBox;
                case "attachedStatus":
                    commonUtil.loadComboBox(comboBox, UIUtils.STATUS, true);
                    return comboBox;
                case "rebateScheduleType":
                    commonUtil.loadComboBox(comboBox, com.stpl.app.util.GeneralCommonUtils.REBATE_SCHEDULE_TYPE, true);
                    return comboBox;
                case "rebateProgramType":
                    commonUtil.loadComboBox(comboBox, com.stpl.app.util.GeneralCommonUtils.REBATE_PROGRAM_TYPE, true);
                    return comboBox;
                case "rsProgramType":
                    commonUtil.loadComboBox(comboBox, com.stpl.app.util.GeneralCommonUtils.REBATE_PROGRAM_TYPE, true);
                    return comboBox;
                case "rsCategory":
                    commonUtil.loadComboBox(comboBox, com.stpl.app.util.GeneralCommonUtils.REBATE_SCHEDULE_CATEGORY, true);
                    return comboBox;
                case "rsDesignation":
                    commonUtil.loadComboBox(comboBox, com.stpl.app.util.GeneralCommonUtils.REBATE_SCHEDULE_DESIGNATION, true);
                    return comboBox;
                case "ifpType":
                    commonUtil.loadComboBox(comboBox, GeneralCommonUtils.IFP_TYPE, true);
                    return comboBox;
                case "ifpStatus":
                    commonUtil.loadComboBox(comboBox, "STATUS", true);
                    return comboBox;
                case "ifpCategory":
                    commonUtil.loadComboBox(comboBox, "IFP_CATEGORY", true);
                    return comboBox;
                case "rsTradeClass":
                    commonUtil.loadComboBox(comboBox, GeneralCommonUtils.TRADE_CLASS, true);
                    return comboBox;
                case "calendar":
                    commonUtil.loadComboBox(comboBox, GeneralCommonUtils.CALENDAR, true);
                    return comboBox;
                case "paymentTerms":
                    commonUtil.loadComboBox(comboBox, GeneralCommonUtils.PAYMENT_TERMS, true);
                    return comboBox;
                case "paymentMethod":
                    commonUtil.loadComboBox(comboBox, GeneralCommonUtils.PAYMENT_METHOD, true);
                    return comboBox;
                case "paymentFrequency":
                    commonUtil.loadComboBox(comboBox, GeneralCommonUtils.PAYMENT_FREQUENCY, true);
                    return comboBox;
                case "calculationType":
                    commonUtil.loadComboBox(comboBox, GeneralCommonUtils.CALCULATION_TYPE, true);
                    return comboBox;
                case "calculationLevel":
                    commonUtil.loadComboBox(comboBox, GeneralCommonUtils.CALCULATION_LEVEL, true);
                    return comboBox;
                case "rebateFrequency":
                    commonUtil.loadComboBox(comboBox, GeneralCommonUtils.REBATE_FREQUENCY, true);
                    return comboBox;
                case "interestBearingIndicator":
                    commonUtil.loadComboBox(comboBox, GeneralCommonUtils.INTEREST_BEARING_INDICATOR, true);
                    return comboBox;
                case "rebateRuleType":
                    commonUtil.loadComboBox(comboBox, GeneralCommonUtils.REBATE_RULE_TYPE, true);
                    return comboBox;
                case "interestBearingBasis":
                    commonUtil.loadComboBox(comboBox, GeneralCommonUtils.INTEREST_BEARING_BASIS, true);
                    return comboBox;
                case "udc1":
                    commonUtil.loadComboBox(comboBox, GeneralCommonUtils.UDC1, true);
                    return comboBox;
                case "udc2":
                    commonUtil.loadComboBox(comboBox, GeneralCommonUtils.UDC2, true);
                    return comboBox;
                case "udc3":
                    commonUtil.loadComboBox(comboBox, GeneralCommonUtils.UDC3, true);
                    return comboBox;
                case "udc4":
                    commonUtil.loadComboBox(comboBox, GeneralCommonUtils.UDC4, true);
                    return comboBox;
                case "udc5":
                    commonUtil.loadComboBox(comboBox, GeneralCommonUtils.UDC5, true);
                    return comboBox;
                case "udc6":
                    commonUtil.loadComboBox(comboBox, GeneralCommonUtils.UDC6, true);
                    return comboBox;
                case "rebatePlanType":
                    commonUtil.loadComboBox(comboBox, "REBATE_PLAN_TYPE", true);
                    return comboBox;
                case "rebatePlanStatus":
                    commonUtil.loadComboBox(comboBox, UIUtils.STATUS, true);
                    return comboBox;
                case "netSalesFormulaType":
                    commonUtil.loadComboBox(comboBox, UIUtils.NS_FORMULA_TYPE, true);
                    return comboBox;
                case "deductionCategory":
                    commonUtil.loadComboBox(comboBox,  com.stpl.app.global.abstractsearch.util.UIUtils.DEDUCTION_CALENDAR_CATEGORY, true);
                    return comboBox;
                case "rebateBasedOn":
                    commonUtil.loadComboBox(comboBox,GeneralCommonUtils.REBATE_BASED_ON, true);
                    return comboBox;
                case "rangeBasedOn":
                    commonUtil.loadComboBox(comboBox,GeneralCommonUtils.REBATE_RANGE_BASED_ON, true);
                    return comboBox;
                case "rebateStructure":
                    commonUtil.loadComboBox(comboBox,GeneralCommonUtils.REBATE_STRUCTURE, true);
                    return comboBox;
                case "formulaType":
                      commonUtil.loadComboBox(comboBox,  RsUtils.REBATE_PLAN_FORMULA_TYPE, true);
                    return comboBox;
                case "createdBy":
                    comboBox.addItem(0);
                    comboBox.setItemCaption(0, ConstantsUtils.SHOW_ALL);
                    for (Map.Entry<Integer, String> entry : userMap.entrySet()) {
                        comboBox.addItem(entry.getKey());
                        comboBox.setItemCaption(entry.getKey(), entry.getValue());
                    }
                    comboBox.setNullSelectionAllowed(true);
                    comboBox.setNullSelectionItemId(0);
                    return comboBox;
                case "modifiedBy":
                    comboBox.addItem(0);
                    comboBox.setItemCaption(0, ConstantsUtils.SHOW_ALL);
                    for (Map.Entry<Integer, String> entry : userMap.entrySet()) {
                        comboBox.addItem(entry.getKey());
                        comboBox.setItemCaption(entry.getKey(), entry.getValue());
                    }
                    comboBox.setNullSelectionAllowed(true);
                    comboBox.setNullSelectionItemId(0);
                    return comboBox;
                default:
                    return null;
            }
        } catch (Exception ex) {
            LOGGER.error(ex);;            
        }
        return null;
    }
    
    @Override
    public void filterRemoved(Object propertyId) {
        // empty
    }
    
    @Override
    public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
        // empty
    }
    
    @Override
    public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
        return null;
    }
    
}
