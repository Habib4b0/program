package com.stpl.gtn.gtn2o.ui.module.lookups.action;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportingDashboardSaveProfileLookupBean;

public class GtnReportProfileDataToPojoAction {

	public GtnReportingDashboardSaveProfileLookupBean saveDataSelectionDataToReportProfilePojo(
			GtnReportingDashboardSaveProfileLookupBean lookupBean, String componentId) {

		try {
			Object customerHierarchy = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_customerHierarchy", componentId).getV8PopupFieldValue();
			lookupBean.setCustomerHierarchy(String.valueOf(customerHierarchy));

			int customerRelation = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_customerSelectionRelationship", componentId)
					.getIntegerFromV8ComboBox();

			lookupBean.setCustomerRelationSid(customerRelation);

			int customerlevelId = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_customerSelectionLevel", componentId)
					.getIntegerFromV8ComboBox();

			lookupBean.setCustomerLevelNo(customerlevelId);

			Object productHierarchy = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_producthierarchy", componentId).getV8PopupFieldValue();
			lookupBean.setProductHierarchy(String.valueOf(productHierarchy));

			int productRelation = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("dataSelectionTab_relationship", componentId).getIntegerFromV8ComboBox();

			lookupBean.setProductRelationSid(productRelation);

			int productLevelId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("dataSelectionTab_level", componentId)
					.getIntegerFromV8ComboBox();

			lookupBean.setProductLevelNo(productLevelId);

		} catch (GtnFrameworkValidationFailedException e) {
			e.printStackTrace();
		}

		return lookupBean;

	}
}
