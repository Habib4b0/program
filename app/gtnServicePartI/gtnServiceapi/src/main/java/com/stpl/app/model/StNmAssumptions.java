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
 * The extended model interface for the StNmAssumptions service. Represents a row in the &quot;ST_NM_ASSUMPTIONS&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see StNmAssumptionsModel
 * @see com.stpl.app.model.impl.StNmAssumptionsImpl
 * @see com.stpl.app.model.impl.StNmAssumptionsModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.StNmAssumptionsImpl")
@ProviderType
public interface StNmAssumptions extends StNmAssumptionsModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.StNmAssumptionsImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<StNmAssumptions, Integer> PROJECTION_PERIOD_ACCESSOR =
		new Accessor<StNmAssumptions, Integer>() {
			@Override
			public Integer get(StNmAssumptions stNmAssumptions) {
				return stNmAssumptions.getProjectionPeriod();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNmAssumptions> getTypeClass() {
				return StNmAssumptions.class;
			}
		};

	public static final Accessor<StNmAssumptions, Integer> NM_ASSUMPTIONS_SID_ACCESSOR =
		new Accessor<StNmAssumptions, Integer>() {
			@Override
			public Integer get(StNmAssumptions stNmAssumptions) {
				return stNmAssumptions.getNmAssumptionsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNmAssumptions> getTypeClass() {
				return StNmAssumptions.class;
			}
		};

	public static final Accessor<StNmAssumptions, Integer> PROJECTION_DETAILS_SID_ACCESSOR =
		new Accessor<StNmAssumptions, Integer>() {
			@Override
			public Integer get(StNmAssumptions stNmAssumptions) {
				return stNmAssumptions.getProjectionDetailsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNmAssumptions> getTypeClass() {
				return StNmAssumptions.class;
			}
		};

	public static final Accessor<StNmAssumptions, Integer> USER_ID_ACCESSOR = new Accessor<StNmAssumptions, Integer>() {
			@Override
			public Integer get(StNmAssumptions stNmAssumptions) {
				return stNmAssumptions.getUserId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNmAssumptions> getTypeClass() {
				return StNmAssumptions.class;
			}
		};

	public static final Accessor<StNmAssumptions, String> ST_NM_ASSUMPTIONS_SID_ACCESSOR =
		new Accessor<StNmAssumptions, String>() {
			@Override
			public String get(StNmAssumptions stNmAssumptions) {
				return stNmAssumptions.getStNmAssumptionsSid();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<StNmAssumptions> getTypeClass() {
				return StNmAssumptions.class;
			}
		};

	public static final Accessor<StNmAssumptions, Integer> SESSION_ID_ACCESSOR = new Accessor<StNmAssumptions, Integer>() {
			@Override
			public Integer get(StNmAssumptions stNmAssumptions) {
				return stNmAssumptions.getSessionId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNmAssumptions> getTypeClass() {
				return StNmAssumptions.class;
			}
		};
}