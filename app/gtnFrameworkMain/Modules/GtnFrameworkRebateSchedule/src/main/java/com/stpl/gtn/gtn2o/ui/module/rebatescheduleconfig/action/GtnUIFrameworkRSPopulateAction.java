
package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsCheckAllUpdateBean;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;

public class GtnUIFrameworkRSPopulateAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private static final Map<String, List<String>> POP_UP_ID_VIEW_MAP = new HashMap<>();
	private static final Map<String, String> PROPERTY_COLUM_MAP = new HashMap<>();

	static {
		POP_UP_ID_VIEW_MAP.put("Deduction Calendar No",
				Arrays.asList(GtnFrameworkCommonConstants.SYSTEM_ID, "deductionCalendarName", "deductionCalendarNo"));
		POP_UP_ID_VIEW_MAP.put("Evaluation Rule", Arrays.asList(GtnFrameworkCommonConstants.SYSTEM_ID));
		POP_UP_ID_VIEW_MAP.put("Calculation Rule", Arrays.asList(GtnFrameworkCommonConstants.SYSTEM_ID));
		POP_UP_ID_VIEW_MAP.put("Net Sales Rule", Arrays.asList(GtnFrameworkCommonConstants.SYSTEM_ID));
		POP_UP_ID_VIEW_MAP.put("Formula No",
				Arrays.asList(GtnFrameworkCommonConstants.SYSTEM_ID, "formulaName", "formulaNo", "formulaType"));
		POP_UP_ID_VIEW_MAP.put("Net Sales Formula", Arrays.asList(GtnFrameworkCommonConstants.SYSTEM_ID, "formulaName"));
		POP_UP_ID_VIEW_MAP.put("Rebate Plan No",
				Arrays.asList(GtnFrameworkCommonConstants.SYSTEM_ID, "secondaryRebatePlanName"));
                
                PROPERTY_COLUM_MAP.put("RS Status", "rsStatus");
		PROPERTY_COLUM_MAP.put("Start Date", "rsStartDate");
		PROPERTY_COLUM_MAP.put("End Date", "rsEndDate");
		PROPERTY_COLUM_MAP.put("Evaluation Rule Bundle", "evaluationRuleBundle");
		PROPERTY_COLUM_MAP.put("Calculation Rule Bundle", "calculationRuleBundle");
		PROPERTY_COLUM_MAP.put("Bundle No", "rsBundleNo");

	}

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnWsCheckAllUpdateBean checkAllUpdateBean = new GtnWsCheckAllUpdateBean();

		if (!"rebateSetupTabPopulateButton".equals(componentId)) {
			checkAllUpdateBean.setCheckAll(true);
		}

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		GtnUIFrameworkBaseComponent rebateSetupTabMassDropDown = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("rebateSetupTabMassDropDown");
		GtnUIFrameworkBaseComponent massTextField = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("massTextField");
		GtnUIFrameworkBaseComponent rebateSetupTabMassDateFeild = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("rebateSetupTabMassDateFeild");

		GtnUIFrameworkBaseComponent component = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("rebateSetupTabMassField");

		if (component.getValueFromComponent() != null) {

			if (POP_UP_ID_VIEW_MAP.get(component.getCaptionFromComboBox()) != null) {

				GtnWsRecordBean dto = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
						.getVaadinComponentData("massCustomTextField").getCustomData();
				Map<String, String> valueMap = new HashMap<>();
				checkAllUpdateBean.setPropertyValueMap(valueMap);
				checkAllUpdateBean.setPropertyId(component.getCaptionFromComboBox());
				for (int i = 0; i < POP_UP_ID_VIEW_MAP.get(component.getCaptionFromComboBox()).size(); i++) {
					String newValue = getValueFromComboBox(dto, component, i);
					valueMap.put(POP_UP_ID_VIEW_MAP.get(component.getCaptionFromComboBox()).get(i), newValue);
				}

			} else {
				checkAllUpdateBean.setPropertyId(PROPERTY_COLUM_MAP.get(component.getCaptionFromComboBox()));

				if (component.getCaptionFromComboBox().equals("Start Date")
						|| component.getCaptionFromComboBox().equals("End Date")) {

					checkAllUpdateBean.setValue(formatter.format(rebateSetupTabMassDateFeild.getValueFromComponent()));

				} else if (component.getCaptionFromComboBox().equals("RS Status")) {

					checkAllUpdateBean.setValue(rebateSetupTabMassDropDown.getValueFromComponent());
				} else {

					checkAllUpdateBean.setValue(massTextField.getValueFromComponent());
				}

			}

		}
		GtnUIFrameworkGlobalUI.updateFieldByMassUpdate(checkAllUpdateBean,
				"/" + GtnWsCDRContants.RS_SERVICE + "/" + GtnWsCDRContants.RS_POPULATE_SERVICE);

		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData("psRebateSetupTabResultDataTable");
		GtnUIFrameworkPagedTableLogic tableLogic = componentData.getCurrentPageTableLogic();
		tableLogic.startSearchProcess(new ArrayList<String>(), true);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}



	public String getValueFromComboBox(GtnWsRecordBean dto, GtnUIFrameworkBaseComponent component, int index)
			throws GtnFrameworkValidationFailedException {
		if (dto.getPropertyValue(POP_UP_ID_VIEW_MAP.get(component.getCaptionFromComboBox()).get(index)) != null) {
			return String
					.valueOf(dto.getPropertyValue(POP_UP_ID_VIEW_MAP.get(component.getCaptionFromComboBox()).get(index)));
		} else {

			return String.valueOf(dto.getPropertyValueByIndex(dto.getRecordHeader().size()));
		}
	}

}
