package com.stpl.gtn.gtn2o.registry.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnQueryLogger;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeGrid;

public class GtnFrameworkScreenRegistryResetAction
implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass
{

	Logger logger = Logger.getLogger(GtnFrameworkScreenRegistryResetAction.class.getName());
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> actionParamsList = gtnUIFrameWorkActionConfig.getActionParameterList();
		
		GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(1).toString(), componentId)
		.setV8PopupFieldValue("");
	
		GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(2).toString(), componentId)
		.loadV8ComboBoxComponentValue(0);
		
		GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(3).toString(), componentId)
		.loadV8FieldValue("");
		
		GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(4).toString(), componentId)
		.loadV8ComboBoxComponentValue(0);
		
		GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(5).toString(), componentId)
		.loadV8FieldValue("");
		
		GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(6).toString(), componentId)
		.setV8PopupFieldValue("");
		
		GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(7).toString(), componentId)
		.setV8PopupFieldValue("");
		
		String s1 = GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(8).toString(), componentId)
				.getCaptionFromV8ComboBox();
		if(! s1.isEmpty())
		{
			GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(8).toString(), componentId)
			.loadV8ComboBoxComponentValue("0");
		}
		
		
		String s2 =GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(9).toString(), componentId)
				.getCaptionFromV8ComboBox();
		if( ! s2.isEmpty())
		{
			GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(9).toString(), componentId)
			.loadV8ComboBoxComponentValue("0");
		}
		
	

		GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(10).toString(), componentId)
		.setV8PopupFieldValue("");
	
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(actionParamsList.get(11).toString(), componentId).getComponentData();
		GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) componentData.getCustomData();
		Grid<GtnWsRecordBean> leftTable = dualListBoxBean.getLeftTable();
		leftTable.setItems(new ArrayList<>());
		String columnId = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(actionParamsList.get(11).toString(), componentId).getComponentConfig()
				.getGtnUIFrameworkV8DualListBoxConfig().getLeftVisibleColumns()[0].toString();
		Component component = leftTable.getHeaderRow(1).getCell(columnId).getComponent();
		HorizontalLayout horizontalLayout = (HorizontalLayout) component;
		if (horizontalLayout.getComponent(0) instanceof TextField) {
			TextField textField = (TextField) horizontalLayout.getComponent(0);
			textField.setValue("");
			textField.setPlaceholder("Show all");
		}

		TreeGrid<GtnWsRecordBean> rightTable = dualListBoxBean.getRightTable();
		if (rightTable.getTreeData().getRootItems().iterator().hasNext()) {
			GtnWsRecordBean recordBean = rightTable.getTreeData().getRootItems().get(0);
			rightTable.getTreeData().removeItem(recordBean);
			rightTable.getDataProvider().refreshAll();
		}

		
		
		GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(12).toString(), componentId)
		.setV8PopupFieldValue("");
		

		String s3 = GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(13).toString(), componentId)
				.getCaptionFromV8ComboBox();
		if(!s3.isEmpty())
		{
			GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(13).toString(), componentId)
			.loadV8ComboBoxComponentValue("0");
		}
		

	
		String s4 = GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(14).toString(), componentId)
				.getCaptionFromV8ComboBox();
		
		if(!s4.isEmpty())
		{
			GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(14).toString(), componentId)
			.loadV8ComboBoxComponentValue("0");
		}
		
				
		
		
		GtnUIFrameworkComponentData componentData1 = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(actionParamsList.get(15).toString(), componentId).getComponentData();
		GtnFrameworkV8DualListBoxBean dualListBoxBean1 = (GtnFrameworkV8DualListBoxBean) componentData1.getCustomData();
		Grid<GtnWsRecordBean> leftTable1 = dualListBoxBean1.getLeftTable();
		leftTable1.setItems(new ArrayList<>());
		String columnId1 = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(actionParamsList.get(15).toString(), componentId).getComponentConfig()
				.getGtnUIFrameworkV8DualListBoxConfig().getLeftVisibleColumns()[0].toString();
		Component component1 = leftTable1.getHeaderRow(1).getCell(columnId1).getComponent();
		HorizontalLayout horizontalLayout1 = (HorizontalLayout) component1;
		if (horizontalLayout1.getComponent(0) instanceof TextField) {
			TextField textField = (TextField) horizontalLayout1.getComponent(0);
			textField.setValue("");
			textField.setPlaceholder("Show all");
		}

		TreeGrid<GtnWsRecordBean> rightTable1 = dualListBoxBean1.getRightTable();
		if (rightTable1.getTreeData().getRootItems().iterator().hasNext()) {
			GtnWsRecordBean recordBean = rightTable1.getTreeData().getRootItems().get(0);
			rightTable1.getTreeData().removeItem(recordBean);
			rightTable1.getDataProvider().refreshAll();
		}

		GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(16).toString(), componentId)
		.setV8PopupFieldValue("");
		
		GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(17).toString(), componentId)
		.loadV8FieldValue("Add");
		
	

		

//		GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(actionParamsList.get(18).toString(), componentId)
//		.loadV8ComboBoxComponentValue("0");
		
		
	}

	
	
}
