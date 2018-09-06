package com.stpl.gtn.gtn2o.registry.action;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.app.gtnforecasting.ui.ForecastUI; 


public class GtnFrameworkNewToOldArchitectureGenerateAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkNewToOldArchitectureGenerateAction.class);

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			 logger.info("doAction----------------------------------------------");
		        final String userId = GtnUIFrameworkGlobalUI.getCurrentUser();
		        String uniqueId = UUID.randomUUID().toString().replaceAll("-", "_").substring(0, 12);
		        List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
		        
		        String fromPeriod = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("Commercial Forecasting_from").getStringCaptionFromV8ComboBox();
		        String toPeriod = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("Commercial Forecasting_to").getStringCaptionFromV8ComboBox();
		        String projectionName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("Commercial Forecasting_projectionName").getV8StringFromField();
		        String description = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("Commercial Forecasting_projectionDescription").getV8StringFromField();

		        GtnWsRecordBean recordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent("forecastLandingScreen_customerHierarchy", componentId).getComponentData().getCustomData();

				String relationshipVersionNo = String.valueOf(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("Commercial_Forecasting_customerRelationshipVersion", componentId).getCaptionFromV8ComboBox());
				String hierarchyVersionNo = recordBean.getPropertyValueByIndex(6).toString();
				
		        List<Object> parametersForDataSelection = new ArrayList<>();
		        parametersForDataSelection.add(fromPeriod);
		        parametersForDataSelection.add(toPeriod);
		        parametersForDataSelection.add(projectionName);
		        parametersForDataSelection.add(description);
		        parametersForDataSelection.add(relationshipVersionNo);
		        parametersForDataSelection.add(hierarchyVersionNo);
		        
		        ForecastUI ui = new ForecastUI();
		        ui.getContentForecasting(userId,uniqueId,parametersForDataSelection);

		} catch (Exception ex) {
			logger.error("Error", ex);
		}
	}

}
