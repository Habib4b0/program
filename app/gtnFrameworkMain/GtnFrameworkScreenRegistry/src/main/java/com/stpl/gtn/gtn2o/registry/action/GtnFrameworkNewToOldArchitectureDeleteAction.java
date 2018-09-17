/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.registry.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class GtnFrameworkNewToOldArchitectureDeleteAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkForecastInnerLevelLoadAction.class);

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamsList = gtnUIFrameWorkActionConfig.getActionParameterList();
		int selectedLevelNo = Integer.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(2).toString()).getCaptionFromV8ComboBox());

		GtnWsRecordBean hierarchyBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(1).toString()).getComponentData().getCustomData();

		List<String> hierarchyCaptionList = new ArrayList<>();
		List hierarchyIdList = new ArrayList<>();

		Map<Integer, String> hierarchyLevelMap = (Map<Integer, String>) hierarchyBean
				.getPropertyValueByIndex(hierarchyBean.getProperties().size() - 2);
		gtnLogger.info("keySet--->" + hierarchyLevelMap.keySet());
		Object[] keySet = hierarchyLevelMap.keySet().toArray();
		for (int i = 0; i < selectedLevelNo; i++) {
			formHierarchyInnerLevelValues(i, hierarchyLevelMap.get(keySet[i]), hierarchyCaptionList, hierarchyIdList);
		}

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(3).toString())
				.addAllItemsToComboBox(hierarchyCaptionList, hierarchyIdList);

	}

	private void formHierarchyInnerLevelValues(int i, String levelValue, List<String> hierarchyCaptionList,
			List hierarchyIdList) {
		int levelNo = i+1;
		String levelName = "Level " + levelNo + " - " + levelValue;
		hierarchyCaptionList.add(levelName);
		hierarchyIdList.add(levelNo);
	}

    
}
