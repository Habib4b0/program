package com.stpl.gtn.gtn2o.registry.config.additionalinformation;

import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.registry.config.common.UpdatePreviousNextCloseSubmitButton;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.GtnUIFrameworkNotesTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;

public class GtnFrameworkAdditionalInformationTabConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addAdditionalInformationComponents(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {

		GtnUIFrameworkComponentConfig mainLayout = configProvider
				.getVerticalLayoutConfig(namespace + "-" + "mainLayout", false, null);
		componentList.add(mainLayout);

		addNotesComponent(componentList, mainLayout.getComponentId(), namespace);
		addUpdatePreviousCloseSubmitButtonLayout(componentList, mainLayout.getComponentId(), namespace);
	}

	private void addNotesComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId,
			String namespace) {
		GtnUIFrameworkComponentConfig note = configProvider.getUIFrameworkComponentConfig(namespace + "-" + "notes",
				true, parentComponentId, GtnUIFrameworkComponentType.NOTES_TAB);
		note.setAuthorizationIncluded(true);

		GtnUIFrameworkNotesTabConfig noteConfig = new GtnUIFrameworkNotesTabConfig();
		note.setNotesTabConfig(noteConfig);

		componentList.add(note);

	}

	private void addUpdatePreviousCloseSubmitButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String parentComponentId, String namespace) {
		UpdatePreviousNextCloseSubmitButton discountProjectionButtonLayout = new UpdatePreviousNextCloseSubmitButton();
		discountProjectionButtonLayout.addCommonButtonLayout(componentList, parentComponentId, namespace);
		discountProjectionButtonLayout.addUpdateButton(componentList, namespace);
		discountProjectionButtonLayout.addPreviousButton(componentList, namespace);
		discountProjectionButtonLayout.addCloseButton(componentList, namespace);
		discountProjectionButtonLayout.addSubmitButton(componentList, namespace);

	}

}
