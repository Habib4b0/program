/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.transaction.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.transaction.bean.GtnUIFrameworkTransactionComponentTypeListBean;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.formatter.GtnWsFormatter;
import com.stpl.gtn.gtn2o.ws.transaction.constants.GtnWsTransactionConstants;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author deepika.krishnakumar
 */
public class GtnUIFrameWorkTransactionTableColumnFormatAction
        implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        return;
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        try {
            List<Object> paramList = gtnUIFrameWorkActionConfig.getActionParameterList();
            GtnUIFrameworkBaseComponent baseTableComponent = GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent((String) paramList.get(1));
            GtnUIFrameworkComponentData tableComponent = baseTableComponent.getComponentData();

            GtnUIFrameworkTransactionComponentTypeListBean componentTypeBean = (GtnUIFrameworkTransactionComponentTypeListBean) paramList
                    .get(3);
            for (GtnWsRecordBean recordBean : tableComponent.getDataTableRecordList()) {
                manageValidTableRecordType(recordBean, baseTableComponent, componentTypeBean);
            }
        } catch (GtnFrameworkValidationFailedException e) {
            throw new GtnFrameworkGeneralException("Error in doAction", e);
        }

    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

    private void manageValidTableRecordType(GtnWsRecordBean record, GtnUIFrameworkBaseComponent tableBaseComponent,
            GtnUIFrameworkTransactionComponentTypeListBean componentBean) throws GtnFrameworkGeneralException {
        
        try {
            List<Object> recordHeaderList = tableBaseComponent.getTableRecordHeader();
            for (int i = 0; i < recordHeaderList.size(); i++) {
                Object recordPropertyId = recordHeaderList.get(i);
                Object propertyValue = record.getPropertyValueByIndex(i);
                Class<?> dataType = tableBaseComponent.getTableColumnProperty(recordPropertyId.toString());
                propertyValue = GtnUIFrameworkGlobalUI.getConvertedPropertyValue(dataType, propertyValue);
                propertyValue = setFormatter(recordPropertyId, propertyValue, componentBean);
                GtnWsRecordBean.addProperties(i, propertyValue, record.getProperties());
            }

            if (recordHeaderList.get(6).equals("itemPrice")) {
                Object recordPropertyValueAMP = record.getPropertyValueByIndex(4);
                Object value = record.getPropertyValueByIndex(6);
                if (value == null || String.valueOf(value).isEmpty()) {
                    return;
                }
                if ("AMP".equals(recordPropertyValueAMP) || "BP".equals(recordPropertyValueAMP)) {
                    value = "$" + new BigDecimal(String.valueOf(value)).setScale(6, BigDecimal.ROUND_DOWN).toString();
                } else if ("CPIURA".equals(recordPropertyValueAMP) || "CPI (Alt) URA".equals(recordPropertyValueAMP)) {
                    value = "$" + new BigDecimal(String.valueOf(value)).setScale(3, BigDecimal.ROUND_DOWN).toString();
                } else if ("URA".equals(recordPropertyValueAMP)) {
                    value = "$" + new BigDecimal(String.valueOf(value)).setScale(4, BigDecimal.ROUND_DOWN).toString();
                } else {
                    value = "$" + Double.parseDouble(String.valueOf(value));
                }
                GtnWsRecordBean.addProperties(6, value, record.getProperties());
            }

        } catch (Exception e) {
            throw new GtnFrameworkGeneralException("Error in doAction", e);
        }
    }

    private Object setFormatter(Object propertyId, Object value,
            GtnUIFrameworkTransactionComponentTypeListBean componentBean) {

        if (componentBean.getFormatterMap() != null && !componentBean.getFormatterMap().isEmpty()) {
            String formatDecimalPattern = componentBean.getFormatterMap(propertyId.toString());
            if (formatDecimalPattern != null) {
                DecimalFormat decimalFormatter = GtnWsFormatter.DECIMAL_FORMATTER.getFormatter();
                decimalFormatter.applyPattern(formatDecimalPattern);
                return formatPercentageValue(decimalFormatter, value);
            }

        }
        return value;
    }

    private Object formatPercentageValue(DecimalFormat decimalFormat, Object value) {
        String formatPattern = decimalFormat.toPattern();
        Object newObjectValue = checkEmptyRecordValue(value);
        if (formatPattern.contains(GtnWsTransactionConstants.PERCENTAGE)) {
            DecimalFormat newDecimalFormatPattern = GtnWsFormatter.DECIMAL_FORMATTER.getFormatter();
            newDecimalFormatPattern.applyPattern(formatPattern.replace(GtnWsTransactionConstants.PERCENTAGE,
                    GtnFrameworkCommonStringConstants.STRING_EMPTY));
            return newDecimalFormatPattern.format(Double.parseDouble(newObjectValue.toString()))
                    + GtnWsTransactionConstants.PERCENTAGE;
        }
        return decimalFormat.format(Double.parseDouble(newObjectValue.toString()));
    }

    private Object checkEmptyRecordValue(Object value) {
        if (GtnFrameworkCommonStringConstants.STRING_EMPTY.equals(value.toString())) {
            return "0";
        }
        return value;
    }

}
