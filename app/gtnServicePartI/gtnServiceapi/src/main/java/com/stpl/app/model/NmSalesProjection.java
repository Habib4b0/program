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

package com.stpl.app.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the NmSalesProjection service. Represents a row in the &quot;NM_SALES_PROJECTION&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see NmSalesProjectionModel
 * @see com.stpl.app.model.impl.NmSalesProjectionImpl
 * @see com.stpl.app.model.impl.NmSalesProjectionModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.NmSalesProjectionImpl")
@ProviderType
public interface NmSalesProjection extends NmSalesProjectionModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.NmSalesProjectionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<NmSalesProjection, Integer> PERIOD_SID_ACCESSOR =
		new Accessor<NmSalesProjection, Integer>() {
			@Override
			public Integer get(NmSalesProjection nmSalesProjection) {
				return nmSalesProjection.getPeriodSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<NmSalesProjection> getTypeClass() {
				return NmSalesProjection.class;
			}
		};

	public static final Accessor<NmSalesProjection, Integer> PROJECTION_DETAILS_SID_ACCESSOR =
		new Accessor<NmSalesProjection, Integer>() {
			@Override
			public Integer get(NmSalesProjection nmSalesProjection) {
				return nmSalesProjection.getProjectionDetailsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<NmSalesProjection> getTypeClass() {
				return NmSalesProjection.class;
			}
		};
}