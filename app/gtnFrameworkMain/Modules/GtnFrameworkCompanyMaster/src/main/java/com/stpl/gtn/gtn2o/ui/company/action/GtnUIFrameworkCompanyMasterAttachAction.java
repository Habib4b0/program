package com.stpl.gtn.gtn2o.ui.company.action;

import java.util.Date;
import java.util.List;

import org.asi.container.ExtContainer;

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
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnUIFrameworkCompanyMasterAttachAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkCompanyMasterAttachAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("In GtnFrameworkCompanyMasterAttachAction");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> attachParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String resultTableId = (String) attachParameterList.get(1);
		String idComponent = (String) attachParameterList.get(GtnWsNumericConstants.TWO);
		List<String> inputColumIdList = (List<String>) attachParameterList.get(GtnWsNumericConstants.THREE);
		List<String> outputColumIdList = (List<String>) attachParameterList.get(GtnWsNumericConstants.FOUR);
		List<Class<?>> columTypeList = (List<Class<?>>) attachParameterList.get(GtnWsNumericConstants.FIVE);

		try {
			GtnWsRecordBean dto = new GtnWsRecordBean();
			ExtContainer<GtnWsRecordBean> container = (ExtContainer<GtnWsRecordBean>) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(resultTableId).getExtCustomTable().getContainerDataSource();
			dto.setRecordHeader(container.getRecordHeader());

			Integer systemId = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(String.valueOf(attachParameterList.get(GtnWsNumericConstants.SIX)))
					.getIntegerFromField();
			addPropertiesInBean(componentId, inputColumIdList, outputColumIdList, columTypeList, dto);
			dto.addProperties("createdBy", getCurrentUserName());
			dto.addProperties("createdDate", new Date());
			dto.addProperties("modifiedBy", GtnFrameworkCommonStringConstants.STRING_EMPTY);
			dto.addProperties("modifiedDate", null);
			if (String.valueOf(attachParameterList.get(GtnWsNumericConstants.SEVEN)).equals("IdentifierAttach")) {
				dto.getProperties().add(systemId);
				dto.getProperties().add(GtnFrameworkCommonStringConstants.STRING_EMPTY);
				dto.getProperties().add(Integer.valueOf(GtnUIFrameworkGlobalUI.getCurrentUser()));
			} else {
				dto.addProperties("source", "GTN");
				dto.getProperties().add(Integer.valueOf(GtnUIFrameworkGlobalUI.getCurrentUser()));
				dto.getProperties().add(systemId);
			}

			if (idComponent != null && GtnUIFrameworkGlobalUI.getVaadinBaseComponent(idComponent)
					.getData() instanceof GtnWsRecordBean) {
				GtnWsRecordBean parentDto = (GtnWsRecordBean) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(idComponent)
						.getData();
				dto.getProperties().add(parentDto.getProperties().get(parentDto.getProperties().size() - 1));
			}
			container.addBean(dto);
		} catch (

		Exception e) {
			gtnLogger.error(e.getMessage(), e);
		}
	}

	/**
	 * @param componentId
	 * @param inputColumIdList
	 * @param outputColumIdList
	 * @param columTypeList
	 * @param gtnWsRecordBean
	 * @throws GtnFrameworkValidationFailedException
	 */
	private void addPropertiesInBean(String componentId, List<String> inputColumIdList, List<String> outputColumIdList,
			List<Class<?>> columTypeList, GtnWsRecordBean gtnWsRecordBean)
			throws GtnFrameworkValidationFailedException {
		for (int i = 0; i < columTypeList.size(); i++) {
			GtnUIFrameworkBaseComponent vaadinField = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(outputColumIdList.get(i));

			Object componentValue;
			switch (columTypeList.get(i).getName()) {
			case "java.lang.String":
				componentValue = getFieldValue(vaadinField);
				break;
			case "java.lang.Integer":
				componentValue = vaadinField.getValueFromComponent();
				break;
			case "java.util.Date":
				componentValue = vaadinField.getValueFromComponent();
				break;
			default:
				componentValue = vaadinField.getStringFromField();
				break;
			}

			gtnWsRecordBean.addProperties(inputColumIdList.get(i), componentValue);

			if (vaadinField.getComponentConfig().getComponentType().equals(GtnUIFrameworkComponentType.TEXTBOX)) {
				boolean isReadOnly = vaadinField.isReadOnly();
				vaadinField.setComponentReadOnly(false);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(outputColumIdList.get(i), componentId)
						.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
				vaadinField.setComponentReadOnly(isReadOnly);
			} else {
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(outputColumIdList.get(i), componentId)
						.loadDateValue(null);
			}

		}
	}

	private String getCurrentUserName() {
		String cmUserId = GtnUIFrameworkGlobalUI.getCurrentUser();
		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setUserId(cmUserId);
		generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		gtnRequest.setGtnWsGeneralRequest(generalWSRequest);
		GtnUIFrameworkWebserviceResponse userNameResponse = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE_USER_NAME,
				gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		String cmUserName = userNameResponse.getGtnWsGeneralResponse().getUserName();
		GtnUIFrameworkGlobalUI.addSessionProperty("userName", cmUserName);
		return String.valueOf(cmUserName);
	}

	private Object getFieldValue(GtnUIFrameworkBaseComponent vaadinField) throws GtnFrameworkValidationFailedException {
		Object value;
		if (vaadinField.getComponentConfig().getComponentType().equals(GtnUIFrameworkComponentType.COMBOBOX)) {
			value = vaadinField.getCaptionFromComboBox();
		} else {
			value = vaadinField.getStringFromField();
		}
		return value;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
