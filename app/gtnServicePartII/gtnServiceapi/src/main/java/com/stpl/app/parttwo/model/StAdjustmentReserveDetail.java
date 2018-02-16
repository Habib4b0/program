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
 * The extended model interface for the StAdjustmentReserveDetail service. Represents a row in the &quot;ST_ADJUSTMENT_RESERVE_DETAIL&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see StAdjustmentReserveDetailModel
 * @see com.stpl.app.parttwo.model.impl.StAdjustmentReserveDetailImpl
 * @see com.stpl.app.parttwo.model.impl.StAdjustmentReserveDetailModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.parttwo.model.impl.StAdjustmentReserveDetailImpl")
@ProviderType
public interface StAdjustmentReserveDetail
	extends StAdjustmentReserveDetailModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.parttwo.model.impl.StAdjustmentReserveDetailImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<StAdjustmentReserveDetail, String> WORKFLOW_ID_ACCESSOR =
		new Accessor<StAdjustmentReserveDetail, String>() {
			@Override
			public String get(
				StAdjustmentReserveDetail stAdjustmentReserveDetail) {
				return stAdjustmentReserveDetail.getWorkflowId();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<StAdjustmentReserveDetail> getTypeClass() {
				return StAdjustmentReserveDetail.class;
			}
		};
}