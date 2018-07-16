package com.stpl.gtn.gtn2o.ui.framework.action.validation.v8;

import java.util.ArrayList;

import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidator;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TreeGrid;
import com.vaadin.ui.HorizontalLayout;

public class GtnUIFrameWorkV8ConditionalValidator implements GtnUIFrameworkValidator {

	public void isNotNull(Object v8Value) throws GtnFrameworkValidationFailedException {
		if (v8Value != null) {
			return;
		}
		throw new GtnFrameworkValidationFailedException("Not Empty validation Failed");

	}

	public void isNotEmpty(Object v8Value) throws GtnFrameworkValidationFailedException {
		isNotNull(v8Value);
		if (!"".equals(String.valueOf(v8Value))) {
			return;
		}

		throw new GtnFrameworkValidationFailedException("Not Empty validation Failed");

	}

	@Override
	public void validate(String v8ComponentId, String v8FieldId,
			GtnUIFrameworkValidationConfig v8GtnUIFrameworkValidationConfig)
			throws GtnFrameworkValidationFailedException {

		if (v8GtnUIFrameworkValidationConfig != null && v8GtnUIFrameworkValidationConfig.getConditionList() != null) {
			GtnUIFrameworkBaseComponent v8VaadinFieldBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(v8FieldId, v8ComponentId);
                       Object v8CurrentValue = null;
                       
                        if(v8VaadinFieldBaseComponent.getComponent() instanceof ComboBox){
                           v8CurrentValue = v8VaadinFieldBaseComponent.getStringCaptionFromV8ComboBox();
                            v8CurrentValue = v8CurrentValue.equals("-Select one-")?null:v8CurrentValue;
                        }
                        else if (v8VaadinFieldBaseComponent.getComponent() instanceof HorizontalLayout) {
                        	GtnUIFrameworkComponentData dualListBoxData = v8VaadinFieldBaseComponent.getComponentData();
                    		GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) dualListBoxData.getCustomData();
                    		TreeGrid<GtnWsRecordBean> rightTable = dualListBoxBean.getRightTable();
                    		rightTable.getTreeData().getRootItems();
                        	v8CurrentValue = rightTable.getTreeData().getRootItems().isEmpty() == true ? null : rightTable.getTreeData().getRootItems().get(0);
            			}
                        else{
                            v8CurrentValue=v8VaadinFieldBaseComponent.getV8StringFromField();
                        }
			for (GtnUIFrameworkConditionalValidationType v8Condition : v8GtnUIFrameworkValidationConfig
					.getConditionList()) {

				if (GtnUIFrameworkConditionalValidationType.NOT_NULL == v8Condition) {
					isNotNull(v8CurrentValue);

				} else if (GtnUIFrameworkConditionalValidationType.NOT_EMPTY == v8Condition) {

					isNotEmpty(v8CurrentValue);
				}

			}

		}
	}

	@Override
	public GtnUIFrameworkValidator createInstance() {
		return new GtnUIFrameWorkV8ConditionalValidator();
	}

}