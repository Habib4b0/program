/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.List;

import org.asi.container.ExtContainer;
import org.asi.ui.extfilteringtable.ExtFilterTable;

import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkIdDescDataType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.ComboBox;

public class GtnUIFrameworkAddRecordAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<String> fieldValues = gtnUIFrameWorkActionConfig.getFieldValues();
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		String resultTableId = (String) (parameters.get(0));

		List<GtnUIFrameworkIdDescDataType> idDescTypeList = (List<GtnUIFrameworkIdDescDataType>) parameters.get(2);

		GtnUIFrameworkComponentData componentData;

		componentData = GtnUIFrameworkGlobalUI.getVaadinComponentData(resultTableId, componentId);

		ExtFilterTable resultTable = (ExtFilterTable) componentData.getCustomData();
		GtnWsRecordBean dto = new GtnWsRecordBean();

		GtnUIFrameworkPagedTableConfig tableConfig = componentData.getCurrentComponentConfig().getGtnPagedTableConfig();

		ExtContainer<GtnWsRecordBean> container = (ExtContainer<GtnWsRecordBean>) resultTable.getContainerDataSource();
		dto.setRecordHeader(container.getRecordHeader());

		for (int i = 0; i < dto.getRecordHeader().size(); i++) {
			AbstractField<?> vaadinField = (AbstractField<?>) GtnUIFrameworkGlobalUI
					.getVaadinComponent(fieldValues.get(i), componentId);
			GtnUIFrameworkIdDescDataType currentDescDataType = idDescTypeList.get(i);
			this.setDtoAccordingToType(currentDescDataType, vaadinField, dto, i,
					tableConfig.getTableColumnDataType()[i]);
		}

		container.addBean(dto);

	}

	private void setDtoAccordingToType(GtnUIFrameworkIdDescDataType currentDescDataType, AbstractField<?> vaadinField,
			GtnWsRecordBean dto, int recordIndex, Class<?> dataType) {

		if (currentDescDataType.equals(GtnUIFrameworkIdDescDataType.ID)) {
			dto.addProperties(dto.getRecordHeader().get(recordIndex).toString(), getValue(dataType, vaadinField));
			dto.addAdditionalProperty(vaadinField.getValue());
			return;
		}
		if (currentDescDataType.equals(GtnUIFrameworkIdDescDataType.DESC)) {
			dto.addProperties(dto.getRecordHeader().get(recordIndex).toString(),
					((ComboBox) vaadinField).getItemCaption(vaadinField.getValue()));
			dto.addAdditionalProperty(vaadinField.getValue());
			return;
		}
		if (vaadinField.getData() instanceof GtnWsRecordBean) {

			GtnWsRecordBean data = (GtnWsRecordBean) vaadinField.getData();

			dto.addProperties(dto.getRecordHeader().get(recordIndex).toString(),
					data.getPropertyValue(dto.getRecordHeader().get(recordIndex).toString()));
			if (data.getPropertyValue("systemId") != null) {
				dto.addAdditionalProperty(data.getPropertyValue("systemId"));
			} else {
				dto.addAdditionalProperty(data.getPropertyValueByIndex(data.getRecordHeader().size()));
			}
		} else {
			dto.addAdditionalProperty(0);
		}

	}

	private Object getValue( Class<?> dataType, AbstractField<?> vaadinField) {

		if (dataType.equals(Integer.class)) {
			return "null".equals(vaadinField.getValue()) || "".equals(vaadinField.getValue()) ? 0
					: Integer.parseInt(vaadinField.getValue().toString());
		} else if (dataType.equals(String.class)) {
			return vaadinField.getValue().toString();
		} else if (dataType.equals(Double.class)) {
			return "null".equals(vaadinField.getValue()) || "".equals(vaadinField.getValue()) ? 0d
					: Double.parseDouble(vaadinField.getValue().toString());
		}
		return vaadinField.getValue().toString();
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}