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
 * The extended model interface for the NationalAssumptionsActuals service. Represents a row in the &quot;NATIONAL_ASSUMPTIONS_ACTUALS&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see NationalAssumptionsActualsModel
 * @see com.stpl.app.model.impl.NationalAssumptionsActualsImpl
 * @see com.stpl.app.model.impl.NationalAssumptionsActualsModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.NationalAssumptionsActualsImpl")
@ProviderType
public interface NationalAssumptionsActuals
	extends NationalAssumptionsActualsModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.NationalAssumptionsActualsImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<NationalAssumptionsActuals, Integer> PERIOD_SID_ACCESSOR =
		new Accessor<NationalAssumptionsActuals, Integer>() {
			@Override
			public Integer get(
				NationalAssumptionsActuals nationalAssumptionsActuals) {
				return nationalAssumptionsActuals.getPeriodSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<NationalAssumptionsActuals> getTypeClass() {
				return NationalAssumptionsActuals.class;
			}
		};

	public static final Accessor<NationalAssumptionsActuals, Integer> ITEM_MASTER_SID_ACCESSOR =
		new Accessor<NationalAssumptionsActuals, Integer>() {
			@Override
			public Integer get(
				NationalAssumptionsActuals nationalAssumptionsActuals) {
				return nationalAssumptionsActuals.getItemMasterSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<NationalAssumptionsActuals> getTypeClass() {
				return NationalAssumptionsActuals.class;
			}
		};

	public static final Accessor<NationalAssumptionsActuals, String> PRICE_TYPE_ACCESSOR =
		new Accessor<NationalAssumptionsActuals, String>() {
			@Override
			public String get(
				NationalAssumptionsActuals nationalAssumptionsActuals) {
				return nationalAssumptionsActuals.getPriceType();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<NationalAssumptionsActuals> getTypeClass() {
				return NationalAssumptionsActuals.class;
			}
		};
}