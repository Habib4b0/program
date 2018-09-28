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
import com.stpl.gtn.gtn2o.ws.hierarchyrelationship.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnCustomerSelectionRelationshipLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnCustomerSelectionRelationshipLoadAction.class);

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			List<Object> params = gtnUIFrameWorkActionConfig.getActionParameterList();

			GtnWsRecordBean recordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent((String) params.get(1), componentId).getComponentData().getCustomData();
			if (recordBean != null) {
				Map<Integer, List<GtnWsRelationshipBuilderBean>> relationshipMap = (Map<Integer, List<GtnWsRelationshipBuilderBean>>) recordBean
						.getPropertyValueByIndex(recordBean.getProperties().size() - 1);
				List<GtnWsRelationshipBuilderBean> relationshipBuilderBeanListMapper = relationshipMap
						.get(String.valueOf(recordBean.getPropertyValueByIndex(7)));
				List<String> relationshipCaptionList = new ArrayList<>();
				List<Integer> relationshipIdList = new ArrayList<>();

				ObjectMapper mapper = new ObjectMapper();

				List<GtnWsRelationshipBuilderBean> relationshipBuilderBeanList = mapper.convertValue(
						relationshipBuilderBeanListMapper, new TypeReference<List<GtnWsRelationshipBuilderBean>>() {
						});

				for (GtnWsRelationshipBuilderBean relationshipBuilderBean : relationshipBuilderBeanList) {
					relationshipCaptionList.add(relationshipBuilderBean.getRelationshipName());
					relationshipIdList.add(relationshipBuilderBean.getRelationshipBuilderSid());
				}

				GtnUIFrameworkGlobalUI.getVaadinBaseComponent((String) params.get(2))
						.loadItemsToCombobox(relationshipCaptionList, relationshipIdList);

				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(params.get(2).toString()).loadV8ComboBoxComponentValue(
						params.size() > 3 ? Integer.valueOf(params.get(3).toString()) : 0);
			}
		} catch (Exception ex) {
			logger.error("Error", ex);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
