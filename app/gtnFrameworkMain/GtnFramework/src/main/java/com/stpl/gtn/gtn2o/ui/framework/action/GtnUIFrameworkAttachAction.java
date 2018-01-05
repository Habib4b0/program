package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.Date;
import java.util.List;

import org.asi.container.ExtContainer;
import org.asi.ui.extfilteringtable.ExtFilterTable;

import com.stpl.gtn.gtn2o.ui.framework.component.setter.GtnUIFrameworkSetter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;

public class GtnUIFrameworkAttachAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> attachParameter = gtnUIFrameWorkActionConfig.getActionParameterList();
		String resultTableId = (String) attachParameter.get(0);
		String idComponent = (String) attachParameter.get(1);
		List<String> inputColumIds = (List<String>) attachParameter.get(2);
		List<String> outputColumIds = (List<String>) attachParameter.get(3);
		List<Class<?>> columTypeList = (List<Class<?>>) attachParameter.get(4);

		VerticalLayout resultLayout = (VerticalLayout) GtnUIFrameworkGlobalUI.getVaadinComponent(resultTableId,
				componentId);
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) resultLayout.getData();
		ExtFilterTable resultTable = (ExtFilterTable) componentData.getCustomData();
		GtnWsRecordBean dto = new GtnWsRecordBean();
		ExtContainer<GtnWsRecordBean> container = (ExtContainer<GtnWsRecordBean>) resultTable.getContainerDataSource();
		dto.setRecordHeader(container.getRecordHeader());
		GtnUIFrameworkSetter gtnUIFrameworkSetter = new GtnUIFrameworkSetter();
		for (int i = 0; i < columTypeList.size(); i++) {
			AbstractField<?> vaadinField = (AbstractField<?>) GtnUIFrameworkGlobalUI
					.getVaadinComponent(outputColumIds.get(i), componentId);
			Object value = "";
			switch (columTypeList.get(i).getName()) {
			case "java.lang.String":
				if (vaadinField instanceof ComboBox) {
					value = ((ComboBox) vaadinField).getItemCaption(vaadinField.getValue());
				} else {
					value = String.valueOf(vaadinField.getValue());
				}
				break;
			case "java.lang.Integer":
				value = vaadinField.getValue();
				break;
			case "java.util.Date":
				value = vaadinField.getValue();
				break;
			default:
				value = String.valueOf(vaadinField.getValue());
				break;
			}

			dto.addProperties(inputColumIds.get(i), value);

			if (vaadinField instanceof TextField) {
				gtnUIFrameworkSetter.loadFieldValue(outputColumIds.get(i), "");
			} else {
				gtnUIFrameworkSetter.loadDateValue(outputColumIds.get(i), null);
			}

		}
		dto.addProperties("createdBy", String.valueOf(GtnUIFrameworkGlobalUI.getCurrentUser()));
		dto.addProperties("createdDate", new Date());
		dto.addProperties("modifiedBy", "");
		dto.addProperties("modifiedDate", null);
		if (idComponent != null) {
			GtnUIFrameworkComponentData idComponentData = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(idComponent, componentId).getComponentData();
			if (idComponentData.getCustomData() instanceof GtnWsRecordBean) {
				GtnWsRecordBean parentDto = (GtnWsRecordBean) idComponentData.getCustomData();
				dto.getProperties().add(parentDto.getProperties().get(parentDto.getProperties().size() - 1));
			}
		}
		container.addBean(dto);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}