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
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractHeaderRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.contract.GtnWsContractHeaderResponse;

public class GtnUIFrameworkContractHeaderResetAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkContractHeaderResetAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("Entering GtnFrameworkItemMasterAddAction doAction ");
		int position;
		try {
			position = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractHeaderTabSheet")
					.getTabSheetSelectedTabIndex();
			Integer contractMasterSid = (Integer) GtnUIFrameworkGlobalUI.getSessionProperty("contractMasterSid");
			GtnWsContractMasterBean info = new GtnWsContractMasterBean();
			if (contractMasterSid == 0) {
				setValueToComponents(info, position);
				if (position == 2) {
					GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent(
									GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIASATTACH_RESULT_TABLE)
							.removeAllItemsFromExtFilterTable();
				}
				if (position == 3) {
					GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) GtnUIFrameworkGlobalUI
							.getVaadinComponent("notesTab");
					notesTab.refreshNotesTab();
				}
			} else {
				info.setContractMasterSid(contractMasterSid);
				GtnWsContractHeaderRequest imRequest = new GtnWsContractHeaderRequest();
				imRequest.setGtnWsContractMasterBean(info);
				GtnUIFrameworkWebserviceRequest gtnRequest = new GtnUIFrameworkWebserviceRequest();
				gtnRequest.setGtnWsContractHeaderRequest(imRequest);

				GtnUIFrameworkWebserviceResponse response = callFetchInformationService(						gtnRequest);

				setValueToComponents(response.getGtnWsContractHeaderResponse().getGtnWsContractMasterBean(), position);
				if (position == 2) {
					loadTabAfterSave(response.getGtnWsContractHeaderResponse());
				} else if (position == 3) {
					loadNotesTab(componentId, response.getGtnWsContractHeaderResponse());
				}

			}

		} catch (Exception e) {
			gtnLogger.error("Error while calling doAction in GtnFrameworkItemMasterAddAction ", e);
		} finally {
			gtnLogger.info("Exit GtnFrameworkItemMasterAddAction doAction ");
		}

	}

    public GtnUIFrameworkWebserviceResponse callFetchInformationService(GtnUIFrameworkWebserviceRequest gtnRequest) {
        return new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
                GtnWsContractHeaderContants.GTN_WS_CONTRACT_HEADER_SERVICE
                        + GtnWsContractHeaderContants.GTN_WS_CONTRACT_HEADER_FETCH_INFORMATION_SERVICE,
                gtnRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
        
    }

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void setValueToComponents(GtnWsContractMasterBean info, int position) {
		try {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractId").loadFieldValue(info.getContractId());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractNo").loadFieldValue(info.getContractNo());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractName").loadFieldValue(info.getContractName());
			switch (position) {
			case 0:
				setValueToComponentsForTab1(info);
				break;
			case 1:
				setValueToComponentsForTab2(info);
				break;
			case 2:
				setValueToComponentsForTab3();
				break;
			default:
				break;
			}

		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
		}
	}

	private void loadTabAfterSave(GtnWsContractHeaderResponse reponseBean) throws GtnFrameworkGeneralException {
		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIASATTACH_RESULT_TABLE)
				.removeAllItemsFromExtFilterTable();

		loadIdentifierTab(reponseBean.getGtnwsContractAliasMasterBeanList());

	}

	private void loadIdentifierTab(List<GtnwsContractAliasMasterBean> identifierSaveList)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkBaseComponent container = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIASATTACH_RESULT_TABLE);
		try {
			GtnWsRecordBean chResetAliasBean;
			for (GtnwsContractAliasMasterBean dto : identifierSaveList) {
				chResetAliasBean = new GtnWsRecordBean();
				chResetAliasBean.setRecordHeader(container.getTableRecordHeader());
				chResetAliasBean.addProperties("contractAliasNo", dto.getContractAliasNo());
				chResetAliasBean.addProperties("contractAliasName", dto.getContractAliasName());
				chResetAliasBean.addProperties("contractAliasType", dto.getContractTypeDesc());
				chResetAliasBean.addProperties("aliasStartDate", dto.getStartDate());
				chResetAliasBean.addProperties("aliasEndDate", dto.getEndDate());
				chResetAliasBean.getProperties().add(dto.getContractType());
				chResetAliasBean.getProperties().add(dto.getTpCompanyMasterSid());
				chResetAliasBean.getProperties().add(dto.getContractAliasMasterSid());
				container.addItemToDataTable(chResetAliasBean);
			}
		} catch (Exception systemExcption) {
			throw new GtnFrameworkGeneralException("Save Error", systemExcption);
		}

	}

	private void loadNotesTab(String componentId, GtnWsContractHeaderResponse reponseBean) {
		try {
			GtnUIFrameWorkActionConfig chResetNotesTabLoadAction = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.LOAD_NOTES_TAB);
			chResetNotesTabLoadAction.addActionParameter(reponseBean.getNotesTabList());
			chResetNotesTabLoadAction.addActionParameter(reponseBean.getGtnWsContractMasterBean().getInternalNotes());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, chResetNotesTabLoadAction);
		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
		}
	}

	private void setValueToComponentsForTab1(GtnWsContractMasterBean info) {
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractHeaderTabContractId")
				.loadFieldValue(info.getContractId());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractHeaderTabContractNo")
				.loadFieldValue(info.getContractNo());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractHeaderTabContractName")
				.loadFieldValue(info.getContractName());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractHeaderTabContractType")
				.loadComboBoxComponentValue(info.getContractType());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractHeaderTabContractStatus")
				.loadComboBoxComponentValue(info.getContractStatus());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractHeaderTabDocumentType")
				.loadComboBoxComponentValue(info.getDocumentType());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractHeaderContractStartDate")
				.loadDateValue(info.getStartDate());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractHeaderContractEndDate").loadDateValue(info.getEndDate());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractHeaderTabDocumentClass")
				.loadComboBoxComponentValue(info.getDocumentClass());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractHeaderTabTradeClass")
				.loadComboBoxComponentValue(info.getContractTradeClass());
                GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_TRADING_PARTNER)
					.setComponentReadOnly(false);
                GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_TRADING_PARTNER)
				.loadFieldValue(info.getTradingPartnerName());
                GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_TRADING_PARTNER)
					.setComponentReadOnly(true);
                GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME)
					.setComponentReadOnly(false);
                GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME)
				.loadFieldValue(info.getCompanyName());
                GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME)
					.setComponentReadOnly(true);
                GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_TERM).loadFieldValue(info.getTerm());
		if (info.getCompanyMasterByContHoldCompanyMasterSid() != null) {
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_TRADING_PARTNER)
					.setComponentReadOnly(false);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_TRADING_PARTNER)
					.loadFieldValue(info.getTradingPartnerName());
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_TRADING_PARTNER)
					.setComponentReadOnly(true);
			GtnUIFrameworkComponentData tpComponentData = (GtnUIFrameworkComponentData) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_TRADING_PARTNER)
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
		}
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractHeaderTabReNegotiationStartDate")
				.loadDateValue(info.getRenegotiationStartDate());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractHeaderTabReNegotiationEndDate")
				.loadDateValue(info.getRenegotiationEndDate());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractHeaderTabPriceProtectionStartDate")
				.loadDateValue(info.getPriceprotectionStartDate());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractHeaderTabPriceProtectionEndDate")
				.loadDateValue(info.getPriceprotectionEndDate());
                GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractHeaderTabForecastEligibleDate")
				.loadDateValue(info.getContractEligibleDate());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractHeaderTaManufacturerNo")
				.loadComboBoxComponentValue(info.getCompanyMasterByManfCompanyMasterSid());
	}

	private void setValueToComponentsForTab2(GtnWsContractMasterBean info) {
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabInsideOwner")
				.loadFieldValue(info.getInsideOwner());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabInsidePhone")
				.loadFieldValue(info.getInsidePhone());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabInsideAuthor")
				.loadFieldValue(info.getInsideAuthor());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabInsideAdditional")
				.loadFieldValue(info.getInsideAdditional());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabInsideAdditionalName")
				.loadFieldValue(info.getInsideAdditionalName());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabInsideAdditionalPhone")
				.loadFieldValue(info.getInsideAdditionalPhone());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabOutsideOwner")
				.loadFieldValue(info.getOutsideOwner());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabOutsidePhone")
				.loadFieldValue(info.getOutsidePhone());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabOutsideAuthor")
				.loadFieldValue(info.getOutsideAuthor());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabOutsideAdditional")
				.loadFieldValue(info.getOutsideAdditional());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabOutsideAdditionalName")
				.loadFieldValue(info.getOutsideAdditionalName());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabOutsideAdditionalPhone")
				.loadFieldValue(info.getOutsideAdditionalPhone());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabAffiliatedContractInfo")
				.loadFieldValue(info.getAffiliatedContractInfo());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabShippingTerms")
				.loadFieldValue(info.getShippingTerms());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabProposalStartDate")
				.loadDateValue(info.getProposalStartDate());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabProposalEndDate")
				.loadDateValue(info.getProposalEndDate());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabOriginalStartDate")
				.loadDateValue(info.getOriginalStartDate());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabOriginalEndDate")
				.loadDateValue(info.getOriginalEndDate());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabAwardStatus")
				.loadComboBoxComponentValue(info.getAwardStatus());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabLastUpdatedDate")
				.loadDateValue(info.getLastUpdatedDate());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabPriceEscalationClause")
				.loadFieldValue(info.getPriceEscalationClause());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabExemptFromLowPrice")
				.loadFieldValue(info.getExemptFromLowPrice());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabPriceResetIndicator")
				.loadComboBoxComponentValue(info.getPriceResetIndicator());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabCancellationClause")
				.loadFieldValue(info.getCancellationClause());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabMostFavoredNation")
				.loadFieldValue(info.getMostFavoredNation());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabCategory")
				.loadFieldValue(info.getCategory());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabCurrency")
				.loadFieldValue(info.getCurrency());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabMinimumOrder")
				.loadFieldValue(info.getMinimumOrder());
		if (info.getCompanyMasterByBunitCompanyMasterSid() != null) {
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME)
					.setComponentReadOnly(false);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME)
					.loadDateValue(info.getCompanyName());
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME)
					.setComponentReadOnly(true);
			GtnUIFrameworkComponentData companyNameComponentData = (GtnUIFrameworkComponentData) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME)
					.getComponent().getData();
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
		}
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractHeaderTabTerm").loadFieldValue(info.getTerm());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabPaymentTerms")
				.loadComboBoxComponentValue(info.getPaymentTerms());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractInformationTabAdvanceNoticeDays")
				.loadFieldValue(info.getAdvanceNoticeDays());
	}

	private void setValueToComponentsForTab3() {
		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIAS_TRADING_PARTNER)
				.setComponentReadOnly(false);
		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIAS_TRADING_PARTNER)
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIAS_TRADING_PARTNER)
				.setComponentReadOnly(true);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractAliasStartDate").loadDateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractAliasEndDate").loadDateValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractAliasContractAliasType")
				.loadComboBoxComponentValue(null);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractAliasContractAliasNo")
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractAliasContractAliasName")
				.loadFieldValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
	}
}
