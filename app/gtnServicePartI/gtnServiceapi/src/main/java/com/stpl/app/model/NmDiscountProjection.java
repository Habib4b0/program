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
 * The extended model interface for the NmDiscountProjection service. Represents a row in the &quot;NM_DISCOUNT_PROJECTION&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see NmDiscountProjectionModel
 * @see com.stpl.app.model.impl.NmDiscountProjectionImpl
 * @see com.stpl.app.model.impl.NmDiscountProjectionModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.NmDiscountProjectionImpl")
@ProviderType
public interface NmDiscountProjection extends NmDiscountProjectionModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.NmDiscountProjectionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<NmDiscountProjection, Integer> PERIOD_SID_ACCESSOR =
		new Accessor<NmDiscountProjection, Integer>() {
			@Override
			public Integer get(NmDiscountProjection nmDiscountProjection) {
				return nmDiscountProjection.getPeriodSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<NmDiscountProjection> getTypeClass() {
				return NmDiscountProjection.class;
			}
		};

	public static final Accessor<NmDiscountProjection, Integer> PROJECTION_DETAILS_SID_ACCESSOR =
		new Accessor<NmDiscountProjection, Integer>() {
			@Override
			public Integer get(NmDiscountProjection nmDiscountProjection) {
				return nmDiscountProjection.getProjectionDetailsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<NmDiscountProjection> getTypeClass() {
				return NmDiscountProjection.class;
			}
		};
}