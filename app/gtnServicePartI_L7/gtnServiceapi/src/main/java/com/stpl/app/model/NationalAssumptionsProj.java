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
 * The extended model interface for the NationalAssumptionsProj service. Represents a row in the &quot;NATIONAL_ASSUMPTIONS_PROJ&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see NationalAssumptionsProjModel
 * @see com.stpl.app.model.impl.NationalAssumptionsProjImpl
 * @see com.stpl.app.model.impl.NationalAssumptionsProjModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.NationalAssumptionsProjImpl")
@ProviderType
public interface NationalAssumptionsProj extends NationalAssumptionsProjModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.NationalAssumptionsProjImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<NationalAssumptionsProj, Integer> PERIOD_SID_ACCESSOR =
		new Accessor<NationalAssumptionsProj, Integer>() {
			@Override
			public Integer get(NationalAssumptionsProj nationalAssumptionsProj) {
				return nationalAssumptionsProj.getPeriodSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<NationalAssumptionsProj> getTypeClass() {
				return NationalAssumptionsProj.class;
			}
		};

	public static final Accessor<NationalAssumptionsProj, Integer> ITEM_MASTER_SID_ACCESSOR =
		new Accessor<NationalAssumptionsProj, Integer>() {
			@Override
			public Integer get(NationalAssumptionsProj nationalAssumptionsProj) {
				return nationalAssumptionsProj.getItemMasterSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<NationalAssumptionsProj> getTypeClass() {
				return NationalAssumptionsProj.class;
			}
		};

	public static final Accessor<NationalAssumptionsProj, String> PRICE_TYPE_ACCESSOR =
		new Accessor<NationalAssumptionsProj, String>() {
			@Override
			public String get(NationalAssumptionsProj nationalAssumptionsProj) {
				return nationalAssumptionsProj.getPriceType();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<NationalAssumptionsProj> getTypeClass() {
				return NationalAssumptionsProj.class;
			}
		};
}