package com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.listener;

import java.util.List;

import org.asi.ui.extcustomcheckbox.ExtCustomCheckBox;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.v7.ui.AbstractField;

public class GtnUIFieldFactoryExtCustomBoxClickListener implements ExtCustomCheckBox.ClickListener {

	private static GtnUIFieldFactoryExtCustomBoxClickListener gtnUIFieldFactoryExtCustomBoxClickListener = new GtnUIFieldFactoryExtCustomBoxClickListener();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void click(ExtCustomCheckBox.ClickEvent event) {
		try {
			AbstractField<?> current = (AbstractField<?>) event.getSource();
			GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) current.getData();
			GtnUIFrameworkActionParameter actionparameter = componentData.getActionParameter();
			actionparameter.setCurrentValue(current.getValue());
			List<GtnUIFrameWorkActionConfig> actionConfigList = componentData.getCurrentComponentConfig()
					.getGtnUIFrameWorkItemClickActionConfigList();
			String componentId = actionparameter.getVaadinComponentId();
			GtnUIFrameworkActionExecutor.executeActionList(componentId, actionConfigList);

		} catch (GtnFrameworkGeneralException e) {
			GtnWSLogger.getGTNLogger(GtnUIFieldFactoryExtCustomBoxClickListener.class).error(e.getMessage(), e);
		}
	}

	public static GtnUIFieldFactoryExtCustomBoxClickListener getListener() {
		return gtnUIFieldFactoryExtCustomBoxClickListener;
	}

}
