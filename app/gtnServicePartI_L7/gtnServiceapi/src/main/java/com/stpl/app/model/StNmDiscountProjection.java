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
 * The extended model interface for the StNmDiscountProjection service. Represents a row in the &quot;ST_NM_DISCOUNT_PROJECTION&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see StNmDiscountProjectionModel
 * @see com.stpl.app.model.impl.StNmDiscountProjectionImpl
 * @see com.stpl.app.model.impl.StNmDiscountProjectionModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.StNmDiscountProjectionImpl")
@ProviderType
public interface StNmDiscountProjection extends StNmDiscountProjectionModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.StNmDiscountProjectionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<StNmDiscountProjection, Integer> USER_ID_ACCESSOR =
		new Accessor<StNmDiscountProjection, Integer>() {
			@Override
			public Integer get(StNmDiscountProjection stNmDiscountProjection) {
				return stNmDiscountProjection.getUserId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNmDiscountProjection> getTypeClass() {
				return StNmDiscountProjection.class;
			}
		};

	public static final Accessor<StNmDiscountProjection, Integer> PERIOD_SID_ACCESSOR =
		new Accessor<StNmDiscountProjection, Integer>() {
			@Override
			public Integer get(StNmDiscountProjection stNmDiscountProjection) {
				return stNmDiscountProjection.getPeriodSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNmDiscountProjection> getTypeClass() {
				return StNmDiscountProjection.class;
			}
		};

	public static final Accessor<StNmDiscountProjection, Integer> PROJECTION_DETAILS_SID_ACCESSOR =
		new Accessor<StNmDiscountProjection, Integer>() {
			@Override
			public Integer get(StNmDiscountProjection stNmDiscountProjection) {
				return stNmDiscountProjection.getProjectionDetailsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNmDiscountProjection> getTypeClass() {
				return StNmDiscountProjection.class;
			}
		};

	public static final Accessor<StNmDiscountProjection, Integer> RS_MODEL_SID_ACCESSOR =
		new Accessor<StNmDiscountProjection, Integer>() {
			@Override
			public Integer get(StNmDiscountProjection stNmDiscountProjection) {
				return stNmDiscountProjection.getRsModelSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNmDiscountProjection> getTypeClass() {
				return StNmDiscountProjection.class;
			}
		};

	public static final Accessor<StNmDiscountProjection, Integer> SESSION_ID_ACCESSOR =
		new Accessor<StNmDiscountProjection, Integer>() {
			@Override
			public Integer get(StNmDiscountProjection stNmDiscountProjection) {
				return stNmDiscountProjection.getSessionId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNmDiscountProjection> getTypeClass() {
				return StNmDiscountProjection.class;
			}
		};
}