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
 * The extended model interface for the StChProjectionDiscount service. Represents a row in the &quot;ST_CH_PROJECTION_DISCOUNT&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see StChProjectionDiscountModel
 * @see com.stpl.app.model.impl.StChProjectionDiscountImpl
 * @see com.stpl.app.model.impl.StChProjectionDiscountModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.StChProjectionDiscountImpl")
@ProviderType
public interface StChProjectionDiscount extends StChProjectionDiscountModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.StChProjectionDiscountImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<StChProjectionDiscount, Integer> PROJECTION_DETAILS_SID_ACCESSOR =
		new Accessor<StChProjectionDiscount, Integer>() {
			@Override
			public Integer get(StChProjectionDiscount stChProjectionDiscount) {
				return stChProjectionDiscount.getProjectionDetailsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StChProjectionDiscount> getTypeClass() {
				return StChProjectionDiscount.class;
			}
		};

	public static final Accessor<StChProjectionDiscount, Integer> USER_ID_ACCESSOR =
		new Accessor<StChProjectionDiscount, Integer>() {
			@Override
			public Integer get(StChProjectionDiscount stChProjectionDiscount) {
				return stChProjectionDiscount.getUserId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StChProjectionDiscount> getTypeClass() {
				return StChProjectionDiscount.class;
			}
		};

	public static final Accessor<StChProjectionDiscount, Integer> PERIOD_SID_ACCESSOR =
		new Accessor<StChProjectionDiscount, Integer>() {
			@Override
			public Integer get(StChProjectionDiscount stChProjectionDiscount) {
				return stChProjectionDiscount.getPeriodSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StChProjectionDiscount> getTypeClass() {
				return StChProjectionDiscount.class;
			}
		};

	public static final Accessor<StChProjectionDiscount, Integer> SESSION_ID_ACCESSOR =
		new Accessor<StChProjectionDiscount, Integer>() {
			@Override
			public Integer get(StChProjectionDiscount stChProjectionDiscount) {
				return stChProjectionDiscount.getSessionId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StChProjectionDiscount> getTypeClass() {
				return StChProjectionDiscount.class;
			}
		};

	public static final Accessor<StChProjectionDiscount, Integer> RS_MODEL_SID_ACCESSOR =
		new Accessor<StChProjectionDiscount, Integer>() {
			@Override
			public Integer get(StChProjectionDiscount stChProjectionDiscount) {
				return stChProjectionDiscount.getRsModelSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StChProjectionDiscount> getTypeClass() {
				return StChProjectionDiscount.class;
			}
		};
}