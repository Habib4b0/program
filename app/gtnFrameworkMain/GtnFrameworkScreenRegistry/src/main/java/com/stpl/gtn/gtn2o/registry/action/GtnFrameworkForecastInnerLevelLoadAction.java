package com.stpl.gtn.gtn2o.registry.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkForecastInnerLevelLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkForecastInnerLevelLoadAction.class);

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamsList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String selectedLevelValue = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(2).toString(), componentId).getCaptionFromV8ComboBox();
		int selectedLevelNo = selectedLevelValue == "" ? 0 : Integer.parseInt(selectedLevelValue);
		if (selectedLevelNo != 0) {
			GtnWsRecordBean hierarchyBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamsList.get(1).toString(), componentId).getComponentData()
					.getCustomData();
			List<String> hierarchyCaptionList = new ArrayList<>();
			List<Integer> hierarchyIdList = new ArrayList<>();

			Map<Integer, String> hierarchyLevelMap = (Map<Integer, String>) hierarchyBean
					.getPropertyValueByIndex(hierarchyBean.getProperties().size() - 2);
			gtnLogger.info("keySet--->" + hierarchyLevelMap.keySet());
			Object[] keySet = hierarchyLevelMap.keySet().toArray();
			for (int i = 0; i < selectedLevelNo; i++) {
				formHierarchyInnerLevelValues(i, hierarchyLevelMap.get(keySet[i]), hierarchyCaptionList,
						hierarchyIdList);
			}
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(3).toString())
					.loadItemsToCombobox(hierarchyCaptionList, hierarchyIdList);

		}
		String selectedInnerLevelNo = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(3).toString(), componentId).getCaptionFromV8ComboBox();
		if (selectedInnerLevelNo != "" || !selectedInnerLevelNo.isEmpty()) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(3).toString())
					.loadV8ComboBoxComponentValue(0);
		}

	}

	private void formHierarchyInnerLevelValues(int i, String levelValue, List<String> hierarchyCaptionList,
			List<Integer> hierarchyIdList) {
		int levelNo = i + 1;
		String levelName = "Level " + levelNo + " - " + levelValue;
		hierarchyCaptionList.add(levelName);
		hierarchyIdList.add(levelNo);
	}

}
