package com.stpl.gtn.gtn2o.ui.framework.component.listener;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.MessageBoxListener;

public class GtnUIMsgBoxTwoButtonListener implements MessageBoxListener {
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIMsgBoxTwoButtonListener.class);

	private List<GtnUIFrameWorkActionConfig> onSucessActionConfigList;
	private List<GtnUIFrameWorkActionConfig> onFailureActionConfigList;
	private String sourceComponentId;

	public GtnUIMsgBoxTwoButtonListener(List<GtnUIFrameWorkActionConfig> onSucessActionConfigList,
			String sourceComponentId) {
		super();
		this.onSucessActionConfigList = onSucessActionConfigList == null ? onSucessActionConfigList
				: new ArrayList<>(onSucessActionConfigList);
		this.sourceComponentId = sourceComponentId;
	}

	public GtnUIMsgBoxTwoButtonListener(List<GtnUIFrameWorkActionConfig> onSucessActionConfigList,
			List<GtnUIFrameWorkActionConfig> onFailureActionConfigList, String sourceComponentId) {
		super();
		this.onSucessActionConfigList = onSucessActionConfigList == null ? onSucessActionConfigList
				: new ArrayList<>(onSucessActionConfigList);
		this.onFailureActionConfigList = onFailureActionConfigList == null ? onFailureActionConfigList
				: new ArrayList<>(onFailureActionConfigList);
		this.sourceComponentId = sourceComponentId;
	}

	@Override
	public void buttonClicked(ButtonId buttonId) {
		if (buttonId.name().equalsIgnoreCase("YES") && (onSucessActionConfigList != null)) {
			try {
				GtnUIFrameworkActionExecutor.executeActionList(sourceComponentId, onSucessActionConfigList);
			} catch (GtnFrameworkGeneralException e) {
				gtnLogger.error(e.getMessage());

			}
		}

		if (buttonId.name().equalsIgnoreCase("NO") && (onFailureActionConfigList != null)) {
			try {
				GtnUIFrameworkActionExecutor.executeActionList(sourceComponentId, onFailureActionConfigList);
			} catch (GtnFrameworkGeneralException e) {
				gtnLogger.error(e.getMessage());
			}
		}

	}
}
