package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.tabload;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkSessionManagerAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.constants.GtnFrameworkContractDashboardContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.NotesDTO;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataRow;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractDashboardRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.contract.GtnWsContractDashboardResponse;

public class GtnFrameworkContractInformationTabLoadAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkContractInformationTabLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnWsContractDashboardSessionBean processDataBean = GtnFrameworkSessionManagerAction
				.getDashboardSessionBean(componentId);
		if (!processDataBean.isContractLoaded()) {
			processDataBean.setNeedOperation(false);
			processDataBean.setContractLoaded(true);
			processDataBean.setContractInfoFieldList((List<String>) parameters.get(1));
			loadFieldDetails(processDataBean, componentId);
			if (processDataBean.isViewMode()) {
				disableFields(processDataBean.getContractInfoFieldList());
			}
			processDataBean.setNeedOperation(true);
		}

	}

	private void disableFields(List<String> fieldList) {
		for (String field : fieldList) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(field).setEnable(false);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void loadFieldDetails(GtnWsContractDashboardSessionBean processDataBean, String componentId)
			throws GtnFrameworkGeneralException {
		try {
			processDataBean.setContractInfoBean(new GtnWsRecordBean());
			processDataBean.getContractInfoBean()
					.setRecordHeader(Arrays.asList(processDataBean.getContractInfoFieldList().toArray()));
			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
			GtnWsContractDashboardRequest cdInfoTabCdRequest = new GtnWsContractDashboardRequest();
			generalWSRequest.setUserId(processDataBean.getProcessBean().getUserId());
			generalWSRequest.setSessionId(processDataBean.getProcessBean().getSessionId());
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			request.setGtnWsContractDashboardRequest(cdInfoTabCdRequest);
			request.setGtnWsGeneralRequest(generalWSRequest);
			cdInfoTabCdRequest.setContractId(processDataBean.getProcessBean().getContractId());
			cdInfoTabCdRequest.setSessionDate(processDataBean.getProcessBean().getSessionDate());
			cdInfoTabCdRequest.setCfpContractId(processDataBean.getProcessBean().getCfpContractId());
			cdInfoTabCdRequest.setIfpContractId(processDataBean.getProcessBean().getIfpContractId());
			cdInfoTabCdRequest.setPsContractId(processDataBean.getProcessBean().getPsContractId());
			cdInfoTabCdRequest.setRsContractId(processDataBean.getProcessBean().getRsContractId());
			GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
					GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
							+ GtnWsContractDashboardContants.GET_CONTRACT_INFO_FIELD_DATA,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			GtnWsContractDashboardResponse cdResponse = response.getGtnWsContractDashboardResponse();
			processDataBean.getContractInfoBean().setProperties(cdResponse.getContractInfoBean().getProperties());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractId")
					.loadFieldValue(processDataBean.getContractInfoBean().getStringPropertyByIndex(0));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractNo")
					.loadFieldValue(processDataBean.getContractInfoBean().getStringPropertyByIndex(1));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractName")
					.loadFieldValue(processDataBean.getContractInfoBean().getStringPropertyByIndex(2));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractType")
					.loadFieldValue(processDataBean.getContractInfoBean().getStringPropertyByIndex(53));
			GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
			resetActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMED_RESET_ACTION);
			resetActionConfig.addActionParameter(processDataBean.getContractInfoFieldList());
			resetActionConfig.addActionParameter(processDataBean.getContractInfoBean().getProperties());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, resetActionConfig);
			GtnUIFrameworkGlobalUI.getVaadinComponentData(processDataBean.getContractInfoFieldList().get(9))
					.setCustomDataList(Arrays.asList(processDataBean.getContractInfoBean().getProperties().get(49)));
			GtnUIFrameworkGlobalUI.getVaadinComponentData(processDataBean.getContractInfoFieldList().get(12))
					.setCustomDataList(Arrays.asList(processDataBean.getContractInfoBean().getProperties().get(50)));
			Object[] aliasColumns = GtnFrameworkContractDashboardContants.getAliasTableColumn();
			processDataBean.setContractAliasRecordList(getCustomisedAliasTableRecord(
					cdResponse.getContractAliasRecordList(), Arrays.asList(aliasColumns)));
			processDataBean.setNotesTabRecordList(
					getCustomisedNotesTableRecord(cdResponse.getNotesTabRecordList(), processDataBean));
		} catch (Exception e) {
			gtnLogger.error("Exception in GtnFrameworkContractInformationTabLoadAction", e);
		}
	}

	private List<GtnWsRecordBean> getCustomisedAliasTableRecord(List<GtnUIFrameworkDataRow> dataRowList,
			List<Object> recordHeader) {
		List<GtnWsRecordBean> records = new ArrayList<>();
		for (GtnUIFrameworkDataRow record : dataRowList) {
			GtnWsRecordBean dto = new GtnWsRecordBean();
			dto.setRecordHeader(recordHeader);
			Long startDateObj = (Long) record.getColList().get(4);
			Long endDateObj = (Long) record.getColList().get(5);
			if (startDateObj != null) {
				record.setElement(4, new Date(startDateObj));
			}
			if (endDateObj != null) {
				record.setElement(5, new Date(endDateObj));
			}
			dto.setProperties(record.getColList());
			records.add(dto);
		}
		return records;
	}

	private List<GtnWsRecordBean> getCustomisedNotesTableRecord(List<GtnUIFrameworkDataRow> dataRowList,
			GtnWsContractDashboardSessionBean processDataBean) throws GtnFrameworkValidationFailedException {
		List<GtnWsRecordBean> records = new ArrayList<>();
		List<NotesDTO> notesDTOList = new ArrayList<>();
		for (GtnUIFrameworkDataRow record : dataRowList) {
			GtnWsRecordBean dto = new GtnWsRecordBean();
			Object createdDateObj = record.getColList().get(4);
			if (createdDateObj != null) {
				record.setElement(4, new Date((Long) createdDateObj));
			}
			dto.setProperties(record.getColList());
			records.add(dto);
			NotesDTO notesDTO = new NotesDTO();
			notesDTO.setDocDetailsId(dto.getIntegerPropertyByIndex(0));
			notesDTO.setDocumentFullPath(dto.getStringPropertyByIndex(3));
			String filePath = notesDTO.getDocumentFullPath();
			String fileNameWithId = filePath.substring(filePath.lastIndexOf('/') + 1, filePath.lastIndexOf('_'))
					+ filePath.substring(filePath.lastIndexOf('.'));
			String fileName = fileNameWithId;
			notesDTO.setDocumentName(fileName);
			SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
			TimeZone central = TimeZone.getTimeZone("CST");
			format.setTimeZone(central);
			notesDTO.setDateAdded(format.format(dto.getDatePropertyByIndex(4)));
			notesDTO.setUserId(dto.getIntegerPropertyByIndex(5));
			notesDTO.setUserName(dto.getStringPropertyByIndex(6));
			notesDTOList.add(notesDTO);
		}
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("notesTab").setNotesTabValue(
				Arrays.asList(processDataBean.getContractInfoBean().getStringPropertyByIndex(48), notesDTOList));
		return records;
	}
}
