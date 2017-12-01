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
 * The extended model interface for the PhsActuals service. Represents a row in the &quot;PHS_ACTUALS&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see PhsActualsModel
 * @see com.stpl.app.model.impl.PhsActualsImpl
 * @see com.stpl.app.model.impl.PhsActualsModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.PhsActualsImpl")
@ProviderType
public interface PhsActuals extends PhsActualsModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.PhsActualsImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<PhsActuals, Integer> PERIOD_SID_ACCESSOR = new Accessor<PhsActuals, Integer>() {
			@Override
			public Integer get(PhsActuals phsActuals) {
				return phsActuals.getPeriodSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<PhsActuals> getTypeClass() {
				return PhsActuals.class;
			}
		};

	public static final Accessor<PhsActuals, String> PRICE_TYPE_ACCESSOR = new Accessor<PhsActuals, String>() {
			@Override
			public String get(PhsActuals phsActuals) {
				return phsActuals.getPriceType();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<PhsActuals> getTypeClass() {
				return PhsActuals.class;
			}
		};

	public static final Accessor<PhsActuals, Integer> NA_PROJ_DETAILS_SID_ACCESSOR =
		new Accessor<PhsActuals, Integer>() {
			@Override
			public Integer get(PhsActuals phsActuals) {
				return phsActuals.getNaProjDetailsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<PhsActuals> getTypeClass() {
				return PhsActuals.class;
			}
		};
}