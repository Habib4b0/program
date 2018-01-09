/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.contractdashboard.constants.GtnFrameworkContractDashboardContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author STPL
 */
public class GtnUIFrameWorkContractTableRecordTypeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameWorkContractTableRecordTypeAction.class);

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        // No Need to Implement. Its an unused method.
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        try {
            List<Object> params = gtnUIFrameWorkActionConfig.getActionParameterList();
            GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent((String) params.get(1));
            GtnUIFrameworkComponentData tableComponentData = tableBaseComponent.getComponentData();
            for (GtnWsRecordBean record : tableComponentData.getDataTableRecordList()) {
                manageTableRecordType(record, tableBaseComponent);
            }
        } catch (GtnFrameworkValidationFailedException e) {
            gtnLogger.error("Exception in GtnUIFrameWorkContractTableRecordTypeAction", e);
        }
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

    private void manageTableRecordType(GtnWsRecordBean record, GtnUIFrameworkBaseComponent tableBaseComponent)
            throws GtnFrameworkValidationFailedException {
        try {
            List<Object> recordHeader = tableBaseComponent.getTableRecordHeader();
            GtnUIFrameworkBaseComponent priceprotectionpricingTable = GtnUIFrameworkGlobalUI
                    .getVaadinBaseComponent(GtnFrameworkContractDashboardContants.PRICING_TAB_PRICING_TABLE);
            for (int i = 0; i < recordHeader.size(); i++) {
                Object propertyId = recordHeader.get(i);
                Object value = record.getPropertyValueByIndex(i);
                if (GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[8].equals(propertyId)) {
                    value = getFieldValue(record);
                }
                if ((GtnFrameworkContractDashboardContants.getPriceProtectionEditableColumn()[17].equals(propertyId)) && !priceprotectionpricingTable.getExtPagedTable().isReadOnly()) {
                    value = getFieldValuePriceTolerance(record);
                } else {
                    Class<?> type = tableBaseComponent.getTableColumnProperty(propertyId.toString());
                    value = GtnUIFrameworkGlobalUI.getConvertedPropertyValue(type, value);
                }
                GtnWsRecordBean.addProperties(i, value, record.getProperties());
            }
        } catch (GtnFrameworkValidationFailedException e) {
            gtnLogger.error("Exception in manageTableRecordType", e);
        }
    }

    private Object getFieldValue(GtnWsRecordBean contractTableRecordBean) {
        String depandingValue = contractTableRecordBean.getStringPropertyByIndex(51);
        if (depandingValue.startsWith("P")) {
            return contractTableRecordBean.getPropertyValueByIndex(52);
        }
        if (depandingValue.startsWith("D")) {
            Object value = contractTableRecordBean.getPropertyValueByIndex(53);
            if (value != null && Long.class.isAssignableFrom(value.getClass())) {
                value = new Date((Long) value);
            }
            return value;
        }
        if (depandingValue.startsWith("M")) {
            return contractTableRecordBean.getStringPropertyByIndex(54).trim();
        }
        return "";
    }

    private Object getFieldValuePriceTolerance(GtnWsRecordBean bean) {
        String depandingValue = bean.getStringPropertyByIndex(48);
        if (!(GtnFrameworkContractDashboardContants.STRINGUTILS_EMPTY.equals(depandingValue)) && (!(GtnFrameworkContractDashboardContants.STRINGUTILS_EMPTY.equals(bean.getPropertyValueByIndex(22)))) && (bean.getIndex(GtnFrameworkContractDashboardContants.PRICE_TOLERANCE) == 22)) {
            DecimalFormat formatDecimal = new DecimalFormat(GtnFrameworkContractDashboardContants.TWODECIMAL_ZERO);
            if (depandingValue.startsWith(GtnFrameworkContractDashboardContants.PER) || depandingValue.startsWith(GtnFrameworkContractDashboardContants.PERCENTAGE)) {
                return formatDecimal.format(bean.getDoublePropertyByIndex(22)) + GtnFrameworkContractDashboardContants.PERCENTAGE;
            }
            if (depandingValue.startsWith(GtnFrameworkContractDashboardContants.DOL)) {
                return GtnFrameworkContractDashboardContants.DOLLER + formatDecimal.format(bean.getDoublePropertyByIndex(22));

            }
        }
        return GtnFrameworkContractDashboardContants.STRINGUTILS_EMPTY;
    }
}
