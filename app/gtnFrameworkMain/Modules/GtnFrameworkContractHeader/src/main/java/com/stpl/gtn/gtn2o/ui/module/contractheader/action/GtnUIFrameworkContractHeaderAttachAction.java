package com.stpl.gtn.gtn2o.ui.module.contractheader.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.module.contractheader.constants.GtnUIFrameworkContractHeaderStringContants;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnUIFrameworkContractHeaderAttachAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkContractHeaderAttachAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			List<Object> attachParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
			String resultTableId = (String) attachParameterList.get(1);
			List<String> inputColumIdList = (List<String>) attachParameterList.get(3);
			List<String> outputColumIdList = (List<String>) attachParameterList.get(4);
			List<Class<?>> columTypeList = (List<Class<?>>) attachParameterList.get(5);
			GtnWsRecordBean dto = new GtnWsRecordBean();
			dto.setRecordHeader(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(resultTableId).getTableRecordHeader());
			GtnUIFrameworkComponentData idComponentData = (GtnUIFrameworkComponentData) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIAS_TRADING_PARTNER)
					.getComponent().getData();
			Integer tpId = 0;

			if (idComponentData != null && idComponentData.getCustomData() != null) {
				GtnWsRecordBean tpDto = (GtnWsRecordBean) idComponentData.getCustomData();
				tpId = Integer.parseInt(String.valueOf(tpDto.getPropertyValueByIndex(5)));
			}
			if (idComponentData != null) {
				idComponentData.setCustomData(null);
			}
			Integer contractType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractAliasContractAliasType")
					.getIntegerFromField();

			for (int i = 0; i < columTypeList.size(); i++) {
				GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(outputColumIdList.get(i));
				Object value;
				switch (columTypeList.get(i).getName()) {
				case "java.lang.String":
					value = getValue(gtnUIFrameworkBaseComponent);
					break;
				case "java.lang.Integer":
					value = gtnUIFrameworkBaseComponent.getIntegerFromField();
					break;
				case "java.util.Date":
					value = gtnUIFrameworkBaseComponent.getDateFromDateField();
					break;
				default:
					value = String.valueOf(gtnUIFrameworkBaseComponent.getStringFromField());
					break;
				}

				dto.addProperties(inputColumIdList.get(i), value);

				if (gtnUIFrameworkBaseComponent.getComponentConfig().getComponentType()
						.equals(GtnUIFrameworkComponentType.TEXTBOX)) {
					gtnUIFrameworkBaseComponent.setPropertyValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
				} else {
					gtnUIFrameworkBaseComponent.loadDateValue(null);
				}

			}
			dto.getProperties().add(contractType);
			dto.getProperties().add(tpId);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIAS_TRADING_PARTNER)
					.setComponentReadOnly(false);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIAS_TRADING_PARTNER)
					.loadDateValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIAS_TRADING_PARTNER)
					.setComponentReadOnly(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(resultTableId).addItemToDataTable(dto);
		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
		}
	}

	private Object getValue(GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent)
			throws GtnFrameworkValidationFailedException {
		Object value = null;
		if (gtnUIFrameworkBaseComponent.getComponentConfig().getComponentType()
				.equals(GtnUIFrameworkComponentType.COMBOBOX)) {
			value = gtnUIFrameworkBaseComponent.getCaptionFromComboBox();
		} else {
			value = String.valueOf(gtnUIFrameworkBaseComponent.getStringFromField());
		}
		return value;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
