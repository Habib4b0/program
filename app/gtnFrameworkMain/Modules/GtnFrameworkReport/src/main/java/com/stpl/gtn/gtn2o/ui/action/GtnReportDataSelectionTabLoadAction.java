package com.stpl.gtn.gtn2o.ui.action;

import java.util.Arrays;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ComboBox;

public class GtnReportDataSelectionTabLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportDataSelectionTabLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			logger.info("-------------------------GtnReportDataSelectionTabLoadAction------------------------------"
					+ componentId);

			GtnWsReportDataSelectionBean reportDataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(componentId).getComponentData().getSharedPopupData();

			logger.info("-------popupData-----------" + reportDataSelectionBean.getCompanyReport());

			GtnWsRecordBean customerRecordBean = reportDataSelectionBean.getCustomerHierarchyRecordBean();
			for(int i=0;i<20;i++){
				logger.info("---------customerRecordBean-------"+customerRecordBean.getPropertyValueByIndex(i));
			}
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_company", componentId)
					.loadV8ComboBoxComponentValue(reportDataSelectionBean.getCompanyReport());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_businessUnit", componentId)
					.loadV8ComboBoxComponentValue(reportDataSelectionBean.getBusinessUnitReport());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_fromPeriod", componentId)
					.loadV8ComboBoxComponentValue(reportDataSelectionBean.getFromPeriodReport());

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_dsTabProjectionName", componentId)
					.loadV8ComboBoxComponentValue(reportDataSelectionBean.getReportDataSource());
			
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_customerHierarchy", componentId)
					.setV8PopupFieldValue(customerRecordBean.getPropertyValueByIndex(0));

//			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_customerSelectionRelationship", componentId)
//			.loadV8ComboBoxComponentValue(reportDataSelectionBean.getCustomerRelationshipBuilderSid());
//			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_customerSelectionLevel", componentId)
//			.loadV8ComboBoxComponentValue(reportDataSelectionBean.getCustomerHierarchyForecastLevel());
//			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_customerRelationshipVersion", componentId)
//			.loadV8ComboBoxComponentValue(reportDataSelectionBean.getCustomerRelationshipBuilderSid());
//			
//			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_relationship", componentId)
//			.loadV8ComboBoxComponentValue(reportDataSelectionBean.getCustomerRelationshipBuilderSid());
//			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_level", componentId)
//			.loadV8ComboBoxComponentValue(reportDataSelectionBean.getCustomerRelationshipBuilderSid());
//			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_productRelationshipVersion", componentId)
//			.loadV8ComboBoxComponentValue(reportDataSelectionBean.getCustomerRelationshipBuilderSid());
		} catch (Exception exception) {
			logger.error("Error message", exception);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return null;
	}

}
