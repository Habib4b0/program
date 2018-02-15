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
 * The extended model interface for the ChProjectionDiscount service. Represents a row in the &quot;CH_PROJECTION_DISCOUNT&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see ChProjectionDiscountModel
 * @see com.stpl.app.model.impl.ChProjectionDiscountImpl
 * @see com.stpl.app.model.impl.ChProjectionDiscountModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.ChProjectionDiscountImpl")
@ProviderType
public interface ChProjectionDiscount extends ChProjectionDiscountModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.ChProjectionDiscountImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ChProjectionDiscount, Integer> PROJECTION_DETAILS_SID_ACCESSOR =
		new Accessor<ChProjectionDiscount, Integer>() {
			@Override
			public Integer get(ChProjectionDiscount chProjectionDiscount) {
				return chProjectionDiscount.getProjectionDetailsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<ChProjectionDiscount> getTypeClass() {
				return ChProjectionDiscount.class;
			}
		};

	public static final Accessor<ChProjectionDiscount, Integer> PERIOD_SID_ACCESSOR =
		new Accessor<ChProjectionDiscount, Integer>() {
			@Override
			public Integer get(ChProjectionDiscount chProjectionDiscount) {
				return chProjectionDiscount.getPeriodSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<ChProjectionDiscount> getTypeClass() {
				return ChProjectionDiscount.class;
			}
		};

	public static final Accessor<ChProjectionDiscount, Integer> RS_MODEL_SID_ACCESSOR =
		new Accessor<ChProjectionDiscount, Integer>() {
			@Override
			public Integer get(ChProjectionDiscount chProjectionDiscount) {
				return chProjectionDiscount.getRsModelSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<ChProjectionDiscount> getTypeClass() {
				return ChProjectionDiscount.class;
			}
		};
}