package com.stpl.gtn.gtn2o.ui.module.contractheader.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.GtnUIFrameworkNotesTab;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.contractheader.constants.GtnUIFrameworkContractHeaderStringContants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnWsContractMasterBean;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnwsContractAliasMasterBean;
import com.stpl.gtn.gtn2o.ws.contract.contants.GtnWsContractHeaderContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractHeaderRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.contract.GtnWsContractHeaderResponse;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnUIFrameworkContractHeaderEditAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkContractHeaderEditAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> chEditActionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String tableId = (String) chEditActionParamList.get(1);
		boolean isEditable = (boolean) chEditActionParamList.get(2);
		Object itemId = gtnUIFrameWorkActionConfig.getActionParameterList()
				.get(gtnUIFrameWorkActionConfig.getActionParameterList().size() - 1);
		if (itemId instanceof Boolean) {
			itemId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableId).getValueFromPagedDataTable();
		}
		GtnWsRecordBean gtnWsRecordBean = (GtnWsRecordBean) itemId;
		if (gtnWsRecordBean == null) {
			return;
		}
		GtnUIFrameworkGlobalUI.addSessionProperty("restrictReloadFlag", true);
		GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsContractMasterBean infoBean = new GtnWsContractMasterBean();

		int systemId = (Integer) gtnWsRecordBean.getPropertyValueByIndex(7);
		infoBean.setContractMasterSid(systemId);
		GtnUIFrameworkGlobalUI.addSessionProperty("contractMasterSid", systemId);
		GtnWsContractHeaderRequest imRequest = new GtnWsContractHeaderRequest();
		imRequest.setGtnWsContractMasterBean(infoBean);
		gtnRequest.setGtnWsContractHeaderRequest(imRequest);

		try {
			GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
					GtnWsContractHeaderContants.GTN_WS_CONTRACT_HEADER_SERVICE
							+ GtnWsContractHeaderContants.GTN_WS_CONTRACT_HEADER_FETCH_INFORMATION_SERVICE,
					gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

			setValueToComponents(response.getGtnWsContractHeaderResponse().getGtnWsContractMasterBean(), isEditable,
					componentId);
			loadTabAfterSave(response.getGtnWsContractHeaderResponse());
			loadNotesTab(componentId, response.getGtnWsContractHeaderResponse());

		} catch (GtnFrameworkValidationFailedException e) {
			gtnLogger.error(e.getMessage(), e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void setValueToComponents(GtnWsContractMasterBean info, boolean isEditable, String componentId) {
		try {			
			GtnUIFrameworkBaseComponent contractHeaderTabTradingPartner = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
					GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_TRADING_PARTNER);
			contractHeaderTabTradingPartner.setComponentReadOnly(false);
			contractHeaderTabTradingPartner.loadFieldValue(info.getTradingPartnerName());

			GtnUIFrameworkComponentData tpComponentData = (GtnUIFrameworkComponentData) contractHeaderTabTradingPartner
					.getComponent().getData();
			GtnWsRecordBean tpData = new GtnWsRecordBean();
			List<Object> tpList = new ArrayList<>(
					Arrays.asList(new Object[] { GtnUIFrameworkContractHeaderStringContants.STRING_ZERO,
							GtnUIFrameworkContractHeaderStringContants.STRING_ZERO,
							GtnUIFrameworkContractHeaderStringContants.STRING_ZERO,
							GtnUIFrameworkContractHeaderStringContants.STRING_ZERO,
							GtnUIFrameworkContractHeaderStringContants.STRING_ZERO,
							info.getCompanyMasterByContHoldCompanyMasterSid() }));
			tpData.setProperties(tpList);
			tpComponentData.setCustomData(tpData);
			contractHeaderTabTradingPartner.getComponent().setData(tpComponentData);
			contractHeaderTabTradingPartner.setComponentReadOnly(true);
			GtnUIFrameWorkActionConfig chEditDefaultValueActionConfig = new GtnUIFrameWorkActionConfig();
			chEditDefaultValueActionConfig.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
			chEditDefaultValueActionConfig.addActionParameter(GtnUIFrameworkContractHeaderStringContants.CH_FIELDS);
			chEditDefaultValueActionConfig.addActionParameter(Arrays.asList(new Object[] { info.getContractId(),
					info.getContractNo(), info.getContractName(), info.getContractId(), info.getContractNo(),
					info.getContractName(), info.getContractType(), info.getContractStatus(), info.getDocumentType(),
					info.getStartDate(), info.getEndDate(), info.getDocumentClass(), info.getContractTradeClass(),
					info.getRenegotiationStartDate(), info.getRenegotiationEndDate(),
					info.getPriceprotectionStartDate(), info.getPriceprotectionEndDate(),
					info.getCompanyMasterByManfCompanyMasterSid(), info.getInsideOwner(), info.getInsidePhone(),
					info.getInsideAuthor(), info.getInsideAdditional(), info.getInsideAdditionalName(),
					info.getInsideAdditionalPhone(), info.getOutsideOwner(), info.getOutsidePhone(),
					info.getOutsideAuthor(), info.getOutsideAdditional(), info.getOutsideAdditionalName(),
					info.getOutsideAdditionalPhone(), info.getAffiliatedContractInfo(), info.getShippingTerms(),
					info.getProposalStartDate(), info.getProposalEndDate(), info.getOriginalStartDate(),
					info.getOriginalEndDate(), info.getAwardStatus(), info.getLastUpdatedDate(),
					info.getPriceEscalationClause(), info.getExemptFromLowPrice(), info.getPriceResetIndicator(),
					info.getCancellationClause(), info.getMostFavoredNation(), info.getCategory(), info.getCurrency(),
					info.getMinimumOrder(), null, null, null, GtnFrameworkCommonStringConstants.STRING_EMPTY,
					GtnFrameworkCommonStringConstants.STRING_EMPTY, info.getTerm()==0?GtnFrameworkCommonStringConstants.STRING_EMPTY:String.valueOf(info.getTerm()),
					info.getPaymentTerms(),info.getAdvanceNoticeDays().intValue() == 0 ? GtnFrameworkCommonStringConstants.STRING_EMPTY: String.valueOf(info.getAdvanceNoticeDays().intValue())
                                        ,info.getContractEligibleDate()

			}));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, chEditDefaultValueActionConfig);

			GtnUIFrameworkBaseComponent contractHeaderTabName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(
					GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME);

			contractHeaderTabName.setComponentReadOnly(false);
			contractHeaderTabName.loadDateValue(info.getCompanyName());

			GtnUIFrameworkComponentData companyNameComponentData = (GtnUIFrameworkComponentData) contractHeaderTabName
					.getData();
			GtnWsRecordBean companyNameData = new GtnWsRecordBean();
			List<Object> companyNameList = new ArrayList<>(
					Arrays.asList(new Object[] { GtnUIFrameworkContractHeaderStringContants.STRING_ZERO,
							GtnUIFrameworkContractHeaderStringContants.STRING_ZERO,
							GtnUIFrameworkContractHeaderStringContants.STRING_ZERO,
							GtnUIFrameworkContractHeaderStringContants.STRING_ZERO,
							GtnUIFrameworkContractHeaderStringContants.STRING_ZERO,
							info.getCompanyMasterByBunitCompanyMasterSid() }));
			companyNameData.setProperties(companyNameList);
			companyNameComponentData.setCustomData(companyNameData);
			contractHeaderTabName.getComponent().setData(companyNameComponentData);
			contractHeaderTabName.setComponentReadOnly(true);

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractHeaderDeleteButton")
					.setComponentVisible(isEditable && !info.isRecordLockStatus());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractHeaderAddResetButton")
					.setComponentVisible(isEditable && !info.isRecordLockStatus());
			GtnUIFrameworkBaseComponent component = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("contractHeaderAddSaveButton");
			component.setCaption("UPDATE");
			component.setVisible(isEditable && !info.isRecordLockStatus());

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIAS_TRADING_PARTNER)
					.setPropertyValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);

			setEditViewProperties(isEditable);
			GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) GtnUIFrameworkGlobalUI
					.getVaadinComponent("notesTab");
			notesTab.setNotesTabEnable(isEditable);
		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
		}
	}

	private void setEditViewProperties(boolean value) {

		for (String field : GtnUIFrameworkContractHeaderStringContants.GTN_CONTACT_HEADER_INFORMATION_TAB_FIELD_LIST) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(field).setComponentEnable(value);
		}
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractAliasInformationPanel").setComponentVisible(value);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("identifierAttachButton").setComponentVisible(value);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("identifierRemoveButton").setComponentVisible(value);
	}

	private void loadTabAfterSave(GtnWsContractHeaderResponse reponseBean) throws GtnFrameworkGeneralException {
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractAliasattachResultTable")
				.removeAllItemsFromExtFilterTable();

		loadIdentifierTab(reponseBean.getGtnwsContractAliasMasterBeanList());

	}

	private void loadIdentifierTab(List<GtnwsContractAliasMasterBean> identifierSaveList)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkBaseComponent container = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("contractAliasattachResultTable");
		try {
			GtnWsRecordBean chAliasBean;
			for (GtnwsContractAliasMasterBean dto : identifierSaveList) {
				chAliasBean = new GtnWsRecordBean();
				chAliasBean.setRecordHeader(container.getTableRecordHeader());
				chAliasBean.addProperties("contractAliasNo", dto.getContractAliasNo());
				chAliasBean.addProperties("contractAliasName", dto.getContractAliasName());
				chAliasBean.addProperties("contractAliasType", dto.getContractTypeDesc());
				chAliasBean.addProperties("aliasStartDate", dto.getStartDate());
				chAliasBean.addProperties("aliasEndDate", dto.getEndDate());

				chAliasBean.getProperties().add(dto.getContractType());
				chAliasBean.getProperties().add(dto.getTpCompanyMasterSid());
				chAliasBean.getProperties().add(dto.getContractAliasMasterSid());
				container.addItemToDataTable(chAliasBean);
			}
		} catch (Exception systemExcption) {
			throw new GtnFrameworkGeneralException("Save Error", systemExcption);
		}

	}

	private void loadNotesTab(String componentId, GtnWsContractHeaderResponse reponseBean) {
		try {
			GtnUIFrameWorkActionConfig chEditNotesTabLoadAction = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.LOAD_NOTES_TAB);
			chEditNotesTabLoadAction.addActionParameter(reponseBean.getNotesTabList());
			chEditNotesTabLoadAction.addActionParameter(reponseBean.getGtnWsContractMasterBean().getInternalNotes());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, chEditNotesTabLoadAction);
		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
		}
	}
}
