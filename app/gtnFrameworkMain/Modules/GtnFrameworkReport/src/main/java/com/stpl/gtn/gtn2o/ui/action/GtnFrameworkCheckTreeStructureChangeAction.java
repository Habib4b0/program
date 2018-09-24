package com.stpl.gtn.gtn2o.ui.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.customview.constants.GtnWsCustomViewConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.customview.GtnWsCustomViewRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsCustomViewResponse;

public class GtnFrameworkCheckTreeStructureChangeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkCheckTreeStructureChangeAction.class);

	public GtnFrameworkCheckTreeStructureChangeAction() {
		super();
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		boolean saveYesAction = (boolean) actionParameterList.get(1);
		GtnWsReportDataSelectionBean dataSelectionBean = (GtnWsReportDataSelectionBean) actionParameterList.get(2);
		logger.info(" Yes button click? " + saveYesAction);
		if (saveYesAction) {
			GtnWsCustomViewRequest newReportCustomViewRequest = (GtnWsCustomViewRequest) actionParameterList.get(3);
			List<GtnWsRecordBean> treeNodeList = newReportCustomViewRequest.getCvTreeNodeList();
			boolean isEditMode = (boolean) actionParameterList.get(4);
			boolean isSelectBtn = (boolean) actionParameterList.get(5);
			if (isEditMode && !isSelectBtn && dataSelectionBean != null) {
				final GtnUIFrameworkWebServiceClient customViewTreeStructureClient = new GtnUIFrameworkWebServiceClient();
				final GtnUIFrameworkWebserviceRequest customViewTreeStructureRequest = new GtnUIFrameworkWebserviceRequest();
				GtnWsCustomViewRequest customViewRequest = new GtnWsCustomViewRequest();
				customViewRequest.setCvSysId(
						Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("customSid"))));
				customViewRequest.setCustomViewType("report");
				customViewTreeStructureRequest.setGtnWsCustomViewRequest(customViewRequest);
				GtnUIFrameworkWebserviceResponse wsCustomViewTreeStructureResponse = customViewTreeStructureClient
						.callGtnWebServiceUrl(
								GtnWsCustomViewConstants.GTN_CUSTOM_VIEW_SERVICE
										+ GtnWsCustomViewConstants.CUSTOM_VIEW_GET_TREE_DATA,
								customViewTreeStructureRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

				GtnWsCustomViewResponse customViewResponse = wsCustomViewTreeStructureResponse
						.getGtnWsCustomViewResponse();
				List<GtnWsRecordBean> savedNodeList = customViewResponse.getCvTreeNodeList();
				if (checkForChange(customViewResponse.getCustomViewType(),
						newReportCustomViewRequest.getCustomViewType())
						|| checkForTreeStructureChange(savedNodeList, treeNodeList)) {
					logger.info("****Inside CustomViewStructureChange****");
					dataSelectionBean.setCustomViewStructureChanged(true);
				}
			}
		} else {
			dataSelectionBean.setCustomViewStructureChanged(false);
		}
	}

	private boolean checkForChange(String oldStr, String newStr) {
		return !oldStr.equals(newStr);
	}

	private boolean checkForTreeStructureChange(List<GtnWsRecordBean> savedNodeList,
			List<GtnWsRecordBean> treeNodeList) {
		if (savedNodeList.size() != treeNodeList.size()) {
			return true;
		}
		for (int i = 0; i < savedNodeList.size(); i++) {
			GtnWsRecordBean oldBean = savedNodeList.get(i);
			GtnWsRecordBean newBean = treeNodeList.get(i);
			if (checkForChangeInTree(oldBean, newBean)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkForChangeInTree(GtnWsRecordBean oldBean, GtnWsRecordBean newBean) {
		return (oldBean.getIntegerPropertyByIndex(1) != newBean.getAdditionalIntegerPropertyByIndex(0))
				|| !(oldBean.getStringPropertyByIndex(3).equals(newBean.getStringPropertyByIndex(3)))
				|| (oldBean.getIntegerPropertyByIndex(4) != newBean.getIntegerPropertyByIndex(4));
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
