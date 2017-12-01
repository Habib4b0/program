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
 * The extended model interface for the StMAssumptions service. Represents a row in the &quot;ST_M_ASSUMPTIONS&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see StMAssumptionsModel
 * @see com.stpl.app.model.impl.StMAssumptionsImpl
 * @see com.stpl.app.model.impl.StMAssumptionsModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.StMAssumptionsImpl")
@ProviderType
public interface StMAssumptions extends StMAssumptionsModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.StMAssumptionsImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<StMAssumptions, Integer> PROJ_YEAR_ACCESSOR = new Accessor<StMAssumptions, Integer>() {
			@Override
			public Integer get(StMAssumptions stMAssumptions) {
				return stMAssumptions.getProjYear();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StMAssumptions> getTypeClass() {
				return StMAssumptions.class;
			}
		};

	public static final Accessor<StMAssumptions, Integer> USER_ID_ACCESSOR = new Accessor<StMAssumptions, Integer>() {
			@Override
			public Integer get(StMAssumptions stMAssumptions) {
				return stMAssumptions.getUserId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StMAssumptions> getTypeClass() {
				return StMAssumptions.class;
			}
		};

	public static final Accessor<StMAssumptions, String> ST_M_ASSUMPTIONS_SID_ACCESSOR =
		new Accessor<StMAssumptions, String>() {
			@Override
			public String get(StMAssumptions stMAssumptions) {
				return stMAssumptions.getStMAssumptionsSid();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<StMAssumptions> getTypeClass() {
				return StMAssumptions.class;
			}
		};

	public static final Accessor<StMAssumptions, Integer> PROJECTION_PERIOD_ACCESSOR =
		new Accessor<StMAssumptions, Integer>() {
			@Override
			public Integer get(StMAssumptions stMAssumptions) {
				return stMAssumptions.getProjectionPeriod();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StMAssumptions> getTypeClass() {
				return StMAssumptions.class;
			}
		};

	public static final Accessor<StMAssumptions, Integer> PROJECTION_DETAILS_SID_ACCESSOR =
		new Accessor<StMAssumptions, Integer>() {
			@Override
			public Integer get(StMAssumptions stMAssumptions) {
				return stMAssumptions.getProjectionDetailsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StMAssumptions> getTypeClass() {
				return StMAssumptions.class;
			}
		};

	public static final Accessor<StMAssumptions, Integer> SESSION_ID_ACCESSOR = new Accessor<StMAssumptions, Integer>() {
			@Override
			public Integer get(StMAssumptions stMAssumptions) {
				return stMAssumptions.getSessionId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StMAssumptions> getTypeClass() {
				return StMAssumptions.class;
			}
		};

	public static final Accessor<StMAssumptions, Integer> M_ASSUMPTIONS_SID_ACCESSOR =
		new Accessor<StMAssumptions, Integer>() {
			@Override
			public Integer get(StMAssumptions stMAssumptions) {
				return stMAssumptions.getMAssumptionsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StMAssumptions> getTypeClass() {
				return StMAssumptions.class;
			}
		};
}