package com.stpl.gtn.gtn2o.registry.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.hierarchyrelationship.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnCustomerSelectionForecastLevelLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnCustomerSelectionRelationshipLoadAction.class);

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		try {
			List<Object> params = gtnUIFrameWorkActionConfig.getActionParameterList();

			GtnWsRecordBean recordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent((String) params.get(1),componentId).getComponentData().getCustomData();

			Map<Integer, List<GtnWsRelationshipBuilderBean>> relationshipMap = (Map<Integer, List<GtnWsRelationshipBuilderBean>>) recordBean
					.getPropertyValueByIndex(recordBean.getProperties().size() - 1);
			int relationshipBuilderSid = Integer.parseInt(String.valueOf(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(params.get(3).toString(),componentId).getCaptionFromV8ComboBox()));

			if (relationshipBuilderSid != 0) {

				List<GtnWsRelationshipBuilderBean> relationshipBuilderBeanListMapper = getList(recordBean,
						relationshipMap);

				List<String> relationshipCaptionList = new ArrayList<>();
				List<Integer> relationshipIdList = new ArrayList<>();

				ObjectMapper mapper = new ObjectMapper();

				List<GtnWsRelationshipBuilderBean> relationshipBuilderBeanList = mapper.convertValue(
						relationshipBuilderBeanListMapper, new TypeReference<List<GtnWsRelationshipBuilderBean>>() {
						});

				for (GtnWsRelationshipBuilderBean relationshipBuilderBean : relationshipBuilderBeanList) {
					if (relationshipBuilderBean.getRelationshipBuilderSid() == relationshipBuilderSid) {
						relationshipCaptionList.add(String.valueOf(relationshipBuilderBean.getVersionNo()));
						relationshipIdList.add(relationshipBuilderBean.getVersionNo());
						break;
					}
				}

				GtnUIFrameworkGlobalUI.getVaadinBaseComponent((String) params.get(4),componentId)
						.loadItemsToCombobox(relationshipCaptionList, relationshipIdList);

				Map<Integer, String> hierarchyMap = (Map<Integer, String>) recordBean
						.getPropertyValueByIndex(recordBean.getProperties().size() - 2);

				List<String> hierarchyLevelCaptionList = new ArrayList<>();
				List<Integer> hierarchyLevelIdList = new ArrayList<>();

				for (Map.Entry<Integer, String> hierarchyLevelEntry : hierarchyMap.entrySet()) {
					formHierarchyLevelValues(hierarchyLevelEntry, hierarchyLevelCaptionList);
					hierarchyLevelIdList.add(hierarchyLevelEntry.getKey());
				}

				GtnUIFrameworkGlobalUI.getVaadinBaseComponent((String) params.get(2),componentId)
						.loadItemsToCombobox(hierarchyLevelCaptionList, hierarchyLevelIdList);

				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(params.get(2).toString(), componentId).loadV8ComboBoxComponentValue(0);
			}
		} catch (Exception ex) {
			logger.error("Error", ex);
		}

	}

	public List<GtnWsRelationshipBuilderBean> getList(GtnWsRecordBean recordBean,
			Map<Integer, List<GtnWsRelationshipBuilderBean>> relationshipMap) {
		return relationshipMap
				.get(recordBean.getStringPropertyByIndex(7));
	
	}

	private void formHierarchyLevelValues(Entry<Integer, String> hierarchyLevelEntry,
			List<String> hierarchyLevelCaptionList) {
		String levelValue = "Level " + hierarchyLevelEntry.getKey() + " - " + hierarchyLevelEntry.getValue();
		hierarchyLevelCaptionList.add(levelValue);
	}
}
