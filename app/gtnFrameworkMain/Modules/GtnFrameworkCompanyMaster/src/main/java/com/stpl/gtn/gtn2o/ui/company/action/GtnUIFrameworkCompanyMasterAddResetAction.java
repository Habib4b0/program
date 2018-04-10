/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.company.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.NotesDTO;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyUdcInfoBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterInformationBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.Component;

public class GtnUIFrameworkCompanyMasterAddResetAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkCompanyMasterAddResetAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("Entering inside configureParams");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			GtnUIFrameworkGlobalUI.addSessionProperty("companyMasterSid", 0);
			GtnCMasterInformationBean companyInformation = new GtnCMasterInformationBean();

			configureParams(gtnUIFrameWorkActionConfig);
			loadCMInfoFieldValues(companyInformation, componentId);
			loadCMAddressValues(companyInformation, componentId);

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

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCompanyStringContants.COMPANY_INFO_SYSTEM_ID, componentId)
					.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCompanyStringContants.COMPANY_INFO_CREATED_BY, componentId)
					.loadFieldValue(userName);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCreatedByID", componentId)
					.loadFieldValue(userId);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCompanyStringContants.COMPANY_INFO_CREATED_DATE, componentId)
					.loadDateValue(new Date());
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCompanyStringContants.COMPANY_INFO_CREATED_BY, componentId)
					.setComponentEnable(false);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCompanyStringContants.COMPANY_INFO_CREATED_DATE, componentId)
					.setComponentEnable(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabModifiedBy", componentId)
					.setComponentEnable(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationModifiedDate", componentId)
					.setComponentEnable(false);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCompanyStringContants.COMPANY_INFO_SYSTEM_ID, componentId)
					.setComponentEnable(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabSource", componentId)
					.setComponentEnable(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyMasterAddDeleteButton", componentId)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("userId", componentId).loadFieldValue(userId);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("SessionId", componentId)
					.loadFieldValue(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("tabSheet", componentId)
					.setTabComponentVisible(GtnWsNumericConstants.FIVE, false);

			GtnUIFrameworkBaseComponent parentAttachTableComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCompanyStringContants.PARENT_ATTACH_RESULT_TABLE, componentId);
			GtnUIFrameworkBaseComponent tradeClassAttachTableComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
					GtnFrameworkCompanyStringContants.TRADE_CLASS_ATTACH_RESULT_TABLE, componentId);
			GtnUIFrameworkBaseComponent identifierAttachTableComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
					GtnFrameworkCompanyStringContants.IDENTIFIER_ATTACH_RESULT_TABLE, componentId);

			parentAttachTableComponent.removeAllGridItems();
			parentAttachTableComponent.setTableFieldEditable(true);

			tradeClassAttachTableComponent.removeAllGridItems();
			tradeClassAttachTableComponent.setTableFieldEditable(true);

			identifierAttachTableComponent.removeAllGridItems();
			handleIdentifierTableColumn(identifierAttachTableComponent);
			identifierAttachTableComponent.setTableFieldEditable(true);

			List<Object> result = new ArrayList<>();
			result.add(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			result.add(new ArrayList<NotesDTO>());

			GtnUIFrameworkBaseComponent notesTab = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("notesTab");
			notesTab.setNotesTabValue(result);

			Component saveButton = GtnUIFrameworkGlobalUI.getVaadinComponent("companyMasterAddSaveButton", componentId);
			saveButton.setCaption("SAVE");
		} catch (Exception ex) {
			gtnLogger.error(ex.getMessage(), ex);
			throw new GtnFrameworkGeneralException(ex);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameworkCompanyMasterAddResetAction();
	}

	private void handleIdentifierTableColumn(GtnUIFrameworkBaseComponent resultTableidentifier) {
		resultTableidentifier.setTableColumns(GtnFrameworkCompanyStringContants.getIdentifierTabTableColumnList());
		resultTableidentifier
				.setTableColumnHeaders(GtnFrameworkCompanyStringContants.getIdentifierTabTableHeaderList());
		resultTableidentifier.resetFilter();
	}

	public void loadCMInfoFieldValues(GtnCMasterInformationBean companyInformation, String componentId) {

		GtnCMasterCompanyUdcInfoBean udcInfo = new GtnCMasterCompanyUdcInfoBean();
		Object[] textFieldValues = new Object[] { companyInformation.getCompanyId(), companyInformation.getCompanyNo(),
				companyInformation.getCompanyName(), companyInformation.getCompanyId(),
				companyInformation.getCompanyNo(), companyInformation.getCompanyName(), companyInformation.getSource(),
				companyInformation.getFinancialSystem(), companyInformation.getCompanyMasterSystemId(),
				companyInformation.getCreatedBy(), companyInformation.getModifiedBy() };
		loadFieldValue(GtnFrameworkCompanyStringContants.getTextFieldComponentIds(), textFieldValues, componentId);
		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCompanyStringContants.COMPANY_INFO_CREATED_DATE, componentId)
				.loadDateValue(companyInformation.getCreatedDate());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationModifiedDate", componentId)
				.loadDateValue(companyInformation.getModifiedDate());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyStatus", componentId)
				.loadComboBoxComponentValue(companyInformation.getCompanyStatus());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationCompanyStartDate", componentId)
				.loadDateValue(companyInformation.getCompanyStartDate());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationCompanyEndDate", componentId)
				.loadDateValue(companyInformation.getCompanyEndDate());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyCategory", componentId)
				.loadComboBoxComponentValue(companyInformation.getCompanyCategory());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyGroup", componentId)
				.loadComboBoxComponentValue(companyInformation.getCompanyGroup());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabCompanyType", componentId)
				.loadComboBoxComponentValue(companyInformation.getCompanyType());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabOrganizationKey", componentId)
				.loadComboBoxComponentValue(companyInformation.getOrganizationKey());

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabUdc1", componentId)
				.loadComboBoxComponentValue(udcInfo.getUdc1() != null ? udcInfo.getUdc1() : 0);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabUdc2", componentId)
				.loadComboBoxComponentValue(udcInfo.getUdc2() != null ? udcInfo.getUdc2() : 0);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabUdc3", componentId)
				.loadComboBoxComponentValue(udcInfo.getUdc3() != null ? udcInfo.getUdc3() : 0);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabUdc4", componentId)
				.loadComboBoxComponentValue(udcInfo.getUdc4() != null ? udcInfo.getUdc4() : 0);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabUdc5", componentId)
				.loadComboBoxComponentValue(udcInfo.getUdc5() != null ? udcInfo.getUdc5() : 0);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyInformationTabUdc6", componentId)
				.loadComboBoxComponentValue(udcInfo.getUdc6() != null ? udcInfo.getUdc6() : 0);

	}

	void loadFieldValue(String[] componentIds, Object[] values, String parentComponetId) {
		for (int i = 0; i < componentIds.length; i++) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentIds[i], parentComponetId).loadFieldValue(values[i]);
		}

	}

	public void loadCMAddressValues(GtnCMasterInformationBean companyInformation, String componentId) {

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabAdderss1", componentId)
				.loadFieldValue(companyInformation.getAddress1());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabCity", componentId)
				.loadFieldValue(companyInformation.getCity());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabZipCode", componentId)
				.loadFieldValue(companyInformation.getZipCode());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabCountry", componentId)
				.loadFieldValue(companyInformation.getCountry());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabAddress2", componentId)
				.loadFieldValue(companyInformation.getAddress2());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabState", componentId)
				.loadFieldValue(companyInformation.getState());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("addressTabRegionCode", componentId)
				.loadFieldValue(companyInformation.getRegionCode());

	}

}
