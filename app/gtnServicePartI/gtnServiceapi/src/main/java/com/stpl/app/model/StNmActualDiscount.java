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
 * The extended model interface for the StNmActualDiscount service. Represents a row in the &quot;ST_NM_ACTUAL_DISCOUNT&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see StNmActualDiscountModel
 * @see com.stpl.app.model.impl.StNmActualDiscountImpl
 * @see com.stpl.app.model.impl.StNmActualDiscountModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.StNmActualDiscountImpl")
@ProviderType
public interface StNmActualDiscount extends StNmActualDiscountModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.StNmActualDiscountImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<StNmActualDiscount, Integer> PERIOD_SID_ACCESSOR =
		new Accessor<StNmActualDiscount, Integer>() {
			@Override
			public Integer get(StNmActualDiscount stNmActualDiscount) {
				return stNmActualDiscount.getPeriodSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNmActualDiscount> getTypeClass() {
				return StNmActualDiscount.class;
			}
		};

	public static final Accessor<StNmActualDiscount, Integer> USER_ID_ACCESSOR = new Accessor<StNmActualDiscount, Integer>() {
			@Override
			public Integer get(StNmActualDiscount stNmActualDiscount) {
				return stNmActualDiscount.getUserId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNmActualDiscount> getTypeClass() {
				return StNmActualDiscount.class;
			}
		};

	public static final Accessor<StNmActualDiscount, Integer> PROJECTION_DETAILS_SID_ACCESSOR =
		new Accessor<StNmActualDiscount, Integer>() {
			@Override
			public Integer get(StNmActualDiscount stNmActualDiscount) {
				return stNmActualDiscount.getProjectionDetailsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNmActualDiscount> getTypeClass() {
				return StNmActualDiscount.class;
			}
		};

	public static final Accessor<StNmActualDiscount, Integer> RS_MODEL_SID_ACCESSOR =
		new Accessor<StNmActualDiscount, Integer>() {
			@Override
			public Integer get(StNmActualDiscount stNmActualDiscount) {
				return stNmActualDiscount.getRsModelSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNmActualDiscount> getTypeClass() {
				return StNmActualDiscount.class;
			}
		};

	public static final Accessor<StNmActualDiscount, Integer> SESSION_ID_ACCESSOR =
		new Accessor<StNmActualDiscount, Integer>() {
			@Override
			public Integer get(StNmActualDiscount stNmActualDiscount) {
				return stNmActualDiscount.getSessionId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNmActualDiscount> getTypeClass() {
				return StNmActualDiscount.class;
			}
		};
}