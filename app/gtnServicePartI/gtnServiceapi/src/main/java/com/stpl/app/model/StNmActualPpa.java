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
 * The extended model interface for the StNmActualPpa service. Represents a row in the &quot;ST_NM_ACTUAL_PPA&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see StNmActualPpaModel
 * @see com.stpl.app.model.impl.StNmActualPpaImpl
 * @see com.stpl.app.model.impl.StNmActualPpaModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.StNmActualPpaImpl")
@ProviderType
public interface StNmActualPpa extends StNmActualPpaModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.StNmActualPpaImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<StNmActualPpa, Integer> PERIOD_SID_ACCESSOR = new Accessor<StNmActualPpa, Integer>() {
			@Override
			public Integer get(StNmActualPpa stNmActualPpa) {
				return stNmActualPpa.getPeriodSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNmActualPpa> getTypeClass() {
				return StNmActualPpa.class;
			}
		};

	public static final Accessor<StNmActualPpa, Integer> PROJECTION_DETAILS_SID_ACCESSOR =
		new Accessor<StNmActualPpa, Integer>() {
			@Override
			public Integer get(StNmActualPpa stNmActualPpa) {
				return stNmActualPpa.getProjectionDetailsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNmActualPpa> getTypeClass() {
				return StNmActualPpa.class;
			}
		};

	public static final Accessor<StNmActualPpa, Integer> USER_ID_ACCESSOR = new Accessor<StNmActualPpa, Integer>() {
			@Override
			public Integer get(StNmActualPpa stNmActualPpa) {
				return stNmActualPpa.getUserId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNmActualPpa> getTypeClass() {
				return StNmActualPpa.class;
			}
		};

	public static final Accessor<StNmActualPpa, Integer> SESSION_ID_ACCESSOR = new Accessor<StNmActualPpa, Integer>() {
			@Override
			public Integer get(StNmActualPpa stNmActualPpa) {
				return stNmActualPpa.getSessionId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNmActualPpa> getTypeClass() {
				return StNmActualPpa.class;
			}
		};
}