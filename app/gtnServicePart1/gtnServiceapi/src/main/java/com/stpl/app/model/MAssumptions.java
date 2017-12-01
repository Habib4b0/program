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
 * The extended model interface for the MAssumptions service. Represents a row in the &quot;M_ASSUMPTIONS&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see MAssumptionsModel
 * @see com.stpl.app.model.impl.MAssumptionsImpl
 * @see com.stpl.app.model.impl.MAssumptionsModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.MAssumptionsImpl")
@ProviderType
public interface MAssumptions extends MAssumptionsModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.MAssumptionsImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<MAssumptions, Integer> M_ASSUMPTIONS_SID_ACCESSOR =
		new Accessor<MAssumptions, Integer>() {
			@Override
			public Integer get(MAssumptions mAssumptions) {
				return mAssumptions.getMAssumptionsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<MAssumptions> getTypeClass() {
				return MAssumptions.class;
			}
		};
}