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

package com.stpl.app.parttwo.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the StAdjustmentGtnDetail service. Represents a row in the &quot;ST_ADJUSTMENT_GTN_DETAIL&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see StAdjustmentGtnDetailModel
 * @see com.stpl.app.parttwo.model.impl.StAdjustmentGtnDetailImpl
 * @see com.stpl.app.parttwo.model.impl.StAdjustmentGtnDetailModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.parttwo.model.impl.StAdjustmentGtnDetailImpl")
@ProviderType
public interface StAdjustmentGtnDetail extends StAdjustmentGtnDetailModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.parttwo.model.impl.StAdjustmentGtnDetailImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<StAdjustmentGtnDetail, String> WORKFLOW_ID_ACCESSOR =
		new Accessor<StAdjustmentGtnDetail, String>() {
			@Override
			public String get(StAdjustmentGtnDetail stAdjustmentGtnDetail) {
				return stAdjustmentGtnDetail.getWorkflowId();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<StAdjustmentGtnDetail> getTypeClass() {
				return StAdjustmentGtnDetail.class;
			}
		};
}