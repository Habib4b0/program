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
 * The extended model interface for the MedicaidUraActuals service. Represents a row in the &quot;MEDICAID_URA_ACTUALS&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see MedicaidUraActualsModel
 * @see com.stpl.app.model.impl.MedicaidUraActualsImpl
 * @see com.stpl.app.model.impl.MedicaidUraActualsModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.MedicaidUraActualsImpl")
@ProviderType
public interface MedicaidUraActuals extends MedicaidUraActualsModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.MedicaidUraActualsImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<MedicaidUraActuals, Integer> PERIOD_SID_ACCESSOR =
		new Accessor<MedicaidUraActuals, Integer>() {
			@Override
			public Integer get(MedicaidUraActuals medicaidUraActuals) {
				return medicaidUraActuals.getPeriodSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<MedicaidUraActuals> getTypeClass() {
				return MedicaidUraActuals.class;
			}
		};

	public static final Accessor<MedicaidUraActuals, String> PRICE_TYPE_ACCESSOR =
		new Accessor<MedicaidUraActuals, String>() {
			@Override
			public String get(MedicaidUraActuals medicaidUraActuals) {
				return medicaidUraActuals.getPriceType();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<MedicaidUraActuals> getTypeClass() {
				return MedicaidUraActuals.class;
			}
		};

	public static final Accessor<MedicaidUraActuals, Integer> NA_PROJ_DETAILS_SID_ACCESSOR =
		new Accessor<MedicaidUraActuals, Integer>() {
			@Override
			public Integer get(MedicaidUraActuals medicaidUraActuals) {
				return medicaidUraActuals.getNaProjDetailsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<MedicaidUraActuals> getTypeClass() {
				return MedicaidUraActuals.class;
			}
		};
}