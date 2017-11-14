package com.stpl.gtn.gtn2o.ui.action.duallistbox;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.bean.GtnFrameworkDualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;

public class GtnUIFrameWorkLoadDualListBoxAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnUIFrameWorkLoadDualListBoxAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			logger.info("Inside GtnUIFrameWorkLoadDualListBoxAction Method");
			List<Object> componentIdList = gtnUIFrameWorkActionConfig.getActionParameterList().subList(1,
					gtnUIFrameWorkActionConfig.getActionParameterList().size());
			List<Object> queryParameters = new ArrayList<>(componentIdList.size() - 1);
			for (int i = 1; i < componentIdList.size() - 1; i++) {
				if (componentIdList.get(i) != null) {
					GtnUIFrameworkBaseComponent component = GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent(componentIdList.get(i).toString(), componentId);
					if (component.isComponentPresent()) {
						queryParameters.add(getValueFromField(component));
					} else {
						queryParameters.add(componentIdList.get(i).toString());
					}
				}
			}

			queryParameters.add(getForecastSessionId(componentIdList.get(componentIdList.size() - 1), componentId));
			AbstractComponent dualListBoxLayout = GtnUIFrameworkGlobalUI
					.getVaadinComponent(componentIdList.get(0).toString(), componentId);
			GtnUIFrameworkComponentData dualListBoxcomponentData = (GtnUIFrameworkComponentData) dualListBoxLayout
					.getData();
			GtnFrameworkDualListBoxBean dualListBoxBean = (GtnFrameworkDualListBoxBean) dualListBoxcomponentData
					.getCustomData();
			dualListBoxBean.setGtnDualListBoxqueryParameters(queryParameters);
			dualListBoxcomponentData.setCustomData(dualListBoxBean);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new GtnFrameworkGeneralException(ex);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private String getValueFromField(GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent)
			throws GtnFrameworkValidationFailedException {
		Object fieldValue = gtnUIFrameworkBaseComponent.getObjectFromField();
		return fieldValue == null ? "" : fieldValue.toString();
	}

	private String getForecastSessionId(Object componentId, String sourceComponentId) {
		if (componentId == null) {
			return null;
		}
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(componentId.toString(), sourceComponentId);
		return ((GtnForecastBean) gtnUIFrameworkComponentData.getCustomData()).getForecastSessionId();
	}

}