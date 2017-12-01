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
 * The extended model interface for the MSupplementalDiscProj service. Represents a row in the &quot;M_SUPPLEMENTAL_DISC_PROJ&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see MSupplementalDiscProjModel
 * @see com.stpl.app.model.impl.MSupplementalDiscProjImpl
 * @see com.stpl.app.model.impl.MSupplementalDiscProjModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.MSupplementalDiscProjImpl")
@ProviderType
public interface MSupplementalDiscProj extends MSupplementalDiscProjModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.MSupplementalDiscProjImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<MSupplementalDiscProj, Integer> PROJECTION_DETAILS_SID_ACCESSOR =
		new Accessor<MSupplementalDiscProj, Integer>() {
			@Override
			public Integer get(MSupplementalDiscProj mSupplementalDiscProj) {
				return mSupplementalDiscProj.getProjectionDetailsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<MSupplementalDiscProj> getTypeClass() {
				return MSupplementalDiscProj.class;
			}
		};
}