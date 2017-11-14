/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.framework.type;

import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameWorkConditionalValidator;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkCustomValidator;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkFormatValidator;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkLengthValidator;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkSizeValidator;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidator;

/**
 *
 * @author Mahesh.James
 */
public enum GtnUIFrameworkSupportedValidationType {
	LENGTH(new GtnUIFrameworkLengthValidator()), SIZE(new GtnUIFrameworkSizeValidator()), FORMAT(
			new GtnUIFrameworkFormatValidator()), CONDITION(
					new GtnUIFrameWorkConditionalValidator()), CUSTOM(new GtnUIFrameworkCustomValidator());

	private final GtnUIFrameworkValidator gtnUIFrameworkValidatior;

	private GtnUIFrameworkSupportedValidationType(GtnUIFrameworkValidator gtnUIFrameworkValidatior) {
		this.gtnUIFrameworkValidatior = gtnUIFrameworkValidatior;
	}

	public GtnUIFrameworkValidator getGtnUIFrameWorkAction() {
		return gtnUIFrameworkValidatior.createInstance();
	}

}
