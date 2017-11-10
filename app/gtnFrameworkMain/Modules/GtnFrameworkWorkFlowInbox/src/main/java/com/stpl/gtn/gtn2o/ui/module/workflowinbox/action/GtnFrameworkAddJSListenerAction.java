package com.stpl.gtn.gtn2o.ui.module.workflowinbox.action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.JavaScriptFunction;

public class GtnFrameworkAddJSListenerAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkAddJSListenerAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// Empty Method

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParams = gtnUIFrameWorkActionConfig.getActionParameterList();
		final String businessProcess = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParams.get(1).toString())
				.getCaptionFromComboBox();
		final GtnUIFrameworkBaseComponent historyBtn = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParams.get(2).toString());
		final GtnUIFrameworkBaseComponent openBtn = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParams.get(3).toString());
		final GtnUIFrameworkBaseComponent table = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParams.get(4).toString());
		historyBtn.setComponentEnable(false);
		openBtn.setComponentEnable(false);
		JavaScript.getCurrent().addFunction("storageEventListener", new JavaScriptFunction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void call(JSONArray arguments) throws JSONException {
				try {
					if (arguments.getString(0).equals(businessProcess) && arguments.getBoolean(1)) {
						table.getLogicFromPagedDataTable()
								.setCurrentPage(table.getLogicFromPagedDataTable().getCurrentPage());
						historyBtn.setComponentEnable(false);
						openBtn.setComponentEnable(false);
						JavaScript.getCurrent().execute("localStorage.setItem('" + businessProcess + "', 'false');");
					}
				} catch (Exception e) {
					gtnLogger.error(e.getMessage());
				}
			}
		});
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
