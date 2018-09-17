package com.stpl.gtn.gtn2o.registry.action;

import java.util.List;
import java.util.Set;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;

public class GtnFrameworkDeleteViewAction 
implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass
																							{
	private GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkDeleteViewAction.class);
	
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		
		List<Object> actionParamsList = gtnUIFrameWorkActionConfig.getActionParameterList();
		
		logger.info("Inside Delete action");
		
		String gridComponentId = actionParamsList.get(1).toString(); 
		
		logger.info("" + actionParamsList.size() +
		"Table Id" + gridComponentId);
		

		AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(gridComponentId, componentId);
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) abstractComponent.getData();
		Set<GtnWsRecordBean> rows = componentData.getPagedGrid().getValue();
		GtnWsRecordBean selectedRow = rows.isEmpty() ? null : rows.iterator().next();

		
		
	}

	
}
