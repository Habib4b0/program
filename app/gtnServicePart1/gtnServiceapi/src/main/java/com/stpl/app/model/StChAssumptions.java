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
 * The extended model interface for the StChAssumptions service. Represents a row in the &quot;ST_CH_ASSUMPTIONS&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see StChAssumptionsModel
 * @see com.stpl.app.model.impl.StChAssumptionsImpl
 * @see com.stpl.app.model.impl.StChAssumptionsModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.StChAssumptionsImpl")
@ProviderType
public interface StChAssumptions extends StChAssumptionsModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.StChAssumptionsImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<StChAssumptions, Integer> PROJECTION_DETAILS_SID_ACCESSOR =
		new Accessor<StChAssumptions, Integer>() {
			@Override
			public Integer get(StChAssumptions stChAssumptions) {
				return stChAssumptions.getProjectionDetailsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StChAssumptions> getTypeClass() {
				return StChAssumptions.class;
			}
		};

	public static final Accessor<StChAssumptions, Integer> USER_ID_ACCESSOR = new Accessor<StChAssumptions, Integer>() {
			@Override
			public Integer get(StChAssumptions stChAssumptions) {
				return stChAssumptions.getUserId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StChAssumptions> getTypeClass() {
				return StChAssumptions.class;
			}
		};

	public static final Accessor<StChAssumptions, Integer> QUARTER_ACCESSOR = new Accessor<StChAssumptions, Integer>() {
			@Override
			public Integer get(StChAssumptions stChAssumptions) {
				return stChAssumptions.getQuarter();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StChAssumptions> getTypeClass() {
				return StChAssumptions.class;
			}
		};

	public static final Accessor<StChAssumptions, Integer> YEAR_ACCESSOR = new Accessor<StChAssumptions, Integer>() {
			@Override
			public Integer get(StChAssumptions stChAssumptions) {
				return stChAssumptions.getYear();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StChAssumptions> getTypeClass() {
				return StChAssumptions.class;
			}
		};

	public static final Accessor<StChAssumptions, String> ST_CH_ASSUMPTIONS_SID_ACCESSOR =
		new Accessor<StChAssumptions, String>() {
			@Override
			public String get(StChAssumptions stChAssumptions) {
				return stChAssumptions.getStChAssumptionsSid();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<StChAssumptions> getTypeClass() {
				return StChAssumptions.class;
			}
		};

	public static final Accessor<StChAssumptions, Integer> CH_ASSUMPTIONS_SID_ACCESSOR =
		new Accessor<StChAssumptions, Integer>() {
			@Override
			public Integer get(StChAssumptions stChAssumptions) {
				return stChAssumptions.getChAssumptionsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StChAssumptions> getTypeClass() {
				return StChAssumptions.class;
			}
		};

	public static final Accessor<StChAssumptions, Integer> SESSION_ID_ACCESSOR = new Accessor<StChAssumptions, Integer>() {
			@Override
			public Integer get(StChAssumptions stChAssumptions) {
				return stChAssumptions.getSessionId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StChAssumptions> getTypeClass() {
				return StChAssumptions.class;
			}
		};
}