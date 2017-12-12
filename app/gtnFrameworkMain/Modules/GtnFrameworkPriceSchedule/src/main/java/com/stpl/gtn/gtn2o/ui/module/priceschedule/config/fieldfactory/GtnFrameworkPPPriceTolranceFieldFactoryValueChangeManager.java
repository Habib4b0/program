package com.stpl.gtn.gtn2o.ui.module.priceschedule.config.fieldfactory;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;

public class GtnFrameworkPPPriceTolranceFieldFactoryValueChangeManager {

	GtnFrameworkPPPriceTolranceFieldFactoryValueChangeManager() {
		return;
	}

	public static Boolean isValueChangeAllowed() {
		return (boolean) (GtnUIFrameworkGlobalUI.getSessionProperty("PriceToleranceValueChangeAllowed"));
	}

	public static void setValueChangeAllowed(boolean value) {
		GtnUIFrameworkGlobalUI.addSessionProperty("PriceToleranceValueChangeAllowed", value);
	}

}
