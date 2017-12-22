/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.stpl.app.parttwo.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.stpl.app.parttwo.model.StAdjustmentGtnDetail;
import com.stpl.app.parttwo.service.StAdjustmentGtnDetailLocalServiceUtil;

/**
 * The extended model base implementation for the StAdjustmentGtnDetail service. Represents a row in the &quot;ST_ADJUSTMENT_GTN_DETAIL&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link StAdjustmentGtnDetailImpl}.
 * </p>
 *
 * @author
 * @see StAdjustmentGtnDetailImpl
 * @see StAdjustmentGtnDetail
 * @generated
 */
@ProviderType
public abstract class StAdjustmentGtnDetailBaseImpl
	extends StAdjustmentGtnDetailModelImpl implements StAdjustmentGtnDetail {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a st adjustment gtn detail model instance should use the {@link StAdjustmentGtnDetail} interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			StAdjustmentGtnDetailLocalServiceUtil.addStAdjustmentGtnDetail(this);
		}
		else {
			StAdjustmentGtnDetailLocalServiceUtil.updateStAdjustmentGtnDetail(this);
		}
	}
}