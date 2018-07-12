package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameWorkGridStaticLoadAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(String.valueOf(actionParameterList.get(0)), componentId);
		String[] headerIdArray = baseComponent.getComponentData().getCurrentComponentConfig()
				.getGtnUIFrameWorkGridConfig().getColumnHeadersId();
		List<String> recordHeader = Arrays.asList(headerIdArray);
		GtnUIFrameworkDataTable dataTable = (GtnUIFrameworkDataTable) actionParameterList.get(1);
		List<GtnWsRecordBean> recordBeanList = dataTable.getDataTable().stream().map(data -> {
			GtnWsRecordBean bean = new GtnWsRecordBean();
			bean.setRecordHeader(recordHeader);
			bean.setProperties(data.getColList());
			return bean;
		}).collect(Collectors.toList());
		
		baseComponent.setGridItems(recordBeanList);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
