package com.stpl.gtn.gtn2o.ui.module.contractheader.action;

import java.util.Arrays;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.GtnUIFrameworkNotesTab;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.contractheader.constants.GtnUIFrameworkContractHeaderStringContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnWsContractMasterBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnUIFrameworkContractHeaderAddAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkContractHeaderAddAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.info("Entering GtnFrameworkItemMasterAddAction doAction ");
		try {
			GtnUIFrameworkGlobalUI.addSessionProperty("restrictReloadFlag", Boolean.TRUE);
			setValueToComponents(componentId);
			GtnUIFrameworkGlobalUI.addSessionProperty("contractMasterSid", 0);
		} catch (Exception e) {
			gtnLogger.error("Error while calling doAction in GtnFrameworkItemMasterAddAction ", e);
		} finally {
			gtnLogger.info("Exit GtnFrameworkItemMasterAddAction doAction ");
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void setValueToComponents(String componentId) {
		GtnWsContractMasterBean info = new GtnWsContractMasterBean();
		try {
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_TRADING_PARTNER)
					.setPropertyValue(info.getTradingPartnerName());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, getDefaultValueActionConfig(info));

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME)
					.setPropertyValue(info.getCompanyName());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractHeaderDeleteButton").setComponentVisible(false);

			GtnUIFrameworkBaseComponent component = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("contractHeaderAddSaveButton");
			component.setCaption("SAVE");
			component.setVisible(true);

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnUIFrameworkContractHeaderStringContants.CONTRACT_ALIAS_TRADING_PARTNER)
					.setPropertyValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) GtnUIFrameworkGlobalUI
					.getVaadinComponent("notesTab");
			notesTab.refreshNotesTab();

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractAliasattachResultTable")
					.removeAllItemsFromExtFilterTable();

			setEditViewProperties(true);
		} catch (Exception e) {
			gtnLogger.error(e.getMessage(), e);
		}
	}

	private void setEditViewProperties(boolean value) {

		for (String field : GtnUIFrameworkContractHeaderStringContants.GTN_CONTACT_HEADER_INFORMATION_TAB_FIELD_LIST) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(field).setComponentEnable(value);
		}
		String[] buttons = new String[] { "contractAliasInformationPanel", "identifierAttachButton",
				"identifierRemoveButton", "contractHeaderAddResetButton" };
		for (String string : buttons) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(string).setComponentVisible(value);
		}
	}

	private static GtnUIFrameWorkActionConfig getDefaultValueActionConfig(GtnWsContractMasterBean info) {
		GtnUIFrameWorkActionConfig chAddDefaultValueActionConfig = new GtnUIFrameWorkActionConfig();
		chAddDefaultValueActionConfig.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		chAddDefaultValueActionConfig.addActionParameter(GtnUIFrameworkContractHeaderStringContants.CH_FIELDS);
		chAddDefaultValueActionConfig.addActionParameter(Arrays.asList(new Object[] { info.getContractId(),
				info.getContractNo(), info.getContractName(), info.getContractId(), info.getContractNo(),
				info.getContractName(), info.getContractType(), info.getContractStatus(), info.getDocumentType(),
				info.getStartDate(), info.getEndDate(), info.getDocumentClass(), info.getContractTradeClass(),
				info.getRenegotiationStartDate(), info.getRenegotiationEndDate(), info.getPriceprotectionStartDate(),
				info.getPriceprotectionEndDate(), info.getCompanyMasterByManfCompanyMasterSid(), info.getInsideOwner(),
				info.getInsidePhone(), info.getInsideAuthor(), info.getInsideAdditional(),
				info.getInsideAdditionalName(), info.getInsideAdditionalPhone(), info.getOutsideOwner(),
				info.getOutsidePhone(), info.getOutsideAuthor(), info.getOutsideAdditional(),
				info.getOutsideAdditionalName(), info.getOutsideAdditionalPhone(), info.getAffiliatedContractInfo(),
				info.getShippingTerms(), info.getProposalStartDate(), info.getProposalEndDate(),
				info.getOriginalStartDate(), info.getOriginalEndDate(), info.getAwardStatus(),
				info.getLastUpdatedDate(), info.getPriceEscalationClause(), info.getExemptFromLowPrice(),
				info.getPriceResetIndicator(), info.getCancellationClause(), info.getMostFavoredNation(),
				info.getCategory(), info.getCurrency(), info.getMinimumOrder(), null, null, null,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				info.getTerm(), info.getPaymentTerms(), info.getAdvanceNoticeDays(), info.getContractEligibleDate()

		}));
		return chAddDefaultValueActionConfig;
	}

}
