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
 * The extended model interface for the StChActualDiscount service. Represents a row in the &quot;ST_CH_ACTUAL_DISCOUNT&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see StChActualDiscountModel
 * @see com.stpl.app.model.impl.StChActualDiscountImpl
 * @see com.stpl.app.model.impl.StChActualDiscountModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.StChActualDiscountImpl")
@ProviderType
public interface StChActualDiscount extends StChActualDiscountModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.StChActualDiscountImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<StChActualDiscount, Integer> PERIOD_SID_ACCESSOR =
		new Accessor<StChActualDiscount, Integer>() {
			@Override
			public Integer get(StChActualDiscount stChActualDiscount) {
				return stChActualDiscount.getPeriodSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StChActualDiscount> getTypeClass() {
				return StChActualDiscount.class;
			}
		};

	public static final Accessor<StChActualDiscount, Integer> PROJECTION_DETAILS_SID_ACCESSOR =
		new Accessor<StChActualDiscount, Integer>() {
			@Override
			public Integer get(StChActualDiscount stChActualDiscount) {
				return stChActualDiscount.getProjectionDetailsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StChActualDiscount> getTypeClass() {
				return StChActualDiscount.class;
			}
		};

	public static final Accessor<StChActualDiscount, Integer> USER_ID_ACCESSOR = new Accessor<StChActualDiscount, Integer>() {
			@Override
			public Integer get(StChActualDiscount stChActualDiscount) {
				return stChActualDiscount.getUserId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StChActualDiscount> getTypeClass() {
				return StChActualDiscount.class;
			}
		};

	public static final Accessor<StChActualDiscount, Integer> SESSION_ID_ACCESSOR =
		new Accessor<StChActualDiscount, Integer>() {
			@Override
			public Integer get(StChActualDiscount stChActualDiscount) {
				return stChActualDiscount.getSessionId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StChActualDiscount> getTypeClass() {
				return StChActualDiscount.class;
			}
		};

	public static final Accessor<StChActualDiscount, Integer> RS_MODEL_SID_ACCESSOR =
		new Accessor<StChActualDiscount, Integer>() {
			@Override
			public Integer get(StChActualDiscount stChActualDiscount) {
				return stChActualDiscount.getRsModelSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StChActualDiscount> getTypeClass() {
				return StChActualDiscount.class;
			}
		};
}