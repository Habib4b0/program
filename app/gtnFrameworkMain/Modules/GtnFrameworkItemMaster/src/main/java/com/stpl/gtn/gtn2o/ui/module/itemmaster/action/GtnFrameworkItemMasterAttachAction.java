package com.stpl.gtn.gtn2o.ui.module.itemmaster.action;

import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkItemMasterAttachAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkItemMasterAttachAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("In GtnFrameworkItemMasterAttachAction");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> attachParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String resultTableId = (String) attachParameterList.get(1);
		String idComponent = (String) attachParameterList.get(2);
		List<String> inputColumIdList = (List<String>) attachParameterList.get(3);
		List<String> outputColumIdList = (List<String>) attachParameterList.get(4);
		List<Class<?>> columTypeList = (List<Class<?>>) attachParameterList.get(5);
		List<String> extraColumList = (List<String>) attachParameterList.get(7);
		try {
			GtnWsRecordBean dto = new GtnWsRecordBean();
			dto.setRecordHeader(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(resultTableId).getTableRecordHeader());
			Integer itemQulaifierSid = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(String.valueOf(attachParameterList.get(6))).getIntegerFromField();
			for (int i = 0; i < columTypeList.size(); i++) {
				GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(outputColumIdList.get(i));
				Object value;
				switch (columTypeList.get(i).getName()) {
				case "java.lang.String":
					value = getValue(baseComponent);
					break;
				case "java.lang.Integer":
					value = baseComponent.getValueFromComponent();
					break;
				case "java.util.Date":
					value = baseComponent.getValueFromComponent();
					break;
				default:
					value = baseComponent.getStringFromField();
					break;
				}

				dto.addProperties(inputColumIdList.get(i), value);

				if (baseComponent.getComponentConfig().getComponentType().equals(GtnUIFrameworkComponentType.TEXTBOX)
						|| baseComponent.getComponentConfig().getComponentType()
								.equals(GtnUIFrameworkComponentType.POPUPTEXTFIELD)) {
					boolean isReadOnly = baseComponent.isReadOnly();
					baseComponent.setComponentReadOnly(false);
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent(outputColumIdList.get(i), componentId)
							.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
					baseComponent.setComponentReadOnly(isReadOnly);
				} else {
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent(outputColumIdList.get(i), componentId)
							.loadDateValue(null);
				}

			}
			dto.addProperties("createdBy", getCurrentUserName());
			dto.addProperties("createdDate", new Date());
			dto.addProperties("modifiedBy", GtnFrameworkCommonStringConstants.STRING_EMPTY);
			dto.addProperties("modifiedDate", null);
			dto.addProperties("source", "GTN");
			for (String columnName : extraColumList) {
				dto.addProperties(columnName, "");
			}
			dto.getProperties().add(itemQulaifierSid);
			if (idComponent != null && GtnUIFrameworkGlobalUI.getVaadinBaseComponent(idComponent)
					.getData() instanceof GtnWsRecordBean) {
				GtnWsRecordBean parentDto = (GtnWsRecordBean) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(idComponent)
						.getData();
				dto.getProperties().add(parentDto.getProperties().get(parentDto.getProperties().size() - 1));
			}
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(resultTableId).addItemToDataTable(dto);
		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
		}
	}

	private String getCurrentUserName() {
		String userId = GtnUIFrameworkGlobalUI.getCurrentUser();
		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setUserId(userId);
		generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		gtnRequest.setGtnWsGeneralRequest(generalWSRequest);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE_USER_NAME,
				gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		String userName = response.getGtnWsGeneralResponse().getUserName();
		GtnUIFrameworkGlobalUI.addSessionProperty("userName", userName);
		return String.valueOf(userName);
	}

	private Object getValue(GtnUIFrameworkBaseComponent baseComponent) throws GtnFrameworkValidationFailedException {
		Object value;
		if (baseComponent.getComponentConfig().getComponentType().equals(GtnUIFrameworkComponentType.COMBOBOX)) {
			value = baseComponent.getCaptionFromComboBox();
		} else {
			value = baseComponent.getStringFromField();
		}
		return value;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
