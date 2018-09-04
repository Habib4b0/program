package com.stpl.gtn.gtn2o.registry.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnCustomerSelectionForecastLevelLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnCustomerSelectionRelationshipLoadAction.class);

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		try {
			List<Object> params = gtnUIFrameWorkActionConfig.getActionParameterList();

			GtnWsRecordBean recordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent((String) params.get(1)).getComponentData().getCustomData();

			Map<Integer, String> hierarchyMap = (Map<Integer, String>) recordBean
					.getPropertyValueByIndex(recordBean.getProperties().size() - 2);

			List<String> hierarchyLevelCaptionList = new ArrayList<>();
			List hierarchyLevelIdList = new ArrayList<>();

			for (Map.Entry<Integer, String> hierarchyLevelEntry : hierarchyMap.entrySet()) {
				formHierarchyLevelValues(hierarchyLevelEntry, hierarchyLevelCaptionList);
				hierarchyLevelIdList.add(hierarchyLevelEntry.getKey());
			}

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent((String) params.get(2))
					.addAllItemsToComboBox(hierarchyLevelCaptionList, hierarchyLevelIdList);

		} catch (Exception ex) {
			logger.error("Error", ex);
		}

	}

	private void formHierarchyLevelValues(Entry<Integer, String> hierarchyLevelEntry,
			List<String> hierarchyLevelCaptionList) {
		String levelValue = "Level " + hierarchyLevelEntry.getKey() + " - " + hierarchyLevelEntry.getValue();
		hierarchyLevelCaptionList.add(levelValue);
	}
}
