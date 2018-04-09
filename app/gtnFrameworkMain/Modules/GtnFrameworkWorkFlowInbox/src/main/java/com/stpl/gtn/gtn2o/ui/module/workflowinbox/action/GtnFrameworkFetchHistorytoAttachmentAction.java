package com.stpl.gtn.gtn2o.ui.module.workflowinbox.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants.GtnFrameworkWorkflowInboxClassConstants;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkFetchHistorytoAttachmentAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkFetchHistorytoAttachmentAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnWsRecordBean gtnWsRecordBean = gtnUIFrameWorkActionConfig.getActionParameter().getItemId();
		try {

			GtnUIFrameworkComponentData fetchhistoryTabResultDataTablecomponentData = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(GtnFrameworkWorkflowInboxClassConstants.ATTACHMENTSEARCHRESULT_TABLE);
			GtnUIFrameworkPagedTableLogic fetchhistoryTabTableLogic = fetchhistoryTabResultDataTablecomponentData
					.getCurrentPageTableLogic();

			String fileName = String.valueOf(gtnWsRecordBean.getPropertyValueByIndex(5));

			List<GtnWebServiceSearchCriteria> additioanlSearchCriteriaList = new ArrayList<>();

			GtnWebServiceSearchCriteria userIdCriteria = new GtnWebServiceSearchCriteria();

			additioanlSearchCriteriaList.add(userIdCriteria);

			userIdCriteria.setFieldId(GtnFrameworkWorkflowInboxClassConstants.ATTACHMENT_LINK);

			userIdCriteria.setExpression(GtnFrameworkWorkflowInboxClassConstants.EQUALSOF);
			userIdCriteria.setFilterValue1(fileName);

			fetchhistoryTabTableLogic.setAdditioanlSearchCriteriaList(additioanlSearchCriteriaList);

			boolean isActiveFlag = true;
			fetchhistoryTabTableLogic.startSearchProcess(new ArrayList<String>(), isActiveFlag);

		} catch (Exception ex) {
			gtnLogger.error(ex.getMessage(), ex);
			throw new GtnFrameworkGeneralException(
					"Error in GtnFrameworkFetchHistorytoAttachmentAction class doAction method", ex);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}

