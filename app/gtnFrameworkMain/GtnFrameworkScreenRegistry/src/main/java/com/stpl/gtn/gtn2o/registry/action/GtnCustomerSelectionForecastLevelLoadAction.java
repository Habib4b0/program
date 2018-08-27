package com.stpl.gtn.gtn2o.registry.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.hierarchyrelationship.bean.GtnWsHierarchyDefinitionBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnCustomerSelectionForecastLevelLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnCustomerSelectionRelationshipLoadAction.class);

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		try {
			List<Object> params = gtnUIFrameWorkActionConfig.getActionParameterList();

			GtnWsRecordBean recordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent((String) params.get(1)).getComponentData().getCustomData();

			Map<String, GtnWsHierarchyDefinitionBean> hierarchyMap = (Map<String, GtnWsHierarchyDefinitionBean>) recordBean
					.getPropertyValueByIndex(recordBean.getProperties().size() - 2);
			GtnWsHierarchyDefinitionBean hierarchyDefinitionBeanMapper = hierarchyMap
					.get(String.valueOf(recordBean.getPropertyValueByIndex(0)));

			List<String> hierarchyLevelCaptionList = new ArrayList<>();
			List hierarchyLevelIdList = new ArrayList<>();

			ObjectMapper mapper = new ObjectMapper();

			GtnWsHierarchyDefinitionBean hierarchyDefinitionBean = mapper.convertValue(hierarchyDefinitionBeanMapper,
					new TypeReference<GtnWsHierarchyDefinitionBean>() {
					});
			Map<Integer, String> hierarchyLevelValuesMap = hierarchyDefinitionBeanMapper.getHierarchyLevelValues();

			for (Map.Entry<Integer, String> hierarchyLevelEntry : hierarchyLevelValuesMap.entrySet()) {
				hierarchyLevelCaptionList.add(hierarchyLevelValuesMap.get(hierarchyLevelEntry.getKey()));
				hierarchyLevelIdList.add(Integer.valueOf(hierarchyLevelEntry.getKey()));
			}

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent((String) params.get(2))
					.addAllItemsToComboBox(hierarchyLevelCaptionList, hierarchyLevelIdList);

		} catch (Exception ex) {
			logger.error("Error", ex);
		}

	}
}
