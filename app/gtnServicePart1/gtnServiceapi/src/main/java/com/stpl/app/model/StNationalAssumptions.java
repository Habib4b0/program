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
 * The extended model interface for the StNationalAssumptions service. Represents a row in the &quot;ST_NATIONAL_ASSUMPTIONS&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see StNationalAssumptionsModel
 * @see com.stpl.app.model.impl.StNationalAssumptionsImpl
 * @see com.stpl.app.model.impl.StNationalAssumptionsModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.StNationalAssumptionsImpl")
@ProviderType
public interface StNationalAssumptions extends StNationalAssumptionsModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.StNationalAssumptionsImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<StNationalAssumptions, String> START_PERIOD_ACCESSOR =
		new Accessor<StNationalAssumptions, String>() {
			@Override
			public String get(StNationalAssumptions stNationalAssumptions) {
				return stNationalAssumptions.getStartPeriod();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<StNationalAssumptions> getTypeClass() {
				return StNationalAssumptions.class;
			}
		};

	public static final Accessor<StNationalAssumptions, Integer> USER_ID_ACCESSOR =
		new Accessor<StNationalAssumptions, Integer>() {
			@Override
			public Integer get(StNationalAssumptions stNationalAssumptions) {
				return stNationalAssumptions.getUserId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNationalAssumptions> getTypeClass() {
				return StNationalAssumptions.class;
			}
		};

	public static final Accessor<StNationalAssumptions, String> END_PERIOD_ACCESSOR =
		new Accessor<StNationalAssumptions, String>() {
			@Override
			public String get(StNationalAssumptions stNationalAssumptions) {
				return stNationalAssumptions.getEndPeriod();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<StNationalAssumptions> getTypeClass() {
				return StNationalAssumptions.class;
			}
		};

	public static final Accessor<StNationalAssumptions, Integer> NA_PROJ_MASTER_SID_ACCESSOR =
		new Accessor<StNationalAssumptions, Integer>() {
			@Override
			public Integer get(StNationalAssumptions stNationalAssumptions) {
				return stNationalAssumptions.getNaProjMasterSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNationalAssumptions> getTypeClass() {
				return StNationalAssumptions.class;
			}
		};

	public static final Accessor<StNationalAssumptions, String> PRICE_TYPE_ACCESSOR =
		new Accessor<StNationalAssumptions, String>() {
			@Override
			public String get(StNationalAssumptions stNationalAssumptions) {
				return stNationalAssumptions.getPriceType();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<StNationalAssumptions> getTypeClass() {
				return StNationalAssumptions.class;
			}
		};

	public static final Accessor<StNationalAssumptions, Integer> SESSION_ID_ACCESSOR =
		new Accessor<StNationalAssumptions, Integer>() {
			@Override
			public Integer get(StNationalAssumptions stNationalAssumptions) {
				return stNationalAssumptions.getSessionId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNationalAssumptions> getTypeClass() {
				return StNationalAssumptions.class;
			}
		};
}