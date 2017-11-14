package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.List;

import org.asi.ui.customwindow.CustomWindow;

import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.listener.GtnUIPopUpCloseListener;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkView;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.UI;

/**
 *
 * @author STPL
 */
public class GtnUIFrameWorkPopUpAction implements GtnUIFrameWorkAction {
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnUIFrameWorkPopUpAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}
	/*
	 * Param 0 - View Component id , Param 1 - Pop up Window Name, Param2 -
	 * Width, Param3 - Height, Param4 - Close Action class, Param5 -
	 * PopUpSharedData *
	 */

	@Override
	public void doAction(String sourceComponentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			List<Object> configList = gtnUIFrameWorkActionConfig.getActionParameterList();
			CustomWindow popUpWindow = new CustomWindow((String) configList.get(1));
			configWindowStyles(popUpWindow, configList);

			GtnUIFrameworkView view = (GtnUIFrameworkView) GtnUIFrameworkGlobalUI
					.getVaadinComponent((String) configList.get(0));
			if (view == null) {
				view = new GtnUIFrameworkView((String) configList.get(0), sourceComponentId);
				configureReplicableWindow(popUpWindow);

				GtnUIFrameworkGlobalUI.addViewToNavigator(view.getGeneratedViewId(), view);
				logger.info("Add to Navigator ::: View Generated Id:" + view.getGeneratedViewId());
			} else {
				configureModalWindow(popUpWindow);
			}
			setSharedPopupData(view, configList);
			view.buildScreen(sourceComponentId);

			popUpWindow.setContent(view.getRootLayout());
			if (configList.size() > 4 && configList.get(4) != null) {
				popUpWindow.addCloseListener(GtnUIPopUpCloseListener.getListener());
			}
			GtnUIFrameworkComponentData componentData = new GtnUIFrameworkComponentData();
			componentData.setCustomData(gtnUIFrameWorkActionConfig);
			componentData.setSourceComponentId(sourceComponentId);
			popUpWindow.setData(componentData);
			GtnUIFrameworkComponentData viewComponentData = (GtnUIFrameworkComponentData) view.getData();
			viewComponentData.setCustomWindow(popUpWindow);

			if (gtnUIFrameWorkActionConfig.getChildActionList() != null) {
				GtnUIFrameworkActionExecutor.executeActionList(viewComponentData.getViewId(),
						gtnUIFrameWorkActionConfig.getChildActionList());
			}
			UI.getCurrent().addWindow(popUpWindow);

		} catch (GtnFrameworkGeneralException | IllegalArgumentException | NullPointerException ex) {
			logger.error("Popup window Error", ex);
		}
	}

	private void configWindowStyles(CustomWindow window, List<Object> configList) {

		window.addStyleName("valo-theme-customwindow");
		window.addStyleName("bootstrap-ui");
		window.addStyleName("bootstrap");
		if (configList.size() > 2 && configList.get(2) != null) {
			window.setWidth(configList.get(2).toString());
		}
		if (configList.size() > 3 && configList.get(3) != null) {
			window.setHeight(configList.get(3).toString());
		}
		if (configList.size() > 6 && configList.get(6) != null) {
			window.addStyleName(configList.get(6).toString());
		}

	}

	private void configureModalWindow(CustomWindow window) {
		window.center();
		window.setModal(true);
		window.setClosable(true);
		window.setMinimizeBtnVisible(false);
	}

	private void configureReplicableWindow(CustomWindow window) {
		window.addStyleName("bootstrap-forecast bootstrap-nm");
		window.setModal(false);
		window.setClosable(false);
		window.setPositionX(0);
		window.setPositionY(0);
		window.setMinimizeToTray();
		window.setMinimizeBtnVisible(true);
	}

	private void setSharedPopupData(GtnUIFrameworkView view, List<Object> configList) {
		GtnUIFrameworkComponentData viewComponentData = (GtnUIFrameworkComponentData) view.getData();
		viewComponentData.setSharedPopupData(null);
		if (configList.size() > 5 && configList.get(5) != null) {
			viewComponentData.setSharedPopupData(configList.get(5));
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
