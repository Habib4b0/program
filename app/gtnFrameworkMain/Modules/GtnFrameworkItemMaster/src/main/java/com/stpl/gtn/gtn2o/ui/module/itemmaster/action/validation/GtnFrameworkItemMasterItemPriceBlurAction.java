/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.itemmaster.action.validation;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.listener.GtnUIFrameworkValueChangeListener;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkRegexStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Deepika.krishnakumar
 */
public class GtnFrameworkItemMasterItemPriceBlurAction extends GtnUIFrameworkValueChangeListener
		implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String pricingName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemPricingQualifierName")
				.getCaptionFromComboBox();

		List<String> itemPriceValue = (List<String>) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);

		for (String textFields : itemPriceValue) {

			String itemValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(textFields).getStringFromField()
					.replaceAll(",", "");

			if (pricingName.equals("AMP") || pricingName.equals("BP") && !itemValue.isEmpty()
					&& itemValue.matches(GtnFrameworkRegexStringConstants.NUMERIC_WITH_DOT_REGEX)) {
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(textFields)
						.loadFieldValue(new BigDecimal(itemValue).setScale(6, BigDecimal.ROUND_DOWN));

			}
			if (pricingName.equals("CPIURA") || pricingName.equals("CPI (Alt) URA") && !itemValue.isEmpty()
					&& itemValue.matches(GtnFrameworkRegexStringConstants.NUMERIC_WITH_DOT_REGEX)) {
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(textFields)
						.loadFieldValue(new BigDecimal(itemValue).setScale(3, BigDecimal.ROUND_DOWN));

			}
			if (pricingName.equals("URA") && !itemValue.isEmpty()
					&& itemValue.matches(GtnFrameworkRegexStringConstants.NUMERIC_WITH_DOT_REGEX)) {
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(textFields)
						.loadFieldValue(new BigDecimal(itemValue).setScale(4, BigDecimal.ROUND_DOWN));

			}
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
