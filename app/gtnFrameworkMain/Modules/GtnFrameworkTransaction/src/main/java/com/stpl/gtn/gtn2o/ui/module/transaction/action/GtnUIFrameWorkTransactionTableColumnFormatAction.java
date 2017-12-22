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
import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author deepika.krishnakumar
 */
public class GtnUIFrameWorkTransactionTableColumnFormatAction  implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

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
			GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent((String) paramList.get(1));
			GtnUIFrameworkComponentData tableComponent = tableBaseComponent.getComponentData();
	
			GtnUIFrameworkTransactionComponentTypeListBean componentTypeBean = (GtnUIFrameworkTransactionComponentTypeListBean) paramList
					.get(3);
			for (GtnWsRecordBean recordBean : tableComponent.getDataTableRecordList()) {
				manageTableRecordType(recordBean, tableBaseComponent,componentTypeBean);
			}
		} catch (GtnFrameworkValidationFailedException e) {
			throw new GtnFrameworkGeneralException("Error in doAction", e);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void manageTableRecordType(GtnWsRecordBean record, GtnUIFrameworkBaseComponent tableBaseComponent,
			GtnUIFrameworkTransactionComponentTypeListBean componentBean) throws GtnFrameworkGeneralException {
		try {
			List<Object> recordHeader = tableBaseComponent.getTableRecordHeader();
			for (int i = 0; i < recordHeader.size(); i++) {
				Object propertyId = recordHeader.get(i);
				Object value = record.getPropertyValueByIndex(i);
				Class<?> type = tableBaseComponent.getTableColumnProperty(propertyId.toString());
				value = GtnUIFrameworkGlobalUI.getConvertedPropertyValue(type, value);
				value = setFormatter(propertyId, value, componentBean);
				GtnWsRecordBean.addProperties(i, value, record.getProperties());

			}
			
			
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Error in doAction", e);
		}
	}

	private Object setFormatter(Object propertyId, Object value,
			GtnUIFrameworkTransactionComponentTypeListBean componentBean) {
            
		String decimalFormatPattern = componentBean.getFormatterMap(propertyId.toString());
		if (decimalFormatPattern != null) {
			DecimalFormat decimalFormat = GtnWsFormatter.DECIMAL_FORMATTER.getFormatter();
			 decimalFormat.applyPattern(decimalFormatPattern);
			return formatPercentValue(decimalFormat, value);
		}
                    return value; 
                
	}

	private Object formatPercentValue(DecimalFormat decimalFormat, Object value) {
		String formatterPatter = decimalFormat.toPattern();
		Object newValue = checkEmptyValue(value);
		if (formatterPatter.contains(GtnWsTransactionConstants.PERCENTAGE)) {
			DecimalFormat newDecimalFormat = GtnWsFormatter.DECIMAL_FORMATTER.getFormatter();
			newDecimalFormat.applyPattern(formatterPatter.replace(GtnWsTransactionConstants.PERCENTAGE, GtnFrameworkCommonStringConstants.STRING_EMPTY));
			return newDecimalFormat.format(Double.parseDouble(newValue.toString())) + GtnWsTransactionConstants.PERCENTAGE;
		}
		return decimalFormat.format(Double.parseDouble(newValue.toString()));
	}
	
	private Object checkEmptyValue(Object value) {
		if(GtnFrameworkCommonStringConstants.STRING_EMPTY.equals(value.toString())) {
			return "0";
		}
		return value;
	}

}
