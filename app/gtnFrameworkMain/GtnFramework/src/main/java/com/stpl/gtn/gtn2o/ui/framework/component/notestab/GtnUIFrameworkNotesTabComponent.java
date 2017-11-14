package com.stpl.gtn.gtn2o.ui.framework.component.notestab;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.GtnUIFrameworkNotesTab;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.UI;

public class GtnUIFrameworkNotesTabComponent implements GtnUIFrameworkComponent {
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkNotesTabComponent.class);

	@Override
	public AbstractComponent buildVaadinComponent(GtnUIFrameworkComponentConfig componentConfig) {
		gtnLogger.info("Entering into the buildVaadinComponent() of GtnUIFrameworkNotesTabComponent ");
		componentConfig.getNotesTabConfig().setMode("Add");
		componentConfig.getNotesTabConfig().setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		componentConfig.getNotesTabConfig().setUserName(GtnUIFrameworkGlobalUI.getCurrentUserName());
		componentConfig.getNotesTabConfig().setModuleName(UI.getCurrent().getId());
		GtnUIFrameworkNotesTab notesTab = new GtnUIFrameworkNotesTab(componentConfig.getNotesTabConfig());

		gtnLogger.info("End into the buildVaadinComponent() of GtnUIFrameworkNotesTabComponent ");

		return notesTab;
	}

	@Override
	public void reloadComponent(GtnUIFrameworkActionType action, String dependentComponentId, String componentId,
			Object reloadInput) {
		return;

	}

	@Override
	public void resetToDefault(String componentId, GtnUIFrameworkComponentConfig componentConfig) {
		GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
		GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) baseComponent.getComponent();
		notesTab.refreshNotesTab();
	}

}