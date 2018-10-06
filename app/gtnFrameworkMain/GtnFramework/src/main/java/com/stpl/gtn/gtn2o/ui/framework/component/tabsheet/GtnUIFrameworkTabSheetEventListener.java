package com.stpl.gtn.gtn2o.ui.framework.component.tabsheet;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.TabSheet;

public class GtnUIFrameworkTabSheetEventListener implements TabSheet.SelectedTabChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
        private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkTabSheetEventListener.class);

	@Override
	public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
		try {
			AbstractComponent abstractTabSheetComponent = event.getTabSheet();
			AbstractComponent abstractTabComponent = (AbstractComponent) event.getTabSheet().getSelectedTab();
			GtnUIFrameworkComponentData tabSheetComponentData = (GtnUIFrameworkComponentData) abstractTabSheetComponent
					.getData();
                        
			GtnUIFrameworkComponentData tabComponentData = (GtnUIFrameworkComponentData) abstractTabComponent.getData();
			GtnUIFrameworkTabConfig currentTabConfig = tabComponentData.getGtnUIFrameworkTabConfig();
                        int currentTabIndex = currentTabConfig.getTabIndex();
                        gtnLogger.info("currentTabIndex-->" +currentTabIndex);

                        if (!tabComponentData.isTabLoaded()) {
				GtnUIFrameworkGlobalUI.addChildComponent(tabComponentData.getComponentIdInMap(),
						currentTabConfig.getTabLayoutComponentConfigList().subList(1,
								currentTabConfig.getTabLayoutComponentConfigList().size()));
				tabComponentData.setTabLoaded(true);
			}
			GtnUIFrameworkActionExecutor.executeActionList(
					tabComponentData.getCurrentComponentConfig().getComponentId(),
					tabComponentData.getCurrentComponentConfig().getGtnUIFrameWorkActionConfigList());
                        for(GtnUIFrameWorkActionConfig action : tabSheetComponentData.getCurrentComponentConfig().getGtnUIFrameWorkActionConfigList()){
                            action.addActionParameter(currentTabIndex);
                        }
			GtnUIFrameworkActionExecutor.executeActionList(tabSheetComponentData.getComponentIdInMap(),
					tabSheetComponentData.getCurrentComponentConfig().getGtnUIFrameWorkActionConfigList());

		} catch (GtnFrameworkGeneralException e) {
			getGtnLogger().error(e.getMessage(), e);
		}
	}

	public GtnWSLogger getGtnLogger() {
		return  GtnWSLogger.getGTNLogger(GtnUIFrameworkTabSheetComponent.class);
	}

}
