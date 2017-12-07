package com.stpl.gtn.gtn2o.ui.module.priceschedule.action;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkModeType;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.constants.GtnFrameworkPSConstants;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnUIFrameWorkTableRecordTypeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameWorkTableRecordTypeAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			List<Object> actionParams = gtnUIFrameWorkActionConfig.getActionParameterList();
			GtnUIFrameworkBaseComponent psPpTableBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent((String) actionParams.get(1));

			GtnUIFrameworkComponentData psPpTableComponentData = psPpTableBaseComponent.getComponentData();
			for (GtnWsRecordBean record : psPpTableComponentData.getDataTableRecordList()) {
				managePpTableRecordType(record, psPpTableBaseComponent);
			}
		} catch (GtnFrameworkValidationFailedException e) {
			gtnLogger.error("Exception in GtnUIFrameWorkContractTableRecordTypeAction", e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void managePpTableRecordType(GtnWsRecordBean record, GtnUIFrameworkBaseComponent tableBaseComponent)
			throws GtnFrameworkValidationFailedException {
		try {
			List<Object> psPriceProtectionRecordHeader = tableBaseComponent.getTableRecordHeader();
			for (int i = 0; i < psPriceProtectionRecordHeader.size(); i++) {
				Object psPriceProtectionPropertyId = psPriceProtectionRecordHeader.get(i);
				Object psPriceProtectionValue = record.getPropertyValueByIndex(i);
				if (GtnFrameworkPSConstants.getPriceProtectionEditableList().toArray()[12]
						.equals(psPriceProtectionPropertyId)) {
					psPriceProtectionValue = getFieldValue(record);
				}
				if (GtnFrameworkPSConstants.getPriceProtectionEditableList().toArray()[21]
						.equals(psPriceProtectionPropertyId)) {
					psPriceProtectionValue = getFieldValuePriceTolerance(record);
				} else {
					Class<?> type = tableBaseComponent.getTableColumnProperty(psPriceProtectionPropertyId.toString());
					psPriceProtectionValue = GtnUIFrameworkGlobalUI.getConvertedPropertyValue(type,
							psPriceProtectionValue);
				}
				GtnWsRecordBean.addProperties(i, psPriceProtectionValue, record.getProperties());
			}
		} catch (GtnFrameworkValidationFailedException e) {
			gtnLogger.error("Exception in manageTableRecordType", e);
		}
	}

	private Object getFieldValue(GtnWsRecordBean bean) {
		int size = bean.getProperties().size();
		String depandingValue = bean.getStringPropertyByIndex(size - 2);
		if (depandingValue.startsWith("P")) {
			return bean.getPropertyValueByIndex(size - 3);
		}
		if (depandingValue.startsWith("D")) {
			Object value = bean.getPropertyValueByIndex(size - 4);
			Object mode = GtnUIFrameworkGlobalUI.getSessionProperty("mode");
			if (value != null && Long.class.isAssignableFrom(value.getClass())) {
				if (mode != null && mode == GtnUIFrameworkModeType.VIEW) {
					DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
					value = formatter.format(new Date((Long) value));
				} else {
					value = new Date((Long) value);
				}
			}
			return value;
		}
		if (depandingValue.startsWith("M")) {
			DecimalFormat formatDecimal = new DecimalFormat("0.00");
			return formatDecimal.format(bean.getDoublePropertyByIndex(size - 26));
		}
		return "";
	}

	private Object getFieldValuePriceTolerance(GtnWsRecordBean bean) {
		int size = bean.getProperties().size();
		String depandingValue = (bean.getStringPropertyByIndex(size - 5));
		if (!(depandingValue.startsWith("-Sel")) && (!(("").equals(bean.getStringPropertyByIndex(size - 18))))&&(bean.getIndex("psDetailsPriceTol") == size - 18)) {

			DecimalFormat formatDecimal = new DecimalFormat("0.00");
			if (depandingValue.startsWith("per") || depandingValue.startsWith("%")) {
				return formatDecimal.format(bean.getDoublePropertyByIndex(size - 18)) + "%";
			}
			if (depandingValue.startsWith("dol")) {
				return "$" + formatDecimal.format(bean.getDoublePropertyByIndex(size - 18));
			}

		}

		return "";
	}
}
