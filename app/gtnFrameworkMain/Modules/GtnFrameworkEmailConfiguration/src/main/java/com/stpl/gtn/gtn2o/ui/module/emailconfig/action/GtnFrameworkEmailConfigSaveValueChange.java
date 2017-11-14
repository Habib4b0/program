package com.stpl.gtn.gtn2o.ui.module.emailconfig.action;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.emailconfig.bean.GtnWsEMailConfigurationBean;
import com.stpl.gtn.gtn2o.ws.emailconfig.constants.GtnWsEMailConfigurationConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.emailconfig.GtnWsMailConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkEmailConfigSaveValueChange
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		List<String> fieldId = gtnUIFrameWorkActionConfig.getFieldValues();
		processNameOnChange(parameters, componentId, fieldId);
	}

	public GtnWsEMailConfigurationBean getModifiedRequest(GtnWsEMailConfigurationBean configurationBean,
			GtnWsGeneralRequest generalRequest, final List<Object> parameters) {
		generalRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		String emailNotificationTabProcessName = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(1).toString()).getStringFromField();

		configurationBean.setEmailNotificationTabProcessName(emailNotificationTabProcessName);

		return configurationBean;
	}

	private void processNameOnChange(final List<Object> parameters, String componentId, List<String> fieldId) {
		final GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		final GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		GtnWsMailConfigurationRequest mcRequest = new GtnWsMailConfigurationRequest();
		GtnWsEMailConfigurationBean configurationBean = new GtnWsEMailConfigurationBean();
		getModifiedRequest(configurationBean, generalRequest, parameters);
		mcRequest.setConfigurationBean(configurationBean);
		request.setGtnWsGeneralRequest(generalRequest);
		request.setMailConfigurationRequest(mcRequest);
		if (!configurationBean.getEmailNotificationTabProcessName().isEmpty()) {
			GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
					GtnWsEMailConfigurationConstants.MAIL_CONFIG_SAVE_ACTION_SAVE
							+ GtnWsEMailConfigurationConstants.MAIL_CONFIG_COMBOBOX_ONCHANGE,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			GtnWsEMailConfigurationBean bean = response.getGtnWsMailConfigurationResponse().geteMailConfigurationBean();
			List<Object[]> defaultValue = bean.getComboboxOnChangeDataLoad();

			if (!defaultValue.isEmpty()) {
				Object[] valueList = defaultValue.get(0);
				List<String> fieldList = Arrays.asList("addEmailTo", "addSubject", "addEmailCc", "addEmailBody",
						"addFailureTo", "addSubject1", "addEmailCc1", "addEmailBody1");
				for (int i = 0; i < fieldList.size(); i++) {
					if (fieldList.get(i).equalsIgnoreCase("addEmailTo")
							|| fieldList.get(i).equalsIgnoreCase("addEmailCc")
							|| fieldList.get(i).equalsIgnoreCase("addFailureTo")
							|| fieldList.get(i).equalsIgnoreCase("addEmailCc1")) {
						GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldList.get(i), componentId)
								.loadFieldValue(replaceComma(valueList[i + 1]));
					} else {
						GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldList.get(i), componentId)
								.loadFieldValue(valueList[i + 1]);

					}
				}
			}
		} else {
			Iterator itr = fieldId.iterator();
			while (itr.hasNext())
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(itr.next().toString()).loadFieldValue("");
		}
	}

	public String replaceComma(Object emailIds) {
		String replaceComma;
		replaceComma = (emailIds == null || emailIds.equals("") || emailIds.equals("null")) ? ""
				: emailIds.toString().replace(" ", ",");
		return replaceComma;
	}

	public GtnFrameworkEmailConfigSaveValueChange createInstance() {
		return new GtnFrameworkEmailConfigSaveValueChange();
	}

}
