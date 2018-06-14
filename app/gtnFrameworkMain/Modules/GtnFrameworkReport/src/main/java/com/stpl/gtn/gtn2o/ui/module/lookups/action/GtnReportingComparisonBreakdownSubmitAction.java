package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportVariableBreakdownLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.vaadin.ui.AbstractComponent;

public class GtnReportingComparisonBreakdownSubmitAction implements GtnUIFrameworkActionShareable, GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// TODO Auto-generated method stub
		AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI.getVaadinComponent("comparisonBreakdownResultsLayout_comparisonBreakdownResultsPagedTableComponent",
                componentId);
		
		GtnUIFrameworkComponentData gridComponent = (GtnUIFrameworkComponentData) abstractComponent.getData();
		
        List<Object[]> gtnReportComparisonBreakdownLookupBeanList = (List<Object[]>) gridComponent.getCustomData();
        
        List<String> inputList = new ArrayList<String>();
        for(int i = 0 ; i<gtnReportComparisonBreakdownLookupBeanList.size();i++) {
        	inputList.add(i,gtnReportComparisonBreakdownLookupBeanList.get(i)[1].toString());
        }
        
        GtnWsReportDataSelectionBean gtnWsReportDataSelectionBean = new GtnWsReportDataSelectionBean();
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		// TODO Auto-generated method stub
		return null;
	}

}
