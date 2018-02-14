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
 * The extended model interface for the NmProjectionSelection service. Represents a row in the &quot;NM_PROJECTION_SELECTION&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see NmProjectionSelectionModel
 * @see com.stpl.app.model.impl.NmProjectionSelectionImpl
 * @see com.stpl.app.model.impl.NmProjectionSelectionModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.NmProjectionSelectionImpl")
@ProviderType
public interface NmProjectionSelection extends NmProjectionSelectionModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.NmProjectionSelectionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<NmProjectionSelection, Integer> NM_PROJECTION_SELECTION_SID_ACCESSOR =
		new Accessor<NmProjectionSelection, Integer>() {
			@Override
			public Integer get(NmProjectionSelection nmProjectionSelection) {
				return nmProjectionSelection.getNmProjectionSelectionSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<NmProjectionSelection> getTypeClass() {
				return NmProjectionSelection.class;
			}
		};
}